package proyecto.business.entities.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.Typeofactivities;

import java.util.List;

public interface TypeofactivitiesDAO extends CrudRepository<Typeofactivities,String> {

    List<Typeofactivities> findAll();

}
