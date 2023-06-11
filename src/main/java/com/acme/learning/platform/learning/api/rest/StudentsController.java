package com.acme.learning.platform.learning.api.rest;

import com.acme.learning.platform.learning.domain.service.StudentService;
import com.acme.learning.platform.learning.mapping.StudentMapper;
import com.acme.learning.platform.learning.resource.CreateStudentResource;
import com.acme.learning.platform.learning.resource.StudentResource;
import com.acme.learning.platform.learning.resource.UpdateStudentResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/api/v1/students")

//imformacion que puede ser util para la documentacion
@Tag(name="Students", description = "Create, read, update and delete students")

public class StudentsController {

    private StudentService studentService;
    private StudentMapper mapper;

    public StudentsController(StudentService studentService, StudentMapper mapper){
        this.studentService=studentService;
        this.mapper=mapper;
    }

    @GetMapping
    public Page<StudentResource> getAllStudents(Pageable pageable){
        return mapper.modelListPage(studentService.getAll(),pageable);
    }

    @Operation(summary = "Get all students")
    @GetMapping("{studentId}")          //"PathVariable" reconoce la variable de esta linea
    public StudentResource getStudentById(@PathVariable Long studentId){
        return mapper.toResource(studentService.getById(studentId));
    }

    @Operation(summary = "Create student", responses={
            @ApiResponse(description = "Student successfully created",

                    responseCode = "201",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentResource.class)
                    )
            )
    })



    @PostMapping
    public ResponseEntity<StudentResource> createStudent(@RequestBody CreateStudentResource resource){
        return new ResponseEntity<>(mapper.toResource(studentService.create(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @PutMapping("{studentId}")
    public StudentResource updateStudent(@PathVariable Long studentId,
                                         @RequestBody UpdateStudentResource resource){
        return mapper.toResource(studentService.update(studentId,mapper.toModel(resource)));
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId){
        return studentService.delete(studentId);
    }
}
