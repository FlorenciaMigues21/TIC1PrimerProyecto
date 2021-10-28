package proyecto.business.entities;

import javax.persistence.Entity;

@Entity
public class Admin extends User{

    public Admin() {
    }

    public Admin(String password, String mail) {
        super(password, mail);
    }

    public Admin(String password, String username, String mail, boolean blocked, int phone, Country country) {
        super(password, username, mail, blocked, phone, country);
    }
}
