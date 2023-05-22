package com.acme.learning.platform.shared.domain.service.communication;

import org.apache.logging.log4j.util.Strings;

public abstract class BaseResponse<T> {

    private boolean success;
    private String message;
    private T resource;

    //se usa este metodo al encontrarse un unhappypath
    //ya que se pasa como parametro un mensaje de error
    public BaseResponse(String message){
        this.success=false;
        this.message=message;
        this.resource=null;
    }

    //se usa este metodo al encontrarse un happypath
    //ya que se pasa como parametro el request
    public BaseResponse(T resource){
        this.success=true;
        this.message= Strings.EMPTY;
        this.resource=resource;
    }
}
