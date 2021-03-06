package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.*;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.UserAlreadyExist;
import proyecto.business.exceptions.UserNotFound;
import proyecto.business.persistence.AdminDAO;
import proyecto.business.persistence.TouristDAO;
import proyecto.business.persistence.TouristOperatorDAO;

import java.util.Collection;

@Service
public class UserManager<T extends User> {

    @Autowired
    TouristDAO touristController;

    @Autowired
    AdminDAO adminController;

    @Autowired
    TouristOperatorDAO touristOperatorController;

    @Autowired
    PublicationManager publicationManager;

    public T findByMail(String mail)throws InvalidUserInformation, UserNotFound {
        if(mail.equals("") || mail == null)
            throw new InvalidUserInformation("Informacion invalida");
        try{
            T user = null;
            user = user instanceof Tourist ? (T) touristController.findByMail(mail) : user instanceof TouristOperator ? (T) adminController.findByMail(mail) : (T) touristOperatorController.findByMail(mail);
            return user;
        }catch(Exception e){
            e.printStackTrace();
            throw new UserNotFound("Usuario no encontrado");
        }
    }

    public T logIn(T user) throws InvalidUserInformation, UserNotFound {
        if(user==null || user.getMail().equals("") || user.getMail() == null || user.getPassword().equals("") || user.getPassword() == null) {
            throw new InvalidUserInformation("Informacion invalida");
        }
        user = user instanceof Tourist ? (T) touristController.findByMailAndPassword(user.getMail(),user.getPassword()) : user instanceof TouristOperator ? (T) touristOperatorController.findByMailAndPassword(user.getMail(),user.getPassword()) : (T) adminController.findByMailAndPassword(user.getMail(),user.getPassword());
        if(user != null){
            return user;
        }
        throw new UserNotFound("Usuario no encontrado");
    }

    public void addUser(T user) throws InvalidUserInformation, UserAlreadyExist {
        if(user==null)
            throw new InvalidUserInformation("Informacion invalida");
        try{
            if (user instanceof Tourist)
                touristController.save((Tourist) user);
            if (user instanceof TouristOperator)
                touristOperatorController.save((TouristOperator) user);
            if (user instanceof Admin)
                adminController.save((Admin) user);
        }catch(Exception e){
            e.printStackTrace();
            throw new InvalidUserInformation("Error. No se ha podido crear el user");
        }
    }

    public void deleteUser(T user) throws InvalidUserInformation, UserAlreadyExist {
        if(user==null)
            throw new InvalidUserInformation("Informacion invalida");
        try{
            if (user instanceof Tourist)
                touristController.delete( (Tourist) user);
            if (user instanceof TouristOperator) {
                Collection<Publication> publications = publicationManager.getPublicationFromOperator((TouristOperator) user);
                for (Publication publication : publications) {
                    publicationManager.deletePublication(publication);
                }
                touristOperatorController.delete((TouristOperator) user);
            }
            if (user instanceof Admin)
                adminController.delete((Admin) user);
        }catch(Exception e){
            e.printStackTrace();
            throw new InvalidUserInformation("Error. No se ha podido crear el user");
        }
    }
}
