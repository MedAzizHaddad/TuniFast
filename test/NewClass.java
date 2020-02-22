
import java.util.ArrayList;
import tunifast.esprit.Entitie.messages;
import tunifast.esprit.Service.MessageCrud;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mohamedazizhaddad
 */
public class NewClass {
    
    
    
    public static void main(String[] args) {
        System.out.println("jo");
         ArrayList<messages> result = new ArrayList<messages>();
        MessageCrud m = new MessageCrud();
      result =  m.getMessages(1,1);
      result.forEach((n) -> System.out.println(n.getContent())); 
      
    }
    
}
