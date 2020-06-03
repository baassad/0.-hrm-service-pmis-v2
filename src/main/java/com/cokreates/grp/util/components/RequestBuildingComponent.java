package com.cokreates.grp.util.components;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.cokreates.grp.beans.employee.EmployeeCreationDTO;
import org.springframework.stereotype.Component;

import com.cokreates.core.MasterDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceRequestBody;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RequestBuildingComponent<Dto extends MasterDTO> {

    public DataServiceRequest<Dto> getRequestForRead(List<String> nodePath,
                                                     Dto node,
                                                     String employeeOid,
                                                     String nodeOid,
                                                     String approvalHistoryOid,
                                                     Object comment,
                                                     String status,
                                                     String requesterOid,
                                                     String reviewerOid,
                                                     String approverOid,
                                                     Class dtoClass){
        DataServiceRequest<Dto> request = new DataServiceRequest<>();
        DataServiceRequestBody<Dto> requestBody = new DataServiceRequestBody<>();

        requestBody.setEmployeeOid(employeeOid);
        requestBody.setNodeOid(nodeOid);
        requestBody.setNode(node);
        requestBody.setNodePath(nodePath);
        
        if (node != null) {
            node.setConfig("");
            node.setCreatedBy("System");
            node.setUpdatedBy("System");
            node.setCreatedOn(new Timestamp(System.currentTimeMillis()));
            node.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
            node.setDataStatus("Active");
            node.setRowStatus("Active");
        }

        requestBody.setApprovalHistoryOid(approvalHistoryOid);
        requestBody.setComment(comment);
        requestBody.setStatus(status);

        requestBody.setRequesterOid(requesterOid);
        requestBody.setReviewerOid(reviewerOid);
        requestBody.setApproverOid(approverOid);

        requestBody.setDtoClass(dtoClass);

        request.setBody(requestBody);

        return request;
    }



    public DataServiceRequest<Dto> getRequestToCreateEmployee(GeneralDTO node, Class dtoClass){
        DataServiceRequest<Dto> request = new DataServiceRequest<>();
        DataServiceRequestBody<Dto> requestBody = new DataServiceRequestBody<>();

        requestBody.setGeneral(node);
        requestBody.setDtoClass(dtoClass);

        request.setBody(requestBody);

        return request;
    }



//    public DataServiceRequest<MasterApprovalDTO> getRequestForApprovalHistory(MasterApprovalDTO requestDTO){
//        DataServiceRequest<MasterApprovalDTO> request = new DataServiceRequest<>();
//        DataServiceRequestBody<MasterApprovalDTO> requestBody = new DataServiceRequestBody<>();
//
//        requestBody.setApprovalHistoryOid(requestDTO.getApprovalHistoryOid());
//        requestBody.setComment(requestDTO.getComment());
//        requestBody.setStatus(requestDTO.getStatus());
//        requestBody.setEmployeeOid(requestDTO.getOid());
//
//        request.setBody(requestBody);
//
//        return request;
//    }

    public DataServiceRequest<Dto> getRequestForGettingEmployeeDetails(String employeeOid){
        DataServiceRequest<Dto> request = new DataServiceRequest<>();
        DataServiceRequestBody<Dto> requestBody = new DataServiceRequestBody<>();

        requestBody.setEmployeeOid(employeeOid);

        request.setBody(requestBody);

        return request;
    }


    public DataServiceRequest<EmployeeOfficeDTO> getRequestForEmployeeOfficeForUpdate(EmployeeOfficeDTO node, String employeeOid){
        DataServiceRequest<EmployeeOfficeDTO> request = new DataServiceRequest<>();
        Date date = new Date();

        node.setCreatedBy("System");
        node.setCreatedOn(new Timestamp(date.getTime()));
        node.setUpdatedBy("System");
        node.setUpdatedOn(new Timestamp(date.getTime()));
        node.setDataStatus("");
        node.setConfig("");


        DataServiceRequestBody<EmployeeOfficeDTO> requestBody = new DataServiceRequestBody<>();

        requestBody.setEmployeeOid(employeeOid);
        requestBody.setNode(node);

        request.setBody(requestBody);

        return request;
    }

    public DataServiceRequest<EmployeeOfficeDTO> getRequestForEmployeeOffice(EmployeeOfficeDTO node, String employeeOid){
        DataServiceRequest<EmployeeOfficeDTO> request = new DataServiceRequest<>();
        Date date = new Date();

        node.setIsApprover("No");
        node.setIsReviewer("No");
        node.setIsAttendanceAdmin("No");
        node.setIsAttendanceDataEntryOperator("No");
        node.setCreatedBy("System");
        node.setCreatedOn(new Timestamp(date.getTime()));
        node.setDataStatus("");
        node.setConfig("");


        DataServiceRequestBody<EmployeeOfficeDTO> requestBody = new DataServiceRequestBody<>();

        requestBody.setEmployeeOid(employeeOid);
        requestBody.setNode(node);

        request.setBody(requestBody);

        return request;
    }
    
    public DataServiceRequest<Dto> getRequestForRead(List<String> nodePath,Dto node,String employeeOid, Class dtoClass){
        DataServiceRequest<Dto> request = new DataServiceRequest<>();
        DataServiceRequestBody<Dto> requestBody = new DataServiceRequestBody<>();

        requestBody.setEmployeeOid(employeeOid);
        //requestBody.setNodeOid(nodeOid);
        requestBody.setNode(node);
        requestBody.setNodePath(nodePath);
        requestBody.setDtoClass(dtoClass);

        request.setBody(requestBody);

        return request;
    }

    public DataServiceRequest<EmployeeCreationDTO> getRequestForImport(EmployeeCreationDTO node, String employeeOid){
        DataServiceRequest<EmployeeCreationDTO> request = new DataServiceRequest<>();

        node.setIsApprover("No");
        node.setIsReviewer("No");
        node.setIsAttendanceAdmin("No");
        node.setIsAttendanceDataEntryOperator("No");
        DataServiceRequestBody<EmployeeCreationDTO> requestBody = new DataServiceRequestBody<>();

        requestBody.setEmployeeOid(employeeOid);

        //requestBody.setNodeOid(nodeOid);

        node.setRowStatus("Active");
        node.setCreatedBy("System");
        Date date = new Date();
        node.setCreatedOn(new Timestamp(date.getTime()));
        node.setConfig("");
        node.setDataStatus("Actived");

        requestBody.setNode(node);


        request.setBody(requestBody);

        return request;
    }


}
