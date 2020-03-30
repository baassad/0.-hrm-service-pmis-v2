package com.cokreates.grp.beans.professional.serviceHistory;

import java.sql.Date;

import com.cokreates.core.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceHistory extends BaseEntity {
    
    private Date govtServiceDate, gazettedDate, encadrementDate, seniorityDate;
    private String cadre;
}