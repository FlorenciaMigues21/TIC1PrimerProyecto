package proyecto.business.entities_managers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import proyecto.business.entities.*;
import proyecto.business.exceptions.DataBaseError;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.PublicationCreationError;
import proyecto.business.exceptions.PublicationsLoadError;
import proyecto.business.persistence.PublicationDAO;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PublicationManager {

    @Autowired
    private PublicationDAO controller;

    @Autowired
    private HygieneManager hygieneManager;

    @Autowired
    private IncludedManager includedManager;

    @Autowired
    private PhotoManager photoManager;

    public void createAndUpdatePublication(Publication publication) throws PublicationCreationError {
        /*if (publication.verifyObjectIncomplete())
            throw new PublicationCreationError("Publicacion con datos incompletos");*/
        try {
            for (Hygiene hygene: publication.getMedidas_de_higiene())
                try {
                    if (hygieneManager.searchHygiene(hygene.getMedidas()) == null)
                        hygieneManager.createHygiene(hygene);
                }catch(Exception e){
                    hygieneManager.createHygiene(hygene);
                }
            for (IncludedInPublication included: publication.getIncluido())
                try {
                    if(includedManager.searchIncluded(included.getIncluido()) == null)
                        includedManager.createIncluded(included);
                }catch(Exception e){
                    includedManager.createIncluded(included);
                }
            for(Photo foto: publication.getPhotoList())
                photoManager.savePhoto(foto);
            controller.save(publication);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PublicationCreationError("Error al guardar la publicacion, por favor, intentelo otra vez");
        }
    }

    public Collection<Publication> getPublicationFromOperator(TouristOperator operator) throws InvalidUserInformation, PublicationsLoadError {
        if (operator.verifyObjectIncomplete())
            throw new InvalidUserInformation("Informacion del operador incompleta");
        try {
            Collection<Publication> publicaciones = controller.findAllByOperador(operator);
            return publicaciones;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PublicationsLoadError("Error al cargar las publicaciones del operador turistico " + operator.getUsername());
        }

    }

    public Collection<Publication> getPublicationByValidated(boolean validated) throws PublicationsLoadError {
        try {
            return controller.findAllByValidated(validated);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PublicationsLoadError("Error al cargar las publicaciones que tienen validado como: " + validated);
        }
    }

    public ArrayList<Publication> getRecomendedPublications(String mail) throws DataBaseError {
        try{
            Collection<Integer> publication_id_list = controller.findRecomendationsForUser(mail);
            ArrayList<Publication> publications = new ArrayList<>();
            for(Integer publication_id : publication_id_list){
                publications.add(controller.findByIdEvent(publication_id));
            }
            return publications;
        }catch(Exception e){
            e.printStackTrace();
            throw new DataBaseError("Error al cargar las publicaciones recomendadas",e.getMessage());
        }
    }


}
