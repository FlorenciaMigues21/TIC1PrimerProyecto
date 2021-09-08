package business.entities;


import business.exceptions.InvalidUserInformation;
import business.exceptions.UserAlreadyExist;
import business.exceptions.UserNotFound;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class user {

    @Id
    @Column(name = "mail")
    private String mail;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    public user(){

    }

    public user(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public user(String mail, String username, String password) {
        this.mail = mail;
        this.username = username;
        this.password = password;
    }

    public boolean loginUser(String mail,String password) throws UserNotFound {
        return true;
    }

    public static boolean singupUser(String mail, String password, String username, String passwordafirmation) throws UserAlreadyExist, InvalidUserInformation {
        if(mail == null || mail.equals("") || password == null || password.equals("") || username == null || username.equals("")){
            throw new InvalidUserInformation("Datos del usuario vacios");
        }else {
            if(password.equals(passwordafirmation)) {
                Configuration con = new Configuration().configure().addAnnotatedClass(user.class);
                SessionFactory sf = con.buildSessionFactory();
                Session session = sf.openSession();
                Transaction tx = session.beginTransaction();
                session.save(new user(mail,username,password));
                tx.commit();
                return true;
            }else{
                throw new InvalidUserInformation("Las contraseñas no coinciden");
            }
        }
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
