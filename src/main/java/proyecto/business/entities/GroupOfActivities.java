package proyecto.business.entities;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;

@Entity
public class GroupOfActivities {
    private Typeofactivities group;
    private ArrayList<Typeofactivities> listaDeActividades;

    public GroupOfActivities() {
    }

    public GroupOfActivities(Typeofactivities group, ArrayList<Typeofactivities> listaDeActividades) {
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
    public ArrayList<Typeofactivities> getListaDeActividades() {
        return listaDeActividades;
    }

    public void setListaDeActividades(ArrayList<Typeofactivities> listaDeActividades) {
        this.listaDeActividades = listaDeActividades;
    }
}
