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
    private int idCambio;


    public Cambio() {
    }

    public Cambio(Divisa divisaOrigen, Divisa divisaCambio, float cantidad, int idCambio) {
        this.divisaOrigen = divisaOrigen;
        this.divisaCambio = divisaCambio;
        this.cantidad = cantidad;
        this.idCambio = idCambio;
    }


    @Id
    @GeneratedValue
    public int getIdCambio() {
        return idCambio;
    }

    public void setIdCambio(int idCambio) {
        this.idCambio = idCambio;
    }

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