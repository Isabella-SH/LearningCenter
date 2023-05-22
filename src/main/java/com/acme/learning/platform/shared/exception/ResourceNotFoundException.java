package com.acme.learning.platform.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//que se vincule con un status de response de HTTP
@ResponseStatus(HttpStatus.NOT_FOUND)

public class ResourceNotFoundException extends RuntimeException{

    //constructores
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    //otro constructor que nos simplificara la vida

    //"%s": es el primer parametro
    //"%d": es el segundo parametro
    //por ejemplo: "CategoriId5" no se encuentra
    public ResourceNotFoundException(String resourceName, Long resourceId){
        super(String.format("%s with id %d not found.", resourceName,resourceId));
    }

}
