package proyecto.business.entities_managers.Busqueda;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Service;
import proyecto.business.entities.Publication;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class IndexingService {

    private final EntityManager em;

    @Transactional
    public void initiateIndexing() throws InterruptedException {            //INICIA LA INDEXACION DE LAS ENTIDADES

        log.info("Initiating indexing...");
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        try {fullTextEntityManager.createIndexer(Publication.class).startAndWait();}
        catch (Exception NoIndexo){
            System.out.println("NO INDEXO");
        };                                                                               //o cambio por .typesToIndexInParallel(2).threadsToLoadObjects(5).
        log.info("All entities indexed");
    }
}



/*
Podemos llamar al método initiateIndexing ()
al inicio de la aplicación o crear una API en un controlador REST para llamarlo.
Adentro del CreateIndexer se le puede dejar vacio o poner una clase en concreto para indexar en este caso Publication
*/