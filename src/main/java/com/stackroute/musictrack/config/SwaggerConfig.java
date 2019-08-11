package com.stackroute.musictrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
//@EnableSwagger2 annotation enables Swagger support in the class.
@EnableSwagger2
public class SwaggerConfig {
    //create bean for below method
    @Bean
    public Docket trackApi(){
        //get all
        //The select() method called on the Docket bean instance returns an ApiSelectorBuilder, which provides the apis() and paths() methods that are used to filter the controllers
        // and methods that are being documented using String predicates.
        // to check api document use localhost:8080/swagger-ui.html
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.stackroute.trackservice")).paths(regex("/api/v1.*")).build();
    }
}
