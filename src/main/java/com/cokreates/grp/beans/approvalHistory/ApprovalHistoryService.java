package com.cokreates.grp.beans.approvalHistory;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterService;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceRequestBody;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.request.ActorRequestBodyDTO;
import com.cokreates.grp.util.request.ApprovalHistoryRequestBodyDTO;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ApprovalHistoryService extends MasterService<ApprovalHistoryDTO,ApprovalHistory> {

    public ApprovalHistoryService(RequestBuildingComponent<ApprovalHistoryDTO> requestBuildingComponent,
                                  DataServiceRestTemplateClient< ApprovalHistoryDTO, ApprovalHistory> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
    }

    public List<ApprovalHistoryDTO> getApprovalHistory(ApprovalHistoryDTO node) {

        String gDataEndPointUrl = getGData()+ Constant.GDATA_GET+Constant.VERSION_1 + Constant.GDATA_APPROVAL_HISTORY;

        DataServiceRequest<ApprovalHistoryDTO> request = getRequestBuildingComponent().getRequestForRead(null, null, node.getEmployeeOid(),
                null, null, null, node.getStatus(),
                null, null, null, this.getDtoClass());

        return getDataServiceRestTemplateClient().getApprovalHistory(getNodePath(), request, gDataEndPointUrl);

    }

    public ApprovalHistory updateApprovalHistory(ApprovalHistoryRequestBodyDTO node) {

        String gDataEndPointUrl = getGData()+Constant.GDATA_UPDATE+Constant.VERSION_1;

        DataServiceRequest<ApprovalHistoryDTO> request = getRequestBuildingComponent().getRequestForRead(getNodePath(), null, null,
                null, node.getOid(), node.getComment(), node.getStatus(),
                null, null, null, this.getDtoClass());

        getDataServiceRestTemplateClient().updateApprovalHistory(getNodePath(), request, gDataEndPointUrl);

        return null;
    }


    public List<ApprovalHistoryDTO> getApprovalHistoryByActor(ActorRequestBodyDTO node) {

        String gDataEndPointUrl = getGData()+Constant.GDATA_GET+Constant.VERSION_1 + Constant.GDATA_APPROVAL_HISTORY_BY_ACTOR;;

        DataServiceRequest<ApprovalHistoryDTO> request = getRequestBuildingComponent().getRequestForRead(getNodePath(), null, null,
                null, node.getOid(), null, null,
                node.getRequesterOid(), node.getReviewerOid(), node.getApproverOid(), this.getDtoClass());

//        request.setBody(parseBeforeApprovalUpdate(request.getBody()));

//        DataServiceRequestBody dataServiceRequestBody = parseBeforeApprovalUpdate(request.getBody());

        return getDataServiceRestTemplateClient().getApprovalHistory(getNodePath(), request, gDataEndPointUrl);

    }


    @Override
    public Class getDtoClass() {
        return ApprovalHistoryDTO.class;
    }
}
