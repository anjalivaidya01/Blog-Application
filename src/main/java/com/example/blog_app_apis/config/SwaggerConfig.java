package com.example.blog_app_apis.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import jdk.javadoc.doclet.Doclet;
import jdk.jshell.SourceCodeAnalysis;
import org.springdoc.core.configuration.SpringDocKotlinConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestHeader;

import java.lang.annotation.Documented;
import java.util.Collection;
import java.util.Collections;

@Configuration
public class SwaggerConfig {
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
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("License of APIs")
                                .url("API License URL"))
                );
    }
//@Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getInfo()).select().apis(RequestHeaderSelectors.any()).path(PathSelectors.any()).build();
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
