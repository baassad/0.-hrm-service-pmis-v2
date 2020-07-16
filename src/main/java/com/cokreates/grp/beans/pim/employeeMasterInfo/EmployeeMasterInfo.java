package com.cokreates.grp.beans.pim.employeeMasterInfo;

import com.cokreates.grp.beans.pim.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table
public class EmployeeMasterInfo extends SuperEntity {

    private String nameEn;
    private String nameBn;
    private String enothiId;
    private String grpUsername;
    private String govtId;
    private String status;
    private String isFreedomFighter;
    private String isDescendantOfFreedomFighter;
    private String photoUrl;
    private String signatureUrl;
    private Date gazettedDate;
    private Date joinDate;


}
