package com.cokreates.grp.util.webclient;


import com.cokreates.core.BaseEntity;
import com.cokreates.grp.util.components.HeaderUtilComponent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


// TODO: Take BaseService Logic to Component with Overridden methods as interface calls
@Data
@RequiredArgsConstructor
public abstract class CommonOrganogramBaseService<E extends BaseEntity, D extends IOidHolderRequestBodyDTO> {

    private final HeaderUtilComponent headerUtilComponent;
    private final ModelMapper modelMapper;

    @Value("${spring.application.zuul_url}")
    String ZUUL_BASE_URL;

    @Value("${spring.application.cmn_app_name}")
    String CMN_ORGANOGRAM_URL;


    @Autowired
    private HttpHeaders headers;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    HttpServletRequest request;


    public E getByOid(String oid) {
        ServiceRequestDTO<OidHolderRequestBodyDTO> requestDTO = generateOidHolderRequestDTO(oid);
        String apiEndpoint = GET_BY_OID_PATH;
        String apiEndUrl = getApiPrefix() + apiEndpoint;
        return findByOidAndIsDeleted(apiEndUrl, requestDTO, getDtoClass());
    }

    public List<E> getListByIdSet(Set<String> oids) {
        ServiceRequestDTO<GetListByOidSetRequestBodyDTO> requestDTO = generateGetListByOidSetRequestBodyDTO(oids);
        String apiEndpoint = GET_LIST_BY_ID_SET_PATH;
        String apiEndUrl = getApiPrefix() + apiEndpoint;
        return findByOidSetAndIsDeleted(apiEndUrl, requestDTO, getDtoClass());
    }

    public List<E> getList() {
        ServiceRequestDTO<EmptyBodyDTO> requestDTO = generateEmptyBodyRequestDTO();
        String apiEndpoint = GET_LIST_PATH;
        String apiEndUrl = getApiPrefix() + apiEndpoint;
        return findByIsDeleted(apiEndUrl, requestDTO, getDtoClass());
    }

    public String getApiPrefix() {
        return COMMON_OFFICE_TO_URL_MAP.get(getName()) + VERSION_INFO;
    }

    public E findByOidAndIsDeleted(String url, @Valid ServiceRequestDTO<OidHolderRequestBodyDTO> dto, Class dtoClass) {
        List<D> list = null;
        try {
            String GLOBAL_URL = ZUUL_BASE_URL + CMN_ORGANOGRAM_URL;
            if (ZUUL_BASE_URL.contains("localhost")) {
                GLOBAL_URL = ZUUL_BASE_URL;
            }
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            ResponseEntity<String> response = restTemplate.exchange(GLOBAL_URL + url, HttpMethod.POST, new HttpEntity(dto, headers), String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            JsonNode content = jsonNode.get("body").get("data");
            list = objectMapper.readValue(
                    content.toString(),
                    objectMapper.getTypeFactory().constructCollectionType(
                            List.class, dtoClass));
        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JsonNode content = jsonNode.get("body").get("data");
            throw new ServiceExceptionHolder.ResourceNotFoundException(
                    content.get(0).asText());
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("ConnectException")) {
                throw new ServiceExceptionHolder.ResourceNotFoundException("common organogram api " +  url + " does not work at " + ZUUL_BASE_URL);
            }
        }
        if (list.isEmpty()) {
            throw new ServiceExceptionHolder.ResourceNotFoundException(
                    "No " + getEntityClass().getSimpleName() + " found with id: " + dto.getBody().getOid() + " at : " + ZUUL_BASE_URL + CMN_ORGANOGRAM_URL);
        }

        return convertForParse(list.get(0));
    }

    public List<E> findByOidSetAndIsDeleted(String url, @Valid ServiceRequestDTO<GetListByOidSetRequestBodyDTO> dto, Class dtoClass) {
        List<D> list = new ArrayList<>();
        if (dto.getBody().getOids().isEmpty()) return new ArrayList<>();
        try {
            String GLOBAL_URL = ZUUL_BASE_URL + CMN_ORGANOGRAM_URL;
            if (ZUUL_BASE_URL.contains("localhost")) {
                GLOBAL_URL = ZUUL_BASE_URL;
            }
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            ResponseEntity<String> response = restTemplate.exchange(GLOBAL_URL + url, HttpMethod.POST, new HttpEntity(dto, headers), String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            JsonNode content = jsonNode.get("body").get("data");
            list = objectMapper.readValue(
                    content.toString(),
                    objectMapper.getTypeFactory().constructCollectionType(
                            List.class, dtoClass));
        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JsonNode content = jsonNode.get("body").get("data");
            throw new ServiceExceptionHolder.ResourceNotFoundException(
                    content.get(0).asText());
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("ConnectException")) {
                throw new ServiceExceptionHolder.ResourceNotFoundException("common organogram api " +  url + " does not work at " + ZUUL_BASE_URL);
            }
        }
        return convertListForParse(list);
    }

    public List<E> findByIsDeleted(String url, @Valid ServiceRequestDTO<EmptyBodyDTO> dto, Class dtoClass) {
        List<D> list = null;
        try {
            String GLOBAL_URL = ZUUL_BASE_URL + CMN_ORGANOGRAM_URL;
//            if (ZUUL_BASE_URL.contains("localhost")) {
//                GLOBAL_URL = ZUUL_BASE_URL;
//            }
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            ResponseEntity<String> response = restTemplate.exchange(GLOBAL_URL + url, HttpMethod.POST, new HttpEntity(dto, headers), String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            JsonNode content = jsonNode.get("body").get("data");
            list = objectMapper.readValue(
                    content.toString(),
                    objectMapper.getTypeFactory().constructCollectionType(
                            List.class, dtoClass));
        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JsonNode content = jsonNode.get("body").get("data");
            throw new ServiceExceptionHolder.ResourceNotFoundException(
                    content.get(0).asText());
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("ConnectException")) {
                throw new ServiceExceptionHolder.ResourceNotFoundException("common organogram api " +  url + " does not work at " + ZUUL_BASE_URL);
            }
        }
        return convertListForParse(list);
    }

    public List<E> findByMiscellaneous(String url, @Valid ServiceRequestDTO<MultipleOidHolderRequestBodyDTO> dto, Class dtoClass) {
        List<D> list = null;
        try {
            String GLOBAL_URL = ZUUL_BASE_URL + CMN_ORGANOGRAM_URL;
            if (ZUUL_BASE_URL.contains("localhost")) {
                GLOBAL_URL = ZUUL_BASE_URL;
            }
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            ResponseEntity<String> response = restTemplate.exchange(GLOBAL_URL + url, HttpMethod.POST, new HttpEntity(dto, headers), String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            JsonNode content = jsonNode.get("body").get("data");
            list = objectMapper.readValue(
                    content.toString(),
                    objectMapper.getTypeFactory().constructCollectionType(
                            List.class, dtoClass));
        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            JsonNode content = jsonNode.get("body").get("data");
            throw new ServiceExceptionHolder.ResourceNotFoundException(
                    content.get(0).asText());
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("ConnectException")) {
                throw new ServiceExceptionHolder.ResourceNotFoundException("common organogram api " +  url + " does not work at " + ZUUL_BASE_URL);
            }
        }
        return convertListForParse(list);
    }

    protected E convertForParse(D d) {
        return modelMapper.map(d, getEntityClass());
    }

    protected List<E> convertListForParse(List<D> d) {
        return d.stream().map(new Function<D, E>() {
            @Override
            public E apply(D d1) {
                return CommonOrganogramBaseService.this.convertForParse(d1);
            }
        }).collect(Collectors.toList());
    }

    public String getName() {
        return getEntityClass().getSimpleName();
    }

    public ServiceRequestDTO<OidHolderRequestBodyDTO> generateOidHolderRequestDTO(String oid) {
        ServiceRequestDTO<OidHolderRequestBodyDTO> requestDTO = new ServiceRequestDTO<>();

        OidHolderRequestBodyDTO bodyDTO = new OidHolderRequestBodyDTO();
        bodyDTO.setOid(oid);

        requestDTO.setHeader(getHeader());
        requestDTO.setMeta(new HashMap<>());
        requestDTO.setBody(bodyDTO);
        return requestDTO;
    }

    public ServiceRequestDTO<GetListByOidSetRequestBodyDTO> generateGetListByOidSetRequestBodyDTO(Set<String> oids) {
        ServiceRequestDTO<GetListByOidSetRequestBodyDTO> requestDTO = new ServiceRequestDTO<>();

        GetListByOidSetRequestBodyDTO bodyDTO = new GetListByOidSetRequestBodyDTO();
        bodyDTO.setOids(oids);

        requestDTO.setHeader(getHeader());
        requestDTO.setMeta(new HashMap<>());
        requestDTO.setBody(bodyDTO);
        return requestDTO;
    }

    public ServiceRequestDTO<EmptyBodyDTO> generateEmptyBodyRequestDTO() {
        ServiceRequestDTO<EmptyBodyDTO> requestDTO = new ServiceRequestDTO<>();

        requestDTO.setHeader(getHeader());
        requestDTO.setMeta(new HashMap<>());
        requestDTO.setBody(new EmptyBodyDTO());
        return requestDTO;
    }

    public ServiceRequestHeaderDTO getHeader() {
        ServiceRequestHeaderDTO headerDTO = new ServiceRequestHeaderDTO();

        headerDTO.setRequestClient("grp");
        headerDTO.setRequestId("random-uuid");
        headerDTO.setRequestRetryCount(3);
        headerDTO.setRequestSource("portal");
        headerDTO.setRequestSourceService("portal");
        headerDTO.setRequestTime(new Date());
        headerDTO.setRequestTimeoutInSeconds(30);
        headerDTO.setRequestType("random");
        headerDTO.setRequestVersion("v1");

        return headerDTO;
    }

    @SuppressWarnings("unchecked")
    public Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public Class<D> getDtoClass() {
        return (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
}
