package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.Cambio;
import proyecto.business.entities.Divisa;

import java.util.Collection;

public interface CambioDAO extends CrudRepository<Cambio,String> {

    Collection<Cambio> findAll();

    Collection<Cambio> findByDivisaOrigen(Cambio divisaOrigen);

    Cambio findByDivisaOrigenAndDivisaCambio(Divisa divisaOrigen, Divisa divisaCambio);
}
