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
  /*@Service
    @RequiredArgsConstructor
    @Slf4j
    class IndexingService {

        private final EntityManager em;

        @Transactional
        public void initiateIndexing() throws InterruptedException {
            log.info("Initiating indexing...");
            FullTextEntityManager fullTextEntityManager =
                    Search.getFullTextEntityManager(em);
            fullTextEntityManager.createIndexer().startAndWait();
            log.info("All entities indexed");
        }
    }

    @Component
    @Slf4j
    @RequiredArgsConstructor
    public class SearchService {

        private final EntityManager entityManager;

        public List<Publication> getPublicationBasedOnWord(String word){
            FullTextEntityManager fullTextEntityManager =
                    Search.getFullTextEntityManager(entityManager);

            QueryBuilder qb = fullTextEntityManager
                    .getSearchFactory()
                    .buildQueryBuilder()
                    .forEntity(Publication.class)
                    .get();

            Query PublicationQuery = (Query) qb.keyword()
                    .onFields("body","hashTags")
                    .matching(word)
                    .createQuery();

            FullTextQuery fullTextQuery = fullTextEntityManager
                    .createFullTextQuery((org.apache.lucene.search.Query) PublicationQuery, Publication.class);
            return (List<Publication>) fullTextQuery.getResultList();
        }


    } */
}
