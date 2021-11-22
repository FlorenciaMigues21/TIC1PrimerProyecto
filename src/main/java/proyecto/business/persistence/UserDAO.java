package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import proyecto.business.entities.User;

@NoRepositoryBean
public interface UserDAO<T extends User> extends CrudRepository<T,String>{

        T findByMailAndPassword(String mail,String password);

        T findByMail(String mail);

        T deleteByMail(String mail);

}
