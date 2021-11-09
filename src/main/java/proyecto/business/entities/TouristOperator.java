package proyecto.business.entities;

import com.sun.istack.NotNull;

import javax.persistence.Entity;

@Entity
public class TouristOperator extends User{
    private boolean validated;
    private float calification;
    private String name;

    public TouristOperator() {
    }

    public TouristOperator(String password, String mail) {
        super(password, mail);
    }

    public TouristOperator(String password, String username, String mail, boolean blocked, String phone) {
        super(password, username, mail, blocked, phone);
    }

    public TouristOperator(String password, String username, String mail, boolean blocked, String phone, boolean validated, float calification, String name) {
        super(password, username, mail, blocked, phone);
        this.validated = validated;
        this.calification = calification;
        this.name = name;
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
