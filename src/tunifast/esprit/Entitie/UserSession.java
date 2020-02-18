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
public final class UserSession {

    private static UserSession instance;

    private int idUser;
    private String  role;

    private UserSession(int idUser, String  role) {
        this.idUser = idUser;
        this.role = role;
    }

    public UserSession() {
    }

    public UserSession(int idUser) {
        this.idUser = idUser;
    }
//---------------------------------------------------
    public static UserSession getInstace(int idUser , String  role) {
        if(instance == null) {
            instance = new UserSession(idUser,role);
        }
        return instance;
    }
    public static UserSession getInstace(){
        return instance;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getRole() {
        return role;
    }

    public void cleanUserSession() {
        idUser = 0;// or null
        role = "";// or null
    }

    @Override
    public String toString() {
        return "UserSession{" + "idUser=" + idUser + ", role=" + role + '}';
    }

    
}