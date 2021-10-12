package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.Typeofactivities;
import proyecto.business.persistence.TypeofactivitiesDAO;

import java.util.Collection;

@Service
public class TypeofactivitiesManager {

    @Autowired
    TypeofactivitiesDAO controller;

    public Collection<Typeofactivities> getAllActivityTypes(){
        return controller.findAll();
    }
}
