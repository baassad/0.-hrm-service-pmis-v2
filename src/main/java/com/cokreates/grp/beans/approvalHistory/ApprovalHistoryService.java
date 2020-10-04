package com.cokreates.grp.beans.approvalHistory;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterService;
import com.cokreates.grp.beans.common.ApproverCommentDTO;
import com.cokreates.grp.beans.common.Change;
import com.cokreates.grp.beans.common.RequesterCommentDTO;
import com.cokreates.grp.beans.common.ReviewerCommentDTO;
import com.cokreates.grp.beans.employee.EmployeeDTO;
import com.cokreates.grp.beans.employee.EmployeeService;
import com.cokreates.grp.beans.employeeOffice.EmployeeOffice;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeService;
import com.cokreates.grp.beans.notification.email.EmailService;
import com.cokreates.grp.beans.notification.pushNotification.NotificationService;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceRequestBody;
import com.cokreates.grp.data.constants.NodeNameBn;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.dummyService.DummyEmployeeOfficeService;
import com.cokreates.grp.util.exceptions.ServiceExceptionHolder;
import com.cokreates.grp.util.request.ActorRequestBodyDTO;
import com.cokreates.grp.util.request.ApprovalHistoryRequestBodyDTO;
import com.cokreates.grp.util.request.MiscellaneousRequestProperty;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.*;

@Service
@Slf4j
public class ApprovalHistoryService extends MasterService<ApprovalHistoryDTO,ApprovalHistory> {

    @Autowired
    DummyEmployeeOfficeService dummyEmployeeOfficeService;

    @Autowired
    EmployeeOfficeService employeeOfficeService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EmailService emailService;

    @Autowired
    NotificationService notificationService;

    public ApprovalHistoryService(RequestBuildingComponent<ApprovalHistoryDTO> requestBuildingComponent,
                                  DataServiceRestTemplateClient< ApprovalHistoryDTO, ApprovalHistory> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }

    public List<ApprovalHistoryDTO> getApprovalHistory(ApprovalHistoryDTO node) {

        String gDataEndPointUrl = getGData()+ Constant.GDATA_GET+Constant.VERSION_1 + Constant.GDATA_APPROVAL_HISTORY;

        DataServiceRequest<ApprovalHistoryDTO> request = getRequestBuildingComponent().getRequestForRead(null, null, node.getEmployeeOid(),
                null, null, null, node.getStatus(),
                null, null, null, this.getDtoClass());

        return getDataServiceRestTemplateClient().getListData(getNodePath(), request, gDataEndPointUrl);

    }

    public List<ApprovalHistoryDTO> getApprovalHistoryByOid(String oid) {

        String gDataEndPointUrl = getGData()+ Constant.GDATA_GET+Constant.VERSION_1 + Constant.GDATA_APPROVAL_HISTORY_OID;

        DataServiceRequest<ApprovalHistoryDTO> request = getRequestBuildingComponent().getRequestForRead(null, null, oid,
                null, null, null, null,
                null, null, null, this.getDtoClass());

        return getDataServiceRestTemplateClient().getListData(getNodePath(), request, gDataEndPointUrl);

    }

    public ApprovalHistory updateApprovalHistory(ApprovalHistoryRequestBodyDTO node) {

        String gDataEndPointUrl = getGData()+Constant.GDATA_UPDATE+Constant.VERSION_1;

        DataServiceRequest<ApprovalHistoryDTO> request = getRequestBuildingComponent().getRequestForRead(getNodePath(), null, null,
                null, node.getOid(), node.getComment(), node.getStatus(),
                null, null, null, this.getDtoClass());

        ApprovalHistory created = convertToEntity(getDataServiceRestTemplateClient().updateApprovalHistory(getNodePath(), request, gDataEndPointUrl));

        ApprovalHistoryDTO approvalHistory = getApprovalHistoryByOid(node.getOid()).get(0);

        Change change = approvalHistory.getChange();
        String employeeOid = approvalHistory.getEmployeeOid();

        if (approvalHistory.getStatus().equals(Constant.APPROVED) || approvalHistory.getStatus().equals(Constant.REJECTED)) {


            String action = Constant.REVIEW;


            if (approvalHistory.getComment().getApprover() != null && !approvalHistory.getComment().getApprover().equals("")) {
                ApproverCommentDTO approverCommentDTO = approvalHistory.getComment().getApprover();

                if (approverCommentDTO.getApproverOid() != null && !approverCommentDTO.getApproverOid().equals("")) {
                    action = Constant.APPROVE;
                }
            }

            emailService.emailToRequester(employeeOid, action, approvalHistory.getChangeType(), approvalHistory.getStatus(), NodeNameBn.nodeNameToBangla.get(change.getNodePath()));
            notificationService.notifyRequester(employeeOid, action, approvalHistory.getChangeType(), approvalHistory.getStatus(), NodeNameBn.nodeNameToBangla.get(change.getNodePath()));

        } else {

            emailService.emailToActors(employeeOid, node.getComment(), Constant.APPROVE, approvalHistory.getChangeType(), NodeNameBn.nodeNameToBangla.get(change.getNodePath()));
            notificationService.notifyActors(employeeOid, node.getComment(), Constant.APPROVE, approvalHistory.getChangeType(), NodeNameBn.nodeNameToBangla.get(change.getNodePath()));

        }

        return created;

    }

    public List<ApprovalHistoryDTO> getApprovalHistoryByActor(ActorRequestBodyDTO node) {



        // =================   fetch all offices of employee in request body ====================================================

        String gDataEndPointUrl = getGData()+Constant.GDATA_GET+Constant.VERSION_1 + Constant.GDATA_OFFICE_BY_EMPLOYEE;;

        DataServiceRequest<EmployeeOfficeDTO> requestEmployeeOffice = employeeOfficeService.getRequestBuildingComponent().getRequestForRead(getNodePath(), null, null,
                null, node.getOid(), null, null,
                node.getRequesterOid(), node.getReviewerOid(), node.getApproverOid(), employeeOfficeService.getDtoClass());


        /*try {
            System.out.println("Creating JSON file");
            objectMapper.writeValue(new File("./test.json"), requestEmployeeOffice);

        }catch (Exception e){
            e.printStackTrace();
        }*/
        List<EmployeeOfficeDTO> employeeOfficeDTOList = employeeOfficeService.getDataServiceRestTemplateClient().getListData(getNodePath(), requestEmployeeOffice, gDataEndPointUrl);



        // =================   accumulates offices only which are permitted for him ====================================================


        List<String> officeOidList = new ArrayList<>();

            employeeOfficeDTOList
                    .forEach(employeeOfficeDTO -> {
                        if (node.getReviewerOid() != null && employeeOfficeDTO.getIsReviewer().equals("Yes")) {
                            officeOidList.add(employeeOfficeDTO.getOfficeOid());
                        } else if (node.getApproverOid() != null && employeeOfficeDTO.getIsApprover().equals("Yes")) {
                            officeOidList.add(employeeOfficeDTO.getOfficeOid());
                        }
                    });



        // =================   get all employees' DTOs ( containing only oid in object ) who belong to permitted offices ====================================================


        MiscellaneousRequestProperty miscellaneousRequestProperty = new MiscellaneousRequestProperty();
        miscellaneousRequestProperty.setOfficeOidList(officeOidList);


        gDataEndPointUrl = getGData()+Constant.GDATA_GET+Constant.VERSION_1 + Constant.GDATA_EMPLOYEE_BY_OFFICE;;

        DataServiceRequest<EmployeeDTO> requestEmployee = employeeService.getRequestBuildingComponent().getRequestForRead(getNodePath(), null, null,
                null, node.getOid(), null, null,
                node.getRequesterOid(), node.getReviewerOid(), node.getApproverOid(), employeeService.getDtoClass());

        DataServiceRequestBody dataServiceRequestBody = requestEmployee.getBody();
        dataServiceRequestBody.setMiscellaneousRequestProperty(miscellaneousRequestProperty);

        List<EmployeeDTO> employeeDTOList = employeeService.getDataServiceRestTemplateClient().getListData(getNodePath(), requestEmployee, gDataEndPointUrl);



        // =================   preparing list of employee oids from DTOs who are under jurisdiction ====================================================

        List<String> employeeOidList = new ArrayList<>();

        employeeDTOList
                .forEach(employeeDTO -> {
                    employeeOidList.add(employeeDTO.getOid());
                });



        // =================   finally, get all approval/reviewal requests which are under jurisdiction ====================================================


        miscellaneousRequestProperty = new MiscellaneousRequestProperty();
        miscellaneousRequestProperty.setEmployeeOidList(employeeOidList);

        gDataEndPointUrl = getGData()+Constant.GDATA_GET+Constant.VERSION_1 + Constant.GDATA_APPROVAL_HISTORY_BY_ACTOR;;

        DataServiceRequest<ApprovalHistoryDTO> request = getRequestBuildingComponent().getRequestForRead(getNodePath(), null, null,
                null, node.getOid(), null, null,
                node.getRequesterOid(), node.getReviewerOid(), node.getApproverOid(), this.getDtoClass());

        dataServiceRequestBody = request.getBody();
        dataServiceRequestBody.setMiscellaneousRequestProperty(miscellaneousRequestProperty);

        return getDataServiceRestTemplateClient().getListData(getNodePath(), request, gDataEndPointUrl);

    }

//    public Object getCommentObject(Object comment) {
//        if (comment.toString().contains("requesterOid")) {
//            System.out.println("req");
//            return (RequesterCommentDTO) comment;
//        } else if (comment.toString().contains("reviewerOid")) {
//            System.out.println("rev");
//
//            GsonBuilder builder = new GsonBuilder();
//
//            // Register an adapter to manage the date types as long values
//            builder.registerTypeAdapter(java.sql.Date.class, new JsonDeserializer<java.sql.Date>() {
//                public java.sql.Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//                    return new Date(json.getAsJsonPrimitive().getAsLong());
//                }
//            });
//
//            Gson gson = builder.create();
//
//            String element = gson.toJson(comment);
//
//            LinkedTreeMap<String, Object> mainMap = gson.fromJson(element, LinkedTreeMap.class);
//
//            String mainString = gson.toJson(mainMap);
//
//            System.out.println(mainString);
//
//            ReviewerCommentDTO updateNode = gson.fromJson(mainString, ReviewerCommentDTO.class);
//
//            return comment;
//        } else if (comment.toString().contains("approverOid")) {
//            System.out.println("app");
//            return (ApproverCommentDTO) comment;
//        }
//        throw new ServiceExceptionHolder.TypeMismatchException("Comment from request body cannot be matched properly");
//    }


    @Override
    public Class getDtoClass() {
        return ApprovalHistoryDTO.class;
    }

    @Override
    public Class getEntityClass() {return ApprovalHistory.class;}

}
