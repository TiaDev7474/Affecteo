package www.ong.affectero.Config;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.DataSource;


public class SendEmailNotification {

      private final String username = "nomenjanaharyriry@gmail.com";
      private final String password = "vfro cawq ubsj oqub";

      public void sendWithAttachment(String to,String subject ,String body, String filepath){

            //set up mail properties
            Properties properties = new Properties();
            properties.put("mail.smtp.auth","true");
            properties.put("mail.smtp.starttls.enable","true");
            properties.put("mail.smtp.host","smtp.gmail.com");
            properties.put("mail.smtp.port","587");

           //create a mail session
            Session session = Session.getInstance(properties, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication(){
                          return  new PasswordAuthentication(username, password);
                    }
            });

            try{
                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress(username));
                  message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
                  message.setSubject(subject);

                  MimeBodyPart messageBodyPart = new MimeBodyPart();
                  messageBodyPart.setText(body);

                  MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                  DataSource dataSource = new FileDataSource(filepath);
                  attachmentBodyPart.setDataHandler(new DataHandler(dataSource));
                  attachmentBodyPart.setFileName(dataSource.getName());

                  //create multipart message and add the body part

                  Multipart multipart = new MimeMultipart();
                  multipart.addBodyPart(messageBodyPart);
                  multipart.addBodyPart(attachmentBodyPart);

                  //set up the content of the message to the multipart message

                  message.setContent(multipart);

                  Transport.send(message);

                  System.out.println("Email sent sucessFully");


            }catch (MessagingException e){
                  e.printStackTrace();
            }

      }


}
