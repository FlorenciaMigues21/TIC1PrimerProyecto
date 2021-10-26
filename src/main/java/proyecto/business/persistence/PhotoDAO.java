package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.Photo;

import java.util.Collection;

public interface PhotoDAO extends CrudRepository<Photo,Integer> {

    Photo getByIdPhoto(Integer idPhoto);
}
