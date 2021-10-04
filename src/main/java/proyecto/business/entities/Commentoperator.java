package proyecto.business.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Commentoperator {
    private int idComment;
    private String text;
    private int calification;

    @Id
    @Column(name = "ID_Comment")
    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    @Basic
    @Column(name = "Text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "Calification")
    public int getCalification() {
        return calification;
    }

    public void setCalification(int calification) {
        this.calification = calification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commentoperator that = (Commentoperator) o;
        return idComment == that.idComment && calification == that.calification && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComment, text, calification);
    }
}
