package com.cokreates.grp.util.webservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.tsp.TSPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class WebService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    HttpServletRequest request;

    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
        return headers;
    }

    @Autowired
    private ObjectMapper objectMapper;


    public byte[] postForByteArray(String restUrl, Object body) {
        try {
            ResponseEntity<byte[]> responseEntity = restTemplate.postForEntity(
                    restUrl,
                    body, byte[].class);
            return responseEntity.getBody();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return new byte[1024];
    }

    public <T> List<T> postForList(String restUrl, Class<T> c, Object dto) {
        ResponseEntity<String> response;
        JsonNode jsonNode;
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
            String req = gson.toJson(dto);
            response = restTemplate.exchange(restUrl, HttpMethod.POST,
                    new HttpEntity(req, httpHeaders()), String.class);
            jsonNode = objectMapper.readTree(response.getBody());
            jsonNode = jsonNode.get("body").get("data");
            return objectMapper.readValue(jsonNode.toString(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, c));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }


    public <T> List<T> getRestTemplateResponse(String restUrl, Class c, Object dto) {
        ResponseEntity<String> response;
        JsonNode jsonNode;
        try {
            Gson gson = new Gson();
            String req = gson.toJson(dto);
            response = restTemplate.exchange(restUrl, HttpMethod.POST,
                    new HttpEntity(dto, httpHeaders()), String.class);
            jsonNode = objectMapper.readTree(response.getBody());
            jsonNode = jsonNode.get("body").get("data");
            List<T> responseDTOs =
                    objectMapper.readValue(jsonNode.toString(),
                            objectMapper.getTypeFactory().constructCollectionType(List.class, c));
            return responseDTOs;
//            T main = objectMapper.treeToValue(jsonNode, c);
//            return main;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }


}
