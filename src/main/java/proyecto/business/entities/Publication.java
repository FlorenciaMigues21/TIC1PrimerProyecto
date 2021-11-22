package proyecto.business.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.hibernate.annotations.Formula;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import javax.persistence.*;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Data
@Entity
@Indexed(index = "idEvent")
@NormalizerDef(name="lowercase",
        filters = @TokenFilterDef(factory = LowerCaseFilterFactory.class))
public class Publication {

    @Id
    @GeneratedValue
    @Column(name = "id_event")
    private int idEvent;

    private Date Datefrom;
    private Date Dateto;
    private boolean validated;

    @Field(name = "title",index = Index.YES,analyze=Analyze.YES)
    @Field(normalizer = @Normalizer(definition = "lowercase"))
    private String title;

    @Field (name = "description",index = Index.YES,analyze=Analyze.YES)
    @Field(normalizer = @Normalizer(definition = "lowercase"))
    @NotNull
    private String description;

    private String ubication;

    @Nullable
    //@Formula("(select AVG(c.calification) from comentary c where c.publication_id_event == id)")
    private float calification;

    private boolean needVaccination;
    private int aforo;
    private int precio;

    private Set<Typeofactivities> listaActividadades;
    @ManyToOne
    @NotNull
    private TouristOperator operador;
    private Set<Photo> photoList;
    private String phone;
    private Set<Hygiene> medidas_de_higiene;
    private Set<IncludedInPublication> incluido;
    private int hourStart;
    private int hourFinish;
    private boolean uniqueReservation;
    private boolean reservationAvailable;

 /* @Field(name = "body")
    @Field(name = "bodyFiltered",
            analyzer = @Analyzer(definition = "stop"))
    private String body;  */

    @ContainedIn
    @OneToMany(mappedBy = "operador")
    private List<Publication> publication;


    public Publication() {
    }

    public Publication(Date datefrom, Date dateto, boolean validated, String title, String description, String ubication, boolean needVaccination, int aforo, int idEvent, int precio, Set<Typeofactivities> listaActividadades, TouristOperator operador, String phone, Set<Hygiene> medidas_de_higiene, Set<IncludedInPublication> incluido, int hourStart, int hourFinish, boolean uniqueReservation, boolean reservationAvailable) {
        Datefrom = datefrom;
        Dateto = dateto;
        this.validated = validated;
        this.title = title;
        this.description = description;
        this.ubication = ubication;
        this.needVaccination = needVaccination;
        this.aforo = aforo;
        this.idEvent = idEvent;
        this.precio = precio;
        this.listaActividadades = listaActividadades;
        this.operador = operador;
        this.phone = phone;
        this.medidas_de_higiene = medidas_de_higiene;
        this.incluido = incluido;
        this.hourStart = hourStart;
        this.hourFinish = hourFinish;
        this.uniqueReservation = uniqueReservation;
        this.reservationAvailable = reservationAvailable;
    }

    public Publication(Date datefrom, Date dateto, boolean validated, String title, String description, String ubication, boolean needVaccination, int aforo, int idEvent, int precio, Set<Typeofactivities> listaActividadades, TouristOperator operador, Set<Photo> photoList, String phone, Set<Hygiene> medidas_de_higiene, Set<IncludedInPublication> incluido, int hourStart, int hourFinish, boolean uniqueReservation, boolean reservationAvailable) {
        Datefrom = datefrom;
        Dateto = dateto;
        this.validated = validated;
        this.title = title;
        this.description = description;
        this.ubication = ubication;
        this.needVaccination = needVaccination;
        this.aforo = aforo;
        this.idEvent = idEvent;
        this.precio = precio;
        this.listaActividadades = listaActividadades;
        this.operador = operador;
        this.photoList = photoList;
        this.phone = phone;
        this.medidas_de_higiene = medidas_de_higiene;
        this.incluido = incluido;
        this.hourStart = hourStart;
        this.hourFinish = hourFinish;
        this.uniqueReservation = uniqueReservation;
        this.reservationAvailable = reservationAvailable;
    }

    @NonNull
    @Temporal(TemporalType.DATE)
    public Date getDatefrom() {
        return Datefrom;
    }

    public void setDatefrom(Date from) {
        this.Datefrom = from;
    }

    @NotNull
    @Temporal(TemporalType.DATE)
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

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    @Access(AccessType.PROPERTY)
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER,targetEntity = Typeofactivities.class)
    @NotNull
    @JoinTable(name = "publication_lista_actividadades",
            joinColumns = @JoinColumn(name = "publication_id_event", referencedColumnName = "id_event"), inverseJoinColumns = @JoinColumn(name = "lista_actividadades_name",referencedColumnName = "name"))
    public Set<Typeofactivities> getListaActividadades() {
        return listaActividadades;
    }

    public void setListaActividadades(Set<Typeofactivities> listaActividadades) {
        this.listaActividadades = listaActividadades;
    }


    public TouristOperator getOperador() {
        return operador;
    }

    public void setOperador(TouristOperator operador) {
        this.operador = operador;
    }

    @Access(AccessType.PROPERTY)
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER,targetEntity = Photo.class)
    @NotNull
    @JoinTable(name = "publication_photo_list", joinColumns = @JoinColumn(name = "publication_id_event",referencedColumnName = "id_event"), inverseJoinColumns = @JoinColumn(name = "photo_list_id_photo",referencedColumnName = "id_photo"))
    public Set<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(Set<Photo> photoList) {
        this.photoList = photoList;
    }

    public void addPhotoToList(Photo photo) {
        this.photoList.add(photo);
    }

    @NotNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Access(AccessType.PROPERTY)
    @NotNull
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Hygiene.class)
    @JoinTable(name = "publication_medidas_de_higiene", joinColumns = @JoinColumn(name = "publication_id_event",referencedColumnName = "id_event"), inverseJoinColumns = @JoinColumn(name = "medidas_de_higiene_id",referencedColumnName = "id"))
    public Set<Hygiene> getMedidas_de_higiene() {
        return medidas_de_higiene;
    }

    public void setMedidas_de_higiene(Set<Hygiene> medidas_de_higiene) {
        this.medidas_de_higiene = medidas_de_higiene;
    }
    @Access(AccessType.PROPERTY)
    @NotNull
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = IncludedInPublication.class )
    @JoinTable(name = "publication_incluido", joinColumns = @JoinColumn(name = "publication_id_event",referencedColumnName = "id_event"), inverseJoinColumns = @JoinColumn(name = "incluido_id",referencedColumnName = "id"))
    public Set<IncludedInPublication> getIncluido() {
        return incluido;
    }

    public void setIncluido(Set<IncludedInPublication> incluido) {
        this.incluido = incluido;
    }

    @Nullable
    public int getHourStart() {
        return hourStart;
    }

    public void setHourStart(int hourStart) {
        this.hourStart = hourStart;
    }

    @Nullable
    public int getHourFinish() {
        return hourFinish;
    }

    public void setHourFinish(int hourFinish) {
        this.hourFinish = hourFinish;
    }

    @NotNull
    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @NotNull
    public boolean isUniqueReservation() {
        return uniqueReservation;
    }

    public void setUniqueReservation(boolean uniqueReservation) {
        this.uniqueReservation = uniqueReservation;
    }

    public float getCalification() {
        return calification;
    }

    public void setCalification(float calification) {
        this.calification = calification;
    }

    @NotNull
    public boolean isReservationAvailable() {
        return reservationAvailable;
    }

    public void setReservationAvailable(boolean reservationAvailable) {
        this.reservationAvailable = reservationAvailable;
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
        return this.ubication == null || this.ubication.equals("") || (this.title == null || this.title.equals("") || (this.listaActividadades == null || (this.Datefrom == null || (this.Dateto == null || (this.description == null ? true : this.description.equals("") ? true : false)))));
    }

}