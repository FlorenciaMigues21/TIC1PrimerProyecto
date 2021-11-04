package proyecto.business.entities;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Tourist extends User{
    private boolean vaccinated;
    private Date birthdate;
    private Collection<Typeofactivities> intereses;

    public Tourist() {
    }

    public Tourist(String password, String username) {
        super(password, username);
    }

    public Tourist(String password, String username, String mail, boolean blocked, String phone, Country country, String id) {
        super(password, username, mail, blocked, phone, country, id);
    }

    public Tourist(String password, String username, String mail, boolean blocked, String phone, Country country, String id, boolean vaccinated, Date birthdate) {
        super(password, username, mail, blocked, phone, country, id);
        this.vaccinated = vaccinated;
        this.birthdate = birthdate;
    }

    public Tourist(String password, String username, String mail, boolean blocked, String phone, Country country, String id, boolean vaccinated, Date birthdate, Collection<Typeofactivities> intereses) {
        super(password, username, mail, blocked, phone, country, id);
        this.vaccinated = vaccinated;
        this.birthdate = birthdate;
        this.intereses = intereses;
    }

    @NotNull
    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    @NotNull
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotNull
    public Collection<Typeofactivities> getIntereses() {
        return intereses;
    }

    public void setIntereses(Collection<Typeofactivities> intereses) {
        this.intereses = intereses;
    }

    @Override
    public String toString() {
        return "Tourist{"+ super.getMail() +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tourist tourist = (Tourist) o;
        return vaccinated == tourist.vaccinated && birthdate.equals(tourist.birthdate) && intereses.equals(tourist.intereses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vaccinated, birthdate, intereses);
    }
}
