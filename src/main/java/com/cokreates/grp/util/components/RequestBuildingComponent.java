package com.cokreates.grp.util.components;

import java.sql.Timestamp;
import java.util.List;

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

    public DataServiceRequest<Dto> getRequestForGettingEmployeeDetails(String employeeOid){
        DataServiceRequest<Dto> request = new DataServiceRequest<>();
        DataServiceRequestBody<Dto> requestBody = new DataServiceRequestBody<>();

        requestBody.setEmployeeOid(employeeOid);

        request.setBody(requestBody);

        return request;
    }

    public DataServiceRequest<EmployeeOfficeDTO> getRequestForEmployeeOffice(EmployeeOfficeDTO node, String employeeOid){
        DataServiceRequest<EmployeeOfficeDTO> request = new DataServiceRequest<>();
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

}
