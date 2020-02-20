package com.cokreates.grp.beans.personal.emergencyContact;

import com.cokreates.core.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmergencyContact extends BaseEntity {

    private String name;
    private String relation, address, phone, email;

}
