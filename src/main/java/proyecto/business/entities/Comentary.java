package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comentary {
    private Tourist turista;
    private Publication publication;
    private int calification;
    private String comantary;
    private int idComentary;

    public Comentary() {
    }

    public Comentary(Tourist turista, Publication publication, int calification, String comantary) {
        this.turista = turista;
        this.publication = publication;
        this.calification = calification;
        this.comantary = comantary;
    }

    @ManyToOne
    @NotNull
    public Tourist getTurista() {
        return turista;
    }

    public void setTurista(Tourist turista) {
        this.turista = turista;
    }

    @ManyToOne
    @NotNull
    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    @NotNull
    public int getCalification() {
        return calification;
    }

    public void setCalification(int calification) {
        this.calification = calification;
    }

    @NotNull
    public String getComantary() {
        return comantary;
    }

    public void setComantary(String comantary) {
        this.comantary = comantary;
    }

    @Id
    @GeneratedValue
    public int getIdComentary() {
        return idComentary;
    }

    public void setIdComentary(int idComentary) {
        this.idComentary = idComentary;
    }
}
