package com.cokreates.grp.beans.common;

import com.cokreates.grp.beans.personal.bank.BankDTO;
import com.cokreates.grp.beans.personal.birthPlaceAddress.BirthPlaceAddressDTO;
import com.cokreates.grp.beans.personal.emergencyContactAddress.EmergencyContactAddressDTO;
import com.cokreates.grp.beans.personal.familyInfo.FamilyInfoDTO;
import com.cokreates.grp.beans.personal.file.FileDTO;
import com.cokreates.grp.beans.personal.foreignAddress.ForeignAddressDTO;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import com.cokreates.grp.beans.personal.health.HealthDTO;
import com.cokreates.grp.beans.personal.permanentAddress.PermanentAddressDTO;
import com.cokreates.grp.beans.personal.presentAddress.PresentAddressDTO;
import com.cokreates.grp.beans.personal.sicknesses.InjuriesSicknessDTO;
import com.cokreates.grp.beans.personal.travel.TravelDTO;
import lombok.Data;

import java.util.List;

@Data
public class PersonalDTO {
    GeneralDTO general;
    List<BankDTO> bank;
    List<TravelDTO> travel;
    HealthDTO health;
    List<InjuriesSicknessDTO> injuriesSickness;
    List<FileDTO> file;
    PermanentAddressDTO permanentAddress;
    PresentAddressDTO presentAddress;
    EmergencyContactAddressDTO emergencyContactAddress;
    BirthPlaceAddressDTO birthPlaceAddress;
    ForeignAddressDTO foreignAddress;
    List<FamilyInfoDTO> familyInfo;

}
