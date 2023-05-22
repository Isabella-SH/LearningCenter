package com.acme.learning.platform.shared.exception;

import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;
import java.util.stream.Collectors;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceValidationException extends RuntimeException {

    public ResourceValidationException() {
    }

    public ResourceValidationException(String message) {
        super(message);
    }

    public ResourceValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    //otro constructor que nos simplificara la vida

    public ResourceValidationException(String resourceName, String message){
         super(String.format("Not all constrains satisfied for %s: %s", resourceName,message));
    }


    //cuando el propio framework valida los constrains
    //es muy probable que en mas de un caso, mas de un atributo pueda fallar los constrains
    //entonces nos manda una coleccion de validations o violaciones
    //usamos <T> porque puede ser cualquier tipo de recurso

    //aqui nos dice que todas las escepciones recolectadas se colocarane n un "map"
    //luego de ello se mostrara lo recolectado al usuario
    //separando por coma cada valor del map, para mayor comodidad de la lectura

    public <T>ResourceValidationException(String resourceName,
                                          Set<ConstraintViolation<T>>violations){
        super (String.format("Not all constrains satisfied for %s: %s", resourceName,
                violations.stream().map(violation->String.format("%s %s",
                        violation.getPropertyPath(),violation.getMessage()))
                        .collect(Collectors.joining(", "))));

    }
}
