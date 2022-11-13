/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dao.custom.impl;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import lk.buildcostaffapplication.dao.custom.WorkRecordDAO;
import lk.buildcostaffapplication.dto.SuperDTO;
import lk.buildcostaffapplication.dto.WorkRecordDTO;
import lk.buildcostaffapplication.connection.DBconnection;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Binath Perera
 */
public class WorkRecordDAOimpl implements WorkRecordDAO{
    private static DBconnection connection;
    private static MongoClient mc;
    MongoDatabase database ;
    MongoCollection<Document> collection;
    public WorkRecordDAOimpl(){
        connection=DBconnection.getConnection();
        mc=connection.getMongoClient();
        database = mc.getDatabase("plantHireManagement");
        collection = database.getCollection("workRecords");
    }
    @Override
    public boolean add(SuperDTO ob) {
        WorkRecordDTO record= (WorkRecordDTO)ob; 
        Document d= new Document("date",record.getDay());
        d.append("employeeId",record.getEmployeeID());
        d.append("clockedIn", record.getClockedIn());
        d.append("clockedOut", record.getClockedOut());
        d.append("workLocation", record.getWorkLocation());
        d.append("workDescription", record.getWorkDescription());
        d.append("comment", record.getComment());
        collection.insertOne(d);
        return true;
    }

    @Override
    public boolean delete(String id) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("employeeId", id);
        DeleteResult result=null;
        try{
            result=collection.deleteMany(searchQuery);
            return true;
        }catch(MongoException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(SuperDTO ob) {
        WorkRecordDTO dto= (WorkRecordDTO)ob;
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("date", dto.getDay());
        searchQuery.append("employeeId", dto.getEmployeeID());

        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",new BasicDBObject()
                .append("clockedIn", dto.getClockedIn())
            .append("clockedOut", dto.getClockedOut())
            .append("workLocation", dto.getWorkLocation())
            .append("workDescription", dto.getWorkDescription())
            .append("comment", dto.getComment()));
        collection.updateOne(searchQuery, updateQuery);
        return true;
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
    public WorkRecordDTO getWorkRecordByIDandDay(String id, LocalDate day) {
        Bson find= Filters.and( Filters.eq("employeeId",id),Filters.eq("date",day));
        Document record = collection.find(find).first();
        if(record==null)return null;
        LocalTime clockedIn=null,clockedOut=null;
        String workLocation=null,workDescription=null,comment=null;
        Date ci=(Date)record.get("clockedIn");
        Date co=(Date)record.get("clockedOut");
        try{
            clockedIn= LocalTime.parse(ci.toInstant().atZone(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        try{
            clockedOut= LocalTime.parse(co.toInstant().atZone(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        workLocation=(String)record.get("workLocation");
        workDescription=(String)record.get("workDescription");
        comment=(String)record.get("comment");
        return new WorkRecordDTO(day,id,clockedIn,clockedOut,workLocation,workDescription,comment);
    }

    @Override
    public boolean deleteWorkRecord(String id, LocalDate d) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("employeeId", id);
        searchQuery.append("date", d);
        DeleteResult result=null;
        try{
            result=collection.deleteOne(searchQuery);
            return true;
        }catch(MongoException e){
            e.printStackTrace();
        }
        return false;
    }
    
}
