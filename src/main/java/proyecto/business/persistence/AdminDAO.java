package proyecto.business.persistence;

import org.springframework.transaction.annotation.Transactional;
import proyecto.business.entities.Admin;

@Transactional
public interface AdminDAO extends UserDAO<Admin>{
}
