package proyecto.business.persistence;

import org.dom4j.tree.AbstractEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.User;

import javax.persistence.Entity;
import javax.transaction.Transactional;

public interface UserDAO<T> extends CrudRepository<T,String>{

        T findByMailAndPassword(String mail,String password);

        T findByMail(String mail);
}
