package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.Hygiene;
import proyecto.business.entities.IncludedInPublication;
import proyecto.business.exceptions.DataBaseError;
import proyecto.business.exceptions.IncompleteObjectException;
import proyecto.business.persistence.HygieneDAO;
import proyecto.business.persistence.IncludedDAO;

@Service
public class IncludedManager {
    @Autowired
    IncludedDAO controller;

    public void createIncluded(IncludedInPublication included) throws IncompleteObjectException, DataBaseError {
        if(included == null)
            throw new IncompleteObjectException("Error datos de higiene incompletos");
        try {
            controller.save(included);
        }catch(Exception e){
            e.printStackTrace();
            throw new DataBaseError(e.getMessage(),"Error al crear medidas de higiene " + e.getMessage());
        }
    }


    public IncludedInPublication searchIncluded(String included) throws IncompleteObjectException, DataBaseError {
        if(included.equals("") || included == null)
            throw new IncompleteObjectException("Error datos de higiene incompletos");
        try{
            return controller.findAllByIncluido(included);
        }catch(Exception e){
            e.printStackTrace();
            throw new DataBaseError(e.getMessage(),"Error al cargar included de higiene " + e.getMessage());
        }
    }
}
