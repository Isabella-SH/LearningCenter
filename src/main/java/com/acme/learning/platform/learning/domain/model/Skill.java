package com.acme.learning.platform.learning.domain.model;

import com.acme.learning.platform.shared.domain.model.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;



@With
@NoArgsConstructor
@AllArgsConstructor
@Entity

//crea una tabla de esta entidad, siempre incluir el id dentro
//pero ello lo señalaremos mas abajo
@Table(name="skills")//fuerzo el "name", para respetar las convenciones, el nombre de las tablas son en plural
//sin embargo, nombre de atributos es en singular

public class Skill extends AuditModel {

    //la tarea de generar el id recae en estas definiciones
    //señalamos que el atributo "id", sera lo de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //el atributo "name" no ha de ser nulo ni vacio
    //de tamaño max 60
    @NotNull
    @NotBlank
    @Size(max=60)
    private String name;

    //Relationships

    //"OneToMany": entre skill y criterion establecemos una relacion de uno a muchos
    //"cascade": la relacion se aplica en cascada. Por ejemplo, si elimino un skill, elimino todos los criterios asociados a el
    //"fetch": tiene que ver la con la recuperacion
    //"fetch = FetchType.EAGER": cuando recupero un skill, la coleccion criteria quiero que se vaya a poblar con_todo lo que tengan asociado ese skill, para que siempre que la consulte este con valores
    //"mappedBy = "skill": Criterion debera tener una propiedad llamada skill, para poder navegar
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "skill")
    private Set<Criterion> criteria=new HashSet<>();
}























