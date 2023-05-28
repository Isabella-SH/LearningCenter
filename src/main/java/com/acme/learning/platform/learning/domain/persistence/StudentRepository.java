//esta interfaz no es necesaria implementarla,
//solo esta aqui para respetar el patron de responsabilidad unica

package com.acme.learning.platform.learning.domain.persistence;

import com.acme.learning.platform.learning.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    //realizo las consultas con los atributos que quiero

    List <Student> findAllBy(int age);

    Student findByName(String name);
}
