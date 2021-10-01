package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.User;
import proyecto.business.entities.persistence.UserDAO;
import proyecto.business.exceptions.InvalidUserInformation;
import proyecto.business.exceptions.UserAlreadyExist;

@Service
public class UserManager {

    @Autowired
    private UserDAO iUser;

    public User getUserByMail(String mail){
        return iUser.findByMail(mail);
    }

    public void addUser(User user) throws UserAlreadyExist, InvalidUserInformation{
        if(iUser.findByMail(user.getMail()) != null)
            throw new UserAlreadyExist("Usuario ya existente");
        else
            iUser.save(user);
    }

}
