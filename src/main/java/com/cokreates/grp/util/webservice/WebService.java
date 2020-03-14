package com.cokreates.grp.util.webservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
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

    public <T> List<T> postForList(String restUrl, Class<T> c, Object dto) {
        ResponseEntity<String> response;
        JsonNode jsonNode;
        try {
            response = restTemplate.exchange(restUrl, HttpMethod.POST,
                    new HttpEntity(dto, httpHeaders()), String.class);
            jsonNode = objectMapper.readTree(response.getBody());
            jsonNode = jsonNode.get("body").get("data");
            return objectMapper.readValue(jsonNode.toString(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, c));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }


}