package com.cokreates.grp.beans.employee;

import com.cokreates.core.*;
import com.cokreates.grp.beans.common.*;
import com.cokreates.grp.beans.employeeOffice.EmployeeOffice;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeDTO;
import com.cokreates.grp.beans.employeeOffice.EmployeeOfficeService;
import com.cokreates.grp.beans.employeeOfficeV2.EmployeeOfficeV2DTO;
import com.cokreates.grp.beans.employeeOfficeV2.EmployeeOfficeV2Service;
import com.cokreates.grp.beans.personal.file.FileDTO;
import com.cokreates.grp.beans.personal.file.FileService;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import com.cokreates.grp.beans.personal.general.GeneralService;
import com.cokreates.grp.beans.pim.employeeOfficePim.EmployeeOfficeRepository;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceRequestBody;
import com.cokreates.grp.daas.DataServiceResponse;
import com.cokreates.grp.util.components.ClassConversionComponent;
import com.cokreates.grp.util.components.EmployeeDetailsRenderComponent;
import com.cokreates.grp.util.components.HeaderUtilComponent;
import com.cokreates.grp.util.components.RequestBuildingComponent;
import com.cokreates.grp.util.exceptions.ServiceExceptionHolder;
import com.cokreates.grp.util.request.*;
import com.cokreates.grp.util.webclient.DataServiceClient;
import com.cokreates.grp.util.webclient.DataServiceRestTemplateClient;
import com.cokreates.grp.util.webclient.HrmPimClient;
import com.cokreates.grp.util.webservice.WebService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService extends MasterService<EmployeeDTO, Employee> {

    @Value("${cmn-service-file-management.url}")
    private String fileServiceUrl;

    @Value("${cmn-service-organogram.url}")
    private String organogramUrl;

    @Autowired
    EmployeeDetailsRenderComponent employeeComponent;

    @Autowired
    HrmPimClient hrmPimClient;

    @Autowired
    DataServiceClient dataServiceClient;

    @Autowired
    EmployeeOfficeRepository employeeOfficeRepository;

    @Autowired
    HeaderUtilComponent headerUtilComponent;

    @Autowired
    ClassConversionComponent conversionComponent;

    RequestBuildingComponent<EmployeeOfficeMasterDTO> EmployeeOfficeMasterDTORequestBuildingComponent;

    RequestBuildingComponent<EmployeeDetailsMasterDTO> employeeDetailsMasterDTORequestBuildingComponent;

    @Autowired
    DataServiceRestTemplateClient<EmployeeOfficeMasterDTO, EmployeeOffice> restTemplateEmployeedetailsMasterInfo;

    @Autowired
    DataServiceRestTemplateClient<EmployeeDetailsMasterDTO, EmployeeOffice> restTemplateEmployeeDetailsMasterDTO;

    @Autowired
    GeneralService generalService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeOfficeService employeeOfficeService;

    @Autowired
    WebService webService;

    @Autowired
    FileService fileService;

    @Autowired
    SwitchService switchService;
    
    @Autowired
    EmployeeOfficeV2Service employeeOfficeV2Service;

    public EmployeeService(RequestBuildingComponent<EmployeeDTO> requestBuildingComponent,
                           DataServiceRestTemplateClient<EmployeeDTO, Employee> dataServiceRestTemplateClient){
        super(requestBuildingComponent, dataServiceRestTemplateClient);
        EmployeeOfficeMasterDTORequestBuildingComponent = new RequestBuildingComponent<EmployeeOfficeMasterDTO>();
        employeeDetailsMasterDTORequestBuildingComponent = new RequestBuildingComponent<EmployeeDetailsMasterDTO>();
    }

    public RequestBuildingComponent<EmployeeOfficeMasterDTO> getEmployeeOfficeMasterDTORequestBuildingComponent() {
        return  this.EmployeeOfficeMasterDTORequestBuildingComponent;
    }

    public RequestBuildingComponent<EmployeeDetailsMasterDTO> getEmployeeDetailsMasterDTORequestBuildingComponent() {
        return  this.employeeDetailsMasterDTORequestBuildingComponent;
    }

    public DataServiceRestTemplateClient<EmployeeOfficeMasterDTO, EmployeeOffice> getRestTemplateEmployeedetailsMasterInfo() {
        return  this.restTemplateEmployeedetailsMasterInfo;
    }

    public DataServiceRestTemplateClient<EmployeeDetailsMasterDTO, EmployeeOffice> getRestTemplateEmployeeDetailsMasterDTO() {
        return  this.restTemplateEmployeeDetailsMasterDTO;
    }

    public EmployeeDTO create(GeneralDTO dto) {
        dto = generalService.parseBeforeUpdate(dto);
        DataServiceRequest<EmployeeDTO> request = getRequestBuildingComponent().getRequestToCreateEmployee(dto, getDtoClass());

        String gDataEndPointUrl = getGData() + Constant.GDATA_CREATE + Constant.VERSION_1 + Constant.ENDPOINT_EMPLOYEE;

        return getDataServiceRestTemplateClient().getRestTemplateResponseForEmployee(request,gDataEndPointUrl);
    }

    @Override
    public EmployeeDTO getNode(String employeeOid) {
        DataServiceRequest<EmployeeDTO> request = getRequestBuildingComponent().getRequestForRead(getNodePath(),null, employeeOid,
                null, null, null, null,
                null, null, null, this.getDtoClass());

        String gDataEndPointUrl = getGData() + Constant.GDATA_GET + Constant.VERSION_1 + Constant.GDATA_EMP;
        log.debug("==== gDataEndPointUrl ==== " + gDataEndPointUrl);

        return getDataServiceRestTemplateClient().getRestTemplateResponseForEmployee(request, gDataEndPointUrl);

    }
    
    /*
    public  List<EmployeeOfficeDTO> getEmployeeOfficeList(String employeeOid,String officeUnitPostOid){

        DataServiceRequest<EmployeeDTO> request = getRequestBuildingComponent().getRequestForRead(null,null, employeeOid,
                null, null, null, null,
                null, null, null, this.getDtoClass());

        DataServiceResponseForList<EmployeeOfficeDTO> response = dataServiceClient.getEmployeeOfficeList(request);

        List<EmployeeOfficeDTO> employeeOfficeDTOList = response.getBody().getMain();

        List<EmployeeOfficeDTO> finalEmployeeOfficeDTOList = new ArrayList<>();

        for(EmployeeOfficeDTO employeeOfficeDTO : employeeOfficeDTOList){
            if(employeeOfficeDTO.getOfficeUnitPostOid() != null && employeeOfficeDTO.getStatus().equalsIgnoreCase("Active")
                    && employeeOfficeDTO.getOfficeUnitPostOid().equalsIgnoreCase(officeUnitPostOid)){
                finalEmployeeOfficeDTOList.add(employeeOfficeDTO);
            }

        }

        return finalEmployeeOfficeDTOList;

    }
    */

    public  List<EmployeeOfficeDTO> getEmployeeOfficeList(String employeeOid,String officeUnitPostOid){
        List<EmployeeOfficeV2DTO> officeList = employeeOfficeV2Service.getEmployeeOfficeByEmployeeOidAndOfficeUnitPostOid(employeeOid, officeUnitPostOid);
        List<EmployeeOfficeDTO> employeeOfficeDTOList = convertPmisEmployeeOfficeListToEmployeeOfficeList(officeList);
        return employeeOfficeDTOList;
    }
    
    
    public List<EmployeeInformationDTO> getEmployeeInformationDTO(GetListByOidSetRequestBodyDTO requestDTO){

        String employeeOid = requestDTO.getOids().get(0);

        DataServiceRequest<EmployeeDTO> request = getRequestBuildingComponent().getRequestForGettingEmployeeDetails(employeeOid);

        String gDataEndPointUrl = getGData() + Constant.GDATA_GET + Constant.VERSION_1 + Constant.EMPLOYEE_DETAILS;
        log.debug("==== gDataEndPointUrl ==== " + gDataEndPointUrl);

        DataServiceResponse<EmployeeDetailsDTO> response = dataServiceClient.getEmployeeDetailsFromDaas(request);

        List<EmployeeInformationDTO> employeeInformationDTOS = conversionComponent.convertEmpDetailsToEmpInfo(response.getBody().getMain());

        for(EmployeeInformationDTO dto:employeeInformationDTOS){
            dto.setOid(employeeOid);
        }

        return employeeInformationDTOS;

    }

    public List<EmployeeInformationDTO> getEmployeeMainInformationDTOByOidSet(GetListByOidSetRequestBodyDTO requestDTO){

        if (requestDTO.getOids().isEmpty()) return new ArrayList<>();

        MiscellaneousRequestProperty miscellaneousRequestProperty = new MiscellaneousRequestProperty();
        miscellaneousRequestProperty.setEmployeeOidList(requestDTO.getOids());


        String gDataEndPointUrl = getGData()+Constant.GDATA_GET+Constant.VERSION_1 + Constant.GDATA_MAIN_EMPLOYEE_BY_OID_SET;;

        DataServiceRequest<EmployeeDetailsMasterDTO> requestEmployee = employeeService.getEmployeeDetailsMasterDTORequestBuildingComponent().getRequestForRead(getNodePath(), null, null,
                null, null, null, null,
                null, null, null, EmployeeDetailsMasterDTO.class);

        DataServiceRequestBody dataServiceRequestBody = requestEmployee.getBody();
        dataServiceRequestBody.setMiscellaneousRequestProperty(miscellaneousRequestProperty);

        List<EmployeeDetailsMasterDTO> employeeOfficeMasterDTOList = employeeService.getRestTemplateEmployeeDetailsMasterDTO().getListData(getNodePath(), requestEmployee, gDataEndPointUrl);

        List<EmployeeInformationDTO> employeeInformationDTOS = new ArrayList<>();

        employeeOfficeMasterDTOList
                .forEach(employeeDetailsMasterDTO -> {
                    EmployeeDetailsDTO employeeDetailsDTO = getModelMapper().map(employeeDetailsMasterDTO, EmployeeDetailsDTO.class);
                    employeeInformationDTOS.addAll(conversionComponent.convertEmpDetailsToEmpInfoSetOidResponsibilityType(employeeDetailsDTO));
                });

        setMissingData(employeeInformationDTOS);

        return employeeInformationDTOS;
    }

    public List<EmployeeInformationDTO> getEmployeeMainInformationDTOByOffice(MiscellaneousRequestProperty requestDTO){

        MiscellaneousRequestProperty miscellaneousRequestProperty = new MiscellaneousRequestProperty();
        miscellaneousRequestProperty.setOfficeOidList(requestDTO.getOfficeOidList());
        miscellaneousRequestProperty.setOfficeUnitOidList(requestDTO.getOfficeUnitOidList());

        String endPoint = "";

        boolean hasOfficeUnit = false;

        if (requestDTO.getOfficeUnitOidList() != null) {
            if (!requestDTO.getOfficeUnitOidList().isEmpty()) {
                hasOfficeUnit = true;
                endPoint = Constant.GDATA_MAIN_EMPLOYEE_BY_OFFICE_OFFICE_UNIT;
            } else endPoint = Constant.GDATA_MAIN_EMPLOYEE_BY_OFFICE;
        } else endPoint = Constant.GDATA_MAIN_EMPLOYEE_BY_OFFICE;

        String gDataEndPointUrl = getGData()+Constant.GDATA_GET+Constant.VERSION_1 + endPoint;

        DataServiceRequest<EmployeeDetailsMasterDTO> requestEmployee = employeeService.getEmployeeDetailsMasterDTORequestBuildingComponent().getRequestForRead(getNodePath(), null, null,
                null, null, null, null,
                null, null, null, EmployeeDetailsMasterDTO.class);

        DataServiceRequestBody dataServiceRequestBody = requestEmployee.getBody();
        dataServiceRequestBody.setMiscellaneousRequestProperty(miscellaneousRequestProperty);

        List<EmployeeDetailsMasterDTO> employeeOfficeMasterDTOList = employeeService.getRestTemplateEmployeeDetailsMasterDTO().getListData(getNodePath(), requestEmployee, gDataEndPointUrl);

        List<EmployeeInformationDTO> employeeInformationDTOS = new ArrayList<>();

        boolean finalHasOfficeUnit = hasOfficeUnit;
        if (finalHasOfficeUnit) {
            employeeOfficeMasterDTOList
                    .stream()
                    .filter(employeeDetailsMasterDTO -> employeeDetailsMasterDTO.getNodes() != null)
                    .filter(employeeDetailsMasterDTO -> !employeeDetailsMasterDTO.getNodes().isEmpty())
                    .filter(employeeDetailsMasterDTO -> requestDTO.getOfficeOidList().contains(employeeDetailsMasterDTO.getNodes().get(0).getOfficeOid())
                                                    &&  requestDTO.getOfficeUnitOidList().contains(employeeDetailsMasterDTO.getNodes().get(0).getOfficeUnitOid()))
                    .forEach(employeeDetailsMasterDTO -> {
                        EmployeeDetailsDTO employeeDetailsDTO = getModelMapper().map(employeeDetailsMasterDTO, EmployeeDetailsDTO.class);
                        employeeInformationDTOS.addAll(conversionComponent.convertEmpDetailsToEmpInfoSetOidResponsibilityType(employeeDetailsDTO));
                    });
        } else {
            employeeOfficeMasterDTOList
                    .stream()
                    .filter(employeeDetailsMasterDTO -> employeeDetailsMasterDTO.getNodes() != null)
                    .filter(employeeDetailsMasterDTO -> !employeeDetailsMasterDTO.getNodes().isEmpty())
                    .filter(employeeDetailsMasterDTO -> requestDTO.getOfficeOidList().contains(employeeDetailsMasterDTO.getNodes().get(0).getOfficeOid()))
                    .forEach(employeeDetailsMasterDTO -> {
                        EmployeeDetailsDTO employeeDetailsDTO = getModelMapper().map(employeeDetailsMasterDTO, EmployeeDetailsDTO.class);
                        employeeInformationDTOS.addAll(conversionComponent.convertEmpDetailsToEmpInfoSetOidResponsibilityType(employeeDetailsDTO));
                    });
        }

//        if (employeeInformationDTOS.isEmpty()) {
//
//            GetListByOidSetRequestBodyDTO getListByOidSetRequestBodyDTO = new GetListByOidSetRequestBodyDTO();
//            getListByOidSetRequestBodyDTO.setOids(requestDTO.getOfficeOidList());
//
//            List<EmployeeInformationDTO> improperEmployeeInformationDTOS = getImproperResponsibilityType(getListByOidSetRequestBodyDTO);
//
//            if (improperEmployeeInformationDTOS.isEmpty()) {
//                if (hasOfficeUnit) {
//                    OidRequestBodyDTO oidRequestBodyDTO = new OidRequestBodyDTO();
//                    oidRequestBodyDTO.setOid(requestDTO.getOfficeOidList().get(0));
//
//                    List<OfficeDTO> officeResponseBodyDTO = null;
//
//                    OrganogramRequestDTO<OidRequestBodyDTO> organogramRequestDTO = new OrganogramRequestDTO<>();
//                    organogramRequestDTO.setHeader(headerUtilComponent.getRequestHeaderDTO());
//                    organogramRequestDTO.setBody(oidRequestBodyDTO);
//
//                    try {
//                        officeResponseBodyDTO =
//                                webService.getRestTemplateResponse(organogramUrl + Constant.ENDPOINT_OFFICE_V1_GET_BY_OID, OfficeDTO.class, organogramRequestDTO);
//                    } catch (Exception e) {
//                        log.error(e.getMessage(), e);
//                    }
//
//                    if (officeResponseBodyDTO== null ||
//                            officeResponseBodyDTO.isEmpty()) {
//                        throw new ServiceExceptionHolder.ResourceNotFoundException("No data found from "+ organogramUrl);
//                    }
//
//                    String officeName = officeResponseBodyDTO.get(0).getNameBn();
//
//                    List<OfficeUnitDTO> officeUnitResponseBodyDTO = null;
//
//                    oidRequestBodyDTO.setOid(requestDTO.getOfficeUnitOidList().get(0));
//                    organogramRequestDTO.setBody(oidRequestBodyDTO);
//
//                    try {
//                        officeUnitResponseBodyDTO =
//                                webService.getRestTemplateResponse(organogramUrl + Constant.ENDPOINT_OFFICE_UNIT_V1_GET_BY_OID, OfficeUnitDTO.class, organogramRequestDTO);
//                    } catch (Exception e) {
//                        log.error(e.getMessage(), e);
//                    }
//
//                    if (officeUnitResponseBodyDTO== null ||
//                            officeUnitResponseBodyDTO.isEmpty()) {
//                        throw new ServiceExceptionHolder.ResourceNotFoundException("No data found from "+ organogramUrl);
//                    }
//
//                    String officeUnitName = officeResponseBodyDTO.get(0).getNameBn();
//
//                    throw new ServiceExceptionHolder.ResourceNotFoundException(
//                            "এখানে কোনো স্বপদ পাওয়া যায় নি: " + officeUnitName + ", " + officeName
//                    );
//
//                } else {
//                    OidRequestBodyDTO oidRequestBodyDTO = new OidRequestBodyDTO();
//                    oidRequestBodyDTO.setOid(requestDTO.getOfficeOidList().get(0));
//
//                    List<OfficeDTO> officeResponseBodyDTO = null;
//
//                    OrganogramRequestDTO<OidRequestBodyDTO> organogramRequestDTO = new OrganogramRequestDTO<>();
//                    organogramRequestDTO.setHeader(headerUtilComponent.getRequestHeaderDTO());
//                    organogramRequestDTO.setBody(oidRequestBodyDTO);
//
//                    try {
//                        officeResponseBodyDTO =
//                                webService.getRestTemplateResponse(organogramUrl + Constant.ENDPOINT_OFFICE_V1_GET_BY_OID, OfficeDTO.class, organogramRequestDTO);
//                    } catch (Exception e) {
//                        log.error(e.getMessage(), e);
//                    }
//
//                    if (officeResponseBodyDTO== null ||
//                            officeResponseBodyDTO.isEmpty()) {
//                        throw new ServiceExceptionHolder.ResourceNotFoundException("No data found from "+ organogramUrl);
//                    }
//
//                    String officeName = officeResponseBodyDTO.get(0).getNameBn();
//
//                    throw new ServiceExceptionHolder.ResourceNotFoundException(
//                            "এখানে কোনো স্বপদ পাওয়া যায় নি: " + officeName
//                    );
//                }
//            } else {
//
//                String name = improperEmployeeInformationDTOS.get(0).getNameBn();
//                String officeName = improperEmployeeInformationDTOS.get(0).getOfficeNameBn();
//                String officeUnitName = improperEmployeeInformationDTOS.get(0).getOfficeUnitNameBn();
//                String officeUnitPostName = improperEmployeeInformationDTOS.get(0).getOfficeUnitPostNameBn();
//
//                throw new ServiceExceptionHolder.ResourceNotFoundException(
//                        "ইনার কোনো স্বপদ পাওয়া যায়নি: " + name + " (" + officeUnitPostName + ", " + officeUnitName + ", " + officeName + ")"
//                );
//            }
//
//        }

        setMissingData(employeeInformationDTOS);

        return employeeInformationDTOS;
    }

    public List<EmployeeInformationDTO> getEmployeeInformationDTOByOffice(GetListByOidSetRequestBodyDTO requestDTO){

        if (requestDTO.getOids().isEmpty()) return new ArrayList<>();

        MiscellaneousRequestProperty miscellaneousRequestProperty = new MiscellaneousRequestProperty();
        miscellaneousRequestProperty.setOfficeOidList(requestDTO.getOids());


        String gDataEndPointUrl = getGData()+Constant.GDATA_GET+Constant.VERSION_1 + Constant.GDATA_EMPLOYEE_OFFICE_BY_OFFICE;;

        DataServiceRequest<EmployeeOfficeMasterDTO> requestEmployee = employeeService.getEmployeeOfficeMasterDTORequestBuildingComponent().getRequestForRead(getNodePath(), null, null,
                null, null, null, null,
                null, null, null, EmployeeOfficeMasterDTO.class);

        DataServiceRequestBody dataServiceRequestBody = requestEmployee.getBody();
        dataServiceRequestBody.setMiscellaneousRequestProperty(miscellaneousRequestProperty);

        List<EmployeeOfficeMasterDTO> employeeOfficeMasterDTOList = employeeService.getRestTemplateEmployeedetailsMasterInfo().getListData(getNodePath(), requestEmployee, gDataEndPointUrl);

        // ========= get name from cmn service organogram =========================

        OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO officeOfficeUnitOfficeUnitPostSetRequestBodyDTO = new OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO();

        List<String> officeOids = new ArrayList<>();
        List<String> officeUnitOids = new ArrayList<>();
        List<String> officeUnitPostOids = new ArrayList<>();

        employeeOfficeMasterDTOList
                .forEach(employeeOfficeMasterDTO -> {
                    if (employeeOfficeMasterDTO.getOfficeOid() != null) {
                        if (!employeeOfficeMasterDTO.getOfficeOid().equals(""))
                            officeOids.add(employeeOfficeMasterDTO.getOfficeOid());
                    }
                    if (employeeOfficeMasterDTO.getOfficeUnitOid() != null) {
                        if (!employeeOfficeMasterDTO.getOfficeUnitOid().equals(""))
                            officeUnitOids.add(employeeOfficeMasterDTO.getOfficeUnitOid());
                    }
                    if (employeeOfficeMasterDTO.getOfficeUnitPostOid() != null) {
                        if (!employeeOfficeMasterDTO.getOfficeUnitPostOid().equals(""))
                            officeUnitPostOids.add(employeeOfficeMasterDTO.getOfficeUnitPostOid());
                    }
                });

        officeOfficeUnitOfficeUnitPostSetRequestBodyDTO.setOfficeOids(officeOids);
        officeOfficeUnitOfficeUnitPostSetRequestBodyDTO.setOfficeUnitOids(officeUnitOids);
        officeOfficeUnitOfficeUnitPostSetRequestBodyDTO.setOfficeUnitPostOids(officeUnitPostOids);

        List<OfficeOfficeUnitOfficeUnitPostSetResponseBodyDTO> officeOfficeUnitOfficeUnitPostSetResponseBodyDTO;

        OrganogramRequestDTO<OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO> organogramRequestDTO = new OrganogramRequestDTO<>();
        organogramRequestDTO.setHeader(headerUtilComponent.getRequestHeaderDTO());
        organogramRequestDTO.setBody(officeOfficeUnitOfficeUnitPostSetRequestBodyDTO);
        organogramRequestDTO.setMeta(new HashMap<>());

        try {
            officeOfficeUnitOfficeUnitPostSetResponseBodyDTO =
                    webService.getRestTemplateResponse(organogramUrl + Constant.ENDPOINT_SEARCH_V1_GET_DETAILS, OfficeOfficeUnitOfficeUnitPostSetResponseBodyDTO.class, organogramRequestDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

        if (officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.isEmpty()) {
            throw new ServiceExceptionHolder.ResourceNotFoundException("No data found from "+ organogramUrl);
        }

        List<EmployeeInformationDTO> employeeInformationDTOS = conversionComponent.convertEmpDetailsMasterDTOToEmpInfo(employeeOfficeMasterDTOList, officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.get(0));

        return employeeInformationDTOS;

    }

    public List<EmployeeInformationDTO> getEmployeeInformationDTOByOfficeByEmployeeType(GetListByOidSetRequestBodyDTO requestDTO, String employeeType){

        if (requestDTO.getOids().isEmpty()) return new ArrayList<>();

        MiscellaneousRequestProperty miscellaneousRequestProperty = new MiscellaneousRequestProperty();
        miscellaneousRequestProperty.setOfficeOidList(requestDTO.getOids());


        String gDataUrlSuffix = null;

        if (employeeType.equals(Constant.ADMIN)) {
            gDataUrlSuffix = Constant.GDATA_ADMIN_BY_OFFICE;
        } else if (employeeType.equals(Constant.APPROVER)) {
            gDataUrlSuffix = Constant.GDATA_APPROVER_BY_OFFICE;
        } else if (employeeType.equals(Constant.REVIEWER)) {
            gDataUrlSuffix = Constant.GDATA_REVIEWER_BY_OFFICE;
        }

        String gDataEndPointUrl = getGData()+Constant.GDATA_GET+Constant.VERSION_1 + gDataUrlSuffix;;

        DataServiceRequest<EmployeeOfficeMasterDTO> requestEmployee = employeeService.getEmployeeOfficeMasterDTORequestBuildingComponent().getRequestForRead(getNodePath(), null, null,
                null, null, null, null,
                null, null, null, EmployeeOfficeMasterDTO.class);

        DataServiceRequestBody dataServiceRequestBody = requestEmployee.getBody();
        dataServiceRequestBody.setMiscellaneousRequestProperty(miscellaneousRequestProperty);

        List<EmployeeOfficeMasterDTO> employeeOfficeMasterDTOList = employeeService.getRestTemplateEmployeedetailsMasterInfo().getListData(getNodePath(), requestEmployee, gDataEndPointUrl);

        // ========= get name from cmn service organogram =========================

        OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO officeOfficeUnitOfficeUnitPostSetRequestBodyDTO = new OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO();

        List<String> officeOids = new ArrayList<>();
        List<String> officeUnitOids = new ArrayList<>();
        List<String> officeUnitPostOids = new ArrayList<>();

        employeeOfficeMasterDTOList
                .forEach(employeeOfficeMasterDTO -> {
                    if (employeeOfficeMasterDTO.getOfficeOid() != null) {
                        if (!employeeOfficeMasterDTO.getOfficeOid().equals(""))
                            officeOids.add(employeeOfficeMasterDTO.getOfficeOid());
                    }
                    if (employeeOfficeMasterDTO.getOfficeUnitOid() != null) {
                        if (!employeeOfficeMasterDTO.getOfficeUnitOid().equals(""))
                            officeUnitOids.add(employeeOfficeMasterDTO.getOfficeUnitOid());
                    }
                    if (employeeOfficeMasterDTO.getOfficeUnitPostOid() != null) {
                        if (!employeeOfficeMasterDTO.getOfficeUnitPostOid().equals(""))
                            officeUnitPostOids.add(employeeOfficeMasterDTO.getOfficeUnitPostOid());
                    }
                });

        officeOfficeUnitOfficeUnitPostSetRequestBodyDTO.setOfficeOids(officeOids);
        officeOfficeUnitOfficeUnitPostSetRequestBodyDTO.setOfficeUnitOids(officeUnitOids);
        officeOfficeUnitOfficeUnitPostSetRequestBodyDTO.setOfficeUnitPostOids(officeUnitPostOids);

        List<OfficeOfficeUnitOfficeUnitPostSetResponseBodyDTO> officeOfficeUnitOfficeUnitPostSetResponseBodyDTO;

        OrganogramRequestDTO<OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO> organogramRequestDTO = new OrganogramRequestDTO<>();
        organogramRequestDTO.setHeader(headerUtilComponent.getRequestHeaderDTO());
        organogramRequestDTO.setBody(officeOfficeUnitOfficeUnitPostSetRequestBodyDTO);
        organogramRequestDTO.setMeta(new HashMap<>());

        try {
            officeOfficeUnitOfficeUnitPostSetResponseBodyDTO =
                    webService.getRestTemplateResponse(organogramUrl + Constant.ENDPOINT_SEARCH_V1_GET_DETAILS, OfficeOfficeUnitOfficeUnitPostSetResponseBodyDTO.class, organogramRequestDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

        if (officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.isEmpty()) {
            throw new ServiceExceptionHolder.ResourceNotFoundException("No data found from "+ organogramUrl);
        }

        List<EmployeeInformationDTO> employeeInformationDTOS = conversionComponent.convertEmpDetailsMasterDTOToEmpInfo(employeeOfficeMasterDTOList, officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.get(0));

        return employeeInformationDTOS;

    }

    public List<EmployeeInformationDTO> getImproperResponsibilityType(GetListByOidSetRequestBodyDTO requestDTO){

        if (requestDTO.getOids().isEmpty()) return new ArrayList<>();

        MiscellaneousRequestProperty miscellaneousRequestProperty = new MiscellaneousRequestProperty();
        miscellaneousRequestProperty.setOfficeOidList(requestDTO.getOids());


        String gDataEndPointUrl = getGData()+Constant.GDATA_GET+Constant.VERSION_1 + Constant.GDATA_IMPROPER_RESPONSIBILITY_TYPE;;

        DataServiceRequest<EmployeeOfficeMasterDTO> requestEmployee = employeeService.getEmployeeOfficeMasterDTORequestBuildingComponent().getRequestForRead(getNodePath(), null, null,
                null, null, null, null,
                null, null, null, EmployeeOfficeMasterDTO.class);

        DataServiceRequestBody dataServiceRequestBody = requestEmployee.getBody();
        dataServiceRequestBody.setMiscellaneousRequestProperty(miscellaneousRequestProperty);

        List<EmployeeOfficeMasterDTO> employeeOfficeMasterDTOList = employeeService.getRestTemplateEmployeedetailsMasterInfo().getListData(getNodePath(), requestEmployee, gDataEndPointUrl);

        // ========= get name from cmn service organogram =========================

        OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO officeOfficeUnitOfficeUnitPostSetRequestBodyDTO = new OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO();

        List<String> officeOids = new ArrayList<>();
        List<String> officeUnitOids = new ArrayList<>();
        List<String> officeUnitPostOids = new ArrayList<>();

        employeeOfficeMasterDTOList
                .forEach(employeeOfficeMasterDTO -> {
                    if (employeeOfficeMasterDTO.getOfficeOid() != null) {
                        if (!employeeOfficeMasterDTO.getOfficeOid().equals(""))
                            officeOids.add(employeeOfficeMasterDTO.getOfficeOid());
                    }
                    if (employeeOfficeMasterDTO.getOfficeUnitOid() != null) {
                        if (!employeeOfficeMasterDTO.getOfficeUnitOid().equals(""))
                            officeUnitOids.add(employeeOfficeMasterDTO.getOfficeUnitOid());
                    }
                    if (employeeOfficeMasterDTO.getOfficeUnitPostOid() != null) {
                        if (!employeeOfficeMasterDTO.getOfficeUnitPostOid().equals(""))
                            officeUnitPostOids.add(employeeOfficeMasterDTO.getOfficeUnitPostOid());
                    }
                });

        officeOfficeUnitOfficeUnitPostSetRequestBodyDTO.setOfficeOids(officeOids);
        officeOfficeUnitOfficeUnitPostSetRequestBodyDTO.setOfficeUnitOids(officeUnitOids);
        officeOfficeUnitOfficeUnitPostSetRequestBodyDTO.setOfficeUnitPostOids(officeUnitPostOids);

        List<OfficeOfficeUnitOfficeUnitPostSetResponseBodyDTO> officeOfficeUnitOfficeUnitPostSetResponseBodyDTO;

        OrganogramRequestDTO<OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO> organogramRequestDTO = new OrganogramRequestDTO<>();
        organogramRequestDTO.setHeader(headerUtilComponent.getRequestHeaderDTO());
        organogramRequestDTO.setBody(officeOfficeUnitOfficeUnitPostSetRequestBodyDTO);
        organogramRequestDTO.setMeta(new HashMap<>());

        try {
            officeOfficeUnitOfficeUnitPostSetResponseBodyDTO =
                    webService.getRestTemplateResponse(organogramUrl + Constant.ENDPOINT_SEARCH_V1_GET_DETAILS, OfficeOfficeUnitOfficeUnitPostSetResponseBodyDTO.class, organogramRequestDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

        if (officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.isEmpty()) {
            throw new ServiceExceptionHolder.ResourceNotFoundException("No data found from "+ organogramUrl);
        }

        List<EmployeeInformationDTO> employeeInformationDTOS = conversionComponent.convertEmpDetailsMasterDTOToEmpInfo(employeeOfficeMasterDTOList, officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.get(0));

        return employeeInformationDTOS;

    }

    public List<EmployeeInformationDTO> getImproperResponsibilityTypeByEmployee(GetListByOidSetRequestBodyDTO requestDTO){

        if (requestDTO.getOids().isEmpty()) return new ArrayList<>();

        MiscellaneousRequestProperty miscellaneousRequestProperty = new MiscellaneousRequestProperty();
        miscellaneousRequestProperty.setEmployeeOidList(requestDTO.getOids());


        String gDataEndPointUrl = getGData()+Constant.GDATA_GET+Constant.VERSION_1 + Constant.GDATA_IMPROPER_RESPONSIBILITY_TYPE_BY_EMPLOYEE;;

        DataServiceRequest<EmployeeOfficeMasterDTO> requestEmployee = employeeService.getEmployeeOfficeMasterDTORequestBuildingComponent().getRequestForRead(getNodePath(), null, null,
                null, null, null, null,
                null, null, null, EmployeeOfficeMasterDTO.class);

        DataServiceRequestBody dataServiceRequestBody = requestEmployee.getBody();
        dataServiceRequestBody.setMiscellaneousRequestProperty(miscellaneousRequestProperty);

        List<EmployeeOfficeMasterDTO> employeeOfficeMasterDTOList = employeeService.getRestTemplateEmployeedetailsMasterInfo().getListData(getNodePath(), requestEmployee, gDataEndPointUrl);

        // ========= get name from cmn service organogram =========================

        OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO officeOfficeUnitOfficeUnitPostSetRequestBodyDTO = new OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO();

        List<String> officeOids = new ArrayList<>();
        List<String> officeUnitOids = new ArrayList<>();
        List<String> officeUnitPostOids = new ArrayList<>();

        employeeOfficeMasterDTOList
                .forEach(employeeOfficeMasterDTO -> {
                    if (employeeOfficeMasterDTO.getOfficeOid() != null) {
                        if (!employeeOfficeMasterDTO.getOfficeOid().equals(""))
                            officeOids.add(employeeOfficeMasterDTO.getOfficeOid());
                    }
                    if (employeeOfficeMasterDTO.getOfficeUnitOid() != null) {
                        if (!employeeOfficeMasterDTO.getOfficeUnitOid().equals(""))
                            officeUnitOids.add(employeeOfficeMasterDTO.getOfficeUnitOid());
                    }
                    if (employeeOfficeMasterDTO.getOfficeUnitPostOid() != null) {
                        if (!employeeOfficeMasterDTO.getOfficeUnitPostOid().equals(""))
                            officeUnitPostOids.add(employeeOfficeMasterDTO.getOfficeUnitPostOid());
                    }
                });

        officeOfficeUnitOfficeUnitPostSetRequestBodyDTO.setOfficeOids(officeOids);
        officeOfficeUnitOfficeUnitPostSetRequestBodyDTO.setOfficeUnitOids(officeUnitOids);
        officeOfficeUnitOfficeUnitPostSetRequestBodyDTO.setOfficeUnitPostOids(officeUnitPostOids);

        List<OfficeOfficeUnitOfficeUnitPostSetResponseBodyDTO> officeOfficeUnitOfficeUnitPostSetResponseBodyDTO;

        OrganogramRequestDTO<OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO> organogramRequestDTO = new OrganogramRequestDTO<>();
        organogramRequestDTO.setHeader(headerUtilComponent.getRequestHeaderDTO());
        organogramRequestDTO.setBody(officeOfficeUnitOfficeUnitPostSetRequestBodyDTO);
        organogramRequestDTO.setMeta(new HashMap<>());

        try {
            officeOfficeUnitOfficeUnitPostSetResponseBodyDTO =
                    webService.getRestTemplateResponse(organogramUrl + Constant.ENDPOINT_SEARCH_V1_GET_DETAILS, OfficeOfficeUnitOfficeUnitPostSetResponseBodyDTO.class, organogramRequestDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }

        if (officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.isEmpty()) {
            throw new ServiceExceptionHolder.ResourceNotFoundException("No data found from "+ organogramUrl);
        }

        List<EmployeeInformationDTO> employeeInformationDTOS = conversionComponent.convertEmpDetailsMasterDTOToEmpInfo(employeeOfficeMasterDTOList, officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.get(0));

        return employeeInformationDTOS;

    }

    public EmployeeOfficeDTO appendEmployeeOfficeDTO(EmployeeOfficeDTO employeeOfficeDTO,String employeeOid){
        DataServiceRequest<EmployeeOfficeDTO> request = getRequestBuildingComponent().getRequestForEmployeeOffice(employeeOfficeDTO,employeeOid);

        DataServiceResponse<EmployeeOfficeDTO> response = dataServiceClient.appendEmployeeOffice(request);

        return response.getBody().getMain();

    }

    public EmployeeDTO importEmployee(EmployeeCreationDTO dto){
        DataServiceRequest<EmployeeCreationDTO> request = getRequestBuildingComponent().getRequestForImport(dto,dto.getOid());

        DataServiceResponse<EmployeeDTO> response = dataServiceClient.importEmployee(request);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setOid(response.getBody().getOid());
        return employeeDTO;
    }


    public EmployeeDTO importEmployeeByEmployeeOid(RequestModel<OidRequestBodyDTO> requestDTO){

        String employeeOid = requestDTO.getBody().getData().get(0).getOid();

        DataServiceRequest<EmployeeCreationDTO> request;

        DataServiceResponse<EmployeeDTO> response = new DataServiceResponse<>();

        EmployeeCreationDTO employeeCreationDTO = employeeComponent.getEmployeeCreationDTO(requestDTO);

        ServiceRequestDTO<OidRequestBodyDTO> hrmPimRequest = employeeComponent.getRequestForHrmPim(requestDTO);

        ResponseModel<EmployeeOfficeDTO> employeeOfficeResponse = hrmPimClient.getEmployeeOfficeByEmployeeOid(hrmPimRequest);

        List<EmployeeOfficeDTO> employeeOfficeDTOS = employeeOfficeResponse.getBody().getData();

        for(EmployeeOfficeDTO employeeOfficeDTO:employeeOfficeDTOS){
            if (employeeOfficeDTO.getResponsibilityType().equalsIgnoreCase("Not Assigned")){
                employeeOfficeDTOS.remove(employeeOfficeDTO);
                break;
            }

        }

        if(employeeOfficeDTOS.size() > 0){
            EmployeeOfficeDTO employeeOfficeDTO = employeeOfficeDTOS.get(0);

            BeanUtils.copyProperties(employeeOfficeDTO,employeeCreationDTO);

            employeeCreationDTO.setOid(employeeOid);
            employeeCreationDTO.setEmployeeOfficeOid(employeeOfficeDTO.getOid());

            request = getRequestBuildingComponent().getRequestForImport(employeeCreationDTO,employeeCreationDTO.getOid());

            response = dataServiceClient.importEmployee(request);

        }else {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setOid(null);
            return employeeDTO;
        }

        DataServiceRequest<EmployeeOfficeDTO> appendRequest;
        DataServiceResponse<EmployeeOfficeDTO> appendResponse;

        for(int i = 1; i < employeeOfficeDTOS.size();i++){

            appendRequest = getRequestBuildingComponent().getRequestForEmployeeOffice(employeeOfficeDTOS.get(i),employeeOid);

            appendResponse = dataServiceClient.appendEmployeeOffice(appendRequest);
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setOid(response.getBody().getOid());
        return employeeDTO;


    }



    public List<EmployeeInformationDTO> getProfileInfo(String employeeOid) {
        List<EmployeeInformationDTO> profiles;
        GetListByOidSetRequestBodyDTO dto = new GetListByOidSetRequestBodyDTO();
        dto.setOids(Arrays.asList(employeeOid));
        profiles = getEmployeeInformationDTO(dto);
        if(profiles!=null && profiles.isEmpty()==false) {
            setMissingData(profiles);
            EmployeeInformationDTO empDTO = profiles.get(0);
            setProfilePhotoInfos(empDTO);
            for(EmployeeInformationDTO p : profiles) {
                p.setPhoto(empDTO.getPhoto());
                p.setPhotoFileDTOs(empDTO.getPhotoFileDTOs());
            }
        }
        return profiles;
    }

    private void setProfilePhotoInfos(EmployeeInformationDTO p) {
        List<FileDTO> fileDTOs = fileService.getList(p.getOid());
        List<FileDTO> photos = new ArrayList<>();
        byte[] photoFile = null;
        fileDTOs
                .forEach(fileDTO -> {
                    if (fileDTO.getFileName() != null) {
                        if ("photo".equalsIgnoreCase(fileDTO.getFileName())) {
                            photos.add(fileDTO);
                        }
                    }
                });
//        List<FileDTO> photos = fileDTOs.stream().filter(x -> "photo".equalsIgnoreCase(x.getFileName().trim())).collect(Collectors.toList());
        if(photos.isEmpty()) {
            p.setPhoto(null);
            p.setPhotoFileDTOs(null);
            return;
        }
        p.setPhotoFileDTOs(photos);
        MasterDTO dto = new MasterDTO();

        dto.setOid(photos.get(photos.size() - 1).getFileOid());
        try {
             photoFile = webService.postForByteArray(fileServiceUrl+ Constant.ENDPOINT_DOWNLOAD_FILE, dto);
             p.setPhoto(photoFile);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            p.setPhoto(null);
        }
    }

    public void setMissingData(List<EmployeeInformationDTO> profiles) {
        OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO officeOfficeUnitOfficeUnitPostSetRequestBodyDTO = new OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO();
        officeOfficeUnitOfficeUnitPostSetRequestBodyDTO.setOfficeOids(profiles.stream().map(EmployeeInformationDTO::getOfficeOid).collect(Collectors.toList()));
        officeOfficeUnitOfficeUnitPostSetRequestBodyDTO.setOfficeUnitOids(profiles.stream().map(EmployeeInformationDTO::getOfficeUnitOid).collect(Collectors.toList()));
        officeOfficeUnitOfficeUnitPostSetRequestBodyDTO.setOfficeUnitPostOids(profiles.stream().map(EmployeeInformationDTO::getOfficeUnitPostOid).collect(Collectors.toList()));

        List<OfficeOfficeUnitOfficeUnitPostSetResponseBodyDTO> officeOfficeUnitOfficeUnitPostSetResponseBodyDTO = null;

        OrganogramRequestDTO<OfficeOfficeUnitOfficeUnitPostSetRequestBodyDTO> organogramRequestDTO = new OrganogramRequestDTO<>();
        organogramRequestDTO.setHeader(headerUtilComponent.getRequestHeaderDTO());
        organogramRequestDTO.setBody(officeOfficeUnitOfficeUnitPostSetRequestBodyDTO);
        organogramRequestDTO.setMeta(new HashMap<>());

        try {
            officeOfficeUnitOfficeUnitPostSetResponseBodyDTO =
                    webService.getRestTemplateResponse(organogramUrl + Constant.ENDPOINT_SEARCH_V1_GET_DETAILS, OfficeOfficeUnitOfficeUnitPostSetResponseBodyDTO.class, organogramRequestDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        if (officeOfficeUnitOfficeUnitPostSetResponseBodyDTO== null ||
                officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.isEmpty()) {
            throw new ServiceExceptionHolder.ResourceNotFoundException("No data found from "+ organogramUrl);
        }

        conversionComponent.mapProfileData(officeOfficeUnitOfficeUnitPostSetResponseBodyDTO.get(0), profiles);
    }

    public List<EmptyBodyDTO> setTimeZone(OidRequestBodyDTO oidRequestBodyDTO) {

        TimeZone.setDefault(TimeZone.getTimeZone(oidRequestBodyDTO.getOid()));

        return new ArrayList<>();
    }

    public List<EmptyBodyDTO> getTimeZone(EmptyBodyDTO emptyBodyDTO) {

        return new ArrayList<>();

    }

    public List<EmptyBodyDTO> setNotificationEnabled(OidRequestBodyDTO oidRequestBodyDTO) {

        switchService.setNotificationEnabled(oidRequestBodyDTO.getOid());

        return new ArrayList<>();
    }

    public List<EmptyBodyDTO> setEmailEnabled(OidRequestBodyDTO oidRequestBodyDTO) {

        switchService.setEmailEnabled(oidRequestBodyDTO.getOid());

        return new ArrayList<>();
    }


    @Override
    public Class getDtoClass() {
        return EmployeeDTO.class;
    }

    @Override
    public Class getEntityClass() {return Employee.class;}

    public List<EmployeeOfficeDTO> convertPmisEmployeeOfficeListToEmployeeOfficeList(List<EmployeeOfficeV2DTO> dtoList) {
		List<EmployeeOfficeDTO> resultList = new ArrayList<EmployeeOfficeDTO>();
		for (EmployeeOfficeV2DTO nodeDTO : dtoList) {
        	EmployeeOfficeDTO officeDTO = new EmployeeOfficeDTO();
        	officeDTO.setOid(nodeDTO.getEmployeeOfficeOid());
        	officeDTO.setEmploymentTypeOid(nodeDTO.getEmploymentTypeOid());
        	officeDTO.setIsApprover(nodeDTO.getIsApprover());
        	officeDTO.setIsOfficeAdmin(nodeDTO.getIsOfficeAdmin());
        	officeDTO.setIsOfficeHead(nodeDTO.getIsOfficeHead());
        	officeDTO.setIsReviewer(nodeDTO.getIsReviewer());
            officeDTO.setJoiningDate(nodeDTO.getJoiningDate());
            officeDTO.setOfficeOid(nodeDTO.getOfficeOid());
            officeDTO.setOfficeUnitOid(nodeDTO.getOfficeUnitOid());
            officeDTO.setOfficeUnitPostOid(nodeDTO.getOfficeUnitPostOid());
            officeDTO.setStatus(nodeDTO.getStatus());
            officeDTO.setIsOfficeUnitHead(nodeDTO.getIsOfficeUnitHead());
            officeDTO.setResponsibilityType(nodeDTO.getResponsibilityType());
            officeDTO.setIsAttendanceDataEntryOperator(nodeDTO.getIsAttendanceDataEntryOperator());
            officeDTO.setIsAttendanceAdmin(nodeDTO.getIsAttendanceAdmin());
            officeDTO.setIsAwardAdmin(nodeDTO.getIsAwardAdmin());
            officeDTO.setNodeOid(nodeDTO.getNodeOid());
            officeDTO.setDataStatus(nodeDTO.getDataStatus());
            officeDTO.setRowStatus(nodeDTO.getRowStatus());
            officeDTO.setCreatedBy(nodeDTO.getCreatedBy());
            officeDTO.setUpdatedBy(nodeDTO.getUpdatedBy());
            officeDTO.setCreatedOn(nodeDTO.getCreatedOn()==null?null:new Timestamp(nodeDTO.getCreatedOn().getTime()));
            officeDTO.setUpdatedOn(nodeDTO.getUpdatedOn()==null?null:new Timestamp(nodeDTO.getUpdatedOn().getTime()));
            officeDTO.setInchargeLabelBn(nodeDTO.getInchargeLabelBn());
            officeDTO.setInchargeLabelEn(nodeDTO.getInchargeLabelEn());
            officeDTO.setLastOfficeDate(nodeDTO.getLastOfficeDate());
            resultList.add(officeDTO);
		}
		
		return resultList;
	}
    
}
