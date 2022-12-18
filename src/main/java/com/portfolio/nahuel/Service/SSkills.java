package com.portfolio.nahuel.Service;

import com.portfolio.nahuel.Entity.Skills;
import com.portfolio.nahuel.Repository.RSkills;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nahuel
 */

@Service
@Transactional
public class SSkills {
    @Autowired
    RSkills rSkills;
    
    public List<Skills> list(){
        return rSkills.findAll();
    }
    
    public Optional<Skills> getOne(int id){
        return rSkills.findById(id);
    }
    
    public Optional<Skills> getByNombreSkill(String nombreSkill){
        return rSkills.findByNombreSkill(nombreSkill);
    }
    
    public void save(Skills skill){
        rSkills.save(skill);
    }
    
    public void delete(int id){
        rSkills.deleteById(id);
    }
    
    public boolean existsByID(int id){
        return rSkills.existsById(id);
    }
    
    public boolean existsByNombreSkill(String nombreSkill){
        return rSkills.existsByNombreSkill(nombreSkill);
    }
}
