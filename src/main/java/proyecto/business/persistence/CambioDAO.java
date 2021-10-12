package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.Cambio;

import java.util.Collection;

public interface CambioDAO extends CrudRepository<Cambio,String> {

    Collection<Cambio> findAll();

    Cambio findByDivisaOrigen(Cambio divisaOrigen);

}
