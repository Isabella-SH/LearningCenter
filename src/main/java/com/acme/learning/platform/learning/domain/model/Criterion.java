package com.acme.learning.platform.learning.domain.model;

import com.acme.learning.platform.shared.domain.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity //le decimos a JPA "este entity esta vinculado a una creacion de tabla"

//crea una tabla de esta entidad, siempre incluir el id dentro
//pero ello lo señalaremos mas abajo
@Table(name="criteria")//fuerzo el "name", para respetar las convenciones, el nombre de las tablas son en plural
//sin embargo, nombre de atributos es en singular

public class Criterion extends AuditModel {

    //la tarea de generar el id recae en estas definiciones
    //señalamos que el atributo "id", sera lo de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //el atributo "name" no ha de ser nulo ni vacio
    @NotNull
    @NotBlank
    private String name;

    //Relationships

    //"ManyToOne": entre criterion y skill establecemos una relacion de uno a muchos
    //"fetch": tiene que ver la con la recuperacion
    //"fetch = FetchType.EAGER": cuando recupero un skill, la coleccion criteria quiero que se vaya a poblar con_todo lo que tengan asociado ese skill, para que siempre que la consulte este con valores
    //Cuando yo recupero un objeto de la clase Criterion automaticamente recupera a que skill pertenece
    //"optional=false": no debe guardar un criterio sin skill
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name="skill.id",nullable=false)
    @JsonIgnore //por si se crea una coleccion json de Criterion, entonces esta bno debe aparecer, solo deberia aparecer "criteria"
    private Skill skill;

}
