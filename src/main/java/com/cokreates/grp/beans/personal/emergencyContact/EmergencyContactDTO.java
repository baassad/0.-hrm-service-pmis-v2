package com.cokreates.grp.beans.personal.emergencyContact;


import com.cokreates.core.MasterDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmergencyContactDTO extends MasterDTO {
    private String name;
    private String relation, address, phone, email;
}
