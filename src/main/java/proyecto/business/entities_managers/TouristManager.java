package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import proyecto.business.entities.Tourist;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.UserAlreadyExist;
import proyecto.business.exceptions.UserNotFound;
import proyecto.business.persistence.TouristDAO;

@Service
public class TouristManager {

    @Autowired
    TouristDAO controller;

    public Tourist findTouristByMail(String mail)throws InvalidUserInformation , UserNotFound{
        if(mail.equals("") || mail == null)
            throw new InvalidUserInformation("Informacion invalida");
        try{
            Tourist turista = controller.findByMail(mail);
            return turista;
        }catch(Exception e){
            e.printStackTrace();
            throw new UserNotFound("Usuario no encontrado");
        }
    }

    public Tourist logInTourist(Tourist turista) throws InvalidUserInformation, UserNotFound {
        if(turista==null || turista.getMail().equals("") || turista.getMail() == null || turista.getPassword().equals("") || turista.getPassword() == null) {
            throw new InvalidUserInformation("Informacion invalida");
        }
        turista = controller.findByMailAndPassword(turista.getMail(), turista.getPassword());
        if(turista != null){
            return turista;
        }
        throw new UserNotFound("Usuario no encontrado");
    }

    public void addTourist(Tourist turista) throws InvalidUserInformation,UserAlreadyExist {
        if(turista==null)
            throw new InvalidUserInformation("Informacion invalida");
        try{
            if(controller.findByMail(turista.getMail()) != null)
                throw new UserAlreadyExist("Este mail ya esta registrado");
            else
                controller.save(turista);
        }catch(Exception e){
            e.printStackTrace();
            throw new InvalidUserInformation("Error. No se ha podido crear el turista");
        }
    }

}
