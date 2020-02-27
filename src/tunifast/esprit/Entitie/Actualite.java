package tunifast.esprit.Entitie;

import java.util.Objects;

public class Actualite {

    private int idActualite;
    private int idUser;
    private String lieuActualite;
    private String dateActualite;
    private String heureActualite;
    private String Actualite;
    private int typeActualite;



    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.idActualite;
        hash = 61 * hash + this.idUser;
        hash = 61 * hash + Objects.hashCode(this.lieuActualite);
        hash = 61 * hash + Objects.hashCode(this.dateActualite);
        hash = 61 * hash + Objects.hashCode(this.heureActualite);
        hash = 61 * hash + Objects.hashCode(this.Actualite);
        hash = 61 * hash + this.typeActualite;
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
        final Actualite other = (Actualite) obj;
        if (this.idActualite != other.idActualite) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.typeActualite != other.typeActualite) {
            return false;
        }
        if (!Objects.equals(this.lieuActualite, other.lieuActualite)) {
            return false;
        }
        if (!Objects.equals(this.dateActualite, other.dateActualite)) {
            return false;
        }
        if (!Objects.equals(this.heureActualite, other.heureActualite)) {
            return false;
        }
        if (!Objects.equals(this.Actualite, other.Actualite)) {
            return false;
        }
        return true;
    }

    public Actualite() {
    }

    public Actualite(int idUser, String lieuActualite, String dateActualite, String heureActualite, String Actualite, int typeActualite) {
        this.idUser = idUser;
        this.lieuActualite = lieuActualite;
        this.dateActualite = dateActualite;
        this.heureActualite = heureActualite;
        this.Actualite = Actualite;
        this.typeActualite = typeActualite;
    }

    
    public Actualite(int idActualite, int idUser, String lieuActualite, String dateActualite, String heureActualite, String Actualite, int typeActualite) {
        this.idActualite = idActualite;
        this.idUser = idUser;
        this.lieuActualite = lieuActualite;
        this.dateActualite = dateActualite;
        this.heureActualite = heureActualite;
        this.Actualite = Actualite;
        this.typeActualite = typeActualite;
    }

    public int getIdActualite() {
        return idActualite;
    }

    public void setIdActualite(int idActualite) {
        this.idActualite = idActualite;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLieuActualite() {
        return lieuActualite;
    }

    public void setLieuActualite(String lieuActualite) {
        this.lieuActualite = lieuActualite;
    }

    public String getDateActualite() {
        return dateActualite;
    }

    public void setDateActualite(String dateActualite) {
        this.dateActualite = dateActualite;
    }

    public String getHeureActualite() {
        return heureActualite;
    }

    public void setHeureActualite(String heureActualite) {
        this.heureActualite = heureActualite;
    }

    public String getActualite() {
        return Actualite;
    }

    public void setActualite(String Actualite) {
        this.Actualite = Actualite;
    }

    public int getTypeActualite() {
        return typeActualite;
    }

    public void setTypeActualite(int typeActualite) {
        this.typeActualite = typeActualite;
    }

    @Override
    public String toString() {
        return "Actualite (" + "idActualite:" + idActualite + ", idUser:" + idUser + ", lieuActualite:" + lieuActualite + ", dateActualite:" + dateActualite + ", heureActualite:" + heureActualite + ", Actualite:" + Actualite + ", typeActualite:" + typeActualite + ')';
    }

}
