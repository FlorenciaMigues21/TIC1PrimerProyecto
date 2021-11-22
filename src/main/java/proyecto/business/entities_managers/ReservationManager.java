package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.Publication;
import proyecto.business.entities.Reservation;
import proyecto.business.entities.Tourist;
import proyecto.business.exceptions.DataBaseError;
import proyecto.business.exceptions.InvalidPublicationInformation;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.ReservationCreationError;
import proyecto.business.persistence.ReservationDAO;

import java.util.Collection;

@Service
public class ReservationManager {

    @Autowired
    ReservationDAO controller;

    public void addReservation(Reservation reservation) throws ReservationCreationError, DataBaseError {
        if(reservation == null)
            throw new ReservationCreationError("Error, los datos de la reserva estan vacios");
        try{
            controller.save(reservation);
        }catch(Exception e){
            throw new DataBaseError("Error a la hora de crear la reserva", e.getMessage());
        }
    }

    public Collection<Reservation> getAllReservationFromTourist(Tourist turista) throws InvalidUserInformation, DataBaseError {
        if(turista==null)
            throw new InvalidUserInformation("Error, los datos del turista estan vacios");
        try{
            return controller.findAllByTurista(turista);
        }catch(Exception e){
            throw new DataBaseError("Error a la hora de cargar las reservas del turista " + turista.getUsername(), e.getMessage());
        }
    }

    public Collection<Reservation> getAllReservationFromPublication(Publication publication) throws InvalidPublicationInformation, DataBaseError {
        if(publication == null)
            throw new InvalidPublicationInformation("Error, los datos de la publicacion estan vacios");
        try{
            return controller.findAllByPublication(publication);
        }catch(Exception e){
            throw new DataBaseError("Error a la hora de cargar las reservas de la publicacion " + publication.getTitle() , e.getMessage());
        }
    }

    public void deleteReservation(Reservation reservation) throws ReservationCreationError, DataBaseError {
        if(reservation == null)
            throw new ReservationCreationError("Error, los datos de la reserva estan vacios");
        try{
            controller.delete(reservation);
        }catch(Exception e){
            throw new DataBaseError("Error a la hora de crear la reserva", e.getMessage());
        }
    }
}
