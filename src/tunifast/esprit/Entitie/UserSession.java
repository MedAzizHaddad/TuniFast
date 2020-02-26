/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunifast.esprit.Entitie;

import javafx.stage.Stage;

/**
 *
 * @author mohamedazizhaddad
 */
public final class UserSession {

    private static UserSession instance;

    private int idUser;
    private String  role;
    private int param ;
    private int param2 ;
    private Stage s ;
    private UserSession(int idUser, String  role) {
        this.idUser = idUser;
        this.role = role;
    }
     private UserSession(int idUser, String  role, int param) {
        this.idUser = idUser;
        this.role = role;
        this.param = param ;
    }
     
        private UserSession(int idUser, String  role, int param, int param2) {
        this.idUser = idUser;
        this.role = role;
        this.param = param ;
        this.param2 = param2 ;
    }

    public UserSession() {
    }

  
    public UserSession(int idUser) {
        this.idUser = idUser;
    }
//---------------------------------------------------
    public static UserSession getInstance(int idUser , String  role) {
        if(instance == null) {
            instance = new UserSession(idUser,role);
        }
        return instance;
    }
    public static UserSession getInstance(){
        return instance;
    }
  public static UserSession getInstance(int idUser , String  role , int param) {
        instance = new UserSession(idUser,role,param);
        return instance;
    }
  
    public static UserSession getInstance(int idUser , String  role , int param , int param2) {
        instance = new UserSession(idUser,role,param,param2);
        return instance;
    }
//--------------------------------------------------------------------
    public UserSession(Stage s) {
        this.s = s;
    }

     public static UserSession getInstance(Stage s ) {
        instance = new UserSession(s);
        return instance;
    }
//--------------------------------------------------------------------
    public int getIdUser() {
        return idUser;
    }

    public String getRole() {
        return role;
    }

    public int getParam2() {
        return param2;
    }

    public int getParam() {
        return param;
    }

    public Stage getS() {
        return s;
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