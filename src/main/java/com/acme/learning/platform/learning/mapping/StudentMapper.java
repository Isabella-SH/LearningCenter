package com.acme.learning.platform.learning.mapping;

import com.acme.learning.platform.learning.domain.model.Student;
import com.acme.learning.platform.learning.resource.CreateStudentResource;
import com.acme.learning.platform.learning.resource.StudentResource;
import com.acme.learning.platform.learning.resource.UpdateStudentResource;
import com.acme.learning.platform.shared.mapping.EnhancedModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class StudentMapper implements Serializable {

    private EnhancedModelMapper mapper;

    public StudentMapper(EnhancedModelMapper mapper){
        this.mapper=mapper;
    }

    public StudentResource toResource(Student model){

        return mapper.map(model,StudentResource.class);
    }

    public Student toModel(CreateStudentResource resource){
        return mapper.map(resource,Student.class);
    }

    public Student toModel(UpdateStudentResource resource){
        return mapper.map(resource,Student.class);
    }

    //para que haga la reparticion usamos elgo similar al "paginator"
    public Page<StudentResource> modelListPage(List<Student> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList,StudentResource.class),pageable,modelList.size());
    }


}










