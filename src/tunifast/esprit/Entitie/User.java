/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Entitie;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.util.Objects;

/**
 *
 * @author mohamedazizhaddad
*/
public class User {

    private int idUser;
    private String nom;
    private String prenom;
    private String mail;
    private int numTel;
    private String sexe;
    private String role;
    private String password;
    private String username; 
    private String etat ;

    private Double compte ;
    public User() {
    }

    public User(int idUser, String nom, String prenom, String mail, int numTel, String sexe, String role, String password, String username, String etat, Double compte) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.numTel = numTel;
        this.sexe = sexe;
        this.role = role;
        this.password = password;
        this.username = username;
        this.etat = etat;
        this.compte = compte;
    }

    
    
    public User(int idUser, String nom, String prenom, String mail, int numTel, String sexe, String role, String password, String username, String etat) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.numTel = numTel;
        this.sexe = sexe;
        this.role = role;
        this.password = password;
        this.username = username;
        this.etat = etat;
    }

    public User(String nom, String prenom, String mail, int numTel, String password, String username) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.numTel = numTel;
        this.password = password;
        this.username = username;
    }

    public User(int idUser, String nom, String prenom, String mail, int numTel, String sexe, String role, String password, String username) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.numTel = numTel;
        this.sexe = sexe;
        this.role = role;
        this.password = password;
        this.username = username;
    }

    public User(int idUser, String nom, String prenom, String mail, int numTel, String sexe, String role, String password) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.numTel = numTel;
        this.sexe = sexe;
        this.role = role;
        this.password = password;
    }

   


    public String getUsername() {
        return username;
    }

    public String getEtat() {
        return etat;
    }

    public Double getCompte() {
        return compte;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public int getNumTel() {
        return numTel;
    }

    public String getSexe() {
        return sexe;
    }

    public String getRole() {
        return role;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setCompte(Double compte) {
        this.compte = compte;
    }

    public String getPassword() {
        return password;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.idUser;
        hash = 43 * hash + Objects.hashCode(this.nom);
        hash = 43 * hash + Objects.hashCode(this.prenom);
        hash = 43 * hash + Objects.hashCode(this.mail);
        hash = 43 * hash + this.numTel;
        hash = 43 * hash + Objects.hashCode(this.sexe);
        hash = 43 * hash + Objects.hashCode(this.role);
        hash = 43 * hash + Objects.hashCode(this.password);
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
        final User other = (User) obj;
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.numTel != other.numTel) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        if (!Objects.equals(this.sexe, other.sexe)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "user{" + "idUser=" + idUser + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", numTel=" + numTel + ", sexe=" + sexe + ", role=" + role + ", password=" + password + '}';
    }
    
}
    
    
    
    
    
    
    
    
    
    
    

