package proyecto.business.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;import java.util.List;
import java.util.Objects;

@Entity
public class User {
    private String password;
    private String username;
    private String mail;
    private Byte blocked;
    private int phone;
    private int type;
    private String country;
    private Date birthDate;
    private Byte vaccinated;

    public User() {
    }

    public User(String password, String username, String mail) {
        this.password = password;
        this.username = username;
        this.mail = mail;
    }

    public User(String password, String username, String mail, int phone) {
        this.password = password;
        this.username = username;
        this.mail = mail;
        this.phone = phone;
    }

    public User(String password, String username, String mail, Byte blocked, int phone) {
        this.password = password;
        this.username = username;
        this.mail = mail;
        this.blocked = blocked;
        this.phone = phone;
    }

    public User(String password, String username, String mail, Byte blocked, int phone, int type, String country, Date birthDate, Byte vaccinated) {
        this.password = password;
        this.username = username;
        this.mail = mail;
        this.blocked = blocked;
        this.phone = phone;
        this.type = type;
        this.country = country;
        this.birthDate = birthDate;
        this.vaccinated = vaccinated;
    }

    public User(String password, String username, String mail, Byte blocked, int phone, int type, String country, Date birthDate, Byte vaccinated, List<Typeofactivities> intereces) {
        this.password = password;
        this.username = username;
        this.mail = mail;
        this.blocked = blocked;
        this.phone = phone;
        this.type = type;
        this.country = country;
        this.birthDate = birthDate;
        this.vaccinated = vaccinated;
        this.Intereses = intereces;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "Blocked")
    public Byte getBlocked() {
        return blocked;
    }

    public void setBlocked(Byte blocked) {
        this.blocked = blocked;
    }

    @Basic
    @Column(name = "phone")
    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Basic
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    public Byte getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(Byte vaccinated) {
        this.vaccinated = vaccinated;
    }

    @Basic
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private Collection<Typeofactivities> Intereses;

    @ManyToMany
    public Collection<Typeofactivities> getIntereses() {
        return Intereses;
    }public void setIntereses(Collection<Typeofactivities> intereses) {
        Intereses = intereses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return phone == user.phone && Objects.equals(password, user.password) && Objects.equals(username, user.username) && Objects.equals(mail, user.mail) && Objects.equals(blocked, user.blocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, username, mail, blocked, phone);
    }

}
