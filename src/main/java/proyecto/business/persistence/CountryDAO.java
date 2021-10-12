package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.Country;

import java.util.Collection;

public interface CountryDAO extends CrudRepository<Country,String> {

    Collection<Country> findAll();
}
