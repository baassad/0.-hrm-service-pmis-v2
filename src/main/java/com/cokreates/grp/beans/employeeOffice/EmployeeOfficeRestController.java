package com.cokreates.grp.beans.employeeOffice;

import com.cokreates.core.*;
import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.beans.employee.EmployeeDTO;
import com.cokreates.grp.beans.employee.EmployeeService;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import com.cokreates.grp.beans.pim.pmis.PmisRepository;
import com.cokreates.grp.util.request.GetListByOidSetRequestBodyDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/employee-office")
public class EmployeeOfficeRestController extends MasterRestController<EmployeeOfficeDTO,EmployeeOffice> {

    @Autowired
    PmisRepository pmisRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    protected EmployeeOfficeService employeeOfficeService;

    public EmployeeOfficeRestController(EmployeeOfficeService employeeOfficeService){
        super(employeeOfficeService);
    }

    @PostMapping(Constant.ENDPOINT_CREATE_EMPLOYEE_OFFICE)
    public ResponseModel<EmployeeOfficeDTO> appendEmployeeOffice(@Valid @RequestBody RequestModel<EmployeeOfficeDTO> requestDTO) {
        return resultBuildingComponent.retrieveResultForEmployeeOffice(requestDTO.getHeader(), Collections.singletonList(employeeOfficeService.create(requestDTO.getBody().getData().get(0),requestDTO.getBody().getEmployeeOid())));
    }

    @PostMapping(Constant.ENDPOINT_UPDATE_EMPLOYEE_OFFICE)
    public ResponseModel<EmployeeOfficeDTO> updateEmployeeOffice(@Valid @RequestBody RequestModel<EmployeeOfficeDTO> requestDTO){
        return resultBuildingComponent.retrieveResultForEmployeeOffice(requestDTO.getHeader(),Collections.singletonList(employeeOfficeService.updateEmployeeOffice(requestDTO.getBody().getData().get(0),requestDTO.getBody().getEmployeeOid())));
    }

    /*@PostMapping(Constant.VERSION + Constant.ENDPOINT_GET_LIST_BY_OID_SET)
    public ResponseModel<EmployeeInformationDTO> getEmployeeInformationByOidSet(ServiceRequestDTO<GetListByOidSetRequestBodyDTO> requestDTO){


    }

    @PostMapping(Constant.VERSION + Constant.ENDPOINT_GET_LIST_BY_EMPLOYEE_OFFICE_OID_SET)
    public ResponseModel<EmployeeInformationDTO> getEmployeeInformationByEmployeeOfficeOidSet(ServiceRequestDTO<GetListByOidSetRequestBodyDTO> requestDTO){


    }*/

    @PostMapping("/test")
    public ResponseModel<EmployeeOfficeDTO> test(@RequestBody RequestModel<EmployeeOfficeDTO> requestDTO){

        String employeeOfficeDTOList = pmisRepository.getEmployeeOffices();

        System.out.println("Json is : " + employeeOfficeDTOList);

        try {

            List<EmployeeOfficeDTO> list = objectMapper.readValue(employeeOfficeDTOList, new TypeReference<List<EmployeeOfficeDTO>>(){});
            System.out.println(list.size());

            for (EmployeeOfficeDTO dto:list){
                System.out.println("Printing : " + dto.getOfficeOid());
            }

        }catch (Exception e){
            System.out.println("exception");
        }

        return null;//resultBuildingComponent.retrieveResultForEmployeeOffice(requestDTO.getHeader(),employeeOfficeDTOList);
    }


    @PostMapping(Constant.ENDPOINT_EMPLOYEE_OFFICE)
    public ResponseModel<EmployeeOfficeDTO> getEmployeeOfficeListForEmployeeAndOfficeUnitPostOid(@Valid @RequestBody RequestModel<EmployeeOfficeDTO> requestDTO){
        String officeUnitPostOid =  requestDTO.getBody().getData().get(0).getOfficeUnitPostOid();
        if(officeUnitPostOid != null) {
            return resultBuildingComponent.retrieveResultForEmployeeOffice(requestDTO.getHeader(), employeeOfficeService.getEmployeeOfficeList(requestDTO.getBody().getEmployeeOid(),officeUnitPostOid));
        }else {
            return  null;
        }
    }

}
