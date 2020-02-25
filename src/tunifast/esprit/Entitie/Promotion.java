
package tunifast.esprit.Entitie;

import java.util.Date;
import java.util.Objects;


public class Promotion {

    private int idP;
    private Date dateD;
    private Date dateF;
    private float reduction;
     

    public Promotion(int idP, Date dateD, Date dateF, float reduction) {
        this.idP = idP;
        this.dateD = dateD;
        this.dateF = dateF;
        this.reduction = reduction;
    }
    
     public Promotion( Date dateD, Date dateF, float reduction) {
      
        this.dateD = dateD;
        this.dateF = dateF;
        this.reduction = reduction;
    }

    public Promotion() {

    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public Date getDateD() {
        return dateD;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    public Date getDateF() {
        return dateF;
    }

    public void setDateF(Date dateF) {
        this.dateF = dateF;
    }

    public float getReduction() {
        return reduction;
    }

    public void setReduction(float reduction) {
        this.reduction = reduction;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.idP;
        hash = 37 * hash + Objects.hashCode(this.dateD);
        hash = 37 * hash + Objects.hashCode(this.dateF);
        hash = 37 * hash + Float.floatToIntBits(this.reduction);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Promotion other = (Promotion) obj;
        if (this.idP != other.idP) {
            return false;
        }
        if (Float.floatToIntBits(this.reduction) != Float.floatToIntBits(other.reduction)) {
            return false;
        }
        if (!Objects.equals(this.dateD, other.dateD)) {
            return false;
        }
        if (!Objects.equals(this.dateF, other.dateF)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Promotion{" + "idP=" + idP + ", dateD=" + dateD + ", dateF=" + dateF + ", reduction=" + reduction + '}';
    }

}
