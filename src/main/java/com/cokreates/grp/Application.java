package com.cokreates.grp;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages="com.cokreates")
@SpringBootApplication
@EnableDiscoveryClient
public class Application extends SpringBootServletInitializer{
	
	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+6:00"));
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
