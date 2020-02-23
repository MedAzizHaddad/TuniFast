package tunifast.esprit.Utils;

import tunifast.esprit.Service.UserCrud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dimassi Abdelhak
 */
public class JavamailUtil1 {

    public static String sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send:");
        Properties properties = new Properties();

        String myAccountEmail = "razi.fertani@esprit.tn";
        String password = "Razinesrine2026";

        properties.put("com.hof.email.starttime", "20170519094544");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.connectiontimeout", "60000");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.timeout", "60000");
        properties.put("mail.transport.protocol", "smtp");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        int n = 10;
        String x=getAlphaNumericString(n);
        
        Message message = prepareMessage(session, myAccountEmail, recepient, x);

        Transport.send(message);
        System.out.println("Message envoyé avec succés !");
        return x;
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String x) {
        try {
            UserCrud userC = new UserCrud();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Votre nouveau mot de passe");
            
            message.setText("Votre nouveau mot de passe est : " + x);

            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(JavamailUtil1.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    static String getAlphaNumericString(int n) {

        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb 
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

}
