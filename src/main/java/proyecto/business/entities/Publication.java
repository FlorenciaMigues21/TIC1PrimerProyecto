package proyecto.business.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Publication {
    private int idPublication;
    private Timestamp dateFrom;
    private Timestamp dateTo;
    private byte validated;
    private String paragraph;
    private String title;
    private String ubication;
    private Integer calification;
    private byte needVaccination;
    private Integer aforo;
    private Integer participants;
    private Integer price;

    @Id
    @Column(name = "id_publication")
    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }

    @Basic
    @Column(name = "DateFrom")
    public Timestamp getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Basic
    @Column(name = "DateTo")
    public Timestamp getDateTo() {
        return dateTo;
    }

    public void setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
    }

    @Basic
    @Column(name = "Validated")
    public byte getValidated() {
        return validated;
    }

    public void setValidated(byte validated) {
        this.validated = validated;
    }

    @Basic
    @Column(name = "Paragraph")
    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    @Basic
    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "Ubication")
    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    @Basic
    @Column(name = "Calification")
    public Integer getCalification() {
        return calification;
    }

    public void setCalification(Integer calification) {
        this.calification = calification;
    }

    @Basic
    @Column(name = "NeedVaccination")
    public byte getNeedVaccination() {
        return needVaccination;
    }

    public void setNeedVaccination(byte needVaccination) {
        this.needVaccination = needVaccination;
    }

    @Basic
    @Column(name = "Aforo")
    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    @Basic
    @Column(name = "Participants")
    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    @Basic
    @Column(name = "Price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return idPublication == that.idPublication && validated == that.validated && needVaccination == that.needVaccination && Objects.equals(dateFrom, that.dateFrom) && Objects.equals(dateTo, that.dateTo) && Objects.equals(paragraph, that.paragraph) && Objects.equals(title, that.title) && Objects.equals(ubication, that.ubication) && Objects.equals(calification, that.calification) && Objects.equals(aforo, that.aforo) && Objects.equals(participants, that.participants) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPublication, dateFrom, dateTo, validated, paragraph, title, ubication, calification, needVaccination, aforo, participants, price);
    }
}
