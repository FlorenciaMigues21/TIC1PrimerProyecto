package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.Hygiene;
import proyecto.business.exceptions.DataBaseError;
import proyecto.business.exceptions.IncompleteObjectException;
import proyecto.business.persistence.HygieneDAO;

@Service
public class HygieneManager {

    @Autowired
    HygieneDAO controller;

    public void createHygiene(Hygiene hygiene) throws IncompleteObjectException, DataBaseError {
        if(hygiene == null)
            throw new IncompleteObjectException("Error datos de higiene incompletos");
        try {
            controller.save(hygiene);
        }catch(Exception e){
            e.printStackTrace();
            throw new DataBaseError(e.getMessage(),"Error al crear medidas de higiene " + e.getMessage());
        }
    }

    public Hygiene searchHygiene(String medidas) throws IncompleteObjectException, DataBaseError {
        if(medidas.equals("") || medidas == null)
            throw new IncompleteObjectException("Error datos de higiene incompletos");
        try{
            return controller.findAllByMedidas(medidas);
        }catch(Exception e){
            e.printStackTrace();
            throw new DataBaseError(e.getMessage(),"Error al cargar medidas de higiene " + e.getMessage());
        }
    }
}
