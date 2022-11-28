package com.portfolio.nahuel.Security.Repository;

import com.portfolio.nahuel.Security.Entity.Rol;
import com.portfolio.nahuel.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nahuel
 */

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
    
}
