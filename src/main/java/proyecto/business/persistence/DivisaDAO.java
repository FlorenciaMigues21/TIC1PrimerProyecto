package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.Divisa;

import java.util.Collection;

public interface DivisaDAO extends CrudRepository<Divisa,String> {

    Collection<Divisa> findAll();
}
