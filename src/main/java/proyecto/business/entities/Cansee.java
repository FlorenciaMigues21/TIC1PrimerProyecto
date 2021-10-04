package proyecto.business.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Cansee {
    private byte blocked;

    @Basic
    @Column(name = "blocked")
    public byte getBlocked() {
        return blocked;
    }

    public void setBlocked(byte blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cansee cansee = (Cansee) o;
        return blocked == cansee.blocked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(blocked);
    }
}
