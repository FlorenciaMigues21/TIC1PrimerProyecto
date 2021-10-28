package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.persistence.Entity;

@Entity
public class TouristOperator extends User{
    private boolean validated;
    private float calification;

    public TouristOperator() {
    }

    public TouristOperator(String password, String mail) {
        super(password, mail);
    }

    public TouristOperator(String password, String username, String mail, boolean blocked, int phone, Country country) {
        super(password, username, mail, blocked, phone, country);
    }

    public TouristOperator(String password, String username, String mail, boolean blocked, int phone, Country country, boolean validated, float calification) {
        super(password, username, mail, blocked, phone, country);
        this.validated = validated;
        this.calification = calification;
    }

    @NotNull
    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    @NotNull
    public float getCalification() {
        return calification;
    }

    public void setCalification(float calification) {
        this.calification = calification;
    }

    public boolean verifyObjectIncomplete(){
        return super.verifyObjectIncomplete();
    }
}
