package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.User;

@Service
public class UserController {

    @Autowired
    UserManager manager;

    public UserController(){

    }

    public User getUser(String mail){
        return manager.getUserByMail(mail);
    }

}
