package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.Comentary;
import proyecto.business.entities.Publication;
import proyecto.business.entities.Tourist;
import proyecto.business.exceptions.ComentaryCreationError;
import proyecto.business.exceptions.DataBaseError;
import proyecto.business.persistence.ComentaryDAO;

import java.util.Collection;

@Service
public class ComentaryManager {

    @Autowired
    ComentaryDAO controller;

    public void addComentary(Comentary comentario) throws ComentaryCreationError, DataBaseError {
        if(comentario == null)
            throw new ComentaryCreationError("Error, comentario vacio");
        try{
            controller.save(comentario);
        }catch(Exception e){
            throw new DataBaseError("Error al guardar el comentario",e.getMessage());
        }
    }

    public Collection<Comentary> getComentaryOfPublication(Publication publication){
        return controller.findAllByPublication(publication);
    }

    public Collection<Comentary> getComentaryOfTourist(Tourist tourist){
        return controller.findAllByTurista(tourist);
    }

}
