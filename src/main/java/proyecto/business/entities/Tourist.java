package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Tourist extends User{
    private boolean vaccinated;
    private Date birthdate;
    private Collection<Typeofactivities> intereses;
    private String id;
    private Country country;

    //TODO CREAR UN QUERY QUE BUSQUE EN TODOS LOS GROUP OF ACTIVITIES DE LOS CUALES SAQUE PARA RECOMENDAR, LUEGO PARA RECOMENDAR UN TOP DE DATOS FILTRAR POR CALIFICACION Y HACER UN TOP

    public Tourist() {
    }

    public Tourist(String password, String username) {
        super(password, username);
    }

    public Tourist(String password, String username, String mail, boolean blocked, String phone) {
        super(password, username, mail, blocked, phone);
    }

    public Tourist(String password, String username, String mail, boolean blocked, String phone, boolean vaccinated, Date birthdate, String id, Country country) {
        super(password, username, mail, blocked, phone);
        this.vaccinated = vaccinated;
        this.birthdate = birthdate;
        this.id = id;
        this.country = country;
    }

    public Tourist(String password, String username, String mail, boolean blocked, String phone, boolean vaccinated, Date birthdate, Collection<Typeofactivities> intereses, String id, Country country) {
        super(password, username, mail, blocked, phone);
        this.vaccinated = vaccinated;
        this.birthdate = birthdate;
        this.intereses = intereses;
        this.id = id;
        this.country = country;
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


    @NotNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotNull
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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
