package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.Publication;
import proyecto.business.entities.TouristOperator;

import java.util.Collection;

public interface PublicationDAO extends CrudRepository<Publication,Integer > {

    //TODO HAY QUE MODIFICAR EL FIND ALL PARA QUE SOLO TRAIGA LOS QUE EL ALGORITMO DE BUSQUEDA NECESITE
    Collection<Publication> findAll();

    Collection<Publication> findAllByOperador(TouristOperator operador);
}
