/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dao.custom.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.buildcostaffapplication.connection.AES;
import lk.buildcostaffapplication.dao.custom.UserDAO;

import lk.buildcostaffapplication.dto.SuperDTO;
import lk.buildcostaffapplication.dto.UserDTO;
import lk.buildcostaffapplication.connection.DBconnection;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.Document;

/**
 *
 * @author Binath Perera
 */
public class UserDAOimpl implements UserDAO{
    private static DBconnection connection;
    private static MongoClient mc;
    MongoDatabase database ;
    MongoCollection<Document> collection;
    public UserDAOimpl(){
        connection=DBconnection.getConnection();
        mc=connection.getMongoClient();
        database = mc.getDatabase("plantHireManagement");
        collection = database.getCollection("staffMember");
    }
    
    @Override
    public ArrayList<SuperDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<SuperDTO> getUsersIdsAndNames() {
        FindIterable<Document> find = collection.find(eq("user",true));
        MongoCursor<Document> cursor = find.iterator();
        ArrayList<SuperDTO> idsAndNames=new ArrayList<>();
        try {
            while(cursor.hasNext()) {               
                Document d=cursor.next();
                String id = (String)d.get("_id");
                String username = (String)d.get("username");
                Document name= (Document)d.get("name");
                String first = (String)name.get("first");
                String middle = (String)name.get("middle");
                String last = (String)name.get("last");
                idsAndNames.add(new UserDTO(id,username,first,middle,last));
            }
        } finally {
            cursor.close();
        }
        return idsAndNames;
    }
    @Override
    public SuperDTO search(String id) {
        
        Document user = collection.find(Filters.and(eq("_id",id),eq("user",true))).first();
        String username = (String)user.get("username");
        String password = (String)user.get("password");
        try {
            password= AES.decrypt(password, AES.generateKey());
        } catch (Exception ex) {
            Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String email = (String)(((Document)user.get("contact")).get("email"));
        Document name= (Document)user.get("name");
        String first = (String)name.get("first");
        String middle = (String)name.get("middle");
        String last = (String)name.get("last");
        ArrayList<String> arr=(ArrayList<String>)user.get("access");
        return new UserDTO(id,username,password,email,first,middle,last,getUIaccessNames(arr));

    }
    @Override
    public boolean delete(String id) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", id);
        String username=(String)collection.find(eq("_id", id)).first().get("username");
        BasicDBObject deleteUserCmd = new BasicDBObject("dropUser",username);
        database.runCommand(deleteUserCmd);
        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",new BasicDBObject().append("user",false).append("username", "").append("password", "").append("access",new ArrayList<String>()));
        collection.updateOne(searchQuery, updateQuery);
        return true;
    }
    /*
    String roleDescriptions[][]=new String[][]{
        {"Manage Customers","Add,delete,view and update customer details"},
        {"Manage Items","Add,delete,view and update item details"},
        {"Manage Orders","Add,delete,view and update order details"},
        {"Manage Inquiries","Add,delete,view and update inquiry details"},
        {"Manage Transport","Add,delete,view and update transport details"},
        {"Manage Users","Add,delete,view and update user details"},
        {"Manage Existing Employee Job Details","View and update employee job details. Helps manage daily activities of an employee"},
        {"Add new employees","Will provide the ability to add a new employee"},
        {"Manage Existing Employee Personal and Employment Details","Add,delete,view and update existing employee personal and employment details"}
    };*/
    /*private ArrayList<String> getAccessibleCollections(ArrayList<String> roles){
        ArrayList<String> collections=new ArrayList<>();
        if(roles.contains("Manage Customers")){
            collections.add("customer");
        }
        if(roles.contains("Manage Items")||roles.contains("Manage Orders")||roles.contains("Manage Transport")){
            collections.add("item");
            collections.add("item.chuncks");
            collections.add("item.fs");
            
        }
        if(roles.contains("Manage Orders")||roles.contains("Manage Transport")){
            collections.add("order");
            collections.add("order.chuncks");
            collections.add("order.fs");
        }
        if(roles.contains("Manage Enquiries")){
            collections.add("enquiry");
        }
        if(roles.contains("Manage Users")||roles.contains("Manage Existing Employee Job Details")||roles.contains("Add new employees")||roles.contains("Manage Existing Employee Personal and Employment Details")){
            collections.add("staffMember"); 
        }
        if(roles.contains("Manage Existing Employee Job Details")||roles.contains("Add new employees")||roles.contains("Manage Existing Employee Personal and Employment Details")){
            collections.add("employee.chunks");
            collections.add("employee.files");
        }
        if(roles.contains("Manage Existing Employee Job Details")){
            collections.add("workRecords");
        }
        if(roles.contains("Manage Users")){
            collections.add("userLogFiles");
        }
        return collections;
    }*/
    private ArrayList<String> getAccessibleNames(ArrayList<String> roles){
        ArrayList<String> collections=new ArrayList<>();
        if(roles.contains("Manage Customers")){
            collections.add("customers");
        }
        if(roles.contains("Manage Items")){
            collections.add("items");
        }
        if(roles.contains("Manage Orders")){
            collections.add("orders");
        }
        if(roles.contains("Manage Transport")){
            collections.add("transport");
        }
        if(roles.contains("Manage Enquiries")){
            collections.add("enquiries");
        }
        if(roles.contains("Manage Users")){
            collections.add("users");
        }
        if(roles.contains("Manage Existing Employee Job Details")){
            collections.add("employeeJobDetails");
        }
        if(roles.contains("Add new employees")){
            collections.add("newEmployees");
        }
        if(roles.contains("Manage Existing Employee Personal and Employment Details")){            
            collections.add("employeeOtherDetails");
        }
        if(roles.contains("Manage Existing Employee Job Details")||roles.contains("Add new employees")||roles.contains("Manage Existing Employee Personal and Employment Details")){
            collections.add("employees");
        }
        if(roles.contains("Manage Existing Employee Job Details")||roles.contains("Manage Existing Employee Personal and Employment Details")){
            collections.add("existingEmployees");
        }
        return collections;
    }
    private ArrayList<String> getUIaccessNames(ArrayList<String> roles){
        ArrayList<String> names=new ArrayList<>();
        if(roles.contains("customers")){
            names.add("Manage Customers");
        }
        if(roles.contains("items")){
             names.add("Manage Items");
        }
        if(roles.contains("orders")){
            names.add("Manage Orders");
        }
        if(roles.contains("transport")){
            names.add("Manage Transport");
        }
        if(roles.contains("enquiries")){
            names.add("Manage Enquiries");
        }
        if(roles.contains("users")){
            names.add("Manage Users");
        }
        if(roles.contains("employeeJobDetails")){
            names.add("Manage Existing Employee Job Details");
        }
        if(roles.contains("newEmployees")){
            names.add("Add new employees");
        }
        if(roles.contains("employeeOtherDetails")){            
            names.add("Manage Existing Employee Personal and Employment Details");
        }
        return names;
    }
    @Override
    public boolean add(SuperDTO ob) {
        UserDTO user=(UserDTO)ob;
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", user.getId());

        BasicDBObject updateQuery = new BasicDBObject();
        try {
            updateQuery.append("$set",new BasicDBObject().append("contact.email",user.getEmail()).append("user",true).append("username",user.getUsername()).append("password", AES.encrypt(user.getPassword(), AES.generateKey())).append("access",getAccessibleNames(user.getRoles())).append("2FA", false));
        } catch (Exception ex) {
            Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ArrayList<String> roles=user.getRoles();
       // ArrayList<String> collections=getAccessibleCollections(roles);
        BasicDBObject createUserCmd=null;
        Document userRole1,userRole2=null,userRole3;
        userRole1=new Document("role", "readWrite").append("db","plantHireManagement");
        userRole3=new Document("role", "changeOwnPasswordRole").append("db","admin");
        if(roles.contains("Manage Users")){
            userRole2=new Document("role", "userAdminAnyDatabase").append("db","admin");
        }
        ArrayList<String> collectionsModified=new ArrayList<>();
        
        /*
        for(String collection:collections)userRole1.append("collection","plantHireManagement."+collection);
        */
       // for(String collection:collections)collectionsModified.add("plantHireManagement."+collection);
        userRole1.append("collections",collectionsModified);
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String generatedPassword = RandomStringUtils.random( 12, characters );
         MongoCollection secretCollection;
        if(userRole2==null){
            ArrayList<Document> a= new ArrayList<>();
            a.add(userRole1);
            a.add(userRole3);
            createUserCmd = new BasicDBObject("createUser", user.getUsername())
            .append("pwd", generatedPassword)
            .append("roles",a);
            MongoClient client=MongoClients.create("mongodb://securityUser117:cmk23WEr6@localhost:27017");
            secretCollection= client
                            .getDatabase("plantHireManagement")
                            .getCollection("userPasswords");
            database.runCommand(createUserCmd);
            try {
                secretCollection.insertOne(new Document("_id",user.getId()).append("databasePassword", AES.encrypt(generatedPassword, AES.generateKey())));
            } catch (Exception ex) {
                Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            ArrayList<Document> a= new ArrayList<>();
            a.add(userRole1);
            a.add(userRole2);
            a.add(userRole3);
            createUserCmd = new BasicDBObject("createUser", user.getUsername())
            .append("pwd", generatedPassword)
            .append("roles",a);
            MongoClient client=MongoClients.create("mongodb://securityUser117:cmk23WEr6@localhost:27017");
            secretCollection= client
                            .getDatabase("plantHireManagement")
                            .getCollection("userPasswords");
            database.runCommand(createUserCmd);
            try {
                secretCollection.insertOne(new Document("_id",user.getId()).append("databasePassword", AES.encrypt(generatedPassword, AES.generateKey())));
            } catch (Exception ex) {
                Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        return collection.updateOne(searchQuery, updateQuery).getMatchedCount()>0;
    }
    @Override
    public boolean update(SuperDTO ob) {
        UserDTO user=(UserDTO)ob;
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", user.getId());

        BasicDBObject updateQuery = new BasicDBObject();
        try {
            updateQuery.append("$set",new BasicDBObject().append("user",true).append("contact.email",user.getEmail()).append("username",user.getUsername()).append("password",  AES.encrypt(user.getPassword(), AES.generateKey())).append("access",getAccessibleNames(user.getRoles())));
        } catch (Exception ex) {
            Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String prevUsername=(String)collection.find(eq("_id", user.getId())).first().get("username");
        BasicDBObject updateUserCmd =null;
        if(prevUsername.equals(user.getUsername())){
            ArrayList<String> roles=user.getRoles();

            Document userRole1,userRole2=null,userRole3;
            userRole1=new Document("role", "readWrite").append("db","plantHireManagement");
            userRole3=new Document("role", "changeOwnPasswordRole").append("db","admin");
            if(roles.contains("Manage Users")){
                userRole2=new Document("role", "userAdminAnyDatabase").append("db","admin");
            }
            
            
            if(userRole2==null){
                ArrayList<Document> a= new ArrayList<>();
                a.add(userRole1);
                a.add(userRole3);
                updateUserCmd = new BasicDBObject("updateUser", user.getUsername())
                .append("roles",a);
            }else{
                ArrayList<Document> a= new ArrayList<>();
                a.add(userRole1);
                a.add(userRole2);
                a.add(userRole3);
                updateUserCmd = new BasicDBObject("updateUser", user.getUsername())
                .append("roles",a);
            }
            database.runCommand(updateUserCmd);
        }else{
            ArrayList<String> roles=user.getRoles();
            BasicDBObject createUserCmd=null;
            Document userRole1,userRole2=null,userRole3;
            userRole1=new Document("role", "readWrite").append("db","plantHireManagement");
            userRole3=new Document("role", "changeOwnPasswordRole").append("db","admin");
            if(roles.contains("Manage Users")){
                userRole2=new Document("role", "userAdminAnyDatabase").append("db","admin");
            }
            
            MongoClient client=MongoClients.create("mongodb://securityUser117:cmk23WEr6@localhost:27017");
            String password= (String)client
                            .getDatabase("plantHireManagement")
                            .getCollection("userPasswords")
                            .find(eq("_id",user.getId()))
                            .first().get("databasePassword");
            try {
                password=AES.decrypt(password, AES.generateKey());
            } catch (Exception ex) {
                Logger.getLogger(UserDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(userRole2==null){
                ArrayList<Document> a= new ArrayList<>();
                a.add(userRole1);
                a.add(userRole3);
                createUserCmd = new BasicDBObject("createUser", user.getUsername())
                .append("pwd", password)
                .append("roles",a);
            }else{
                ArrayList<Document> a= new ArrayList<>();
                a.add(userRole1);
                a.add(userRole2);
                a.add(userRole3);
                createUserCmd = new BasicDBObject("createUser", user.getUsername())
                .append("pwd", password)
                .append("roles",a);
            }
            try{
                BasicDBObject deleteUserCmd = new BasicDBObject("dropUser",prevUsername);
                database.runCommand(deleteUserCmd);
            }catch(Exception e){System.out.println("No previous user");}
            database.runCommand(createUserCmd);
            
        }
        
        return collection.updateOne(searchQuery, updateQuery).getMatchedCount()>0;
    }
}
