package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.PublicationInteraction;

import java.util.Collection;

public interface PublicationInteractionDAO extends CrudRepository<PublicationInteraction,Integer> {
    Collection<PublicationInteraction> findAll();
}
