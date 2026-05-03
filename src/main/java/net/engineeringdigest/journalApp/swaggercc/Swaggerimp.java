package net.engineeringdigest.journalApp.swaggercc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swaggerimp {

    @Bean
    public OpenAPI customerOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Journal Api System")
                        .description("Journal Entry")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Shubham")
                                .email("shubhamshakya@gmail.com")));
    }
}
