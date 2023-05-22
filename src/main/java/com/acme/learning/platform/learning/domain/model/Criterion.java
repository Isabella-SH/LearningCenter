package com.acme.learning.platform.learning.domain.model;

import com.acme.learning.platform.shared.domain.model.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

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

}
