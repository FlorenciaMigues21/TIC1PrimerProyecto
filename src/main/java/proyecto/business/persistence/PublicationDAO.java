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

    @Query("SELECT p.id_event FROM publication p, publication_lista_actividadades pla,typeofactivities toa,  group_of_activities goa,group_of_activities_lista_de_actividades goa_list,tourist t,tourist_intereses ti WHERE t.mail=:mail AND t.mail = ti.tourist_mail AND ti.intereses_name = toa.name AND toa.name = pla.lista_actividadades_name AND pla.publication_id_event = p.id_event AND toa.name = goa.group_name AND goa.group_id = goa_list.group_of_activities_group_id AND  goa_list.lista_de_actividades_name = pla.lista_actividadades_name AND pla.publication_id_event = p.id_event HAVING COUNT(p.id_event) < 7 ORDER BY p.calification DESC")
    Collection<Integer> findRecomendationsForUser(@Param("mail") String  mail);
}
