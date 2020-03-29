package com.cokreates.grp.beans.professional.serviceHistory;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceHistoryDTO extends MasterDTO {

    private String govtServiceDate, gazettedDate, encadrementDate, seniorityDate, cadre;

}
