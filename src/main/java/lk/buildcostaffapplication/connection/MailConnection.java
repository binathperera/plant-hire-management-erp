/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 *
 * @author Binath Perera
 */
public class MailConnection {
    
    private static MailConnection connection;
    private final String host;
    private String subject;
    private String body;
    private File file;
    private Properties properties=new Properties();
    
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    
    private MailConnection(){
        try {
            // Load mail properties file
            properties.load(new FileReader("src/main/mail-properties/MailConnection.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        host = properties.getProperty("host"); //get host ip from properties file
        String port = properties.getProperty("port");// get port number from properties file
        
        properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.starttls.enable", "true");//enable tls
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", port);
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        
    }
    public static MailConnection getConnection(){
        if(connection==null){
            connection= new MailConnection();//Create new instance if one does not exist
        }
        return connection;
    } 
    
    public void sendMail(String to,String from,String password){
        properties.put("mail.smtp.user", from);
        properties.put("mail.debug", "true");
        BodyPart messageBodyPart=new MimeBodyPart();
        try {
            messageBodyPart.setText(body);
        } catch (MessagingException ex) {
            Logger.getLogger(MailConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        Multipart multiPart=new MimeMultipart();
        try {
            multiPart.addBodyPart(messageBodyPart);
        } catch (MessagingException ex) {
            Logger.getLogger(MailConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(file!=null){
            DataSource source=new FileDataSource(file.getAbsolutePath());
            try {
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(file.getName());
            } catch (MessagingException ex) {
                Logger.getLogger(MailConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Session session=Session.getDefaultInstance(properties, null);       
        session.setDebug(true);
        try{
            MimeMessage message=new MimeMessage(session);
            message.setSubject(subject);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setContent(multiPart);
            message.saveChanges();
            Transport transport=session.getTransport("smtps");
            transport.connect(host,465,from,password);
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();
        }catch(MessagingException e){
            System.out.println(e.getMessage());
        }
    }
    
}














