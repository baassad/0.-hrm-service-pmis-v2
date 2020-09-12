package com.cokreates.grp.beans.notification.email;

import com.cokreates.core.Constant;
import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.beans.common.RequesterCommentDTO;
import com.cokreates.grp.beans.common.ReviewerCommentDTO;
import com.cokreates.grp.beans.employee.EmployeeService;
import com.cokreates.grp.beans.employee.SwitchService;
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

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
@Data
@RequestScope
public class EmailService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WebService webService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserService userService;

    @Autowired
    SwitchService switchService;

    @Autowired
    UtilCharacter utilCharacter;

    @Value("${email-service-ictd.url}")
    private String emailUrl;

    private final HeaderUtilComponent headerUtilComponent;

    public EmailService(HeaderUtilComponent headerUtilComponent,
                        ModelMapper modelMapper) {
        this.headerUtilComponent = headerUtilComponent;
    }

    public EmailResponseDTO send(EmailDTO dto) {

        if (!switchService.getEmailEnabled().equals("Yes")) {
            if (switchService.getEmailEnabled().equals("mrinal")) {
                dto.getListOfEmployees()
                        .forEach(employeeForEmailDTO -> {
                            employeeForEmailDTO.setEmailAddress("mrinal@cokreates.com");
                        });
            } else return null;
        }

        EmailResponseDTO responseDTO = null;

        try {
            responseDTO =
                    webService.getNodeResponse(emailUrl + Api.ENDPOINT_EMAIL, EmailResponseDTO.class, dto);
        } catch (Exception e) {
            throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
        }

        return responseDTO;
    }

    public EmailResponseDTO notifyForSend(EmailDTO dto) {

        dto.setTag("HRM");
        dto.setToken("EMAIL");
        dto.setScheduledTime("NOW");
        dto.setHasAttachment("False");

        return send(dto);
    }

    public void emailToActors(String employeeOid, Object comment, String action, String nodeName) {

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

        List<EmployeeForEmailDTO> employeeForEmailDTOS = new ArrayList<>();

        String empOid = null;
        String nameBn = null;
        String postNameBn = null;

        String actionBn = null;

        if (action.equals(Constant.REVIEW)) {

            employeeInformationDTOS = employeeService.getEmployeeInformationDTOByOfficeByEmployeeType(getListByOidSetRequestBodyDTO, Constant.REVIEWER);

            RequesterCommentDTO requesterCommentDTO = objectMapper.convertValue(comment, RequesterCommentDTO.class);

            empOid = requesterCommentDTO.getRequesterOid();
            nameBn = requesterCommentDTO.getNameBn();
            postNameBn = requesterCommentDTO.getPostnameBn();

            actionBn = Constant.OF_REVIEW_BN;

        } else {

            employeeInformationDTOS = employeeService.getEmployeeInformationDTOByOfficeByEmployeeType(getListByOidSetRequestBodyDTO, Constant.APPROVER);

            ReviewerCommentDTO reviewerCommentDTO = objectMapper.convertValue(comment, ReviewerCommentDTO.class);

            empOid = reviewerCommentDTO.getReviewerOid();
            nameBn = reviewerCommentDTO.getNameBn();
            postNameBn = reviewerCommentDTO.getPostnameBn();

            actionBn = Constant.OF_APPROVE_BN;

        }

        employeeInformationDTOS
                .forEach(employeeInformationDTO -> {

                    EmployeeForEmailDTO employeeForEmailDTO = new EmployeeForEmailDTO();

                    employeeForEmailDTO.setOrgId(employeeInformationDTO.getOfficeOid());
                    employeeForEmailDTO.setEmpId(employeeInformationDTO.getOid());

                    if (utilCharacter.noData(employeeInformationDTO.getEmail())) {
                        if (switchService.getEmailEnabled().equals("Yes")) {
                            throw new ServiceExceptionHolder.ResourceNotFoundException(
                                    ErrorResponseMessage.NO_EMAIL_IN_GENERAL + employeeInformationDTO.getNameBn()
                            );
                        }
                    }

                    employeeForEmailDTO.setEmailAddress(employeeInformationDTO.getEmail());
                    employeeForEmailDTO.setRecipientType("TO");

                    employeeForEmailDTOS.add(employeeForEmailDTO);

                });

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setListOfEmployees(employeeForEmailDTOS);
        emailDTO.setOid("pmis" + "-" + empOid + "-" + nodeName + "-" + System.currentTimeMillis());

        String message1 = "<h2>জনাব/জনাবা,</h2>" + "<p>" ;
        String message2 = "। </p>" +"<h4>"+ "ধন্যবাদ।" +"</h4>";

        SimpleDateFormat sf = new SimpleDateFormat("dd-MM-YYYY");
        String sendDate = sf.format(new Date(System.currentTimeMillis()));

        String part0 = "আপনার নিকট " + utilCharacter.convertNumberEnToBn(sendDate) + " তারিখ এর অনুরোধ প্রেরিত হয়েছে।";
        String part1 = "কর্মকর্তা/কর্মচারীর নাম: " + nameBn;
        String part2 = " (" + postNameBn + ")";

        emailDTO.setMessage(message1 + part0 + part1 + part2 + message2);
        emailDTO.setSubject(actionBn + " জন্য অনুরোধ");

        notifyForSend(emailDTO);

    }

    public void emailToRequester(String requesterOid, String action, String changeType, String approvalStatus, String nodeName) {

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

        List<EmployeeForEmailDTO> employeeForEmailDTOS = new ArrayList<>();

        String empOid = requesterOid;

        String subject = null;
        String approvalStatusMessage = null;

        employeeInformationDTOS = employeeService.getEmployeeInformationDTO(getListByOidSetRequestBodyDTO);

        if (action.equals(Constant.REVIEW)) {

            subject = "\"" + nodeName + "\"" + " " + changeType + " " + "রিভিউকৃত";
            if (approvalStatus.equals(Constant.APPROVED)) {
                approvalStatusMessage = "অনুমোদনের জন্য প্রেরিত";
            } else if (approvalStatus.equals(Constant.REJECTED)) {
                approvalStatusMessage = "রিভিউকারী কর্তৃক অননুমোদিত";
            }

        } else {

            subject = "\"" + nodeName + "\"" + " " + changeType + " " + "অনুমোদন/অননুমোদন";
            if (approvalStatus.equals(Constant.APPROVED)) {
                approvalStatusMessage = Constant.APPROVED_BN;
            } else if (approvalStatus.equals(Constant.REJECTED)) {
                approvalStatusMessage = Constant.REJECTED_BN;
            }

        }


        employeeInformationDTOS
                .forEach(employeeInformationDTO -> {

                    EmployeeForEmailDTO employeeForEmailDTO = new EmployeeForEmailDTO();

                    employeeForEmailDTO.setOrgId(employeeInformationDTO.getOfficeOid());
                    employeeForEmailDTO.setEmpId(employeeInformationDTO.getOid());

                    if (utilCharacter.noData(employeeInformationDTO.getEmail())) {
                        if (switchService.getEmailEnabled().equals("Yes")) {
                            throw new ServiceExceptionHolder.ResourceNotFoundException(
                                    ErrorResponseMessage.NO_EMAIL_IN_GENERAL + employeeInformationDTO.getNameBn()
                            );
                        }
                    }

                    employeeForEmailDTO.setEmailAddress(employeeInformationDTO.getEmail());
                    employeeForEmailDTO.setRecipientType("TO");

                    employeeForEmailDTOS.add(employeeForEmailDTO);

                });

        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setListOfEmployees(employeeForEmailDTOS);
        emailDTO.setOid("pmis" + "-" + empOid + "-" + nodeName + "-" + System.currentTimeMillis());

        String message1 = "<h2>জনাব/জনাবা,</h2>" + "<p>" ;
        String message2 = "। </p>" +"<h4>"+ "ধন্যবাদ।" +"</h4>";

        String part0 = "আপনার " + "\"" + nodeName + "\"" + " " + changeType + " " + approvalStatusMessage + " হয়েছে। ";
        String part1 = "";

        emailDTO.setMessage(message1 + part0 + part1 + message2);
        emailDTO.setSubject(subject);

        notifyForSend(emailDTO);

    }

}