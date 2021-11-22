package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.Hygiene;

public interface HygieneDAO extends CrudRepository<Hygiene,Integer> {

    Hygiene findAllByMedidas(String medidas);
}
