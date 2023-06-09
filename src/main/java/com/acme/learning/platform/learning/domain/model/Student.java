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
@Entity

//crea una tabla de esta entidad, siempre incluir el id dentro
//pero ello lo señalaremos mas abajo
@Table(name="students")//fuerzo el "name", para respetar las convenciones, el nombre de las tablas son en plural
//sin embargo, nombre de atributos es en singular


//extiende de la clase abstracta
public class Student extends AuditModel {

    //la tarea de generar el id recae en estas definiciones
    //señalamos que el atributo "id", sera lo de la tabla
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //el atributo "name" no ha de ser nulo ni vacio
    //de tamaño max 60 y debe ser unico en relacion con los ya existentes
    @NotNull
    @NotBlank
    @Size(max=60)
    @Column(unique=true)
    private String name;

    private int age;

    @Size(max=240)
    private String address;
}
