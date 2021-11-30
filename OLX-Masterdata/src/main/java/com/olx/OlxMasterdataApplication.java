package com.olx;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class OlxMasterdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlxMasterdataApplication.class, args);
	}
	private ApiInfo getApiInfo()
	{
		return new ApiInfo(
				"OLX Master Data Rest API Documentation",
				"OLX Master Data Rest API's released by Zensar Ltd.",
				"2.5",
				"http://zensar.com/termsofservice",
				new Contact("Shradha","http://shra.com","shra@zensar.com"),
				"SP",
				"http://sp.com",
				new ArrayList<VendorExtension>());
	}
	@Bean
	public Docket getCustomizedDocket()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.olx"))
//				.paths(PathSelectors.ant("/zenmarket*"))
				.paths(PathSelectors.any())
				.build();
	}
	
	  	@Bean
	    @LoadBalanced
	    public RestTemplate getRestTemplate() {
	        return new RestTemplate();
	    }

	    @Bean
	    public ModelMapper getModelMapper() {
	        return new ModelMapper();
	    }
}
