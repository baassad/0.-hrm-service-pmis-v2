package com.cokreates.grp.util.webclient;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cokreates.core.MasterApprovalDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.cokreates.core.BaseEntity;
import com.cokreates.core.Constant;
import com.cokreates.core.MasterDTO;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.util.components.HeaderUtilComponent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


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

    //Can provide data not Node or Node in list
    public D getRestTemplateResponse(List<String> nodePath, DataServiceRequest<D> requestBody, String gDataEndPointUrl) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            log.debug("==== gDataEndPointUrl ==== "+gDataEndPointUrl);
            ResponseEntity<String> response = restTemplate.exchange(gDataEndPointUrl, HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode mainJson = jsonNode.get("body").get("main");
            JsonNode tempJson = jsonNode.get("body").get("temp");

            D main = objectMapper.treeToValue(mainJson, requestBody.getBody().getDtoClass());
            D temp = objectMapper.treeToValue(tempJson, requestBody.getBody().getDtoClass());
            
            if (null != requestBody.getBody().getEmployeeOid()) {
				main.setOid(requestBody.getBody().getEmployeeOid());
			}
            if (null != requestBody.getBody().getNodeOid()) {
				main.setNodeOid(requestBody.getBody().getNodeOid());
			}
            main.setTemp(temp);
            return main;

        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("ConnectException")) {
//                throw new ServiceExceptionHolder.ResourceNotFoundException("common organogram api " +  url + " does not work at " + ZUUL_BASE_URL);
            }
        }
        return null;
    }

    public List<D> getRestTemplateResponseList(List<String> nodePath, DataServiceRequest<D> requestBody, String gDataEndPointUrl) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            log.debug("==== gDataEndPointUrl ==== "+gDataEndPointUrl);
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

            for (int i =0 ;i <mainListSize ; i++) {
                mainMap.put(mainList.get(i).getOid(), i);
            }

            for (int i =0 ;i <tempListSize ; i++) {
                D temp = tempList.get(i);
                int mainListIndex = mainMap.get(temp.getOid());
                D main = mainList.get(mainListIndex);
                main.setTemp(temp);
                mainList.set(i, main);
            }

            return mainList;

        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("ConnectException")) {
//                throw new ServiceExceptionHolder.ResourceNotFoundException("common organogram api " +  url + " does not work at " + ZUUL_BASE_URL);
            }
        }
        return null;
    }

    public List<MasterApprovalDTO> getApprovalHistory(List<String> nodePath, DataServiceRequest<MasterApprovalDTO> requestBody, String gDataUrl) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            gDataUrl = gDataUrl + Constant.GDATA_GET + Constant.VERSION_1;//approval-history-for-request
            log.debug("==== gDataEndPointUrl ==== "+gDataUrl);

            if (requestBody.getBody().getApprovalStatus() != null && requestBody.getBody().getEmployeeOid() !=null) {
                gDataUrl = gDataUrl + Constant.GDATA_APPROVAL_HISTORY_BY_EMPLOYEE_AND_STATUS;//approval-history-for-request

            } else if (requestBody.getBody().getApprovalStatus() != null) {
                gDataUrl = gDataUrl + Constant.GDATA_APPROVAL_HISTORY_BY_STATUS;//approval-history-for-review

            } else {
                gDataUrl = gDataUrl + Constant.GDATA_APPROVAL_HISTORY_BY_EMPLOYEE;//approval-history-for-approve

            }

            ResponseEntity<String> response = restTemplate.exchange(gDataUrl , HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);

        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("ConnectException")) {
//                throw new ServiceExceptionHolder.ResourceNotFoundException("common organogram api " +  url + " does not work at " + ZUUL_BASE_URL);
            }
        }
        return null;
    }

    public void updateSingleObject(List<String> nodePath, DataServiceRequest<D> requestBody, String gDataUrl) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            
            gDataUrl = gDataUrl + Constant.GDATA_NODE_REQUEST;//approval-history-for-request


            log.debug("==== gDataEndPointUrl ==== "+gDataUrl);
            ResponseEntity<String> response = restTemplate.exchange(gDataUrl , HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);
            
        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("ConnectException")) {
//                throw new ServiceExceptionHolder.ResourceNotFoundException("common organogram api " +  url + " does not work at " + ZUUL_BASE_URL);
            }
        }
    }


    public void updateApprovalHistory(List<String> nodePath, DataServiceRequest<MasterApprovalDTO> requestBody, String gDataUrl) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));

            if (requestBody.getBody().getApprovalStatus().equals("REQUESTED")) {
            	gDataUrl = gDataUrl + Constant.GDATA_APPROVAL_HISTORY_REQUEST;//approval-history-for-request

            } else if (requestBody.getBody().getApprovalStatus().equals("REVIEWED")) {
            	gDataUrl = gDataUrl + Constant.GDATA_APPROVAL_HISTORY_REVIEW;//approval-history-for-review

            } else if (requestBody.getBody().getApprovalStatus().equals("APPROVED")) {
            	gDataUrl = gDataUrl + Constant.GDATA_APPROVAL_HISTORY_APPROVE;//approval-history-for-approve

            } else if (requestBody.getBody().getApprovalStatus().equals("REJECTED")) {
            	gDataUrl = gDataUrl + Constant.GDATA_APPROVAL_HISTORY_REJECT;//approval-history-for-reject
            }
            log.debug("==== gDataEndPointUrl ==== "+gDataUrl);
            ResponseEntity<String> response = restTemplate.exchange(gDataUrl , HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);

        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("ConnectException")) {
//                throw new ServiceExceptionHolder.ResourceNotFoundException("common organogram api " +  url + " does not work at " + ZUUL_BASE_URL);
            }
        }
    }


    //TODO: no use, method can be remove
    public D getSingleObject(List<String> nodePath, DataServiceRequest<D> requestBody, String gDataEndPointUrl) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            log.debug("==== gDataEndPointUrl ==== "+gDataEndPointUrl);
            ResponseEntity<String> response = restTemplate.exchange(gDataEndPointUrl, HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode mainJson = jsonNode.get("body").get("main");
            JsonNode tempJson = jsonNode.get("body").get("temp");
            //System.out.println(mainJson);
            //System.out.println(tempJson);

            D main = objectMapper.treeToValue(mainJson, requestBody.getBody().getDtoClass());
            D temp = objectMapper.treeToValue(tempJson, requestBody.getBody().getDtoClass());
            //System.out.println("helloo " + main);
            //System.out.println(" hello " + temp);
            if (null != requestBody.getBody().getEmployeeOid()) {
				main.setOid(requestBody.getBody().getEmployeeOid());
			}
            if (null != requestBody.getBody().getNodeOid()) {
				main.setNodeOid(requestBody.getBody().getNodeOid());
			}
            
            main.setTemp(temp);
            return main;
        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("ConnectException")) {
//                throw new ServiceExceptionHolder.ResourceNotFoundException("common organogram api " +  url + " does not work at " + ZUUL_BASE_URL);
            }
        }
        return null;
    }
    //TODO: no use, method can be remove
    public D getListSingleObject(List<String> nodePath, DataServiceRequest<D> requestBody, String gDataEndPointUrl) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            log.debug("==== gDataEndPointUrl ==== "+gDataEndPointUrl);
            ResponseEntity<String> response = restTemplate.exchange(gDataEndPointUrl, HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode mainJson = jsonNode.get("body").get("main");
            JsonNode tempJson = jsonNode.get("body").get("temp");
            //System.out.println(mainJson);
            //System.out.println(tempJson);

            D main = objectMapper.treeToValue(mainJson, requestBody.getBody().getDtoClass());
            D temp = objectMapper.treeToValue(tempJson, requestBody.getBody().getDtoClass());
            //System.out.println("helloo " + main);
            //System.out.println(" hello " + temp);
            if (null != requestBody.getBody().getEmployeeOid()) {
				main.setOid(requestBody.getBody().getEmployeeOid());
			}
            if (null != requestBody.getBody().getNodeOid()) {
				main.setNodeOid(requestBody.getBody().getNodeOid());
			}

            main.setTemp(temp);
            return main;
        } catch (HttpStatusCodeException ex) {
            ex.printStackTrace();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(ex.getResponseBodyAsString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("ConnectException")) {
//                throw new ServiceExceptionHolder.ResourceNotFoundException("common organogram api " +  url + " does not work at " + ZUUL_BASE_URL);
            }
        }
        return null;
    }
}
