package com.cokreates.grp.util.components;

import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.beans.common.EmployeeDetailsDTO;
import com.cokreates.grp.beans.common.EmployeeOfficeMasterDTO;
import com.cokreates.grp.beans.common.OfficeOfficeUnitOfficeUnitPostSetResponseBodyDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.beans.organogramDTO.OfficeDTO;
import com.cokreates.grp.beans.organogramDTO.OfficeUnitDTO;
import com.cokreates.grp.beans.organogramDTO.OfficeUnitPostDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
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

    public List<EmployeeInformationDTO> convertEmpDetailsMasterDTOToEmpInfo(List<EmployeeOfficeMasterDTO> EmployeeOfficeMasterDTOS,
                                                                            OfficeOfficeUnitOfficeUnitPostSetResponseBodyDTO officeOfficeUnitOfficeUnitPostSetResponseBodyDTO){

        HashMap<String, OfficeDTO> officeMap = new HashMap<>();
        HashMap<String, OfficeUnitDTO> officeUnitMap = new HashMap<>();
        HashMap<String, OfficeUnitPostDTO> officeUnitPostMap = new HashMap<>();

        officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.getOffices()
                .forEach(officeDTO -> {
                    officeMap.put(officeDTO.getOid(), officeDTO);
                });

        officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.getOfficeUnits()
                .forEach(officeUnitDTO -> {
                    officeUnitMap.put(officeUnitDTO.getOid(), officeUnitDTO);
                });

        officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.getOfficeUnitPostS()
                .forEach(officeUnitPostDTO -> {
                    officeUnitPostMap.put(officeUnitPostDTO.getOid(), officeUnitPostDTO);
                });

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

            employeeInformationDTO.setOfficeNameEn(officeMap.get(employeeOfficeMasterDTO.getOfficeOid()).getNameEn());
            employeeInformationDTO.setOfficeNameBn(officeMap.get(employeeOfficeMasterDTO.getOfficeOid()).getNameBn());

            if (officeUnitMap.get(employeeOfficeMasterDTO.getOfficeUnitOid()) != null) {
                employeeInformationDTO.setOfficeUnitNameEn(officeUnitMap.get(employeeOfficeMasterDTO.getOfficeUnitOid()).getNameEn());
                employeeInformationDTO.setOfficeUnitNameBn(officeUnitMap.get(employeeOfficeMasterDTO.getOfficeUnitOid()).getNameBn());
            }

            if (officeUnitPostMap.get(employeeOfficeMasterDTO.getOfficeUnitPostOid()) != null) {
                if (officeUnitPostMap.get(employeeOfficeMasterDTO.getOfficeUnitPostOid()).getPost() != null) {
                    employeeInformationDTO.setOfficeUnitPostNameEn(officeUnitPostMap.get(employeeOfficeMasterDTO.getOfficeUnitPostOid()).getPost().getNameEn());
                    employeeInformationDTO.setOfficeUnitPostNameBn(officeUnitPostMap.get(employeeOfficeMasterDTO.getOfficeUnitPostOid()).getPost().getNameBn());
                }
            }

            employeeInformationDTOS.add(employeeInformationDTO);
        }

        return employeeInformationDTOS;

    }




    public void mapProfileData(OfficeOfficeUnitOfficeUnitPostSetResponseBodyDTO officeOfficeUnitOfficeUnitPostSetResponseBodyDTO, List<EmployeeInformationDTO> profiles) {
        HashMap<String, OfficeDTO> officeMap = new HashMap<>();
        HashMap<String, OfficeUnitDTO> officeUnitMap = new HashMap<>();
        HashMap<String, OfficeUnitPostDTO> officeUnitPostMap = new HashMap<>();

        officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.getOffices()
                .forEach(officeDTO -> {
                    officeMap.put(officeDTO.getOid(), officeDTO);
                });

        officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.getOfficeUnits()
                .forEach(officeUnitDTO -> {
                    officeUnitMap.put(officeUnitDTO.getOid(), officeUnitDTO);
                });

        officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.getOfficeUnitPostS()
                .forEach(officeUnitPostDTO -> {
                    officeUnitPostMap.put(officeUnitPostDTO.getOid(), officeUnitPostDTO);
                });

        profiles.forEach(x -> {
            OfficeDTO officeDTO = officeMap.get(x.getOfficeOid());
            OfficeUnitDTO officeUnitDTO = officeUnitMap.get(x.getOfficeUnitOid());
            OfficeUnitPostDTO officeUnitPostDTO = officeUnitPostMap.get(x.getOfficeUnitPostOid());
            x.setOfficeNameBn(officeDTO.getNameBn());
            x.setOfficeNameEn(officeDTO.getNameEn());
            x.setOfficeUnitNameBn(officeUnitDTO.getNameBn());
            x.setOfficeUnitNameEn(officeUnitDTO.getNameEn());
            x.setOfficeUnitPostNameBn(officeUnitPostDTO.getPost().getNameBn());
            x.setOfficeUnitPostNameEn(officeUnitPostDTO.getPost().getNameEn());
        });
    }
}
