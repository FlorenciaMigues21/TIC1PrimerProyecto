package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PublicationInteraction {
    private Tourist tourist;
    private Publication publication;
    private String message;
    private int typeOfInteraction;
    private int idInteraction;

    public PublicationInteraction() {
    }

    public PublicationInteraction(Tourist tourist, Publication publication, String message, int typeOfInteraction) {
        this.tourist = tourist;
        this.publication = publication;
        this.message = message;
        this.typeOfInteraction = typeOfInteraction;
    }

    @NotNull
    @ManyToOne
    public Tourist getTourist() {
        return tourist;
    }

    public void setTourist(Tourist tourist) {
        this.tourist = tourist;
    }

    @NotNull
    @ManyToOne
    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTypeOfInteraction() {
        return typeOfInteraction;
    }

    public void setTypeOfInteraction(int typeOfInteraction) {
        this.typeOfInteraction = typeOfInteraction;
    }

    @Id
    @GeneratedValue
    public int getIdInteraction() {
        return idInteraction;
    }

    public void setIdInteraction(int idInteraction) {
        this.idInteraction = idInteraction;
    }
}
