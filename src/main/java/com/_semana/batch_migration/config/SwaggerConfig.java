package com._semana.batch_migration.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Migración MySQL a MongoDB")
                        .version("1.0.0")
                        .description("API REST para migrar datos de usuarios de MySQL a MongoDB usando Spring Batch")
                        .contact(new Contact()
                                .name("Tu Nombre")
                                .email("tu.email@example.com")
                                .url("https://github.com/tuusuario"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}