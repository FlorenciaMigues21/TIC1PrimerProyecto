package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.Complaint;
import proyecto.business.entities.Publication;
import proyecto.business.entities.Tourist;

import java.util.Collection;

public interface ComplaintDAO extends CrudRepository<Complaint,Integer> {

    Collection<Complaint> findAllByPublication(Publication publication);

    Collection<Complaint> findAllByTourist(Tourist tourist);
}
