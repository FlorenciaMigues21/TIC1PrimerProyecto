package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Cambio implements Serializable {
    private Divisa divisaOrigen;
    private Divisa divisaCambio;
    private float cantidad;


    public Cambio() {
    }

    public Cambio(Divisa divisaOrigen, Divisa divisaCambio, float cantidad) {
        this.divisaOrigen = divisaOrigen;
        this.divisaCambio = divisaCambio;
        this.cantidad = cantidad;
    }

    @Id
    @NotNull
    @ManyToOne
    public Divisa getDivisaOrigen() {
        return divisaOrigen;
    }

    public void setDivisaOrigen(Divisa divisaOrigen) {
        this.divisaOrigen = divisaOrigen;
    }

    @NotNull
    @ManyToOne
    public Divisa getDivisaCambio() {
        return divisaCambio;
    }

    public void setDivisaCambio(Divisa divisaCambio) {
        this.divisaCambio = divisaCambio;
    }

    @NotNull
    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
}
