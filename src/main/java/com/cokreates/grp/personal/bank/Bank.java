package com.cokreates.grp.personal.bank;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Bank extends BaseEntity {

    private String nameEn;
    private String nameBn;
    private String branch;
    private String accountNumber;
    private String typeOfAccount;
    private String nameOfAccount;
    private String currentCondition;
    //tempData obj
}