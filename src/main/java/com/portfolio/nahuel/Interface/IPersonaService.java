
package com.portfolio.nahuel.Interface;

import com.portfolio.nahuel.Entity.Persona;
import java.util.List;

/**
 *
 * @author nahuel
 */
public interface IPersonaService {
    //traer una Lista de Personas
    public List<Persona> getPersona();
    
    //Guardar un objeto de tipo Persona
    public void savePersona(Persona persona);
    
    //Eliminar un objeto de tipo Persona
    public void deletePersona(Long id);
    
    //Buscar un objeto de tipo Persona
    public Persona findPersona(Long id);
    
}
