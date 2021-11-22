package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class IncludedInPublication {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private String incluido;

    public IncludedInPublication() {
    }

    public IncludedInPublication(String incluido) {
        this.incluido = incluido;
    }

    @NotNull
    public String getIncluido() {
        return incluido;
    }

    public void setIncluido(String incluido) {
        this.incluido = incluido;
    }
}
