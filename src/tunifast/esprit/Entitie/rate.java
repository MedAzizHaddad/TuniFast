/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Entitie;

import java.util.Objects;

/**
 *
 * @author Youssef
 */
public class rate {

    private int idRate;
    private double nbrRate;
    private String feedback;
    private int idUser;
    private int idAnnonce;

    public rate(double nbrRate, int idUser) {
        this.nbrRate = nbrRate;
        this.idUser = idUser;
    }
    

    public rate(int idRate, int nbrRate, String feedback, int idUser, int idAnnonce) {
        this.idRate = idRate;
        this.nbrRate = nbrRate;
        this.feedback = feedback;
        this.idUser = idUser;
        this.idAnnonce = idAnnonce;
        
    }

    public rate(int nbrRate, String feedback, int idUser, int idAnnonce) {
        this.nbrRate = nbrRate;
        this.feedback = feedback;
         this.idUser = idUser;
        this.idAnnonce = idAnnonce;
       
    }

    public rate() {
    }

    
    
    public int getIdRate() {
        return idRate;
    }

    public void setIdRate(int idRate) {
        this.idRate = idRate;
    }

    public double getNbrRate() {
        return nbrRate;
    }

    public void setNbrRate(int nbrRate) {
        this.nbrRate = nbrRate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.idRate;
      
        hash = 97 * hash + Objects.hashCode(this.feedback);
        hash = 97 * hash + this.idUser;
        hash = 97 * hash + this.idAnnonce;
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
        final rate other = (rate) obj;
        if (this.idRate != other.idRate) {
            return false;
        }
        if (this.nbrRate != other.nbrRate) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.idAnnonce != other.idAnnonce) {
            return false;
        }
        if (!Objects.equals(this.feedback, other.feedback)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "rate{" + "idRate=" + idRate + ", nbrRate=" + nbrRate + ", feedback=" + feedback + ", idUser=" + idUser + ", idAnnonce=" + idAnnonce + '}';
    }
    
    
    

}
