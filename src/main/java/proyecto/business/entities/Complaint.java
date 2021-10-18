package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Complaint {
    private Tourist tourist;
    private Publication publication;
    private String text;
    private int idComplaint;

    public Complaint() {
    }

    public Complaint(Tourist tourist, Publication publication, String text) {
        this.tourist = tourist;
        this.publication = publication;
        this.text = text;
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

    @NotNull
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Id
    @GeneratedValue
    public int getIdComplaint() {
        return idComplaint;
    }

    public void setIdComplaint(int idComplaint) {
        this.idComplaint = idComplaint;
    }
}
