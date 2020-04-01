package com.cokreates.grp.beans.common;

import com.cokreates.grp.beans.professional.disciplinaryAction.DisciplinaryActionDTO;
import com.cokreates.grp.beans.professional.jobHistory.JobHistoryDTO;
import com.cokreates.grp.beans.professional.leave.LeaveDTO;
import com.cokreates.grp.beans.professional.posting.PostingDTO;
import com.cokreates.grp.beans.professional.professionalGeneral.ProfessionalGeneralDTO;
import com.cokreates.grp.beans.professional.promotion.PromotionDTO;
import com.cokreates.grp.beans.professional.serviceHistory.ServiceHistoryDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProfessionalDTO {
    ProfessionalGeneralDTO general;
    List<PromotionDTO> promotion;
    List<PostingDTO> posting;
    List<JobHistoryDTO> jobHistory;
    List<ServiceHistoryDTO> serviceHistory;
    List<DisciplinaryActionDTO> disciplinaryAction;
}
