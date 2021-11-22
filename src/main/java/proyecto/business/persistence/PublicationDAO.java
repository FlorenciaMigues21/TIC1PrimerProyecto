package proyecto.business.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import proyecto.business.entities.Publication;
import proyecto.business.entities.TouristOperator;

import java.util.Collection;

public interface PublicationDAO extends CrudRepository<Publication,Integer > {

    //TODO HAY QUE MODIFICAR EL FIND ALL PARA QUE SOLO TRAIGA LOS QUE EL ALGORITMO DE BUSQUEDA NECESITE
    Collection<Publication> findAll();

    Collection<Publication> findAllByOperador(TouristOperator operador);

    Collection<Publication> findAllByValidated(boolean validated);

    Publication findByIdEvent(int idEvent);

    @Query(value = "SELECT p.id_event FROM publication p, publication_lista_actividadades pla,typeofactivities toa,tourist t,tourist_intereses ti WHERE t.mail=:mail AND t.mail = ti.tourist_mail AND ti.intereses_name = toa.name AND toa.name = pla.lista_actividadades_name AND pla.publication_id_event = p.id_event group by p.id_event ORDER BY p.calification DESC limit 7", nativeQuery = true)
    Collection<Integer> findRecomendationsForUser(@Param("mail") String  mail);

    @Query(value =  "SELECT p.id_event FROM publication p, publication_lista_actividadades pla,typeofactivities toa,tourist t,tourist_intereses ti,group_of_activities goa,group_of_activities_lista_de_actividades goa_list WHERE t.mail=:mail AND t.mail = ti.tourist_mail AND ti.intereses_name = goa.group_name AND goa.group_id = goa_list.group_of_activities_group_id AND goa_list.lista_de_actividades_name = toa.name AND toa.name = pla.lista_actividadades_name AND pla.publication_id_event = p.id_event group by p.id_event ORDER BY p.calification DESC limit 7", nativeQuery = true)
    Collection<Integer> findRecomendationsForUserByGroupBy(@Param("mail") String  mail);

    @Query(value = "SELECT p.id_event FROM publication p, publication_lista_actividadades pla,typeofactivities toa,tourist t,tourist_intereses ti,group_of_activities goa,group_of_activities_lista_de_actividades goa_list WHERE t.mail=:mail AND t.mail = ti.tourist_mail AND ti.intereses_name = goa.group_name AND goa.group_name =:group AND goa.group_id = goa_list.group_of_activities_group_id AND goa_list.lista_de_actividades_name = toa.name AND toa.name = pla.lista_actividadades_name AND pla.publication_id_event = p.id_event group by p.id_event ORDER BY p.calification DESC limit 7", nativeQuery = true)
    Collection<Integer> findRecomendationsForUserByGroupByEspecific(@Param("mail") String  mail,@Param("group") String groupBy);
}
