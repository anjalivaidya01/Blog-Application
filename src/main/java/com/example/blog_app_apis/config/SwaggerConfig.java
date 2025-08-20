package com.example.blog_app_apis.config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jdk.javadoc.doclet.Doclet;
import jdk.jshell.SourceCodeAnalysis;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springdoc.core.configuration.SpringDocKotlinConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RequestHeader;

import java.lang.annotation.Documented;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER="Authorization";




    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blogging Application : Backend Courses")
                        .description("This project is developed by Anjali")
                        .version("1.0")
                        .termsOfService("Terms of Service")
                        .contact(new Contact()
                                .name("Anjali")
                                .url("https://google.com")
                                .email("anj@gmail.com"))
                        .license(new License()
                                .name("License of APIs")
                                .url("API License URL"))
                )
                // JWT Security Configuration
                .addSecurityItem(new SecurityRequirement().addList("JWT"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("JWT", new SecurityScheme()
                                .name(AUTHORIZATION_HEADER)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                );
    }

//    private ApiKey apiKey(){
//        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
//    }
//
//private List<SecurityContext> securityContexts(){
//        return Arrays.asList(SecurityContext.builder().securityRefrences(null).build());
//}
//
//private Lists<SecurityRefrences> sf()
//{
//
//    AuthorizationScope scope = new AuthorizationScope("global","accessEverything");
//  return Arrays.asList(new SecurityRefrences("JWT", new AuthorizationScope[] { scope}) );
//}
//
//
//
//@Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//        .apiInfo(getInfo())
//    .securityContexts(securityContexts())
//    .securitySchemes(Arrays.asList(apikeys()))
//        .select()
//        .apis(RequestHeaderSelectors.any())
//        .path(PathSelectors
//        .any()).build();
//    }
//
//    private ApiInfo getInfo(){
//        return new ApiInfo("Blogging Application : Backend Cources",
//                "this project is developed By Anjali ",
//                "1.0","Term of Service",
//                new Contact("Anjali","google.com",
//                        "anj@gmail.com",
//                        "Licence of Apis",
//                        "API Lisence url",
//                        Collections.emptyList()));
//    }
}
