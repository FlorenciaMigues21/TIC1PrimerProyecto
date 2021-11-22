package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.Comentary;
import proyecto.business.entities.Publication;
import proyecto.business.entities.Tourist;
import proyecto.business.exceptions.ComentaryCreationError;
import proyecto.business.exceptions.DataBaseError;
import proyecto.business.exceptions.IncompleteObjectException;
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

    public Collection<Comentary> getComentaryOfPublication(Publication publication) throws IncompleteObjectException, DataBaseError {
        if(publication == null)
            throw new IncompleteObjectException("Error publicacion incompleta");
        try {
            return controller.findAllByPublication(publication);
        }catch(Exception e){
            e.printStackTrace();
            throw new DataBaseError(e.getMessage(),"Error al traer los comentarios de la publicacion " + e.getMessage());
        }
    }

    public Collection<Comentary> getComentaryOfTourist(Tourist tourist) throws IncompleteObjectException, DataBaseError {
        if(tourist == null)
            throw new IncompleteObjectException("Error turista incompleto");
        try {
            return controller.findAllByTurista(tourist);
        }catch(Exception e){
            e.printStackTrace();
            throw new DataBaseError(e.getMessage(),"Error al traer los comentarios del turista " + e.getMessage());
        }
    }

    public void deleteComentary(Comentary comentario) throws ComentaryCreationError, DataBaseError {
        if(comentario == null)
            throw new ComentaryCreationError("Error, comentario vacio");
        try{
            controller.delete(comentario);
        }catch(Exception e){
            throw new DataBaseError("Error al guardar el comentario",e.getMessage());
        }
    }
}
