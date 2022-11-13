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
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;
import com.mongodb.client.result.DeleteResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.buildcostaffapplication.connection.DBconnection;
import lk.buildcostaffapplication.dao.custom.ItemDAO;
import lk.buildcostaffapplication.dto.ItemDTO;
import lk.buildcostaffapplication.dto.SuperDTO;
import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Binath Perera
 */
public class ItemDAOimpl implements ItemDAO{
    
    private static DBconnection connection;
    private static MongoClient mc;
    private static MongoDatabase database ;
    private static MongoCollection<Document> collection;
    private static GridFSBucket gridFSBucket;
    public ItemDAOimpl(){
        connection=DBconnection.getConnection();
        mc=connection.getMongoClient();
        database = mc.getDatabase("plantHireManagement");
        collection = database.getCollection("item");
        gridFSBucket= GridFSBuckets.create(database,"item");
    }
    
    @Override
    public boolean add(SuperDTO ob) {
        ItemDTO item=(ItemDTO)ob;
        Document mongoItem=new Document("_id",item.getSku())
                .append("depot", item.getDepot())
                .append("name", item.getName())
                .append("overview", item.getOverview())
                .append("specifications", item.getSpecifications())
                .append("attachments", item.getAttachments())
                .append("safetyEquipment", item.getSafetyEquipment())
                .append("totalQty", item.getTotalQty()+item.getQtyChange())
                .append("existingQty", item.getExistingQty()+item.getQtyChange())
                .append("originalVal", item.getOriginalVal())
                .append("currentVal", item.getCurrentVal())
                .append("rate", item.getRate());
        //Image
        try (InputStream streamToUploadFrom = new FileInputStream(item.getImage()) ) {
            GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1048576);//1MB chunck size
            ObjectId fileId = gridFSBucket.uploadFromStream(item.getImage().getName(), streamToUploadFrom, options);
            System.out.println("The file id of the image file is: " + fileId.toHexString());
            mongoItem.append("imageFileId", fileId.toHexString());
        }catch(Exception e){
            mongoItem.append("imageFileId", "");
            e.printStackTrace();
        }
        //User Manual
        try (InputStream streamToUploadFrom = new FileInputStream(item.getUserManual()) ) {
            GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1048576);//1MB chunck size
            ObjectId fileId = gridFSBucket.uploadFromStream(item.getUserManual().getName(), streamToUploadFrom, options);
            System.out.println("The file id of the uploaded file is: " + fileId.toHexString());
            mongoItem.append("userManualFileId", fileId.toHexString());
        }catch(Exception e){
            mongoItem.append("userManualFileId", "");
            e.printStackTrace();
        }   
        //Service Manual
        try (InputStream streamToUploadFrom = new FileInputStream(item.getServiceManual()) ) {
            GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1048576);//1MB chunck size
            ObjectId fileId = gridFSBucket.uploadFromStream(item.getServiceManual().getName(), streamToUploadFrom, options);
            System.out.println("The file id of the uploaded file is: " + fileId.toHexString());
            mongoItem.append("serviceManualFileId", fileId.toHexString());
        }catch(Exception e){
            mongoItem.append("serviceManualFileId", "");
            e.printStackTrace();
        }   
        collection.insertOne(mongoItem);
        return true;
    }

    @Override
    public boolean delete(String id) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", id);
        DeleteResult result=null;
        try{
            Document d= collection.find(eq("_id",id)).first();
            String imageFileId=(String)d.get("imageFileId");
            String userManualFileId=(String)d.get("userManualFileId");
            String serviceManualFileId=(String)d.get("serviceManualFileId");
            if(!imageFileId.equals(""))gridFSBucket.delete(new ObjectId(imageFileId));
            if(!userManualFileId.equals(""))gridFSBucket.delete(new ObjectId(userManualFileId));
            if(!serviceManualFileId.equals(""))gridFSBucket.delete(new ObjectId(serviceManualFileId));
            result=collection.deleteOne(searchQuery);
            
            return result.getDeletedCount()>0;
        }catch(MongoException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(SuperDTO ob) {
        ItemDTO item=(ItemDTO) ob;
        Document d= collection.find(eq("_id",item.getSku())).first();
        String imageFileId=(String)d.get("imageFileId");
        String userManualFileId=(String)d.get("userManualFileId");
        String serviceManualFileId=(String)d.get("serviceManualFileId");
        if(!imageFileId.equals(""))gridFSBucket.delete(new ObjectId(imageFileId));
        if(!userManualFileId.equals(""))gridFSBucket.delete(new ObjectId(userManualFileId));
        if(!serviceManualFileId.equals(""))gridFSBucket.delete(new ObjectId(serviceManualFileId));
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id",item.getSku());

        //Image
        ObjectId imageObjectId=null;
        try (InputStream streamToUploadFrom = new FileInputStream(item.getImage()) ) {
            GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1048576);//1MB chunck size
           imageObjectId= gridFSBucket.uploadFromStream(item.getImage().getName(), streamToUploadFrom, options);
           
        }catch(Exception e){
            e.printStackTrace();
        }
        //User Manual
        ObjectId userManualObjectId=null;
        try (InputStream streamToUploadFrom = new FileInputStream(item.getUserManual()) ) {
            GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1048576);//1MB chunck size
            userManualObjectId= gridFSBucket.uploadFromStream(item.getUserManual().getName(), streamToUploadFrom, options);
          
        }catch(Exception e){
            e.printStackTrace();
        }   
        //Service Manual
        ObjectId serviceManualObjectId=null;
        try (InputStream streamToUploadFrom = new FileInputStream(item.getServiceManual()) ) {
            GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1048576);//1MB chunck size
            serviceManualObjectId = gridFSBucket.uploadFromStream(item.getServiceManual().getName(), streamToUploadFrom, options);
            
        }catch(Exception e){
            e.printStackTrace();
        }  
        
        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",new BasicDBObject().append("_id",item.getSku())
                .append("depot", item.getDepot())
                .append("name", item.getName())
                .append("overview", item.getOverview())
                .append("specifications", item.getSpecifications())
                .append("attachments", item.getAttachments())
                .append("safetyEquipment", item.getSafetyEquipment())
                .append("totalQty", item.getTotalQty()+item.getQtyChange())
                .append("existingQty", item.getExistingQty()+item.getQtyChange())
                .append("originalVal", item.getOriginalVal())
                .append("currentVal", item.getCurrentVal())
                .append("rate", item.getRate())
                .append("imageFileId",(imageObjectId!=null)?imageObjectId.toHexString():"")
                .append("userManualFileId", (userManualObjectId!=null)?userManualObjectId.toHexString():"")
                .append("serviceManualFileId", (serviceManualObjectId!=null)?serviceManualObjectId.toHexString():""));
        collection.updateOne(searchQuery, updateQuery);
        return true;
    }
    @Override
    public SuperDTO search(String id) {
        Document d=collection.find(eq("_id",id)).first();
        if(d==null)return null; 
        String depot=(String)d.get("depot");
        String name=(String)d.get("name");
        String overview=(String)d.get("overview");
        String specifications=(String)d.get("specifications");
        String attachments=(String)d.get("attachments");
        String safetyEquipment=(String)d.get("safetyEquipment");
        int totalQty=(int)d.get("totalQty");
        int existingQty=(int)d.get("existingQty");
        double originalVal=(double)d.get("originalVal");
        double currentVal=(double)d.get("currentVal");
        double rate=(double)d.get("rate");
        ObjectId imageFileId = new ObjectId();
        ObjectId userManualFileId = new ObjectId();
        ObjectId serviceManualFileId = new ObjectId();
        String imageFileIdString=(String)d.get("imageFileId");
        String userManualFileIdString=(String)d.get("userManualFileId");
        String serviceManualFileIdString=(String)d.get("serviceManualFileId");
        if(!imageFileIdString.equals(""))imageFileId=new ObjectId(imageFileIdString);
        if(!userManualFileIdString.equals(""))userManualFileId=new ObjectId(userManualFileIdString);
        if(!serviceManualFileIdString.equals(""))serviceManualFileId=new ObjectId(serviceManualFileIdString);
        Bson q1 = Filters.eq("_id", imageFileId);
        GridFSFile d1= gridFSBucket.find(q1).first();
        Bson q2 = Filters.eq("_id", userManualFileId);
        GridFSFile d2= gridFSBucket.find(q2).first();
        Bson q3 = Filters.eq("_id", serviceManualFileId);
        GridFSFile d3= gridFSBucket.find(q3).first();
        String imageFileName= null;
        String userManualFileName= null;      
        String serviceManualFileName= null;
        try{
            imageFileName=d1.getFilename();
        }catch(NullPointerException e){
            System.out.println("Null image file");   
        }
        try{
            userManualFileName=d2.getFilename();
        }catch(NullPointerException e){
             System.out.println("Null user manual file");      
        }
        try{
            serviceManualFileName=d3.getFilename();
        }catch(NullPointerException e){
             System.out.println("Null service manual file");      
        }
        File directory = new File(System.getProperty("user.home") + "/Desktop/ItemFiles");

        if (! directory.exists()){
            directory.mkdir();
        }else{
            try {
                FileUtils.cleanDirectory(directory);
            } catch (IOException ex) {
                Logger.getLogger(OrderDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        File image=null;
        File userManual=null;
        File serviceManual=null;
        if(imageFileName!=null)
        try (FileOutputStream streamToDownloadTo = new FileOutputStream(System.getProperty("user.home") + "/Desktop/ItemFiles/" + imageFileName)) {
            gridFSBucket.downloadToStream(imageFileId, streamToDownloadTo);
            streamToDownloadTo.flush();
            image=new File(System.getProperty("user.home") + "/Desktop/ItemFiles/" + imageFileName);
        }catch(Exception e){
            e.printStackTrace();
        }

        if(userManualFileName!=null){
            try (FileOutputStream streamToDownloadTo = new FileOutputStream(System.getProperty("user.home") + "/Desktop/ItemFiles/" + userManualFileName)) {
                gridFSBucket.downloadToStream(userManualFileId, streamToDownloadTo);
                streamToDownloadTo.flush();
                userManual=new File(System.getProperty("user.home") + "/Desktop/ItemFiles/" + userManualFileName);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(serviceManualFileName!=null){
            try (FileOutputStream streamToDownloadTo = new FileOutputStream(System.getProperty("user.home") + "/Desktop/ItemFiles/"+serviceManualFileName)) {
                gridFSBucket.downloadToStream(serviceManualFileId, streamToDownloadTo);
                streamToDownloadTo.flush();
                serviceManual=new File(System.getProperty("user.home") + "/Desktop/ItemFiles/"+serviceManualFileName);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return new ItemDTO(id, depot, name, overview, specifications, attachments, 
                safetyEquipment, totalQty, existingQty, 0, originalVal, currentVal,
                image, userManual, serviceManual,rate);
    }
    
    @Override
    public ArrayList<ItemDTO> getItemSKUsAndNames() {
        ArrayList<ItemDTO> arr=new ArrayList<>();
        FindIterable<Document> find = collection.find();
        MongoCursor<Document> cursor= find.cursor();
        while(cursor.hasNext()){
            Document item = cursor.next();
            arr.add(new ItemDTO((String)item.get("_id"),(String)item.get("name")));
        }
        return arr;
    }
    
    @Override
    public ArrayList getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ItemDTO> getItemForOrders() {
         ArrayList<ItemDTO> arr=new ArrayList<>();
        FindIterable<Document> find = collection.find();
        MongoCursor<Document> cursor= find.cursor();
        while(cursor.hasNext()){
            Document item = cursor.next();
            arr.add(new ItemDTO((String)item.get("_id"),(String)item.get("name"),(int)item.get("existingQty"),(double)item.get("rate")));
        }
        return arr;
    }

    @Override
    public boolean increaseItemQuantity(String sku, int quantity) {
        Bson filter = eq("_id", sku);
        Bson update = inc("qty", quantity);
        collection.updateOne(filter, update);
        return true;
    }

    @Override
    public boolean reduceItemQuantity(String sku, int quantity) {
        Bson filter = eq("_id", sku);
        Bson update = inc("qty", (-1)*quantity);
        collection.updateOne(filter, update);
        return true;
    }
    

    
}
