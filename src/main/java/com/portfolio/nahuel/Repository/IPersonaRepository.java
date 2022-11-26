package com.portfolio.nahuel.Repository;

import com.portfolio.nahuel.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nahuel
 */

@Repository
public interface IPersonaRepository extends JpaRepository<Persona,Long>{
    
}
