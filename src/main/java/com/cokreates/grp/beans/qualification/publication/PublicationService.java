package com.cokreates.grp.beans.qualification.publication;

import com.cokreates.core.Constant;
import com.cokreates.core.MasterService;
import com.cokreates.core.RequestBodyModel;
import com.cokreates.core.RequestModel;
import com.cokreates.grp.beans.common.EmployeeInformationDTO;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.cokreates.grp.util.webservice.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PublicationService extends MasterService<PublicationDTO, Publication> {

//    @Value("${hrm-service-award-publication.url}")
//    private String awardPublicationUrl;

    @Autowired
    private WebService webService;

    public PublicationService(RequestBuildingComponent<PublicationDTO> requestBuildingComponent,
                              DataServiceRestTemplateClient< PublicationDTO, Publication> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        this.setNodePath(Arrays.asList("qualification", "publication"));
        this.setType("List");
    }

    public List<PublicationDTO> getPublicationList(RequestModel<PublicationDTO> requestDTO) {

        RequestModel<EmployeeInformationDTO> employeeRequest = new RequestModel<>();

        RequestBodyModel<EmployeeInformationDTO> employeeBody = new RequestBodyModel();

        EmployeeInformationDTO employee = new EmployeeInformationDTO();
        employee.setOid(requestDTO.getBody().getData().get(0).getOid());

        employeeBody.setData(Arrays.asList(employee));

        employeeRequest.setHeader(requestDTO.getHeader());
        employeeRequest.setMeta(requestDTO.getMeta());
        employeeRequest.setBody(employeeBody);

//        String endPointUrl = awardPublicationUrl + Constant.PREFIX_PUBLICATION + Constant.ENDPOINT_GET_BY_EMPLOYEE_OID;
        String endPointUrl = Constant.PREFIX_PUBLICATION + Constant.ENDPOINT_GET_BY_EMPLOYEE_OID;

        List<PublicationListResponseBodyDTO> resultFromPublicationSubModule = webService.getRestTemplateResponse(endPointUrl, PublicationListResponseBodyDTO.class, employeeRequest);

        List<PublicationDTO> result = new ArrayList<>();

        resultFromPublicationSubModule
                .forEach(singleResultFromPublicationSubModule -> {
                    PublicationDTO publicationDTO = new PublicationDTO();

                    publicationDTO.setPublicationType(singleResultFromPublicationSubModule.getTypeNameBn());
                    publicationDTO.setPublicationName(singleResultFromPublicationSubModule.getTitle());
                    publicationDTO.setDescription(singleResultFromPublicationSubModule.getDescription());
                    publicationDTO.setPublicationDate(singleResultFromPublicationSubModule.getDateOfReceiving());

                    result.add(publicationDTO);
                });

        return result;

    }

    @Override
    public Class getDtoClass() {
        return PublicationDTO.class;
    }

    @Override
    public Class getEntityClass() {return Publication.class;}
}
