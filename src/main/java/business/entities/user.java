package business.entities;


import business.exceptions.InvalidUserInformation;
import business.exceptions.UserAlreadyExist;
import business.exceptions.UserNotFound;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class user {

    @Id
    private String mail;
    private String username;
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

    public boolean singupUser(user userobj) throws UserAlreadyExist, InvalidUserInformation {
        if(userobj.mail == null || userobj.mail.equals("") || userobj.password == null || userobj.password.equals("") || userobj.username == null || userobj.username.equals("")){
            throw new InvalidUserInformation("Datos del usuario vacios");
        }else {
            Configuration con = new Configuration().configure().addAnnotatedClass(user.class);
            SessionFactory sf = con.buildSessionFactory();
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();
            session.save(userobj);
            tx.commit();
            return true;
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
