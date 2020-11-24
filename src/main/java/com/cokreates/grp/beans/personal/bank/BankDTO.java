package com.cokreates.grp.beans.personal.bank;

import com.cokreates.core.MasterDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankDTO extends MasterDTO {

    private String nameBn;
    private String branch;
    private String accountNumber;
    private String typeOfAccount;
    private String nameOfAccount;
    private String currentCondition;
    private String bankType;
    private String routingNo;
    private String swiftCode;
    private String bankOid;
}
