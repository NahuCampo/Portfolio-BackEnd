package com.portfolio.nahuel.Controller;

import com.portfolio.nahuel.Dto.dtoSkills;
import com.portfolio.nahuel.Entity.Skills;
import com.portfolio.nahuel.Security.Controller.Mensaje;
import com.portfolio.nahuel.Service.SSkills;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nahuel
 */

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "http://localhost:4200")
public class CSkills {
    @Autowired
    SSkills sSkills;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list(){
        List<Skills> list = sSkills.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoSkills dtoskills){
        if(StringUtils.isBlank(dtoskills.getNombreSkill()))
            return new ResponseEntity(new Mensaje("El Nombre es Obligatorio"),
                    HttpStatus.BAD_REQUEST);
        if(sSkills.existsByNombreSkill(dtoskills.getNombreSkill()))
            return new ResponseEntity(new Mensaje("La skill ya existe"),
                    HttpStatus.BAD_REQUEST);
        
        Skills skill = new Skills(dtoskills.getNombreSkill(),
                dtoskills.getPorcentajeSkill());
        sSkills.save(skill);
        
        return new ResponseEntity(new Mensaje("Skill Agregada"),
                HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id,
            @RequestBody dtoSkills dtoskill){
        if(!sSkills.existsByID(id))
            return new ResponseEntity(new Mensaje("El ID no Existe"),
                    HttpStatus.BAD_REQUEST);
        if(sSkills.existsByNombreSkill(dtoskill.getNombreSkill()) 
                && sSkills.getByNombreSkill(dtoskill.getNombreSkill()).get()
                        .getId() != id)
            return new ResponseEntity(new Mensaje("Ya existe"),
                    HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoskill.getNombreSkill()))
            return new ResponseEntity(new Mensaje("El Nombre es Obligatorio"),
                    HttpStatus.BAD_REQUEST);
        
        Skills skill = sSkills.getOne(id).get();
        skill.setNombreSkill(dtoskill.getNombreSkill());
        skill.setPorcentajeSkill(dtoskill.getPorcentajeSkill());
        
        sSkills.save(skill);
        
        return new ResponseEntity(new Mensaje("Skill actualizada"),
                HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sSkills.existsByID(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),
                    HttpStatus.BAD_REQUEST);
        sSkills.delete(id);
        return new ResponseEntity(new Mensaje("Skill Eliminada"),
                HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getByID(@PathVariable("id") int id){
        if(!sSkills.existsByID(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),
                    HttpStatus.BAD_REQUEST);
        Skills skill = sSkills.getOne(id).get();
        return new ResponseEntity(skill, HttpStatus.OK);
    }
}
