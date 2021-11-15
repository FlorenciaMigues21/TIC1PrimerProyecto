package proyecto.business.persistence;

import org.springframework.data.repository.CrudRepository;
import proyecto.business.entities.GroupOfActivities;

import java.util.Collection;

public interface GroupOfActivitiesDAO extends CrudRepository<GroupOfActivities,String> {

    Collection<GroupOfActivities> findAll();

    GroupOfActivities findAllByGroup(String group);

}
