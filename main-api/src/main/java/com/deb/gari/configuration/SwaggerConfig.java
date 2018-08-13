package com.deb.gari.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Debender Prasad
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.deb.gari"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(matainfo());
    }

    private ApiInfo matainfo() {
        return new ApiInfoBuilder()
                .title("Hamar rest API")
                .description("This is the rest API exposed by the application")
                .version("0.0.1")
                .build();
    }
}
