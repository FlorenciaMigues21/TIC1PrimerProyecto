package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import proyecto.business.entities.User;

@NoRepositoryBean
public interface UserDAO<T extends User> extends CrudRepository<T,String>{

        T findByMailAndPassword(String mail,String password);

        T findByMail(String mail);

}
