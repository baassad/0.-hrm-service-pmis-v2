package com.cokreates.grp.util.components;

import com.cokreates.core.BaseEntity;
import com.cokreates.core.MasterDTO;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceRequestBody;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

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
            node.setCreatedOn(new Date());
            node.setUpdatedBy("System");
            node.setUpdatedOn(new Date());
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
