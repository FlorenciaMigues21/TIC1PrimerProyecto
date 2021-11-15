package proyecto.business.entities.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyecto.business.entities.User;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserDAO extends CrudRepository<User,String>{

        User findByMailAndPassword(String mail,String password);

        User findByMail(String mail);
}
