package proyecto.business.entities;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class GroupOfActivities implements Serializable {
    @Id
    @GeneratedValue
    private int groupId;

    @NonNull
    @ManyToOne
    private Typeofactivities group;


    @NotNull
    @ManyToMany
    private Collection<Typeofactivities> listaDeActividades;

    public GroupOfActivities() {
    }

    public GroupOfActivities(Typeofactivities group, Collection<Typeofactivities> listaDeActividades) {
        this.group = group;
        this.listaDeActividades = listaDeActividades;
    }


    public Typeofactivities getGroup() {
        return group;
    }

    public void setGroup(Typeofactivities group) {
        this.group = group;
    }

    public Collection<Typeofactivities> getListaDeActividades() {
        return listaDeActividades;
    }

    public void setListaDeActividades(Collection<Typeofactivities> listaDeActividades) {
        this.listaDeActividades = listaDeActividades;
    }
}
