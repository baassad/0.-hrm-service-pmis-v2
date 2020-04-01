package com.cokreates.grp.util.components;

import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.beans.common.EmployeeDetailsDTO;
import com.cokreates.grp.beans.common.EmployeeOfficeMasterDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ClassConversionComponent {

    public List<EmployeeInformationDTO> convertEmpDetailsToEmpInfo(EmployeeDetailsDTO employeeDetailsDTO){

        List<EmployeeInformationDTO> employeeInformationDTOS = new ArrayList<>();

        for(EmployeeOfficeDTO employeeOfficeDTO:employeeDetailsDTO.getNodes()){

            EmployeeInformationDTO employeeInformationDTO = new EmployeeInformationDTO();
            employeeInformationDTO.setNameBn(employeeDetailsDTO.getGeneral().getNameBn());
            employeeInformationDTO.setNameEn(employeeDetailsDTO.getGeneral().getNameEn());
            employeeInformationDTO.setEmail(employeeDetailsDTO.getGeneral().getEmail());
            employeeInformationDTO.setEmployeeOfficeOid(employeeOfficeDTO.getOid());
            employeeInformationDTO.setEmployeeTypeOid(employeeOfficeDTO.getEmploymentTypeOid());
            //employeeInformationDTO.setSignatureImageOid(employeeDetailsDTO.getGeneral().getSignature());
            employeeInformationDTO.setMobileNo(employeeDetailsDTO.getGeneral().getPhone());
            employeeInformationDTO.setOfficeOid(employeeOfficeDTO.getOfficeOid());
            employeeInformationDTO.setOfficeUnitOid(employeeOfficeDTO.getOfficeUnitOid());
            employeeInformationDTO.setOfficeUnitPostOid(employeeOfficeDTO.getOfficeUnitPostOid());
            //employeeInformationDTO.setProfileImageOid(employeeDetailsDTO.getGeneral().getPhoto());

            employeeInformationDTOS.add(employeeInformationDTO);
        }

        return employeeInformationDTOS;

    }

    public List<EmployeeInformationDTO> convertEmpDetailsMasterDTOToEmpInfo(List<EmployeeOfficeMasterDTO> EmployeeOfficeMasterDTOS){

        List<EmployeeInformationDTO> employeeInformationDTOS = new ArrayList<>();

        for(EmployeeOfficeMasterDTO employeeOfficeMasterDTO:EmployeeOfficeMasterDTOS){

            EmployeeInformationDTO employeeInformationDTO = new EmployeeInformationDTO();

            employeeInformationDTO.setOid(employeeOfficeMasterDTO.getOid());

            employeeInformationDTO.setNameBn(employeeOfficeMasterDTO.getNameBn());
            employeeInformationDTO.setNameEn(employeeOfficeMasterDTO.getNameEn());
            employeeInformationDTO.setMobileNo(employeeOfficeMasterDTO.getPhone());
            employeeInformationDTO.setEmail(employeeOfficeMasterDTO.getEmail());

            employeeInformationDTO.setEmployeeOfficeOid(employeeOfficeMasterDTO.getOid());
            employeeInformationDTO.setEmployeeTypeOid(employeeOfficeMasterDTO.getEmploymentTypeOid());
            //employeeInformationDTO.setSignatureImageOid(employeeDetailsDTO.getGeneral().getSignature());
            employeeInformationDTO.setOfficeOid(employeeOfficeMasterDTO.getOfficeOid());
            employeeInformationDTO.setOfficeUnitOid(employeeOfficeMasterDTO.getOfficeUnitOid());
            employeeInformationDTO.setOfficeUnitPostOid(employeeOfficeMasterDTO.getOfficeUnitPostOid());
            //employeeInformationDTO.setProfileImageOid(employeeDetailsDTO.getGeneral().getPhoto());

            employeeInformationDTOS.add(employeeInformationDTO);
        }

        return employeeInformationDTOS;

    }
}
