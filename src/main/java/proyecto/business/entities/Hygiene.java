package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Hygiene {
    @Id
    @GeneratedValue
    private int id;
    private String medidas;

    public Hygiene() {
    }

    public Hygiene(String medidas) {
        this.medidas = medidas;
    }

    @NotNull
    public String getMedidas() {
        return medidas;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }
}
