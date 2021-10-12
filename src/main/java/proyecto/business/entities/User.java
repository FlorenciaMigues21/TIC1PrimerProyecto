package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;import java.util.List;
import java.util.Objects;

@MappedSuperclass
@DiscriminatorColumn(name="mail", discriminatorType = DiscriminatorType.STRING)
public class User {
    private String password;
    private String username;
    private String mail;
    private boolean blocked;
    private int phone;
    private Country country;

    public User() {
    }

    public User(String password, String mail) {
        this.password = password;
        this.mail = mail;
    }

    public User(String password, String username, String mail, boolean blocked, int phone, Country country) {
        this.password = password;
        this.username = username;
        this.mail = mail;
        this.blocked = blocked;
        this.phone = phone;
        this.country = country;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @NotNull
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @NotNull
    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @NotNull
    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
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
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return blocked == user.blocked && phone == user.phone && password.equals(user.password) && username.equals(user.username) && mail.equals(user.mail) && country.equals(user.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, username, mail, blocked, phone, country);
    }
}
