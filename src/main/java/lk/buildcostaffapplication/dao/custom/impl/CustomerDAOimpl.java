/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dao.custom.impl;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import java.util.ArrayList;
import lk.buildcostaffapplication.connection.DBconnection;
import lk.buildcostaffapplication.dao.DAOFactory;
import lk.buildcostaffapplication.dao.custom.CustomerDAO;
import lk.buildcostaffapplication.dao.custom.OrderDAO;
import lk.buildcostaffapplication.dto.CustomerDTO;
import lk.buildcostaffapplication.dto.SuperDTO;
import org.bson.Document;
/**
 *
 * @author Binath Perera
 */
public class CustomerDAOimpl implements CustomerDAO{

    private static DBconnection connection;
    private static MongoClient mc;
    private static MongoDatabase database ;
    private static MongoCollection<Document> collection;
    private static OrderDAO orderDao=(OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtype.ORDER);
    public CustomerDAOimpl(){
        connection=DBconnection.getConnection();
        mc=connection.getMongoClient();
        database = mc.getDatabase("plantHireManagement");
        collection = database.getCollection("customer");
        
    }
    @Override
    public boolean add(SuperDTO ob) {
        CustomerDTO customer=(CustomerDTO) ob;
        Document d= new Document();
        if(customer.getEmail().equals("")){
            d.append("_id", customer.getNic()); 
        }else{
            d.append("_id", customer.getEmail());
        }
      
        d.append("name", customer.getName());
        d.append("companyName", customer.getCompanyName());
        d.append("nic", customer.getNic());
        d.append("email", customer.getEmail());
        d.append("password", customer.getPassword());
        d.append("billingAddress",customer.getBillingAddress());
        d.append("destinationAddress", customer.getDestinationAddress());
        d.append("contact", customer.getContact());
        try{
            collection.insertOne(d);
        }catch(Exception e){
            return false;
        }
        return true;
    }
/*this.nic = nic;
        this.name = name;
        this.companyName = companyName;
        this.email = email;
        this.billingAddress = billingAddress;
        this.destinationAddress = destinationAddress;
        this.contact = contact;*/
    @Override
    public boolean delete(String id) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", id);
        DeleteResult result=null;
        try{
            result=collection.deleteOne(searchQuery);
            return result.getDeletedCount()>0;
        }catch(MongoException e){
            e.printStackTrace();
        }
       /*OrderDAO dao = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtype.ORDER);
        dao.deleteOrdersByCustomer*/
        return true;
    }

    @Override
    public SuperDTO search(String id) {
        Document first = collection.find(eq("_id",id)).first();
        String name=(String)first.get("name");
        String companyName=(String)first.get("companyName");
        String nic=(String)first.get("nic");
        String email=(String)first.get("email");
        String password=(String)first.get("password");
        String billingAddress=(String)first.get("billingAddress");
        String destinationAddress=(String)first.get("destinationAddress");
        String contact=(String)first.get("contact");
        return new CustomerDTO(nic, name, companyName, email, password, billingAddress, destinationAddress, contact);
    }

    @Override
    public ArrayList getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CustomerDTO> getSomeDetailsOfAllCustomers() {
        ArrayList<CustomerDTO> arr =new ArrayList<>();
        FindIterable<Document> find = collection.find();
        MongoCursor<Document> cursor = find.cursor();
        while(cursor.hasNext()){
            Document d= cursor.next();
            String name=(String)d.get("name");
            String nic=(String)d.get("nic");
            String email=(String)d.get("email");
            arr.add(new CustomerDTO(nic, name,email));
        }  
        return arr;
    }

    @Override
    public boolean setGeneralDetails(CustomerDTO customer) {
        if(!customer.getEmail().equals("")){
            if(collection.find(eq("_id",customer.getEmail())).first()==null){
                Document d= new Document();
                d.append("_id", customer.getEmail());
                d.append("name", customer.getName());
                d.append("companyName", customer.getCompanyName());
                d.append("nic", customer.getNic());
                d.append("email", customer.getEmail());
                d.append("password", customer.getPassword());
                d.append("billingAddress",customer.getBillingAddress());
                d.append("destinationAddress", customer.getDestinationAddress());
                d.append("contact", customer.getContact());
                collection.insertOne(d);
            }else{
                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.append("_id", customer.getEmail()); 
                BasicDBObject updateQuery = new BasicDBObject();
                updateQuery.append("$set",new BasicDBObject()
                        .append("name", customer.getName())
                        .append("companyName", customer.getCompanyName())
                        .append("nic", customer.getNic())
                        .append("email", customer.getEmail())
                        .append("password", customer.getPassword())
                        .append("billingAddress",customer.getBillingAddress())
                        .append("destinationAddress", customer.getDestinationAddress())
                        .append("contact", customer.getContact()));
                collection.updateOne(searchQuery, updateQuery);
            }
        }else{
            if(collection.find(eq("_id",customer.getNic())).first()==null){
                Document d= new Document();
                d.append("_id", customer.getNic());
                d.append("name", customer.getName());
                d.append("companyName", customer.getCompanyName());
                d.append("nic", customer.getNic());
                d.append("email", customer.getEmail());
                d.append("password", customer.getPassword());
                d.append("billingAddress",customer.getBillingAddress());
                d.append("destinationAddress", customer.getDestinationAddress());
                d.append("contact", customer.getContact());
                collection.insertOne(d);
            }else{
                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.append("_id", customer.getNic()); 
                BasicDBObject updateQuery = new BasicDBObject();
                updateQuery.append("$set",new BasicDBObject()
                        .append("name", customer.getName())
                        .append("companyName", customer.getCompanyName())
                        .append("nic", customer.getNic())
                        .append("email", customer.getEmail())
                        .append("password", customer.getPassword())
                        .append("billingAddress",customer.getBillingAddress())
                        .append("destinationAddress", customer.getDestinationAddress())
                        .append("contact", customer.getContact()));
                collection.updateOne(searchQuery, updateQuery);
            }
        }
        return true;
    }
    @Override
    public boolean setDetailsWithoutPassword(CustomerDTO customer) {
        if(!customer.getEmail().equals("")){
            if(collection.find(eq("_id",customer.getEmail())).first()!=null){
                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.append("_id", customer.getEmail()); 
                BasicDBObject updateQuery = new BasicDBObject();
                updateQuery.append("$set",new BasicDBObject()
                .append("name", customer.getName())
                .append("companyName", customer.getCompanyName())
                .append("nic", customer.getNic())
                .append("contact", customer.getContact()));
                collection.updateOne(searchQuery, updateQuery);
                return true;
            }else{
                Document d= new Document();
                d.append("_id", customer.getEmail());
                d.append("name", customer.getName());
                d.append("companyName", customer.getCompanyName());
                d.append("nic", customer.getNic());
                d.append("email", customer.getEmail());
                d.append("password", customer.getPassword());
                d.append("billingAddress",customer.getBillingAddress());
                d.append("destinationAddress", customer.getDestinationAddress());
                d.append("contact", customer.getContact());
                collection.insertOne(d);
                return true;
            }
        }
        if(customer.getEmail().equals("")){
            if(collection.find(eq("_id",customer.getNic())).first()!=null){
                BasicDBObject searchQuery = new BasicDBObject();
                searchQuery.append("_id", customer.getNic()); 
                BasicDBObject updateQuery = new BasicDBObject();
                updateQuery.append("$set",new BasicDBObject()
                .append("name", customer.getName())
                .append("companyName", customer.getCompanyName())
                .append("nic", customer.getNic())
                .append("email", customer.getEmail())
                .append("contact", customer.getContact()));
                collection.updateOne(searchQuery, updateQuery);
                return true;
            }else{
                Document d= new Document();
                d.append("_id", customer.getNic());
                d.append("name", customer.getName());
                d.append("companyName", customer.getCompanyName());
                d.append("nic", customer.getNic());
                d.append("email", customer.getEmail());
                d.append("password", customer.getPassword());
                d.append("billingAddress",customer.getBillingAddress());
                d.append("destinationAddress", customer.getDestinationAddress());
                d.append("contact", customer.getContact());
                collection.insertOne(d);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean update(SuperDTO ob) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
