package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.IncludedInPublication;

public interface IncludedDAO extends CrudRepository<IncludedInPublication, Integer> {

    IncludedInPublication findAllByIncluido(String incluido);

}
