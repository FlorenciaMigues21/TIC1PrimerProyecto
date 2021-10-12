package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.User;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.UserAlreadyExist;
import proyecto.business.exceptions.UserNotFound;
import proyecto.business.persistence.UserDAO;

@Service
public class UserManager<T extends User> {

    @Autowired
    UserDAO<T> controller;

    public T findByMail(String mail)throws InvalidUserInformation, UserNotFound {
        if(mail.equals("") || mail == null)
            throw new InvalidUserInformation("Informacion invalida");
        try{
            T turista = controller.findByMail(mail);
            return turista;
        }catch(Exception e){
            e.printStackTrace();
            throw new UserNotFound("Usuario no encontrado");
        }
    }

    public T logIn(T user) throws InvalidUserInformation, UserNotFound {
        if(user==null || user.getMail().equals("") || user.getMail() == null || user.getPassword().equals("") || user.getPassword() == null) {
            throw new InvalidUserInformation("Informacion invalida");
        }
        user = controller.findByMailAndPassword(user.getMail(), user.getPassword());
        if(user != null){
            return user;
        }
        throw new UserNotFound("Usuario no encontrado");
    }

    public void addUser(T user) throws InvalidUserInformation, UserAlreadyExist {
        if(user==null)
            throw new InvalidUserInformation("Informacion invalida");
        try{
            if(controller.findByMail(user.getMail()) != null)
                throw new UserAlreadyExist("Este mail ya esta registrado");
            else
                controller.save(user);
        }catch(Exception e){
            e.printStackTrace();
            throw new InvalidUserInformation("Error. No se ha podido crear el user");
        }
    }
}
