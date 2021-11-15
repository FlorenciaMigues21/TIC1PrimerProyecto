package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.Typeofactivities;

import java.util.Collection;

public interface TypeofactivitiesDAO extends CrudRepository<Typeofactivities,String> {

    Collection<Typeofactivities> findAll();

}
