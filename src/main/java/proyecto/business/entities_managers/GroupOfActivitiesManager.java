package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.GroupOfActivities;
import proyecto.business.exceptions.DataBaseError;
import proyecto.business.exceptions.IncompleteObjectException;
import proyecto.business.persistence.GroupOfActivitiesDAO;

import java.util.Collection;

@Service
public class GroupOfActivitiesManager {

    @Autowired
    GroupOfActivitiesDAO controller;

    public void createGruop(GroupOfActivities group) throws IncompleteObjectException, DataBaseError {
        if(group == null)
            throw new IncompleteObjectException("Error, grupo incompleto");
        try{
            controller.save(group);
        }catch(Exception e){
            e.printStackTrace();
            throw new DataBaseError(e.getMessage(),"Error al crear grupo " + e.getMessage());
        }
    }

    public GroupOfActivities getGroup(String group) throws DataBaseError {
        try{
            return controller.findAllByGroup(group);
        }catch(Exception e){
            e.printStackTrace();
            throw new DataBaseError(e.getMessage(),"Error al buscar el grupo " + e.getMessage());
        }
    }

    public Collection<GroupOfActivities> getAllGroups() throws DataBaseError {
        try{
            return controller.findAll();
        }catch(Exception e){
            e.printStackTrace();
            throw new DataBaseError(e.getMessage(),"Error al buscar el grupo " + e.getMessage());
        }
    }
}
