import javax.mail.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sendmail{
    public String main(String recipient){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String username = "ranabalisun@gmail.com";
        String password = "bryplemumgdokyhn";
         
         String otp = String.valueOf((int)Math.floor(1000000*(1-Math.random())));
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication (){
                return new PasswordAuthentication(username, password);
            }
        });


        Message message = preparedMessage(session,username,recipient,otp);
        try{
            Transport.send(message);
        }
        catch(Exception e){
            System.err.println("Error in sending the mail");
            e.printStackTrace();
        }
        return otp;

    }


    public static Message preparedMessage(Session session,String username,String recipient,String otp){
        Message message = new MimeMessage(session);
        try{
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("[----- Test Email -----]");
            message.setText("Your otp is here " + otp);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return message;

    }
}