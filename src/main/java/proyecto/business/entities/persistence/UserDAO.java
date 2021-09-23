package proyecto.business.entities.persistence;

import org.springframework.stereotype.Repository;
import proyecto.business.entities.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserDAO extends CrudRepository<User,String>{

        /**
         * Retorna un usuario por mail si encuentra mas de una lanza una excepcion
         * @param mail
         * @return
         * * */
        User findByMail(String mail);
}
