/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Entitie;

/**
 *
 * @author ASUS
 */
public class offre {
     
    private int idoff;
    private int idoUser;
    private int code;
    private float solode;
    private String Email = "Test@gmail.com";
    

    public offre(int idoUser, int code, float solode) {
        this.idoUser = idoUser;
        this.code = code;
        this.solode = solode;
    }
    
    
    
    public offre(int idoff, int idoUser, int code, float solode) {
        this.idoff = idoff;
        this.idoUser = idoUser;
        this.code = code;
        this.solode = solode;
    }
    
    

    public int getIdoff() {
        return idoff;
    }

    public void setIdoff(int idoff) {
        this.idoff = idoff;
    }

    public int getIdoUser() {
        return idoUser;
    }

    public void setIdoUser(int idoUser) {
        this.idoUser = idoUser;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public float getSolode() {
        return solode;
    }

    public void setSolode(float solode) {
        this.solode = solode;
    }

    @Override
    public String toString() {
        return "offre{" + "idoff=" + idoff + ", idoUser=" + idoUser + ", code=" + code + ", solode=" + solode + '}';
    }
    
    
    
    
}
