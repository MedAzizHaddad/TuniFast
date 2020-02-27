/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Entitie;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mohamedazizhaddad
 */
public class Upgrade {
   private int idUp ;
   private String cv ;
   private int exp ;
   private String trajet ;
   private String details ;
   private int idUser ;
   private Date DateTime;
   private String etat ;

    public Upgrade() {
    }

    public Upgrade(int idUp, String cv, int exp, String trajet, String details, int idUser, Date DateTime, String etat) {
        this.idUp = idUp;
        this.cv = cv;
        this.exp = exp;
        this.trajet = trajet;
        this.details = details;
        this.idUser = idUser;
        this.DateTime = DateTime;
        this.etat = etat;
    }

    public int getIdUp() {
        return idUp;
    }

    public String getCv() {
        return cv;
    }

    public int getExp() {
        return exp;
    }

    public String getTrajet() {
        return trajet;
    }

    public String getDetails() {
        return details;
    }

    public int getIdUser() {
        return idUser;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public String getEtat() {
        return etat;
    }

    public void setIdUp(int idUp) {
        this.idUp = idUp;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setTrajet(String trajet) {
        this.trajet = trajet;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setDateTime(Date DateTime) {
        this.DateTime = DateTime;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idUp;
        hash = 67 * hash + Objects.hashCode(this.cv);
        hash = 67 * hash + this.exp;
        hash = 67 * hash + Objects.hashCode(this.trajet);
        hash = 67 * hash + Objects.hashCode(this.details);
        hash = 67 * hash + this.idUser;
        hash = 67 * hash + Objects.hashCode(this.DateTime);
        hash = 67 * hash + Objects.hashCode(this.etat);
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
        final Upgrade other = (Upgrade) obj;
        if (this.idUp != other.idUp) {
            return false;
        }
        if (this.exp != other.exp) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        if (!Objects.equals(this.cv, other.cv)) {
            return false;
        }
        if (!Objects.equals(this.trajet, other.trajet)) {
            return false;
        }
        if (!Objects.equals(this.details, other.details)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.DateTime, other.DateTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Upgrade{" + "idUp=" + idUp + ", cv=" + cv + ", exp=" + exp + ", trajet=" + trajet + ", details=" + details + ", idUser=" + idUser + ", DateTime=" + DateTime + ", etat=" + etat + '}';
    }
   

 

   
   
    
    
}
