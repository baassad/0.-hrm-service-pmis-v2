package com.cokreates.grp.util.webclient;


import com.cokreates.core.BaseEntity;
import com.cokreates.core.Constant;
import com.cokreates.core.MasterDTO;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.daas.DataServiceResponseBody;
import com.cokreates.grp.util.components.HeaderUtilComponent;
import com.cokreates.grp.util.exceptions.ServiceExceptionHolder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;


// TODO: Take BaseService Logic to Component with Overridden methods as interface calls
@Slf4j
@AllArgsConstructor
@Service
public class DataServiceRestTemplateClient<D extends MasterDTO, E extends BaseEntity> {

    @Autowired
    private final HeaderUtilComponent headerUtilComponent;

    @Autowired
    private final ModelMapper modelMapper;

//    @Value("${spring.application.zuul_url}")
//    String ZUUL_BASE_URL;
//
//    @Value("${spring.application.cmn_app_name}")
//    String CMN_ORGANOGRAM_URL;


    @Autowired
    private HttpHeaders headers;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    HttpServletRequest request;

    public D getRestTemplateResponseForEmployee(DataServiceRequest<D> requestBody, String gDataEndPointUrl) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            log.debug("==== gDataEndPointUrl ==== " + gDataEndPointUrl);
            ResponseEntity<String> response = restTemplate.exchange(gDataEndPointUrl, HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode mainJson = jsonNode.get("body");

            D main = objectMapper.treeToValue(mainJson, requestBody.getBody().getDtoClass());

            return main;

        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
                throw new ServiceExceptionHolder.ResourceNotFoundException(ex.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
        }

    }

    //Can provide data not Node or Node in list
    public D getRestTemplateResponse(List<String> nodePath, DataServiceRequest<D> requestBody, String gDataEndPointUrl) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            log.debug("==== gDataEndPointUrl ==== " + gDataEndPointUrl);
            ResponseEntity<String> response = restTemplate.exchange(gDataEndPointUrl, HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode mainJson = jsonNode.get("body").get("main");
            JsonNode tempJson = jsonNode.get("body").get("temp");

            D main = objectMapper.treeToValue(mainJson, requestBody.getBody().getDtoClass());
            D temp = objectMapper.treeToValue(tempJson, requestBody.getBody().getDtoClass());
            /*
            if (null != requestBody.getBody().getEmployeeOid()) {
				main.setOid(requestBody.getBody().getEmployeeOid());
			}
            if (null != requestBody.getBody().getNodeOid()) {
				main.setNodeOid(requestBody.getBody().getNodeOid());
			}
			*/
            main.setTemp(temp);
            return main;

        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
                throw new ServiceExceptionHolder.ResourceNotFoundException(ex.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
        }

    }

    public List<D> getRestTemplateResponseList(List<String> nodePath, DataServiceRequest<D> requestBody, String gDataEndPointUrl) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            log.debug("==== gDataEndPointUrl ==== " + gDataEndPointUrl);
            ResponseEntity<String> response = restTemplate.exchange(gDataEndPointUrl, HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode mainJson = jsonNode.get("body").get("main");
            JsonNode tempJson = jsonNode.get("body").get("temp");

            List<D> mainList = new ArrayList<>();
            List<D> tempList = new ArrayList<>();


            mainList = objectMapper.readValue(
                    mainJson.toString(),
                    objectMapper.getTypeFactory().constructCollectionType(
                            List.class, requestBody.getBody().getDtoClass()));


            tempList = objectMapper.readValue(
                    tempJson.toString(),
                    objectMapper.getTypeFactory().constructCollectionType(
                            List.class, requestBody.getBody().getDtoClass()));

            HashMap<String, Integer> mainMap = new HashMap<>();

            int mainListSize = mainList.size();
            int tempListSize = tempList.size();

            for (int i = 0; i < mainListSize; i++) {
                mainMap.put(mainList.get(i).getOid(), i);
            }

            for (int i = 0; i < tempListSize; i++) {
                D temp = tempList.get(i);
                if (mainMap.containsKey(temp.getOid())) {
                    int mainListIndex = mainMap.get(temp.getOid());
                    D main = mainList.get(mainListIndex);
                    main.setTemp(temp);
                    mainList.set(mainListIndex, main);
                } else {
                    Gson gson = new Gson();
                    String mainString = gson.toJson(new MasterDTO());
                    D main = (D)gson.fromJson(mainString, requestBody.getBody().getDtoClass());
                    main.setTemp(temp);
                    mainList.add(main);
                }
            }

            return mainList;

        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
                throw new ServiceExceptionHolder.ResourceNotFoundException(ex.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
        }

    }

    public D update(List<String> nodePath, DataServiceRequest<D> requestBody, String gDataUrl) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            log.debug("==== gDataEndPointUrl ==== " + gDataUrl);

            ResponseEntity<String> response = restTemplate.exchange(gDataUrl, HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);

            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode mainJson = jsonNode.get("body");

            D main = objectMapper.treeToValue(mainJson, requestBody.getBody().getDtoClass());

            return main;

        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
                throw new ServiceExceptionHolder.ResourceNotFoundException(ex.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
        }

    }

    public Set<String> getListStringData(List<String> nodePath, DataServiceRequest<D> requestBody, String gDataUrl) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            log.debug("==== gDataEndPointUrl ==== " + gDataUrl);

            ResponseEntity<String> response = restTemplate.exchange(gDataUrl, HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);

            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode listJson = jsonNode.get("body");

            listJson = jsonNode.get("body").get("data");

            Set<String> approvalDTOS = new HashSet<>();

            approvalDTOS = objectMapper.readValue(
                    listJson.toString(),
                    objectMapper.getTypeFactory().constructCollectionType(
                            Set.class, String.class));
            return approvalDTOS;

        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
                throw new ServiceExceptionHolder.ResourceNotFoundException(ex.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
        }

    }

    public List<D> getListData(List<String> nodePath, DataServiceRequest<D> requestBody, String gDataUrl) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            log.debug("==== gDataEndPointUrl ==== " + gDataUrl);

            ResponseEntity<String> response = restTemplate.exchange(gDataUrl, HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);

            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode listJson = jsonNode.get("body");

            listJson = jsonNode.get("body").get("data");

            List<D> approvalDTOS = new ArrayList<>();

            approvalDTOS = objectMapper.readValue(
                    listJson.toString(),
                    objectMapper.getTypeFactory().constructCollectionType(
                            List.class, requestBody.getBody().getDtoClass()));
            return approvalDTOS;

        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
                throw new ServiceExceptionHolder.ResourceNotFoundException(ex.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
        }

    }

    public D updateApprovalHistory(List<String> nodePath, DataServiceRequest<D> requestBody, String gDataUrl) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));

            if (requestBody.getBody().getStatus().equals("REQUESTED")) {
                gDataUrl = gDataUrl + Constant.GDATA_APPROVAL_HISTORY_REQUEST;//approval-history-for-request

            } else if (requestBody.getBody().getStatus().equals("REVIEWED")) {
                gDataUrl = gDataUrl + Constant.GDATA_APPROVAL_HISTORY_REVIEW;//approval-history-for-review

            } else if (requestBody.getBody().getStatus().equals("APPROVED")) {
                gDataUrl = gDataUrl + Constant.GDATA_APPROVAL_HISTORY_APPROVE;//approval-history-for-approve

            } else if (requestBody.getBody().getStatus().equals("REJECTED")) {
                gDataUrl = gDataUrl + Constant.GDATA_APPROVAL_HISTORY_REJECT;//approval-history-for-reject
            }
            log.debug("==== gDataEndPointUrl ==== " + gDataUrl);
            ResponseEntity<String> response = restTemplate.exchange(gDataUrl, HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);

            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode mainJson = jsonNode.get("body");

            D main = objectMapper.treeToValue(mainJson, requestBody.getBody().getDtoClass());
            return main;

        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
                throw new ServiceExceptionHolder.ResourceNotFoundException(ex.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceExceptionHolder.ResourceNotFoundException(e.getMessage());
        }

    }

}
