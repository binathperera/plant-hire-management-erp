/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dao.custom.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.DeleteResult;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import lk.buildcostaffapplication.connection.DBconnection;
import lk.buildcostaffapplication.dao.custom.EnquiryDAO;
import lk.buildcostaffapplication.dto.EnquiryDTO;
import lk.buildcostaffapplication.dto.SuperDTO;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Binath Perera
 */
public class EnquiryDAOimpl implements EnquiryDAO{
    private static DBconnection connection;
    private static MongoClient mc;
    MongoDatabase database ;
    MongoCollection<Document> collection;
    public EnquiryDAOimpl(){
        connection=DBconnection.getConnection();
        mc=connection.getMongoClient();
        database = mc.getDatabase("plantHireManagement");
        collection = database.getCollection("enquiry");
    }
    @Override
    public boolean add(SuperDTO ob) {
        EnquiryDTO enquiry=(EnquiryDTO) ob;
        Document d=new Document();
        d.append("date", enquiry.getDate());
        d.append("customerName", enquiry.getCustomerName());
        d.append("companyName", enquiry.getCompanyName());
        d.append("telephone", enquiry.getTelephone());
        d.append("locationOfHire", enquiry.getLocationOfHire());
        d.append("status", enquiry.getStatus());
        d.append("email", enquiry.getEmail());
        d.append("nic", enquiry.getNic());
        d.append("message", enquiry.getMessage());
        d.append("reponse", enquiry.getResponse());
        d.append("itemList", enquiry.getItemList());
        collection.insertOne(d);
        return true;
    }

    @Override
    public boolean delete(String id) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", new ObjectId(id));
        DeleteResult result=null;
        try{
            result=collection.deleteOne(searchQuery);
            return result.getDeletedCount()>0;
        }catch(MongoException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(SuperDTO ob) {
        EnquiryDTO enquiry=(EnquiryDTO) ob;
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", new ObjectId(enquiry.getId()));

        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",new BasicDBObject()
                .append("date", enquiry.getDate())
                .append("customerName", enquiry.getCustomerName())
                .append("companyName", enquiry.getCompanyName())
                .append("telephone", enquiry.getTelephone())
                .append("locationOfHire", enquiry.getLocationOfHire())
                .append("status", enquiry.getStatus())
                .append("email", enquiry.getEmail())
                .append("nic", enquiry.getNic())
                .append("message", enquiry.getMessage())
                .append("reponse", enquiry.getResponse())
                .append("itemList", enquiry.getItemList()));
        collection.updateOne(searchQuery, updateQuery);
        return true;
    }
/*this.id=id;
        this.date = date;
        this.customerName = customerName;
        this.companyName = companyName;
        this.telephone = telephone;
        this.locationOfHire = locationOfHire;
        this.status = status;
        this.email = email;
        this.nic = nic;
        this.message = message;
        this.response = response;
        this.itemList = itemList;*/
    @Override
    public ArrayList<EnquiryDTO> search(int timeIndex, int statusIndex, String nicOrEmail, LocalDate fromDate, LocalDate toDate) {
        ArrayList<EnquiryDTO> arr=new ArrayList<>();
        FindIterable<Document> find=null;
        if(timeIndex==0){
            if(statusIndex==0){
                if(nicOrEmail.equals("")){
                    find = collection.find(eq("status","open"));
                }else{
                    if(collection.countDocuments(Filters.and(eq("status","open"),eq("nic",nicOrEmail)))>0)
                    find = collection.find(Filters.and(eq("status","open"),eq("nic",nicOrEmail)));
                    if(collection.countDocuments(Filters.and(eq("status","open"),eq("email",nicOrEmail)))>0)
                    find = collection.find(Filters.and(eq("status","open"),eq("email",nicOrEmail)));
                }
            }
            if(statusIndex==1){
                if(nicOrEmail.equals("")){
                    find = collection.find(eq("status","closed"));
                }else{
                    if(collection.countDocuments(Filters.and(eq("status","closed"),eq("nic",nicOrEmail)))>0)
                    find = collection.find(Filters.and(eq("status","closed"),eq("nic",nicOrEmail)));
                    if(collection.countDocuments(Filters.and(eq("status","closed"),eq("email",nicOrEmail)))>0)
                    find = collection.find(Filters.and(eq("status","closed"),eq("email",nicOrEmail)));
                }
            }
            if(statusIndex==2){
                if(nicOrEmail.equals("")){
                    find = collection.find();
                }else{
                    if(collection.countDocuments(eq("nic",nicOrEmail))>0)
                    find = collection.find(eq("nic",nicOrEmail));
                    if(collection.countDocuments(eq("email",nicOrEmail))>0)
                    find = collection.find(eq("email",nicOrEmail));
                }
            }
        }
        if(timeIndex==1){
            if(statusIndex==0){
                if(nicOrEmail.equals("")){
                    BasicDBObject query = new BasicDBObject();
                    query.put("status","open");
                    query.put("date", BasicDBObjectBuilder.start("$gte", fromDate).add("$lte", toDate).get());
                    find=collection.find(query).sort(new BasicDBObject("date", -1));
                }else{
                    if(collection.countDocuments(Filters.and(eq("status","open"),eq("nic",nicOrEmail)))>0){
                        BasicDBObject query = new BasicDBObject();
                        query.put("status","open");
                        query.put("nic",nicOrEmail);
                        query.put("date", BasicDBObjectBuilder.start("$gte", fromDate).add("$lte", toDate).get());
                        find=collection.find(query).sort(new BasicDBObject("date", -1));
                    }
                    if(collection.countDocuments(Filters.and(eq("status","open"),eq("email",nicOrEmail)))>0){
                        BasicDBObject query = new BasicDBObject();
                        query.put("status","open");
                        query.put("email",nicOrEmail);
                        query.put("date", BasicDBObjectBuilder.start("$gte", fromDate).add("$lte", toDate).get());
                        find=collection.find(query).sort(new BasicDBObject("date", -1));
                    }
                }
            }
            if(statusIndex==1){
                if(nicOrEmail.equals("")){
                    BasicDBObject query = new BasicDBObject();
                    query.put("status","closed");
                    query.put("date", BasicDBObjectBuilder.start("$gte", fromDate).add("$lte", toDate).get());
                    find=collection.find(query).sort(new BasicDBObject("date", -1));
                }else{
                    if(collection.countDocuments(Filters.and(eq("status","closed"),eq("nic",nicOrEmail)))>0){
                        BasicDBObject query = new BasicDBObject();
                        query.put("status","closed");
                        query.put("nic",nicOrEmail);
                        query.put("date", BasicDBObjectBuilder.start("$gte", fromDate).add("$lte", toDate).get());
                        find=collection.find(query).sort(new BasicDBObject("date", -1));
                    }
                    if(collection.countDocuments(Filters.and(eq("status","closed"),eq("email",nicOrEmail)))>0){
                       BasicDBObject query = new BasicDBObject();
                        query.put("status","closed");
                        query.put("email",nicOrEmail);
                        query.put("date", BasicDBObjectBuilder.start("$gte", fromDate).add("$lte", toDate).get());
                        find=collection.find(query).sort(new BasicDBObject("date", -1));
                    }
                }
            }
            if(statusIndex==2){
                if(nicOrEmail.equals("")){
                    BasicDBObject query = new BasicDBObject();
                    query.put("date", BasicDBObjectBuilder.start("$gte", fromDate).add("$lte", toDate).get());
                    find=collection.find(query).sort(new BasicDBObject("date", -1));
                }else{
                    if(collection.countDocuments(eq("nic",nicOrEmail))>0){
                        BasicDBObject query = new BasicDBObject();
                        query.put("nic",nicOrEmail);
                        query.put("date", BasicDBObjectBuilder.start("$gte", fromDate).add("$lte", toDate).get());
                        find=collection.find(query).sort(new BasicDBObject("date", -1));
                    }
                    if(collection.countDocuments(eq("email",nicOrEmail))>0){
                        BasicDBObject query = new BasicDBObject();
                        query.put("email",nicOrEmail);
                        query.put("date", BasicDBObjectBuilder.start("$gte", fromDate).add("$lte", toDate).get());
                        find=collection.find(query).sort(new BasicDBObject("date", -1));
                    }
                }
            }
            
        }
        if(find!=null){
            for(Document d:find){
                EnquiryDTO enquiry=new EnquiryDTO();
                String id=((ObjectId)d.get("_id")).toHexString();
                enquiry.setId(id);
                enquiry.setDate(LocalDate.parse(((Date)d.get("date")).toInstant().atZone(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
                enquiry.setCustomerName((String)d.get("customerName"));
                enquiry.setCompanyName((String)d.get("companyName"));
                enquiry.setStatus((String)d.get("status"));
                arr.add(enquiry);
            }
        }
        return arr;
    }
    
    @Override
    public int getOpenEnquiryCount() {
        return (int)collection.countDocuments(eq("status","open"));
    }
   
    @Override
    public SuperDTO search(String id) {
        System.out.println(id);
        Document first = collection.find(eq("_id",new ObjectId(id))).first();
       /* BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",new BasicDBObject()
                .append("date", enquiry.getDate())
                .append("customerName", enquiry.getCustomerName())
                .append("companyName", enquiry.getCompanyName())
                .append("telephone", enquiry.getTelephone())
                .append("locationsOfHire", enquiry.getLocationOfHire())
                .append("status", enquiry.getStatus())
                .append("email", enquiry.getEmail())
                .append("nic", enquiry.getNic())
                .append("message", enquiry.getMessage())
                .append("reponse", enquiry.getResponse())
                .append("itemList", enquiry.getItemList()));*/
        LocalDate date=LocalDate.parse(((Date)first.get("date")).toInstant().atZone(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        String customerName=(String)first.get("customerName");
        String companyName=(String)first.get("companyName");
        String telephone=(String)first.get("telephone");
        String locationOfHire=(String)first.get("locationOfHire");
        String status=(String)first.get("status");
        String email=(String)first.get("email");
        String nic=(String)first.get("nic");
        String message=(String)first.get("message");
        String response=(String)first.get("response");
        ArrayList<String> itemNames=(ArrayList<String>) first.get("itemList");
        return new EnquiryDTO(id, date, customerName, companyName, telephone, locationOfHire, status, email, nic, message, response, itemNames);
    }
    
    @Override
    public ArrayList getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
