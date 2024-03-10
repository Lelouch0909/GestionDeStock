package com.lontsi.gestiondestock.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static com.lontsi.gestiondestock.utils.Constants.APPROOT;

@Configuration
public class SwaggerConfiguration {
    
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(
            new ApiInfoBuilder()
            .description("Gestion de Stock API Documentation")
            .title("Gestion de Stock REST API")
            .build()
        )
        .groupName("REST API V1")
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.lontsi.gestiondestock") )
        .paths(PathSelectors.ant(APPROOT + "/**"))
        .build()
        ;

      }
}
