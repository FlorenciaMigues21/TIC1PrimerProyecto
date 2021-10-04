package proyecto.business.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tourist_operator", schema = "pruebas", catalog = "")
public class TouristOperator {
    private String userMail;
    private Byte validated;
    private Integer calification;

    @Id
    @Column(name = "User_mail")
    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    @Basic
    @Column(name = "validated")
    public Byte getValidated() {
        return validated;
    }

    public void setValidated(Byte validated) {
        this.validated = validated;
    }

    @Basic
    @Column(name = "calification")
    public Integer getCalification() {
        return calification;
    }

    public void setCalification(Integer calification) {
        this.calification = calification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TouristOperator that = (TouristOperator) o;
        return Objects.equals(userMail, that.userMail) && Objects.equals(validated, that.validated) && Objects.equals(calification, that.calification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userMail, validated, calification);
    }
}
