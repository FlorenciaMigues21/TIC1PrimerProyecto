package proyecto.business.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Tourist {
    private Byte vaccinated;
    private Date birthdate;
    private String userMail;

    @Basic
    @Column(name = "vaccinated")
    public Byte getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(Byte vaccinated) {
        this.vaccinated = vaccinated;
    }

    @Basic
    @Column(name = "birthdate")
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Id
    @Column(name = "User_mail")
    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourist tourist = (Tourist) o;
        return Objects.equals(vaccinated, tourist.vaccinated) && Objects.equals(birthdate, tourist.birthdate) && Objects.equals(userMail, tourist.userMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vaccinated, birthdate, userMail);
    }
}
