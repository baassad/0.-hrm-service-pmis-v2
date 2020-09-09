package com.cokreates.grp.beans.personal.mobileBanking;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class MobileBanking extends BaseEntity {

    private String providerOid;
    private String accountNumber;
    private String typeOfAccount;
    private String nameOfAccount;
}
