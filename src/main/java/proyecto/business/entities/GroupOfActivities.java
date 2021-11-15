package proyecto.business.entities;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class GroupOfActivities implements Serializable {
    private Typeofactivities group;
    private Collection<Typeofactivities> listaDeActividades;

    public GroupOfActivities() {
    }

    public GroupOfActivities(Typeofactivities group, Collection<Typeofactivities> listaDeActividades) {
        this.group = group;
        this.listaDeActividades = listaDeActividades;
    }

    @Id
    @NonNull
    @ManyToOne
    public Typeofactivities getGroup() {
        return group;
    }

    public void setGroup(Typeofactivities group) {
        this.group = group;
    }

    @NotNull
    @ManyToMany
    public Collection<Typeofactivities> getListaDeActividades() {
        return listaDeActividades;
    }

    public void setListaDeActividades(Collection<Typeofactivities> listaDeActividades) {
        this.listaDeActividades = listaDeActividades;
    }
}
