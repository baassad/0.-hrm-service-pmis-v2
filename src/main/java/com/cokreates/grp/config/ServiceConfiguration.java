package com.cokreates.grp.config;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.time.Duration;
import java.util.Base64;

@Configuration
@Getter
public class ServiceConfiguration {
    @Value("${service.properties.short-code:STR}")
    private String shortCode;

    @Value("${service.properties.version:v0.1}")
    private String version;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }

    @Bean
    public HttpHeaders getHeaders(){
        String plainCredentials="admin:admin";
        String base64Credentials = Base64.getEncoder().encodeToString(plainCredentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        return headers;
    }


}