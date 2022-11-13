/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dao.custom.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.buildcostaffapplication.connection.AES;
import lk.buildcostaffapplication.dao.custom.SettingsDAO;
import lk.buildcostaffapplication.dto.SuperDTO;
import lk.buildcostaffapplication.connection.DBconnection;
import org.bson.Document;

/**
 *
 * @author Binath Perera
 */
public class SettingsDAOimpl implements SettingsDAO{
    MongoDatabase db;
    MongoCollection collection;
    public SettingsDAOimpl(){
        db=DBconnection.getConnection().getMongoClient().getDatabase("plantHireManagement");
        collection=db.getCollection("staffMember");
    }

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

    @Override
    public boolean update2FA(boolean twoFA,String username) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("username", username);

        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",new BasicDBObject().append("2FA",twoFA));
        return collection.updateOne(searchQuery, updateQuery).getModifiedCount()>0;
    }

    @Override
    public boolean get2FA(String username) {
        return (boolean)(((Document)collection.find(eq("username",username)).first()).get("2FA"));
    }

    @Override
    public String getEmail(String username) {
        return (String)((Document)(((Document)collection.find(eq("username",username)).first()).get("contact"))).get("email");
    }

    @Override
    public String getPassword(String username) {
        String value=null;
        try {
            value= AES.decrypt((String)(((Document)collection.find(eq("username",username)).first()).get("password")), AES.generateKey());
        } catch (Exception ex) {
            Logger.getLogger(SettingsDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    @Override
    public boolean updatePassword(String username,String password) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("username", username);

        BasicDBObject updateQuery = new BasicDBObject();
        try {
            updateQuery.append("$set",new BasicDBObject().append("password",AES.encrypt(password, AES.generateKey())));
        } catch (Exception ex) {
            Logger.getLogger(SettingsDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        BasicDBObject ob = new BasicDBObject("updateUser", username)
                .append("pwd", password);
        db.runCommand(ob);*/
        return collection.updateOne(searchQuery, updateQuery).getModifiedCount()>0;
    }

  
    
}
