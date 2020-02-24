/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Entitie;

import java.util.Objects;

/**
 *
 * @author mohamedazizhaddad
 */
public class Reclamation {

    private int idReclamation;
    private String userReporter;
    private String userReported;
    private String dateReclamation;
    private String details;
    private String etatReclamation;
    private String etatUser;

    public Reclamation() {
    }

    public Reclamation(int idReclamation, String userReporter, String userReported, String dateReclamation, String details, String etatReclamation, String etatUser) {
        this.idReclamation = idReclamation;
        this.userReporter = userReporter;
        this.userReported = userReported;
        this.dateReclamation = dateReclamation;
        this.details = details;
        this.etatReclamation = etatReclamation;
        this.etatUser = etatUser;
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public String getUserReporter() {
        return userReporter;
    }

    public String getUserReported() {
        return userReported;
    }

    public String getDateReclamation() {
        return dateReclamation;
    }

    public String getDetails() {
        return details;
    }

    public String getEtatReclamation() {
        return etatReclamation;
    }

    public String getEtatUser() {
        return etatUser;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public void setUserReporter(String userReporter) {
        this.userReporter = userReporter;
    }

    public void setUserReported(String userReported) {
        this.userReported = userReported;
    }

    public void setDateReclamation(String dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setEtatReclamation(String etatReclamation) {
        this.etatReclamation = etatReclamation;
    }

    public void setEtatUser(String etatUser) {
        this.etatUser = etatUser;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idReclamation;
        hash = 97 * hash + Objects.hashCode(this.userReporter);
        hash = 97 * hash + Objects.hashCode(this.userReported);
        hash = 97 * hash + Objects.hashCode(this.dateReclamation);
        hash = 97 * hash + Objects.hashCode(this.details);
        hash = 97 * hash + Objects.hashCode(this.etatReclamation);
        hash = 97 * hash + Objects.hashCode(this.etatUser);
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
        final Reclamation other = (Reclamation) obj;
        if (this.idReclamation != other.idReclamation) {
            return false;
        }
        if (!Objects.equals(this.userReporter, other.userReporter)) {
            return false;
        }
        if (!Objects.equals(this.userReported, other.userReported)) {
            return false;
        }
        if (!Objects.equals(this.dateReclamation, other.dateReclamation)) {
            return false;
        }
        if (!Objects.equals(this.details, other.details)) {
            return false;
        }
        if (!Objects.equals(this.etatReclamation, other.etatReclamation)) {
            return false;
        }
        if (!Objects.equals(this.etatUser, other.etatUser)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idReclamation=" + idReclamation + ", userReporter=" + userReporter + ", userReported=" + userReported + ", dateReclamation=" + dateReclamation + ", details=" + details + ", etatReclamation=" + etatReclamation + ", etatUser=" + etatUser + '}';
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
