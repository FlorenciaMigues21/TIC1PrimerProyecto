package proyecto.business.entities;

import com.sun.istack.NotNull;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.UserAlreadyExist;
import proyecto.business.exceptions.UserNotFound;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NamedQuery(name = "User.login",query = "SELECT u FROM User u WHERE u.mail = ?1 AND u.password = ?2")
@NamedQuery(name = "User.search", query = "SELECT count(u) FROM User u WHERE u.mail = ?1")
public class User {
    private String mail;
    private String password;
    private String username;

    public User() {

    }

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public User(String mail, String password, String username) {
        this.mail = mail;
        this.password = password;
        this.username = username;
    }

    @Id
    @NotNull
    @Column(name = "mail", unique = true)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @NotNull
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User loginUser() throws UserNotFound {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        User u = new User();

        try {

            transaction.begin();

            TypedQuery<User> empLogin = entityManager.createNamedQuery("User.login", User.class);
            empLogin.setParameter(1,this.mail);
            empLogin.setParameter(2,this.password);
            u = empLogin.getSingleResult();

            transaction.commit();
            return u;
        }catch(Exception e){
            throw new UserNotFound("User not found");
        }finally{

            if(transaction.isActive()){
                transaction.rollback();
            }

            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public static boolean singupUser(String mail, String password, String username, String passwordafirmation) throws UserAlreadyExist, InvalidUserInformation {
        if(mail == null || mail.equals("") || password == null || password.equals("") || username == null || username.equals("")){
            throw new InvalidUserInformation("Datos del usuario vacios");
        }else {
            if(password.equals(passwordafirmation)) {
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                EntityTransaction transaction = entityManager.getTransaction();
                User usuario;
                try {
                    transaction.begin();

                    usuario = new User(mail, password, username);
                    entityManager.persist(usuario);

                    transaction.commit();
                    return true;
                }catch(Exception e){ //VERIFICAMOS SI EL USUARIO YA ESTABA REGISTRADO O ES UN ERROR EN LA CONEXION CON LA BASE DE DATOS
                    int cantidad = 0;
                    try{
                        transaction.begin();

                        TypedQuery<User> empSearch = entityManager.createNamedQuery("User.login", User.class);
                        empSearch.setParameter(1,mail);
                        cantidad = empSearch.getResultList().size();

                        transaction.commit();
                    }catch(Exception ex){
                        throw e;
                    }finally{
                        if(transaction.isActive()){
                            transaction.rollback();
                        }

                        entityManager.close();
                        entityManagerFactory.close();

                        if(cantidad > 0 ){
                            throw new UserAlreadyExist("The user with the mail " + mail + " already has an account");
                        }else{
                            throw e;
                        }
                    }

                }finally{
                    if(transaction.isActive()){
                        transaction.rollback();
                    }

                    entityManager.close();
                    entityManagerFactory.close();
                }
            }else{
                throw new InvalidUserInformation("Las contraseñas no coinciden");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (mail != null ? !mail.equals(user.mail) : user.mail != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mail != null ? mail.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
