package com.cokreates.grp.beans.employeeimport;

import com.cokreates.core.MasterService;
import com.cokreates.grp.beans.common.EmployeeDetailsMasterDTO;
import com.cokreates.grp.beans.common.EmployeeOfficeMasterDTO;
import com.cokreates.grp.beans.employee.Employee;
import com.cokreates.grp.beans.employee.EmployeeDTO;
import com.cokreates.grp.beans.pim.employeeMasterInfo.EmployeeMasterInfo;
import com.cokreates.grp.beans.pim.employeeOfficePim.EmployeeOffice;
import com.cokreates.grp.beans.pim.employeeOfficePim.EmployeeOfficeRepository;
import com.cokreates.grp.beans.pim.employeePersonalInfo.EmployeePersonalInfo;
import com.cokreates.grp.beans.pim.employeePersonalInfo.EmployeePersonalInfoRepository;
import com.cokreates.grp.beans.pim.pmis.PmisRepository;
import com.cokreates.grp.data.service.DataEmployeeService;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.request.EmployeeImportRequestDTO;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EmployeeImportService extends MasterService<EmployeeDTO, Employee> {

    @Autowired
    EmployeeOfficeRepository employeeOfficeRepository;

    @Autowired
    EmployeePersonalInfoRepository employeePersonalInfoRepository;

    @Autowired
    PmisRepository pmisRepository;

    @Autowired
    DataEmployeeService dataEmployeeService;

    JSONArray path =  new JSONArray("[\"personal\", \"general\"]");

    public EmployeeImportService(RequestBuildingComponent<EmployeeDTO> requestBuildingComponent,
                           DataServiceRestTemplateClient<EmployeeDTO, Employee> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);

    }



    @Transactional
    public List<String> importEmployees(EmployeeImportRequestDTO requestDTO){

        Set<String> employeeOids = new HashSet<>();
        Set<String> oidSet = new HashSet<>();
        List<EmployeeOffice> employeeOffices = new ArrayList<>();

        Map<String, EmployeeMasterInfo> employeeMasterInfoMap = new HashMap<>();
        Map<String, EmployeePersonalInfo> employeePersonalInfoMap = new HashMap<>();
        Map<String, List<EmployeeOffice>> employeeOfficeMap = new HashMap<>();

        if(requestDTO.getEmployeeOids() != null && requestDTO.getEmployeeOids().size() > 0){

            employeeOids.addAll(requestDTO.getEmployeeOids());
        }else if(requestDTO.getOfficeUnitPostOids() != null && requestDTO.getOfficeUnitPostOids().size() > 0){
            oidSet.addAll(requestDTO.getOfficeUnitPostOids());
            employeeOffices = employeeOfficeRepository.findByOfficeUnitPostOidInAndStatusAndIsDeleted(oidSet,"Active","No");

        }else if(requestDTO.getOfficeUnitOids() != null && requestDTO.getOfficeUnitOids().size() > 0){
            oidSet.addAll(requestDTO.getOfficeUnitOids());
            employeeOffices = employeeOfficeRepository.findByOfficeUnitOidInAndStatusAndIsDeleted(oidSet,"Active","No");

        }else if(requestDTO.getOfficeOids() != null && requestDTO.getOfficeOids().size() > 0){
            oidSet.addAll(requestDTO.getOfficeOids());
            employeeOffices = employeeOfficeRepository.findByOfficeOidInAndStatusAndIsDeleted(oidSet,"Active","No");

        }

        for(EmployeeOffice employeeOffice:employeeOffices){
            employeeOids.add(employeeOffice.getEmployeeMasterInfo().getOid());
        }

        Set<String> pmisOids = pmisRepository.getAllOidsFromPmis();

        employeeOids.removeAll(pmisOids);

        System.out.println("Employee Oids : " + employeeOids );

        List<EmployeePersonalInfo> employeePersonalInfoList = employeePersonalInfoRepository.getEmployeePersonalInfoList(employeeOids);

        employeeOffices = employeeOfficeRepository.findActiveEmployeeByEmployeeOid(employeeOids);

        System.out.println("Employee Office size : " + employeeOffices.size());

        for(EmployeeOffice employeeOffice:employeeOffices){
            if(employeeOffice.getOfficeUnitPostOid() == null){
                continue;
            }

            if(employeeMasterInfoMap.get(employeeOffice.getEmployeeMasterInfo().getOid()) == null){
               employeeMasterInfoMap.put(employeeOffice.getEmployeeMasterInfo().getOid(),employeeOffice.getEmployeeMasterInfo()) ;
            }

            if(employeeOfficeMap.get(employeeOffice.getEmployeeMasterInfo().getOid()) != null){
               employeeOfficeMap.get(employeeOffice.getEmployeeMasterInfo().getOid()).add(employeeOffice);
            }else{
               List<EmployeeOffice> employeeOfficeList = new ArrayList<>();
               employeeOfficeList.add(employeeOffice);
               employeeOfficeMap.put(employeeOffice.getEmployeeMasterInfo().getOid(),employeeOfficeList);
            }

        }

        for(EmployeePersonalInfo employeePersonalInfo:employeePersonalInfoList){
            if(employeePersonalInfoMap.get(employeePersonalInfo.getEmployeeMasterInfo().getOid()) == null){
                employeePersonalInfoMap.put(employeePersonalInfo.getEmployeeMasterInfo().getOid(),employeePersonalInfo);
            }

        }

        List<String> importedEmployeeOids = new ArrayList<>();

        String insertedOid;

        for(String oid:employeeMasterInfoMap.keySet()){
            insertedOid = dataEmployeeService.importEmployees(path,employeeMasterInfoMap.get(oid),employeePersonalInfoMap.get(oid),employeeOfficeMap.get(oid));
            importedEmployeeOids.add(insertedOid);
        }


        return importedEmployeeOids;

    }

    public List<String> importOfficeAdmin(List<String> officeOids){

        Set<String> employeeOids = employeeOfficeRepository.findEmployeeOidsOfOfficeAdminByOfficeOids(officeOids);

        EmployeeImportRequestDTO employeeImportRequestDTO = new EmployeeImportRequestDTO();

        List<String> oidStringList = new ArrayList<>();

        employeeImportRequestDTO.setOfficeOids(oidStringList);
        employeeImportRequestDTO.setOfficeUnitOids(oidStringList);
        employeeImportRequestDTO.setOfficeUnitPostOids(oidStringList);

        employeeImportRequestDTO.setEmployeeOids(employeeOids);

        return importEmployees(employeeImportRequestDTO);

    }
}
