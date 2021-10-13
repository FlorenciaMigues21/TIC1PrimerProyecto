package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.Publication;
import proyecto.business.entities.TouristOperator;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.PublicationCreationError;
import proyecto.business.exceptions.PublicationsLoadError;
import proyecto.business.persistence.PublicationDAO;

import java.util.Collection;

@Service
public class PublicationManager {

    @Autowired
    private PublicationDAO controller;

    public void createPublication(Publication publication) throws PublicationCreationError {
        if(publication.verifyObjectIncomplete())
            throw new PublicationCreationError("Publicacion con datos incompletos");
        try{
            controller.save(publication);
        }catch (Exception e){
            e.printStackTrace();
            throw new PublicationCreationError("Error al guardar la publicacion, por favor, intentelo otra vez");
        }
    }

    public Collection<Publication> getPublicationFromOperator(TouristOperator operator) throws InvalidUserInformation, PublicationsLoadError {
        if(operator.verifyObjectIncomplete())
            throw new InvalidUserInformation("Informacion del operador incompleta");
        try{
            Collection<Publication> publicaciones = controller.findAllByOperador(operator);
            return publicaciones;
        }catch (Exception e){
            e.printStackTrace();
            throw new PublicationsLoadError("Error al cargar las publicaciones del operador turistico " + operator.getUsername());
        }
    }


}
