package com.cokreates.grp.beans.professional.serviceHistory;

import com.cokreates.core.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceHistory extends BaseEntity {
    
    private String govtServiceDate, gazettedDate, encadrementDate, seniorityDate, cadre;
}
