package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

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

    public Reservation() {
    }

    public Reservation(Tourist turista, Publication publication, int cantidad) {
        this.turista = turista;
        this.publication = publication;
        this.cantidad = cantidad;
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
}
