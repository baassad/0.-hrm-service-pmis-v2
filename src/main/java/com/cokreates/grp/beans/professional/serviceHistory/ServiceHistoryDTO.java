package com.cokreates.grp.beans.professional.serviceHistory;

import java.sql.Date;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceHistoryDTO extends MasterDTO {

    private Date govtServiceDate, gazettedDate, encadrementDate, seniorityDate, cadre;

}
