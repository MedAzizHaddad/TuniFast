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
public class Reservation {
    User user ;
    int idReservation;
    int idAnnonce;
    int idUser;
    String dateReservation;
    int nbPlace;
    int montant;
    String etatReservation;
    String lieuDepart;
    String lieuArrivee;
    String dateAnnonce;
    String heureAnnonce;

    public Reservation() {
    }

    public Reservation(int idReservation, int idAnnonce, int idUser, String dateReservation, int nbPlace, int montant, String etatReservation, String lieuDepart, String lieuArrivee, String dateAnnonce, String heureAnnonce) {
        this.idReservation = idReservation;
        this.idAnnonce = idAnnonce;
        this.idUser = idUser;
        this.dateReservation = dateReservation;
        this.nbPlace = nbPlace;
        this.montant = montant;
        this.etatReservation = etatReservation;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.dateAnnonce = dateAnnonce;
        this.heureAnnonce = heureAnnonce;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public int getIdUser() {
        return idUser;
    }

    public User getUser() {
        return user;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public int getMontant() {
        return montant;
    }

    public String getEtatReservation() {
        return etatReservation;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public String getLieuArrivee() {
        return lieuArrivee;
    }

    public String getDateAnnonce() {
        return dateAnnonce;
    }

    public String getHeureAnnonce() {
        return heureAnnonce;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public void setEtatReservation(String etatReservation) {
        this.etatReservation = etatReservation;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public void setDateAnnonce(String dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }

    public void setHeureAnnonce(String heureAnnonce) {
        this.heureAnnonce = heureAnnonce;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.idReservation;
        hash = 83 * hash + this.idAnnonce;
        hash = 83 * hash + this.idUser;
        hash = 83 * hash + Objects.hashCode(this.dateReservation);
        hash = 83 * hash + this.nbPlace;
        hash = 83 * hash + this.montant;
        hash = 83 * hash + Objects.hashCode(this.etatReservation);
        hash = 83 * hash + Objects.hashCode(this.lieuDepart);
        hash = 83 * hash + Objects.hashCode(this.lieuArrivee);
        hash = 83 * hash + Objects.hashCode(this.dateAnnonce);
        hash = 83 * hash + Objects.hashCode(this.heureAnnonce);
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
        final Reservation other = (Reservation) obj;
        if (this.idReservation != other.idReservation) {
            return false;
        }
        if (this.idAnnonce != other.idAnnonce) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.nbPlace != other.nbPlace) {
            return false;
        }
        if (this.montant != other.montant) {
            return false;
        }
        if (!Objects.equals(this.dateReservation, other.dateReservation)) {
            return false;
        }
        if (!Objects.equals(this.etatReservation, other.etatReservation)) {
            return false;
        }
        if (!Objects.equals(this.lieuDepart, other.lieuDepart)) {
            return false;
        }
        if (!Objects.equals(this.lieuArrivee, other.lieuArrivee)) {
            return false;
        }
        if (!Objects.equals(this.dateAnnonce, other.dateAnnonce)) {
            return false;
        }
        if (!Objects.equals(this.heureAnnonce, other.heureAnnonce)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idReservation=" + idReservation + ", idAnnonce=" + idAnnonce + ", idUser=" + idUser + ", dateReservation=" + dateReservation + ", nbPlace=" + nbPlace + ", montant=" + montant + ", etatReservation=" + etatReservation + ", lieuDepart=" + lieuDepart + ", lieuArrivee=" + lieuArrivee + ", dateAnnonce=" + dateAnnonce + ", heureAnnonce=" + heureAnnonce + '}';
    }

}
