/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Entitie;

import java.util.Objects;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author mohamedazizhaddad
 */
public class Annonce {

    private User user ;

   
    private int idAnnonce;
    private int idUser;
    private String lieuDepart;
    private String lieuArrivee;
    private String dateAnnonce;
    private String heureAnnonce;
    private int nbrPlaceDispo;
    private int nbPlaceReser;
    private double prix ;
    
    private String dateAnnPost;
    //////////// 
    private String nomUser ;

    public Annonce(User user, int idAnnonce, int idUser, String lieuDepart, String lieuArrivee, String dateAnnonce, String heureAnnonce, int nbrPlaceDispo, int nbPlaceReser, double prix, String dateAnnPost, String nomUser) {
        this.user = user;
        this.idAnnonce = idAnnonce;
        this.idUser = idUser;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.dateAnnonce = dateAnnonce;
        this.heureAnnonce = heureAnnonce;
        this.nbrPlaceDispo = nbrPlaceDispo;
        this.nbPlaceReser = nbPlaceReser;
        this.prix = prix;
        this.dateAnnPost = dateAnnPost;
        this.nomUser = nomUser;
    }

    
    public Annonce(int idAnnonce, int idUser, String lieuDepart, String lieuArrivee, String dateAnnonce, String heureAnnonce, int nbrPlaceDispo, int nbPlaceReser, String dateAnnPost, String nomUser) {
        this.idAnnonce = idAnnonce;
        this.idUser = idUser;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.dateAnnonce = dateAnnonce;
        this.heureAnnonce = heureAnnonce;
        this.nbrPlaceDispo = nbrPlaceDispo;
        this.nbPlaceReser = nbPlaceReser;
        this.dateAnnPost = dateAnnPost;
        this.nomUser = nomUser;
    }
    

    public Annonce(int idAnnonce, int idUser, String lieuDepart, String lieuArrivee, String dateAnnonce, String heureAnnonce, int nbrPlaceDispo, int nbPlaceReser, String nomUser) {
        this.idAnnonce = idAnnonce;
        this.idUser = idUser;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.dateAnnonce = dateAnnonce;
        this.heureAnnonce = heureAnnonce;
        this.nbrPlaceDispo = nbrPlaceDispo;
        this.nbPlaceReser = nbPlaceReser;
        this.nomUser = nomUser;
    }

    public Annonce() {
    }

    public Annonce(int idAnnonce, int idUser, String lieuDepart, String lieuArrivee, String dateAnnonce, String heureAnnonce, int nbrPlaceDispo, int nbPlaceReser) {
        this.idAnnonce = idAnnonce;
        this.idUser = idUser;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.dateAnnonce = dateAnnonce;
        this.heureAnnonce = heureAnnonce;
        this.nbrPlaceDispo = nbrPlaceDispo;
        this.nbPlaceReser = nbPlaceReser;
    }

    public User getUser() {
        return user;
    }

    public double getPrix() {
        return prix;
    }

    
    public int getIdAnnonce() {
        return idAnnonce;
    }

    public String getDateAnnPost() {
        return dateAnnPost;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

//    public StringProperty lieuDepartProperty() {
//        return lieuDepart;
//    }

    public String getLieuArrivee() {
        return lieuArrivee;
    }

    public String getDateAnnonce() {
        return dateAnnonce;
    }

    public String getHeureAnnonce() {
        return heureAnnonce;
    }

    public int getNbrPlaceDispo() {
        return nbrPlaceDispo;
    }

    public void setDateAnnPost(String dateAnnPost) {
        this.dateAnnPost = dateAnnPost;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNbPlaceReser() {
        return nbPlaceReser;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public void setNbrPlaceDispo(int nbrPlaceDispo) {
        this.nbrPlaceDispo = nbrPlaceDispo;
    }

    public void setNbPlaceReser(int nbPlaceReser) {
        this.nbPlaceReser = nbPlaceReser;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.idAnnonce;
        hash = 19 * hash + this.idUser;
        hash = 19 * hash + Objects.hashCode(this.lieuDepart);
        hash = 19 * hash + Objects.hashCode(this.lieuArrivee);
        hash = 19 * hash + Objects.hashCode(this.dateAnnonce);
        hash = 19 * hash + Objects.hashCode(this.heureAnnonce);
        hash = 19 * hash + this.nbrPlaceDispo;
        hash = 19 * hash + this.nbPlaceReser;
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
        final Annonce other = (Annonce) obj;
        if (this.idAnnonce != other.idAnnonce) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.nbrPlaceDispo != other.nbrPlaceDispo) {
            return false;
        }
        if (this.nbPlaceReser != other.nbPlaceReser) {
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
        return "Annonce{" + "idAnnonce=" + idAnnonce + ", idUser=" + idUser + ", lieuDepart=" + lieuDepart + ", lieuArrivee=" + lieuArrivee + ", dateAnnonce=" + dateAnnonce + ", heureAnnonce=" + heureAnnonce + ", nbrPlaceDispo=" + nbrPlaceDispo + ", nbPlaceReser=" + nbPlaceReser + '}';
    }

}
