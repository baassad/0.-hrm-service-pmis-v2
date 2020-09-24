package com.cokreates.grp.beans.personal.mobileBanking;

import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@EqualsAndHashCode(callSuper = true)
@Data
public class MobileBankingDTO extends MasterDTO {

    @NotBlank
    private String providerOid;

    @NotBlank
    private String accountNumber;
    private String typeOfAccount;

    @NotBlank
    private String nameOfAccount;
}
