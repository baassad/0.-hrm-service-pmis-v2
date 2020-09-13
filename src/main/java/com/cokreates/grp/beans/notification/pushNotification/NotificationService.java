package com.cokreates.grp.beans.notification.pushNotification;

import com.cokreates.core.Constant;
import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.beans.common.EmployeeInformationFromAuthDTO;
import com.cokreates.grp.beans.common.RequesterCommentDTO;
import com.cokreates.grp.beans.common.ReviewerCommentDTO;
import com.cokreates.grp.beans.employee.EmployeeService;
import com.cokreates.grp.beans.employee.SwitchService;
import com.cokreates.grp.beans.notification.email.EmailDTO;
import com.cokreates.grp.beans.notification.email.EmployeeForEmailDTO;
import com.cokreates.grp.beans.user.UserService;
import com.cokreates.grp.data.constants.Api;
import com.cokreates.grp.data.constants.ErrorResponseMessage;
import com.cokreates.grp.util.components.HeaderUtilComponent;
import com.cokreates.grp.util.components.UtilCharacter;
import com.cokreates.grp.util.exceptions.ServiceExceptionHolder;
import com.cokreates.grp.util.request.GetListByOidSetRequestBodyDTO;
import com.cokreates.grp.util.webservice.WebService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
@Data
@RequestScope
public class NotificationService {

    @Autowired
    WebService webService;

    @Autowired
    SwitchService switchService;

    @Value("${notification-service-ictd.url}")
    private String notificationUrl;

    @Value("${pmis-web-home.url}")
    private String pmisWebHomeUrl;

    @Autowired
    UserService userService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UtilCharacter utilCharacter;

    @Autowired
    ObjectMapper objectMapper;

    private final HeaderUtilComponent headerUtilComponent;

    public NotificationService(HeaderUtilComponent headerUtilComponent,
                               ModelMapper modelMapper) {
        this.headerUtilComponent = headerUtilComponent;
    }

    public NotificationResponseDTO send(NotificationDTO dto) {

        if (!switchService.getNotificationEnabled().equals("Yes")) {
            return null;
        }

        NotificationResponseDTO responseDTO = null;

        try {
            responseDTO =
                    webService.getNodeResponse(notificationUrl + Api.ENDPOINT_NOTIFICATION, NotificationResponseDTO.class, dto);
        } catch (Exception e) {
            throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
        }

        return responseDTO;
    }

    public NotificationResponseDTO notifyForSendWithUrl(NotificationDTO dto, String urlEndPoint) {

        String url = pmisWebHomeUrl + urlEndPoint;

//        String url = hrmWebHomeUrl +
//                "?" +
//                Api.ACCORDION + accordion +
//                "&" +
//                Api.TAB + tab +
//                "&" +
//                Api.DATE_OF_RECEIVING + dateOfReceiving.getTime() +
//                "&" +
//                Api.TYPE_OID + typeOid +
//                "&" +
//                Api.OFFICE_OID + officeOid +
//                "&" +
//                Api.OFFICE_UNIT_OID + officeUnitOid +
//                "&" +
//                Api.RECIPIENT_OID + recipientOid;

        dto.setTag("HRM");
        dto.setToken("NTF");
        dto.setUrl(url);

        return send(dto);
    }

    public NotificationResponseDTO notifyForSendWithOutUrl(NotificationDTO dto) {

        dto.setTag("HRM");
        dto.setToken("NTF");

        return send(dto);
    }

    public void notifyActors(String employeeOid, Object comment, String action, String changeType, String nodeName) {

        if (changeType.startsWith("UPDATE")) {
            changeType = "হালনাগাদকরণ";
        } else if (changeType.startsWith("APPEND")) {
            changeType = "সংযোজন";
        } else if (changeType.startsWith("REMOVE")) {
            changeType = "অপসারণ";
        }

        HashSet<String> employeeOidsToNotify = new HashSet<>();

        HashMap<String, String> empOidToOffice = new HashMap<>();

        // fetch all employee office of requester employee

        GetListByOidSetRequestBodyDTO getListByOidSetRequestBodyDTO = new GetListByOidSetRequestBodyDTO();
        getListByOidSetRequestBodyDTO.setOids(Arrays.asList(employeeOid));

        List<EmployeeInformationDTO> detailsByEmpOid = employeeService.getEmployeeInformationDTO(getListByOidSetRequestBodyDTO);

        // generate all office oid list of him

        List<String> officeOidList = new ArrayList<>();

        detailsByEmpOid
                .forEach(detail -> {
                    officeOidList.add(detail.getOfficeOid());
                });

        getListByOidSetRequestBodyDTO.setOids(officeOidList);

        // generate all actors of those office oids

        List<EmployeeInformationDTO> employeeInformationDTOS = new ArrayList<>();

        List<EmployeeForNotificationDTO> employeeForNotificationDTOS = new ArrayList<>();

        String empOid = null;
        String nameBn = null;
        String postNameBn = null;

        String actionBn = null;

        String urlEndPoint = null;

        if (action.equals(Constant.REVIEW)) {

            employeeInformationDTOS = employeeService.getEmployeeInformationDTOByOfficeByEmployeeType(getListByOidSetRequestBodyDTO, Constant.REVIEWER);

            RequesterCommentDTO requesterCommentDTO = objectMapper.convertValue(comment, RequesterCommentDTO.class);

            empOid = requesterCommentDTO.getRequesterOid();
            nameBn = requesterCommentDTO.getNameBn();
            postNameBn = requesterCommentDTO.getPostnameBn();

            actionBn = Constant.OF_REVIEW_BN;
            urlEndPoint = "/review";

        } else {

            employeeInformationDTOS = employeeService.getEmployeeInformationDTOByOfficeByEmployeeType(getListByOidSetRequestBodyDTO, Constant.APPROVER);

            ReviewerCommentDTO reviewerCommentDTO = objectMapper.convertValue(comment, ReviewerCommentDTO.class);

            empOid = reviewerCommentDTO.getReviewerOid();
            nameBn = reviewerCommentDTO.getNameBn();
            postNameBn = reviewerCommentDTO.getPostnameBn();

            actionBn = Constant.OF_APPROVE_BN;
            urlEndPoint = "/approval";

        }

        employeeInformationDTOS
                .forEach(employeeInformationDTO -> {
                    employeeOidsToNotify.add(employeeInformationDTO.getOid());
                    empOidToOffice.put(employeeInformationDTO.getOid(), employeeInformationDTO.getOfficeOid());
                });

        List<EmployeeInformationFromAuthDTO> employeeInformationDTOSFromAuth = userService.getUserOid(employeeOidsToNotify);

        if (employeeInformationDTOSFromAuth.isEmpty()) {
            throw new ServiceExceptionHolder.ResourceNotFoundException(
                    "No user oid found for employee oid: " + employeeOidsToNotify
            );
        }

//        NotificationUrlHelperDTO notificationUrlHelperDTO = new NotificationUrlHelperDTO();

//        notificationUrlHelperDTO.setAccordion("todo");
//
//        notificationUrlHelperDTO.setTab("pending");

//        notificationUrlHelperDTO.setDateOfReceiving(sample.getDateOfReceiving());
//        notificationUrlHelperDTO.setTypeOid(sample.getTypeOid());
//        notificationUrlHelperDTO.setOfficeOid(sample.getReceiverOfficeOid());
//        notificationUrlHelperDTO.setOfficeUnitOid(sample.getReceiverOfficeUnitOid());
//        notificationUrlHelperDTO.setRecipientOid(sample.getRecipientOid());
//        notificationUrlHelperDTO.setHrmWebHomeUrl(hrmWebHomeUrl);

        employeeInformationDTOSFromAuth
                .forEach(employeeInformationDTO -> {
                    EmployeeForNotificationDTO employeeForNotificationDTO = new EmployeeForNotificationDTO();

                    employeeForNotificationDTO.setEmpId(employeeInformationDTO.getEmployeeOid());
                    employeeForNotificationDTO.setUserId(employeeInformationDTO.getUserOid());
                    employeeForNotificationDTO.setOrgId(empOidToOffice.get(employeeInformationDTO.getEmployeeOid()));

                    employeeForNotificationDTOS.add(employeeForNotificationDTO);

                });

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setListOfEmployees(employeeForNotificationDTOS);
        notificationDTO.setOid("pmis" + "-" + employeeOid + "-" + nodeName + "-" + System.currentTimeMillis());

        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-YYYY");
        String sendDate = sf.format(new java.util.Date(System.currentTimeMillis()));

        String part0 = "আপনার নিকট " + utilCharacter.convertNumberEnToBn(sendDate) + " তারিখ এর " + "\"" + nodeName + "\"" + " " + changeType + " " + actionBn + " অনুরোধ প্রেরিত হয়েছে।";
        String part1 = "কর্মকর্তা/কর্মচারীর নাম: " + nameBn;
        String part2 = " (" + postNameBn + ")";

        notificationDTO.setMessage(part0 + part1 + part2);

        notifyForSendWithUrl(notificationDTO, urlEndPoint);

    }

    public void notifyRequester(String requesterOid, String action, String changeType, String approvalStatus, String nodeName) {

        HashSet<String> employeeOidsToNotify = new HashSet<>();

        HashMap<String, String> empOidToOffice = new HashMap<>();

        if (changeType.startsWith("UPDATE")) {
            changeType = "হালনাগাদকরণ";
        } else if (changeType.startsWith("APPEND")) {
            changeType = "সংযোজন";
        } else if (changeType.startsWith("REMOVE")) {
            changeType = "অপসারণ";
        }

        GetListByOidSetRequestBodyDTO getListByOidSetRequestBodyDTO = new GetListByOidSetRequestBodyDTO();
        getListByOidSetRequestBodyDTO.setOids(Arrays.asList(requesterOid));

        List<EmployeeInformationDTO> employeeInformationDTOS = new ArrayList<>();

        List<EmployeeForNotificationDTO> employeeForNotificationDTOS = new ArrayList<>();

        String empOid = requesterOid;

        String approvalStatusMessage = null;

        employeeInformationDTOS = employeeService.getEmployeeInformationDTO(getListByOidSetRequestBodyDTO);

        if (action.equals(Constant.REVIEW)) {

            if (approvalStatus.equals(Constant.APPROVED)) {
                approvalStatusMessage = "অনুমোদনের জন্য প্রেরিত";
            } else if (approvalStatus.equals(Constant.REJECTED)) {
                approvalStatusMessage = "রিভিউকারী কর্তৃক অননুমোদিত";
            }

        } else {

            if (approvalStatus.equals(Constant.APPROVED)) {
                approvalStatusMessage = Constant.APPROVED_BN;
            } else if (approvalStatus.equals(Constant.REJECTED)) {
                approvalStatusMessage = Constant.REJECTED_BN;
            }

        }

        employeeInformationDTOS
                .forEach(employeeInformationDTO -> {
                    employeeOidsToNotify.add(employeeInformationDTO.getOid());
                    empOidToOffice.put(employeeInformationDTO.getOid(), employeeInformationDTO.getOfficeOid());
                });

        List<EmployeeInformationFromAuthDTO> employeeInformationDTOSFromAuth = userService.getUserOid(employeeOidsToNotify);

        if (employeeInformationDTOSFromAuth.isEmpty()) {
            throw new ServiceExceptionHolder.ResourceNotFoundException(
                    "No user oid found for employee oid: " + employeeOidsToNotify
            );
        }

        employeeInformationDTOSFromAuth
                .forEach(employeeInformationDTO -> {
                    EmployeeForNotificationDTO employeeForNotificationDTO = new EmployeeForNotificationDTO();

                    employeeForNotificationDTO.setEmpId(employeeInformationDTO.getEmployeeOid());
                    employeeForNotificationDTO.setUserId(employeeInformationDTO.getUserOid());
                    employeeForNotificationDTO.setOrgId(empOidToOffice.get(employeeInformationDTO.getEmployeeOid()));

                    employeeForNotificationDTOS.add(employeeForNotificationDTO);

                });

        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setListOfEmployees(employeeForNotificationDTOS);
        notificationDTO.setOid("pmis" + "-" + empOid + "-" + nodeName + "-" + System.currentTimeMillis());

        String part0 = "আপনার " + "\"" + nodeName + "\"" + " " + changeType + " " + approvalStatusMessage + " হয়েছে। ";
        String part1 = "";

        notificationDTO.setMessage(part0 + part1);

        notifyForSendWithOutUrl(notificationDTO);

    }


}