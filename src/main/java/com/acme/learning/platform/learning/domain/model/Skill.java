package com.acme.learning.platform.learning.domain.model;

import com.acme.learning.platform.shared.domain.model.AuditModel;
import com.acme.learning.platform.shared.exception.ResourceValidationException;
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
@Getter
@Setter
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
    private Set<Criterion> criteria;

    //la coleccion de criterion es una propiedad de esta clase

    //considerar que esto es un agregate: un skill tiene muchos criterion
    //los agregate deben tener logica de negocio incorporada

    //realiza este metodo aplicando reglas de negocio
    public Skill addCriterion(String criterionName){

        if(criteria==null){
            criteria=new HashSet<>();
        }

        //validate Criterion Name Uniqueness for Skill
        //valido el nombre del criterion, si es elmismo, entonces es un error
        //solo puede existir el mismo nombre de criterion si esta en diferentes skills
        if(!criteria.isEmpty()){
            if (criteria.stream().anyMatch(criterion -> criterion.getName().equals(criterionName)))
                throw new ResourceValidationException("Criterion", "A criterion with the same name alredy exists.");
        }

        //add Criterion to Skill
        criteria.add(new Criterion()
                .withName(criterionName)
                .withSkill(this));

        return this;
    }

}























