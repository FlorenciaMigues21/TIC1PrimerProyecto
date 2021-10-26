package proyecto.business.entities;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Publication {
    private Date Datefrom;
    private Date Dateto;
    private boolean validated;
    private String title;
    private String description;
    private String ubication;
    //private float calification;
    private boolean needVaccination;
    private int aforo;
    private int idEvent;
    private int participants;
    private Divisa divisa;
    private float cantidad;
    private Collection<Typeofactivities> listaActividadades;
    private TouristOperator operador;
    private Collection<Photo> photoList;

    public Publication() {
    }

    public Publication(Date datefrom, Date dateto, boolean validated, String title, String description, String ubication, boolean needVaccination, int aforo, int idEvent, int participants, Divisa divisa, float cantidad, Collection<Typeofactivities> listaActividadades, TouristOperator operador) {
        Datefrom = datefrom;
        Dateto = dateto;
        this.validated = validated;
        this.title = title;
        this.description = description;
        this.ubication = ubication;
        this.needVaccination = needVaccination;
        this.aforo = aforo;
        this.idEvent = idEvent;
        this.participants = participants;
        this.divisa = divisa;
        this.cantidad = cantidad;
        this.listaActividadades = listaActividadades;
        this.operador = operador;
    }

    public Publication(Date datefrom, Date dateto, boolean validated, String title, String description, String ubication, boolean needVaccination, int aforo, int idEvent, int participants, Divisa divisa, float cantidad, Collection<Typeofactivities> listaActividadades, TouristOperator operador, Collection<Photo> photoList) {
        Datefrom = datefrom;
        Dateto = dateto;
        this.validated = validated;
        this.title = title;
        this.description = description;
        this.ubication = ubication;
        this.needVaccination = needVaccination;
        this.aforo = aforo;
        this.idEvent = idEvent;
        this.participants = participants;
        this.divisa = divisa;
        this.cantidad = cantidad;
        this.listaActividadades = listaActividadades;
        this.operador = operador;
        this.photoList = photoList;
    }

    @NonNull
    public Date getDatefrom() {
        return Datefrom;
    }

    public void setDatefrom(Date from) {
        this.Datefrom = from;
    }

    @NotNull
    public Date getDateto() {
        return Dateto;
    }

    public void setDateto(Date to) {
        this.Dateto = to;
    }

    @NotNull
    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    /* TODO ESTE ES UN DATO DERIVADO
     * FALTA PONER LA FORMULA PARA SACAR EL VALOR DENTRO DE LA BASE DE DATOS
     *
    @Formula("")
    public float getCalification() {
        return calification;
    }

    public void setCalification(float calification) {
        this.calification = calification;
    }*/

    @NotNull
    public boolean isNeedVaccination() {
        return needVaccination;
    }

    public void setNeedVaccination(boolean needVaccination) {
        this.needVaccination = needVaccination;
    }

    @NotNull
    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    @Id
    @NotNull
    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    @NotNull
    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    @ManyToOne
    @NotNull
    public Divisa getDivisa() {
        return divisa;
    }

    public void setDivisa(Divisa divisa) {
        this.divisa = divisa;
    }

    @NotNull
    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    @ManyToMany
    @NotNull
    public Collection<Typeofactivities> getListaActividadades() {
        return listaActividadades;
    }

    public void setListaActividadades(Collection<Typeofactivities> listaActividadades) {
        this.listaActividadades = listaActividadades;
    }

    @ManyToOne
    @NotNull
    public TouristOperator getOperador() {
        return operador;
    }

    public void setOperador(TouristOperator operador) {
        this.operador = operador;
    }

    @ManyToMany
    @NotNull
    public Collection<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(Collection<Photo> photoList) {
        this.photoList = photoList;
    }

    public void addPhotoToList(Photo photo) {
        this.photoList.add(photo);
    }

    @Override
    public String toString() {
        return "Publications{" +
                "from=" + Datefrom +
                ", to=" + Dateto +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public boolean verifyObjectIncomplete(){
        return this.ubication == null || this.ubication.equals("") ? true : this.title == null || this.title.equals("") ? true : this.listaActividadades == null ? true : this.Datefrom == null ? true : this.Dateto == null ? true : this.description == null ? true : this.description.equals("") ? true : this.divisa == null ? true : false;
    }

}
