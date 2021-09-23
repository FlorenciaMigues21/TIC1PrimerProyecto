package proyecto.business.entities_managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.business.entities.User;
import proyecto.business.entities.persistence.UserDAO;

@Service
public class UserManager {

    @Autowired
    private UserDAO iUser;

    public User getUserByMail(String mail){
        return iUser.findByMail(mail);
    }

    public void addUser(String mail, String username, String password){
        //if(mail == null || mail.equals(""))
    }

}
