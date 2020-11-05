package com.cokreates.grp.beans.search;

import com.cokreates.core.Constant;
import com.cokreates.core.ResponseModel;
import com.cokreates.core.ServiceRequestDTO;
import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.beans.common.EmployeeInformationIncludedGradeDTO;
import com.cokreates.grp.daas.DataServiceResponse;
import com.cokreates.grp.util.components.ResultBuildingComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/search/v1")
public class SearchController {

      @Autowired
      ResultBuildingComponent resultBuildingComponent;

      @Autowired
      SearchService service;

      @PostMapping(Constant.ENDPOINT_SEARCH_EMPLOYEES)
      ResponseModel<EmployeeInformationDTO> getTheSearchedEmployees(@Valid @RequestBody ServiceRequestDTO<NamedEmployeeRequestBodyDTO> requestDTO){

            return resultBuildingComponent.retrieveResultForEmployeeInformation(requestDTO.getHeader(),service.getTheEmployees(requestDTO));

      }

      @PostMapping(Constant.ENDPOINT_SEARCH_EMPLOYEES_BY_GRADE)
      ResponseModel<EmployeeInformationIncludedGradeDTO> getTheSearchedEmployeesWithGrade(@Valid @RequestBody ServiceRequestDTO<OfficeWithGradeRequestBodyDTO> requestDTO){
            return resultBuildingComponent.retrieveResultForEmployeeInformationWithGrade(requestDTO.getHeader(),service.getTheEmployeesWithGrade(requestDTO));
      }


      @PostMapping(Constant.ENDPOINT_SEARCH_EMPLOYEES_NOT_IMPORTED)
      ResponseModel<EmployeeInformationDTO> getTheSearchedEmployeesNotImported(@Valid @RequestBody ServiceRequestDTO<NamedEmployeeRequestBodyDTO> requestDTO){

            return resultBuildingComponent.retrieveResultForEmployeeInformation(requestDTO.getHeader(),service.getTheEmployeesNotImported(requestDTO));

      }

}
