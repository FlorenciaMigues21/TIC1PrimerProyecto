package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Divisa {
    private String nombre;

    public Divisa() {
    }

    public Divisa(String nombre) {
        this.nombre = nombre;
    }

    @Id
    @NotNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
