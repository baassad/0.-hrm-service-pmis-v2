package com.cokreates.grp.beans.personal.bank;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
}
