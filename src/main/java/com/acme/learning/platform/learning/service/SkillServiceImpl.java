//otro ejemplo-> salesorder(skill)->tiene varios salesorderitem(criterion)

//aqui se trabaja la logica, porque esta es responsabilidad del servicio
//la logica del negocio tiene 2 posibles lugares: la capa del servicio o el modelo del dominio

//en la parte del dominio ya esta "agregar criterio"
//aca lo que se hace es una "busqueda" sobre la coleccion de los skill, no tiene sentido definir ello en el agregate (no se puede buscarasi mismo), por eso se trabaja aqui

//debe haber un repository por cada agregate


package com.acme.learning.platform.learning.service;

import com.acme.learning.platform.learning.domain.model.Skill;
import com.acme.learning.platform.learning.domain.persistence.SkillRepository;
import com.acme.learning.platform.learning.domain.service.SkillService;
import com.acme.learning.platform.shared.exception.ResourceNotFoundException;
import com.acme.learning.platform.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class SkillServiceImpl implements SkillService {

    private static final String ENTITY="Skill";
    private final SkillRepository skillRepository;
    private final Validator validator;


    public SkillServiceImpl(SkillRepository skillRepository, Validator validator){
        this.skillRepository = skillRepository;
        this.validator = validator;
    }


    @Override
    public List<Skill> getAll() {
        return skillRepository.findAll();
    }

    @Override
    public Page<Skill> getAll(Pageable pageable) {
        return skillRepository.findAll(pageable);
    }

    @Override
    public Skill getById(Long skillId) {
        return skillRepository.findById(skillId)
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,skillId));
    }

    @Override
    public Skill create(Skill skill) {

        Set<ConstraintViolation<Skill>> violations= validator.validate(skill);

        //si no esta  vacia la coleccion de violaciones, crea una coleccion de violaciones con eso mismo
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY, violations);
        }

        //si no existe un skill con este nombre
        if(skillRepository.findByName(skill.getName()).isPresent()){
            throw new ResourceValidationException(ENTITY, "An skill with the name already exists");
        }

        return skillRepository.save(skill);
    }

    @Override
    public Skill update(Long id, Skill skill) {

        Set<ConstraintViolation<Skill>> violations= validator.validate(skill);

        //si no esta  vacia la coleccion de violaciones, crea una coleccion de violaciones con eso mismo
        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY, violations);
        }

        Optional<Skill> skillWithName=skillRepository.findByName(skill.getName());

        if(skillWithName.isPresent() && !skillWithName.get().getId().equals(id)){
            throw new ResourceValidationException(ENTITY, "An skill with the same name already exists");
        }

        return skillRepository.findById(id).map(existingSkill->
                skillRepository.save(existingSkill.withName(skill.getName())))
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,id));
    }

    @Override
    public ResponseEntity<?> delete(Long skillId) {

        return skillRepository.findById(skillId).map(skill->{
            skillRepository.delete(skill);
            return ResponseEntity.ok().build();
        })
          .orElseThrow(()->new ResourceNotFoundException(ENTITY,skillId));
    }

    @Override
    //agrega criterio, lo valida y lo agrega a la coleccion de criterios de dicho skill
    //por ultimo retorna el propio skill
    public Skill addCriterionToSkill(Long skillId, String criterionName) {

        return skillRepository.findById(skillId).map(skill->{
            return skillRepository.save(skill.addCriterion(criterionName));
        })
          .orElseThrow(()->new ResourceNotFoundException(ENTITY,skillId));
    }
}
