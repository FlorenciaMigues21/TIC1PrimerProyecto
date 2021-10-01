package proyecto.business.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Publicationsinteraction {
    private int idInteraction;
    private String message;
    private int typeOfInteraction;

    @Id
    @Column(name = "id_interaction")
    public int getIdInteraction() {
        return idInteraction;
    }

    public void setIdInteraction(int idInteraction) {
        this.idInteraction = idInteraction;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "TypeOfInteraction")
    public int getTypeOfInteraction() {
        return typeOfInteraction;
    }

    public void setTypeOfInteraction(int typeOfInteraction) {
        this.typeOfInteraction = typeOfInteraction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publicationsinteraction that = (Publicationsinteraction) o;
        return idInteraction == that.idInteraction && typeOfInteraction == that.typeOfInteraction && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInteraction, message, typeOfInteraction);
    }
}
