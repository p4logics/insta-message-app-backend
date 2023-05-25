package com.chat.config;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;
import com.chat.constant.AppConstant;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author manish
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(AppConstant.BASE_PACKAGE)).build().apiInfo(metaInfo())
				.securitySchemes(Lists.newArrayList(apiKey())).securityContexts(Lists.newArrayList(securityContext()));
	}

	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo(AppConstant.SWAGGER_API_INFO, AppConstant.SWAGGER_API_INFO, AppConstant.VERSION,
				"Terms of Service",
				new Contact(AppConstant.SWAGGER_API_INFO, AppConstant.WEBSITE_URL, AppConstant.CONTACT_EMAIL),
				"Apache License Version 2.0", "https://www.apache.org/licesen.html", Collections.emptyList());
		return apiInfo;
	}

	@Bean
	SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}
}
