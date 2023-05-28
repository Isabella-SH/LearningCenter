//cualquiera que desee inyectar como dependencia un objeto
//en la clase ModelMapper

package com.acme.learning.platform.shared.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

             //nombredelboundedcontext_nombredelaclase
@Configuration("enhancedModelMapperConfiguration") //sirve para diferenciarse de la clase que esta en Learning
public class MappingConfiguration {

    @Bean
    public EnhancedModelMapper modelMapper(){
        return new EnhancedModelMapper();
    }
}
