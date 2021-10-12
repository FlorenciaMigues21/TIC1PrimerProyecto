package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.Comentary;
import proyecto.business.entities.Publication;
import proyecto.business.entities.Tourist;

import java.util.Collection;

public interface ComentaryDAO extends CrudRepository<Comentary,Integer> {

    Collection<Comentary> findAllByPublication(Publication publication);

    Collection<Comentary> findAllByTurista(Tourist turista);
}
