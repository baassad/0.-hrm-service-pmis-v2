package com.cokreates.grp.beans.personal.bank;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class BankDTO extends MasterDTO {

    private String nameEn;
    private String nameBn;
    private String branch;
    private String accountNumber;
    private String typeOfAccount;
    private String nameOfAccount;
    private String currentCondition;
    
    private String oid;
    private String createdBy;
    private String updatedBy;
    private String rowStatus;
    private Date createdOn;
    private Date updatedOn;
    private String config;
    private String dataStatus;
}
