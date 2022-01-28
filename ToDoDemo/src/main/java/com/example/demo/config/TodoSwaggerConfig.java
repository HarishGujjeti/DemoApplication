package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class TodoSwaggerConfig {
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("test").apiInfo(apiInfo()).select()
				.paths(regex("/v1.*")).build();
		
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Todo Service")
				.description("Sample Documentation Generateed Using SWAGGER2 for our TODO Rest API").version("1.0").build();
	}
}
