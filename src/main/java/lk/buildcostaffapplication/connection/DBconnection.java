/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.connection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Binath Perera
 */
public class DBconnection {
    private static DBconnection connection;
    private static MongoClient mc;
    private static String uri;
    private static String username;
    private static String password;
    private static final Properties p=new Properties();
            
    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
    
    private DBconnection(){
        mc= MongoClients.create(uri); //Creates mongo client with specified uri
    }
    
    public static DBconnection getConnection(){
        if(connection==null){
            connection= new DBconnection(); //Create new instance if it doesn't exist
        }
        return connection;
    } 
    
    public static DBconnection getNewConnection(String username,String password){
        try {
            //Get connection details from the properties file
            p.load(new FileReader("src/main/database-properties/DBconnection.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        String host = p.getProperty("host"); //Get database host from properties file
        String port = p.getProperty("port"); //Get port number from properties file
        DBconnection.username=username;
        DBconnection.password=password;
        uri = "mongodb://"+username+":"+password+"@"+host+":"+port+"/plantHireManagement";
        connection= new DBconnection();
        return connection;
    } 
    
    public MongoClient getMongoClient(){
        return mc;
    }
}













