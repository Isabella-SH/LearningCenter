//CREAR ESTA CLASE PARA CADA BOUNDED CONTEXT
package com.acme.learning.platform.learning.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

            //nombredelboundedcontext_nombredelaclase
@Configuration("learningMappingConfiguration") //sirve para diferenciarse de la clase que esta en Learning

public class MappingConfiguration {

    @Bean
    public StudentMapper studentMapper(){
        return new StudentMapper();
    }
}
