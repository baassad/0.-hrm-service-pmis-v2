package com.cokreates.grp.beans.personal.mobileBanking;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class MobileBankingDTO extends MasterDTO {


    private String providerOid;
    private String accountNumber;
    private String typeOfAccount;
    private String nameOfAccount;
}
