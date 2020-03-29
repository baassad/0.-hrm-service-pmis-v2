package com.cokreates.grp.beans.personal.presentAddress;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PresentAddress extends BaseEntity {

    private String road;
    private String village;
    private String postOffice;
    private String postCode;
    private String thana;
    private String district;
    private String division;
    private String country;
    private String phone;
    private String email;
    private String addressType;
}
