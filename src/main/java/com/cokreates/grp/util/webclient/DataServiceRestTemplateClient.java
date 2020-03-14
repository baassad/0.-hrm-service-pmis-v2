package com.cokreates.grp.util.webclient;


import com.cokreates.core.BaseEntity;
import com.cokreates.core.MasterDTO;
import com.cokreates.grp.beans.personal.general.GeneralDTO;
import com.cokreates.grp.daas.DataServiceRequest;
import com.cokreates.grp.util.components.HeaderUtilComponent;
import com.cokreates.grp.util.exceptions.ServiceExceptionHolder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


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

    public D getDataFromParticularNode(List<String> url, DataServiceRequest<D> requestBody) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            ResponseEntity<String> response = restTemplate.exchange("http://43.224.110.22:80/hrm/get/v1/node-in-emp-doc", HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode mainJson = jsonNode.get("body").get("main");
            JsonNode tempJson = jsonNode.get("body").get("temp");
            System.out.println(mainJson);
            System.out.println(tempJson);
//            D main = objectMapper.treeToValue(mainJson, requestBody.getBody().getDtoClass());
//            D temp = objectMapper.treeToValue(tempJson, requestBody.getBody().getDtoClass());
            GeneralDTO main = objectMapper.readValue(objectMapper.writeValueAsString(mainJson), GeneralDTO.class);
            GeneralDTO temp = objectMapper.readValue(objectMapper.writeValueAsString(tempJson), GeneralDTO.class);
            System.out.println("helloo " + main);
            System.out.println(" hello " + temp);
            MasterDTO masterDTO = new MasterDTO();
            masterDTO.setMain(main);
            masterDTO.setTemp(temp);
            return (D) masterDTO;
//            list = objectMapper.readValue(
//                    content.toString(),
//                    objectMapper.getTypeFactory().constructCollectionType(
//                            List.class, dtoClass));
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

    public D getDataFromList(List<String> url, DataServiceRequest<D> requestBody) {
        try {
            headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            System.out.println("hello " + requestBody);
            ResponseEntity<String> response = restTemplate.exchange("http://43.224.110.22:80/hrm/get/v1/list-node-in-emp-doc", HttpMethod.POST, new HttpEntity(requestBody, headers), String.class);
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            JsonNode mainJson = jsonNode.get("body").get("main");
            JsonNode tempJson = jsonNode.get("body").get("temp");
            System.out.println(mainJson);
            System.out.println(tempJson);
            D main = objectMapper.treeToValue(mainJson, getDtoClass());
            D temp = objectMapper.treeToValue(tempJson, getDtoClass());
            System.out.println(main);
            System.out.println(temp);
            return main;
//            list = objectMapper.readValue(
//                    content.toString(),
//                    objectMapper.getTypeFactory().constructCollectionType(
//                            List.class, dtoClass));
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

    @SuppressWarnings("unchecked")
    public Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    public Class<D> getDtoClass() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        Type type = genericSuperclass.getActualTypeArguments()[0];
        if (type instanceof Class) {
            return (Class<D>) type;
        } else if (type instanceof ParameterizedType) {
            return (Class<D>) ((ParameterizedType)type).getRawType();
        }
        return null;
    }
}
