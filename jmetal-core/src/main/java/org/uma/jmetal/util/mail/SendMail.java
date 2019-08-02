package org.uma.jmetal.util.mail;

import java.util.Properties;
//import javax.mail.*;
//import javax.mail.internet.*;

class Mailer{
    public static void send(String from,String password,String to,String sub,String msg){
//        //Get properties object
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.office365.com");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "25");
//        props.put("mail.smtp.starttls.enable", "true");
//
//        //get Session
//        Session session = Session.getDefaultInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(from,password);
//                    }
//                });
//        //compose message
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
//            message.setSubject(sub);
//            message.setText(msg);
//            message.setFrom(new InternetAddress(from));
//            //send message
//            Transport.send(message);
//            System.out.println("message sent successfully");
//        } catch (MessagingException e) {throw new RuntimeException(e);}
//
//    }
}
public class SendMail{
//    public static void main(String[] args) {
//        //from,password,to,subject,message
//        Mailer.send("toon_bcc@hotmail.com","_bccbcc159","toonbcc@gmail.com","Congratulation!","Dear Khun Vichaya,\nProcess finished !!! Come and see for yourself!!!\nBest regards,\nToon Toon");
//        //change from, password and to
    }
}