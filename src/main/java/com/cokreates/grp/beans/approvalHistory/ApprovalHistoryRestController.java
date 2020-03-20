package com.cokreates.grp.beans.approvalHistory;

import com.cokreates.core.*;
import com.cokreates.grp.util.request.ApprovalHistoryRequestBodyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/approval-history")
public class ApprovalHistoryRestController extends MasterRestController<ApprovalHistoryDTO,ApprovalHistory> {

    @Autowired
    protected ApprovalHistoryService approvalHistoryService;

    public ApprovalHistoryRestController(ApprovalHistoryService approvalHistoryService){
        super(approvalHistoryService);
    }

    @PostMapping(Constant.GDATA_APPROVAL_HISTORY)
    public ResponseModel<ApprovalHistoryDTO> getApprovalHistory(@RequestBody RequestModel<ApprovalHistoryDTO> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(), approvalHistoryService.getApprovalHistory(requestDTO.getBody().getData().get(0)));
    }


    @PostMapping(Constant.ENDPOINT_UPDATE_APPROVAL_HISTORY)
    public ResponseModel<ApprovalHistoryDTO> updateApprovalHistory(@RequestBody RequestModel<ApprovalHistoryRequestBodyDTO> requestDTO) {
        return resultBuildingComponent.retrieveResult(requestDTO.getHeader(), Collections.singletonList(service.convertToDto(approvalHistoryService.updateApprovalHistory(requestDTO.getBody().getData().get(0)))));
    }
}
