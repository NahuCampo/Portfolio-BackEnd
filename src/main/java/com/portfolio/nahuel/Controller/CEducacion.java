package com.portfolio.nahuel.Controller;

import com.portfolio.nahuel.Dto.dtoEducacion;
import com.portfolio.nahuel.Entity.Educacion;
import com.portfolio.nahuel.Security.Controller.Mensaje;
import com.portfolio.nahuel.Service.SEducacion;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = "http://localhost:4200")
public class CEducacion {
    
    @Autowired
    SEducacion sEducacion;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoEdu){
        if(StringUtils.isBlank(dtoEdu.getNombreEdu()))
            return new ResponseEntity(new Mensaje("El Nombre es Obligatorio"),
                    HttpStatus.BAD_REQUEST);
        if(sEducacion.existsByNombreEdu(dtoEdu.getNombreEdu()))
            return new ResponseEntity(new Mensaje("La educacion ya existe"),
                    HttpStatus.BAD_REQUEST);
        
        Educacion educacion = new Educacion(dtoEdu.getNombreEdu(),
                dtoEdu.getDescripcionEdu());
        sEducacion.save(educacion);
        
        return new ResponseEntity(new Mensaje("Educacion Agregada"),
                HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id,
            @RequestBody dtoEducacion dtoEdu){
        if(!sEducacion.existsByID(id))
            return new ResponseEntity(new Mensaje("El ID no Existe"),
                    HttpStatus.BAD_REQUEST);
        if(sEducacion.existsByNombreEdu(dtoEdu.getNombreEdu()) 
                && sEducacion.getByNombreEdu(dtoEdu.getNombreEdu()).get()
                        .getId() != id)
            return new ResponseEntity(new Mensaje("Ya existe"),
                    HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoEdu.getNombreEdu()))
            return new ResponseEntity(new Mensaje("El Nombre es Obligatorio"),
                    HttpStatus.BAD_REQUEST);
        
        Educacion educacion = sEducacion.getOne(id).get();
        educacion.setNombreEdu(dtoEdu.getNombreEdu());
        educacion.setDescripcionEdu(dtoEdu.getDescripcionEdu());
        
        sEducacion.save(educacion);
        
        return new ResponseEntity(new Mensaje("Experiencia actualizada"),
                HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!sEducacion.existsByID(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),
                    HttpStatus.BAD_REQUEST);
        sEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Educacion Eliminada"),
                HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getByID(@PathVariable("id") int id){
        if(!sEducacion.existsByID(id))
            return new ResponseEntity(new Mensaje("El ID no existe"),
                    HttpStatus.BAD_REQUEST);
        Educacion educacion = sEducacion.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
}
