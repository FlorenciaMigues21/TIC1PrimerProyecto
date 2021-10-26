package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.Publication;
import proyecto.business.entities.Reservation;
import proyecto.business.entities.Tourist;

import java.util.Collection;

public interface ReservationDAO extends CrudRepository<Reservation,Integer> {

    Collection<Reservation> findAll();

    Collection<Reservation> findAllByPublication(Publication publication);

    Collection<Reservation> findAllByTurista(Tourist turista);


}
