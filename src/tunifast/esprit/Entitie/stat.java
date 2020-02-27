/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Entitie;

/**
 *
 * @author Youssef
 */
public class stat {
     private int idUser;
    private int nbrAnnonce;
    private float moyRate;

    public stat() {
    }

    public stat(int idUser, int nbrAnnonce, float moyRate) {
        this.idUser = idUser;
        this.nbrAnnonce = nbrAnnonce;
        this.moyRate = moyRate;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getNbrAnnonce() {
        return nbrAnnonce;
    }

    public void setNbrAnnonce(int nbrAnnonce) {
        this.nbrAnnonce = nbrAnnonce;
    }

    public float getMoyRate() {
        return moyRate;
    }

    public void setMoyRate(float moyRate) {
        this.moyRate = moyRate;
    }

    @Override
    public String toString() {
        return "stat{" + "L'utilisateur=" + idUser + ", a=" + nbrAnnonce + " annonces, et =" + moyRate + " comme moyenne" + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.idUser;
        hash = 11 * hash + this.nbrAnnonce;
        hash = 11 * hash + Float.floatToIntBits(this.moyRate);
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
        final stat other = (stat) obj;
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.nbrAnnonce != other.nbrAnnonce) {
            return false;
        }
        if (Float.floatToIntBits(this.moyRate) != Float.floatToIntBits(other.moyRate)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
