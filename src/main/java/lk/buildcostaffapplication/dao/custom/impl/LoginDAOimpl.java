/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dao.custom.impl;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lk.buildcostaffapplication.connection.AES;
import lk.buildcostaffapplication.connection.DBconnection;
import lk.buildcostaffapplication.connection.MailConnection;
import lk.buildcostaffapplication.dao.custom.LoginDAO;
import lk.buildcostaffapplication.dto.LoginDTO;
import lk.buildcostaffapplication.dto.SuperDTO;
import lk.buildcostaffapplication.view.MainFrame;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Binath Perera
 */
public class LoginDAOimpl implements LoginDAO{
    static String code,tempPassword;
    @Override
    public boolean add(SuperDTO ob) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(SuperDTO ob) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SuperDTO search(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    MainFrame mf;
    @Override
    public LoginDTO logUser(JPanel panel, String username, String password) {
        MongoClient client=MongoClients.create("mongodb://securityUser117:cmk23WEr6@localhost:27017");
       // MongoIterable<String> databaseNames= mc.listDatabaseNames();
        try{
           // for(String d: databaseNames);
            MongoDatabase database = client.getDatabase("plantHireManagement");
            MongoCollection<Document> collection = database.getCollection("staffMember");
            Bson find= Filters.eq("username",username);   
            Document user = collection.find(find).first();
            Document name = (Document)user.get("name");
            String first = (String)name.get("first");
            String pw = (String)user.get("password");
            try {
                pw=AES.decrypt(pw, AES.generateKey());
            } catch (Exception ex) {
                Logger.getLogger(LoginDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<String> list= (ArrayList<String>)user.get("access");
            if(password.equals(pw)){
                Document contactDetails=(Document)user.get("contact");
                ArrayList<String> access=(ArrayList<String>)user.get("access");
                pw=(String)client
                            .getDatabase("plantHireManagement")
                            .getCollection("userPasswords")
                            .find(eq("_id",user.get("_id")))
                            .first().get("databasePassword");
                pw= AES.decrypt(pw, AES.generateKey());
                String email=(String)contactDetails.get("email");

                boolean twoFactorAuthentication = (boolean)user.get("2FA");
                if(twoFactorAuthentication){
                    generateTenMinuteCode();
                    sleep(1000);
                    try{
                        MailConnection mail= MailConnection.getConnection();
                        mail.setSubject("Authenication code");
                        mail.setBody("Buildco login code "+code+"\nValid for 10 minutes");
                        mail.sendMail(email, "buildcoserviceslk@gmail.com", "udffgvqezewcxeif");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    if(code.equals(JOptionPane.showInputDialog("Enter authentication code "))){
                        DBconnection.getNewConnection(username,pw).getConnection();
                        return new LoginDTO(list,first);                  
                    }else{
                        JOptionPane.showMessageDialog(panel,"Wrong authentication code !");
                    }
                }else{
                    DBconnection.getNewConnection(username,pw).getConnection();
                    return new LoginDTO(list,first);
                }
            }else if(password.equals(tempPassword) && tempPassword!=null){
                DBconnection.getNewConnection(username,pw).getConnection();
                return new LoginDTO(list,first);
            }else{
                JOptionPane.showMessageDialog(panel,"Wrong password !");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(panel,"Incorrect username ");
            e.printStackTrace();
        }
        return null;
    }
    
    private void generateTenMinuteCode(){
        
        Thread c= new Thread(){
            @Override
            public void run(){
                
                try {
                    Random rnd = new Random();
                    int number = rnd.nextInt(999999);
                    // this will convert any number sequence into 6 character.
                    code=String.format("%06d", number);
                    sleep(600000);
                    code=null;
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                
            }
        };
        c.start();
    }
    private void generateTenMinutePassword(){
        
        Thread c= new Thread(){
            @Override
            public void run(){
                try {
                    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                    tempPassword = RandomStringUtils.random( 8, characters );
                    sleep(600000);
                    tempPassword=null;
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                
            }
        };
        c.start();
    }

    @Override
    public void forgotPassword(JPanel panel, String ID) {
        try{
            // for(String d: databaseNames);
            MongoClient client=MongoClients.create("mongodb://securityUser117:cmk23WEr6@localhost:27017");
            MongoDatabase database = client.getDatabase("plantHireManagement");
            MongoCollection<Document> collection = database.getCollection("staffMember");
            Bson find= Filters.and( Filters.eq("username",ID));
            Document user = collection.find(find).first();
            Document contactDetails=(Document)user.get("contact");
            String email=(String)contactDetails.get("email");
            generateTenMinutePassword();
            sleep(1000);
            try{
                MailConnection mail=MailConnection.getConnection();
                mail.setSubject("Temporary Password");
                mail.setBody("Your temporary password "+tempPassword+"\nValid for 10 minutes");
                mail.sendMail(email, "buildcoserviceslk@gmail.com", "udffgvqezewcxeif");
                JOptionPane.showMessageDialog(panel,"A temporary password has been sent to your email");
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(panel,"Username entered does not exist");
        }
    }
    
}




