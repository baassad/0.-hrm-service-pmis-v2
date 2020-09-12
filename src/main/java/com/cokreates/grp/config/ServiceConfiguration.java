package com.cokreates.grp.config;

import com.cokreates.grp.beans.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Collections;

@Configuration
@Getter
@EnableSwagger2
@EnableWebMvc
public class ServiceConfiguration extends WebMvcConfigurerAdapter {
    @Value("${service.properties.short-code:STR}")
    private String shortCode;

    @Value("${service.properties.version:v0.1}")
    private String version;

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build().apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo("TITLE", "DESCIPRION", "VERSION", "TERMS OF SERVICE URL",
                new Contact("NAME", "URL", "EMAIL"), "LICENSE", "LICENSE URL", Collections.emptyList());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        org.modelmapper.config.Configuration configuration = modelMapper.getConfiguration();
        configuration.setAmbiguityIgnored(true);
        configuration.setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(15000))
                .setReadTimeout(Duration.ofMillis(15000))
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
