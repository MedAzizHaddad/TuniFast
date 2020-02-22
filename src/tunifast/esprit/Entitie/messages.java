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
public class messages {
    private int idMessage;
    private int exp ;
    private int recep ;
    private String content;
    private String time ;

    public messages(int idMessage, int exp, int recep) {
        this.idMessage = idMessage;
        this.exp = exp;
        this.recep = recep;
    }

    public messages(int idMessage, int exp, int recep, String content, String time) {
        this.idMessage = idMessage;
        this.exp = exp;
        this.recep = recep;
        this.content = content;
        this.time = time;
    }

    public messages(int idMessage, int exp, int recep, String content) {
        this.idMessage = idMessage;
        this.exp = exp;
        this.recep = recep;
        this.content = content;
    }

    public messages() {
    }

    public int getIdMessage() {
        return idMessage;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public int getExp() {
        return exp;
    }

    public int getRecep() {
        return recep;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRecep(int recep) {
        this.recep = recep;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.idMessage;
        hash = 29 * hash + this.exp;
        hash = 29 * hash + this.recep;
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
        final messages other = (messages) obj;
        if (this.idMessage != other.idMessage) {
            return false;
        }
        if (this.exp != other.exp) {
            return false;
        }
        if (this.recep != other.recep) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
