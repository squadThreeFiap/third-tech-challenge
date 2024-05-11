package br.com.fiap.squad3.restaurantfinder.external.restapi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Restaurant Finder")
                        .version("v1")
                        .description("some description")
                        .termsOfService("dont no")
                        .license(new License().name("apache 2.0")
                                .url("")));
    }
}