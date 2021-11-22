package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.util.Date;

@Entity
public class Reservation {
    @NotNull
    @ManyToOne
    private Tourist turista;
    @ManyToOne
    @NotNull
    private Publication publication;
    @Id
    @GeneratedValue
    private int IdReservation;
    private int cantidad;
    private int hourStart;
    private Date date_reservation;
    private boolean validated_reservation;

    public Reservation() {
    }

    public Reservation(Tourist turista, Publication publication, int cantidad, int hourStart, Date date_reservation, boolean validated_reservation) {
        this.turista = turista;
        this.publication = publication;
        this.cantidad = cantidad;
        this.hourStart = hourStart;
        this.date_reservation = date_reservation;
        this.validated_reservation = validated_reservation;
    }

    public Tourist getTurista() {
        return turista;
    }

    public void setTurista(Tourist turista) {
        this.turista = turista;
    }


    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publications) {
        this.publication = publications;
    }

    @NotNull
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @NotNull
    public int getHourStart() {
        return hourStart;
    }

    public void setHourStart(int hourStart) {
        this.hourStart = hourStart;
    }

    public int getIdReservation() {
        return IdReservation;
    }

    public void setIdReservation(int idReservation) {
        IdReservation = idReservation;
    }

    @NotNull
    public Date getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }

    @NotNull
    public boolean isValidated_reservation() {
        return validated_reservation;
    }

    public void setValidated_reservation(boolean validated_reservation) {
        this.validated_reservation = validated_reservation;
    }
}
