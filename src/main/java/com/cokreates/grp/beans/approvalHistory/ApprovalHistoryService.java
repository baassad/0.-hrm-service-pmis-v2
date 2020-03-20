package com.cokreates.grp.beans.approvalHistory;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterService;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.util.components.RequestBuildingComponent;
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

        String gDataEndPointUrl = getGData()+ Constant.GDATA_GET+Constant.VERSION_1 + Constant.ENDPOINT_APPROVAL_HISTORY;

        DataServiceRequest<ApprovalHistoryDTO> request = getRequestBuildingComponent().getRequestForRead(null, null, node.getEmployeeOid(),
                null, null, null, node.getStatus(), this.getDtoClass());

        return getDataServiceRestTemplateClient().getApprovalHistory(getNodePath(), request, gDataEndPointUrl);

    }

    public ApprovalHistory updateApprovalHistory(ApprovalHistoryRequestBodyDTO node) {

        String gDataEndPointUrl = getGData()+Constant.GDATA_UPDATE+Constant.VERSION_1;

        DataServiceRequest<ApprovalHistoryDTO> request = getRequestBuildingComponent().getRequestForRead(getNodePath(), null, node.getOid(),
                null, node.getOid(), node.getComment(), node.getStatus(), this.getDtoClass());

        getDataServiceRestTemplateClient().updateApprovalHistory(getNodePath(), request, gDataEndPointUrl);

        return null;
    }
    
    @Override
    public Class getDtoClass() {
        return ApprovalHistoryDTO.class;
    }
}
