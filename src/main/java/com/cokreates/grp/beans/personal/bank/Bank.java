package com.cokreates.grp.beans.personal.bank;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Bank extends BaseEntity {

    private String nameBn;
    private String branch;
    private String accountNumber;
    private String typeOfAccount;
    private String nameOfAccount;
    private String currentCondition;
    private String bankType;
}