//necesitamos que nuestras entidades importantes tengan pistas de auditoria
//como la fecha que se creo, en que fecha se actualizaron
//ello no tiene sentido que se repita en varias partes
//por ello se crea una clase abstracta que alberga tal informacion

package com.acme.learning.platform.shared.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter

//implementar estrategia de herencia, ya que esta sera una clase base d elas entidades
//no creara una tabla "AuitModel", solo incorporara los atributos a los descendientes
//va a hacer un mapeo de los atributos a la tabla que le corresponde (los hijos)
@MappedSuperclass

//indicando al sprint que va asociar la captura de pistas de auitoria con este tipo de clase
@EntityListeners(AuditingEntityListener.class)

//es una sutileza
//sus descendientes cuando busquen una representacion en json no se considere estos atributos
//pero al generar uno nuevo si ha de considerarse, pero estos datos se crean internamente
@JsonIgnoreProperties(value={},allowGetters = true)

public abstract class AuditModel {

    @Temporal(TemporalType.TIMESTAMP)
    //la columna se va a llamar "created_at", nopuede estar vacia y no se puede modificar si ya tiene un valor
    @Column(name="created_at",nullable = false,updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    //la columna se va a llamar "created_at", no puede estar vacia
    @Column(name="updated_at",nullable = false)
    @LastModifiedDate
    private Date updatedAt;
}
