package com.cokreates.grp.beans.common;

import com.cokreates.grp.beans.personal.bank.BankDTO;
import com.cokreates.grp.beans.personal.childEducation.ChildEducationDTO;
import com.cokreates.grp.beans.personal.emergencyContactAddress.EmergencyContactAddress;
import com.cokreates.grp.beans.personal.familyInfo.FamilyInfoDTO;
import com.cokreates.grp.beans.personal.file.FileDTO;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import com.cokreates.grp.beans.personal.health.HealthDTO;
import com.cokreates.grp.beans.personal.presentAddress.PresentAddressDTO;
import com.cokreates.grp.beans.personal.sicknesses.InjuriesSicknessDTO;
import com.cokreates.grp.beans.personal.travel.TravelDTO;
import com.cokreates.grp.beans.qualification.education.EducationDTO;

import java.util.List;

public class PersonalDTO {
    List<BankDTO> bank;
    List<FileDTO> file;
    List<TravelDTO> travel;
    List<PresentAddressDTO> address;
    List<EducationDTO> education;
    List<FamilyInfoDTO> familyInfo;
    List<ChildEducationDTO> childEducation;
    List<InjuriesSicknessDTO> injuriesSickness;
    HealthDTO health;
    GeneralDTO general;
    EmergencyContactAddress emergencyContact;
}
