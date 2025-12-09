package io.github.williamandradesantana.libraryapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Library API", version = "v1",
        contact = @Contact(name = "William Santana", email = "williamandrade1058@gmail.com", url = "https://github.com/williamandradesantana"))

)
public class OpenApiConfiguration {
}
