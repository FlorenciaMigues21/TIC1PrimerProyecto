package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.Country;
import proyecto.business.entities.User;
import proyecto.business.entities.persistence.UserDAO;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.UserAlreadyExist;
import proyecto.business.exceptions.UserNotFound;

import java.sql.Date;

@Service
public class UserManager {

    @Autowired
    private UserDAO iUser;

    public User getUserByMail(String mail){
        return iUser.findByMail(mail);
    }

    public void addUser(String mail, String password,
                        String confirmPassword, String userName,
                        byte vaccinated,byte blocked,int phone,
                        int userType, String country, Date birthDate) throws UserAlreadyExist, InvalidUserInformation{
        if(mail == null || mail.equals("") || password == null || password.equals("") || confirmPassword == null || confirmPassword.equals("") || userName == null || userName.equals("")){
            throw new InvalidUserInformation("Información del usuario invalida");
        }
        if(!password.equals(confirmPassword)){
            throw new InvalidUserInformation("Contraseñas distintas");
        }
        if(iUser.findByMail(mail) != null)
            throw new UserAlreadyExist("Usuario ya existente");
        else {
            User user = new User(password,userName,mail,blocked,phone,userType,country,birthDate,vaccinated);
            iUser.save(user);
        }
    }

    public User logInUser(String mail,String password) throws InvalidUserInformation , UserNotFound {
        if(mail == null || mail.equals("") || password == null || password.equals("")){
            throw new InvalidUserInformation("Información del usuario invalida");
        }
        User usuario = iUser.findByMailAndPassword(mail,password);
        if (usuario != null){
            return usuario;
        }
        if(iUser.findByMail(mail)!=null){
            throw new InvalidUserInformation("Contraseña incorrecta");
        }
        throw new UserNotFound("Este usuario no existe");
    }
}
