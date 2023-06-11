package com.acme.learning.platform.shared.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration  //para que al iniciar la aplicacion incorpore
// a esta clase dentro de las configuraciones que usara bootsprint

//*Sprinboot tiene la propiedad de que permite crear etiquetas enel archivo properties y leerlas desde ahi
public class OpenApiConfiguration {

    public OpenAPI customOpenApi( String applicationDescription, String applicationVersion){

        //aca se necesita establecer vinculos con el landing page en donde este puesta tal informacion
        return new OpenAPI().info(new Info()
                .title("ACME Learning Center API")
                .version(applicationVersion)
                .description(applicationDescription)
                .termsOfService("https://acme-learning.com/tos")
                .license(new License().name("Apache 2.0 License"))
                .contact(new Contact()
                        .url("https://acme.studio")
                        .name("ACME.studio")
                )
        );
    }

}
