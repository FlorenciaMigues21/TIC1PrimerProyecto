package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
@DiscriminatorColumn(name="mail", discriminatorType = DiscriminatorType.STRING)
public class User {
    private String password;
    private String username;
    private String mail;
    private boolean blocked;
    private String phone;

    public User() {
    }

    public User(String password, String mail) {
        this.password = password;
        this.mail = mail;
    }

    public User(String password, String username, String mail, boolean blocked, String phone) {
        this.password = password;
        this.username = username;
        this.mail = mail;
        this.blocked = blocked;
        this.phone = phone;
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
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return blocked == user.blocked && phone == user.phone && password.equals(user.password) && username.equals(user.username) && mail.equals(user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, username, mail, blocked, phone);
    }

    public boolean verifyObjectIncomplete(){
        return this.mail == null ? true : this.mail.equals("") ? true : this.password == null ? true : this.password.equals("") ? true : this.username == null ? true : this.username.equals("") ? true : false;
    }
}
