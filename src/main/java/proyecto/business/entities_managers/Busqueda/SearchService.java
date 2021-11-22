package proyecto.business.entities_managers.Busqueda;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import proyecto.business.entities.Publication;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@Component
@Slf4j
@RequiredArgsConstructor
public class SearchService {                    //SERVICIO DE BUSQUEDA
    private final EntityManager entityManager;

    public List<Publication> getPublicationBasedOnWord(String word){              //QUERY DE BUSCAR PUBLICACION POR PALABRA
        FullTextEntityManager fullTextEntityManager =
                Search.getFullTextEntityManager(entityManager);

        QueryBuilder qb = fullTextEntityManager
                .getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Publication.class)
                .get();

        Query PublicationQuery = qb.keyword()
                .onFields("title","description")
                .matching(word)
                .createQuery();

        FullTextQuery fullTextQuery = fullTextEntityManager
                .createFullTextQuery((org.apache.lucene.search.Query) PublicationQuery, Publication.class);
        return (List<Publication>) fullTextQuery.getResultList();
    }



    //QUERY por calificacion ¿?
  /*  public List<Publication> getBasedOnCalification(Long likeCount, String luegover!!!!!!!!!!!!!!!!!, String tag){
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
                .forEntity(Publication.class)
                .get();
        Query CalificationGreater = (Query) qb.range().onField("calification").above(likeCount).createQuery();
        Query hashTagsQuery = (Query) qb.keyword().onField("hashTags").matching(hashTags).createQuery();
        Query tagQuery = (Query) qb.keyword().onField("tag").matching(tag).createQuery();
        Query finalQuery = (Query) qb.bool().must((org.apache.lucene.search.Query) CalificationGreater).should((org.apache.lucene.search.Query) tagQuery).should((org.apache.lucene.search.Query) hashTagsQuery).createQuery();
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery((org.apache.lucene.search.Query) finalQuery, Publication.class);
        fullTextQuery.setSort(qb.sort().byScore().createSort());
        return (List<Publication>) fullTextQuery.getResultList();
    }  */
}
