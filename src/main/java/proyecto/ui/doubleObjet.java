package proyecto.ui;

import proyecto.business.entities.Publication;
import proyecto.business.entities.Tourist;

public class doubleObjet {

    private Tourist turista;
    private String item;
    private Publication publicacion;

    public doubleObjet() {
        this.turista = null;
        this.item = null;
        this.publicacion = null;
    }

    public Tourist getTurista() {
        return turista;
    }

    public String getItem() {
        return item;
    }

    public void setTurista(Tourist turista) {
        this.turista = turista;
    }

    public Publication getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publication publicacion) {
        this.publicacion = publicacion;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
