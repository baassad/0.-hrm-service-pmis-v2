package com.cokreates.grp.util.webclient;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.cokreates.core.MasterDTO;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.util.components.HeaderUtilComponent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;


// TODO: Take BaseService Logic to Component with Overridden methods as interface calls
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
            ResponseEntity<String> response = restTemplate.exchange(gDataEndPointUrl, HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode mainJson = jsonNode.get("body").get("main");
            JsonNode tempJson = jsonNode.get("body").get("temp");
            
            MasterDTO masterDTO = new MasterDTO();
            
            D main = objectMapper.treeToValue(mainJson, requestBody.getBody().getDtoClass());
            D temp = objectMapper.treeToValue(tempJson, requestBody.getBody().getDtoClass());
            masterDTO.setMain(main);
            masterDTO.setTemp(temp);
            return (D) masterDTO;
            
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
    public D getSingleObject(List<String> nodePath, DataServiceRequest<D> requestBody, String gDataEndPointUrl) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            ResponseEntity<String> response = restTemplate.exchange(gDataEndPointUrl, HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode mainJson = jsonNode.get("body").get("main");
            JsonNode tempJson = jsonNode.get("body").get("temp");
            //System.out.println(mainJson);
            //System.out.println(tempJson);
            
            MasterDTO masterDTO = new MasterDTO();
            
            D main = objectMapper.treeToValue(mainJson, requestBody.getBody().getDtoClass());
            D temp = objectMapper.treeToValue(tempJson, requestBody.getBody().getDtoClass());
            //System.out.println("helloo " + main);
            //System.out.println(" hello " + temp);
            
            masterDTO.setMain(main);
            masterDTO.setTemp(temp);
            return (D) masterDTO;
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
            ResponseEntity<String> response = restTemplate.exchange(gDataEndPointUrl, HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode mainJson = jsonNode.get("body").get("main");
            JsonNode tempJson = jsonNode.get("body").get("temp");
            //System.out.println(mainJson);
            //System.out.println(tempJson);
            
            MasterDTO masterDTO = new MasterDTO();
            
            D main = objectMapper.treeToValue(mainJson, requestBody.getBody().getDtoClass());
            D temp = objectMapper.treeToValue(tempJson, requestBody.getBody().getDtoClass());
            //System.out.println("helloo " + main);
            //System.out.println(" hello " + temp);
            
            masterDTO.setMain(main);
            masterDTO.setTemp(temp);
            return (D) masterDTO;
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
