/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Entitie;

/**
 *
 * @author mohamedazizhaddad
 */
public class Profile {
     private int idUser;
     private String mode ;
     private String style ;

    public Profile() {
    }

     
     
    public Profile(int idUser, String mode, String style) {
        this.idUser = idUser;
        this.mode = mode;
        this.style = style;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getMode() {
        return mode;
    }

    public String getStyle() {
        return style;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setStyle(String style) {
        this.style = style;
    }
    
}

