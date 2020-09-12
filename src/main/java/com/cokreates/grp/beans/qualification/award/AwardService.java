package com.cokreates.grp.beans.qualification.award;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterService;
import com.cokreates.core.RequestBodyModel;
import com.cokreates.core.RequestModel;
import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.cokreates.grp.util.webservice.WebService;
import com.netflix.discovery.converters.Auto;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Data
public class AwardService extends MasterService<AwardDTO, Award> {

    @Value("${hrm-service-award-publication.url}")
    private String awardPublicationUrl;

    @Autowired
    private WebService webService;

    public AwardService(RequestBuildingComponent<AwardDTO> requestBuildingComponent,
                        DataServiceRestTemplateClient< AwardDTO, Award> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("qualification", "award"));
        this.setType("List");
    }

    public List<AwardDTO> getAwardList(RequestModel<AwardDTO> requestDTO) {

        RequestModel<EmployeeInformationDTO> employeeRequest = new RequestModel<>();

        RequestBodyModel<EmployeeInformationDTO> employeeBody = new RequestBodyModel();

        EmployeeInformationDTO employee = new EmployeeInformationDTO();
        employee.setOid(requestDTO.getBody().getData().get(0).getOid());

        employeeBody.setData(Arrays.asList(employee));

        employeeRequest.setHeader(requestDTO.getHeader());
        employeeRequest.setMeta(requestDTO.getMeta());
        employeeRequest.setBody(employeeBody);

        String endPointUrl = awardPublicationUrl + Constant.PREFIX_AWARD + Constant.ENDPOINT_GET_BY_EMPLOYEE_OID;
//        String endPointUrl = Constant.PREFIX_AWARD + Constant.ENDPOINT_GET_BY_EMPLOYEE_OID;

        List<AwardListResponseBodyDTO> resultFromAwardSubModule = webService.getRestTemplateResponse(endPointUrl, AwardListResponseBodyDTO.class, employeeRequest);

        List<AwardDTO> result = new ArrayList<>();

        resultFromAwardSubModule
                .forEach(singleResultFromAwardSubModule -> {
                    AwardDTO awardDTO = new AwardDTO();

                    awardDTO.setTitleOfAward(singleResultFromAwardSubModule.getTitle());
                    awardDTO.setAwardReceivalPlace(singleResultFromAwardSubModule.getGivenBy());
                    awardDTO.setCountry(singleResultFromAwardSubModule.getCountryNameBn());
                    awardDTO.setAwardReceivedDate(singleResultFromAwardSubModule.getDateOfReceiving());

                    result.add(awardDTO);
                });

        return result;

    }
    
    @Override
    public Class getDtoClass() {
        return AwardDTO.class;
    }

    @Override
    public Class getEntityClass() {return Award.class;}
}
