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
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.buildcostaffapplication.connection.DBconnection;
import lk.buildcostaffapplication.connection.MailConnection;
import lk.buildcostaffapplication.dao.DAOFactory;
import lk.buildcostaffapplication.dao.custom.ItemDAO;
import lk.buildcostaffapplication.dao.custom.OrderDAO;
import lk.buildcostaffapplication.dto.InstallmentDTO;
import lk.buildcostaffapplication.dto.OrderDTO;
import lk.buildcostaffapplication.dto.OrderItemDTO;
import lk.buildcostaffapplication.dto.OrderTransportDTO;
import lk.buildcostaffapplication.dto.SuperDTO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Binath Perera
 */
public class OrderDAOimpl implements OrderDAO{

    private static DBconnection connection;
    private static MongoClient mc;
    private static MongoDatabase database ;
    private static MongoCollection<Document> collection;
    private static GridFSBucket gridFSBucket;
    private static ItemDAO idao= (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtype.ITEM);
    public OrderDAOimpl(){
        connection=DBconnection.getConnection();
        mc=connection.getMongoClient();
        database = mc.getDatabase("plantHireManagement");
        collection = database.getCollection("order");
        gridFSBucket= GridFSBuckets.create(database,"order");
        
    }
    @Override
    public boolean add(SuperDTO ob) {
        
        OrderDTO order=(OrderDTO) ob;
        ArrayList<OrderItemDTO> itemsList = order.getItems();
        ArrayList<InstallmentDTO> installmentsList = order.getInstallments();
        ArrayList<Document> items= new ArrayList<>();
        for(OrderItemDTO orderItem:itemsList){
            Document item=new Document();
            item.append("sku", orderItem.getSku());
            item.append("name", orderItem.getName());
            item.append("quantity", orderItem.getQty());
            idao.reduceItemQuantity(orderItem.getSku(),orderItem.getQty());
            item.append("totalPrice", orderItem.getTotalPrice());
            items.add(item);
        }
        ArrayList<Document> installments= new ArrayList<>();
        for(InstallmentDTO orderInstallment:installmentsList){
            Document installment=new Document();
            installment.append("number", orderInstallment.getNumber());
            installment.append("amount", orderInstallment.getAmount());
            installment.append("date", orderInstallment.getDate());
            installment.append("paid", orderInstallment.isPaid());
            installment.append("isInvoiceIssued", false);
            installments.add(installment);
        }
        Document d= new Document();
        d.append("_id", order.getOrderId());
        d.append("customerId", order.getCustomerId());
        d.append("requestedFrom", order.getFrom());
        d.append("requestedTo", order.getTo());
        d.append("orderDate",order.getOrderDate());
        d.append("status", order.getStatus());
        d.append("totalPriceForItems", order.getTotalPriceForItems());
        d.append("deliveryCharges", order.getDeliveryCharges());
        d.append("discount",order.getDiscount());
        d.append("netTotal", order.getNetTotal());
        d.append("items", items);
        d.append("installments", installments);
        d.append("deliver", order.isDeliver());
        d.append("pickup", order.isPickup());
        d.append("delivered", false);
        d.append("pickedUp", false);
        d.append("deliverDetails", "");
        d.append("pickupDetails", "");
        d.append("billingAddress", order.getBillingAddress());
        d.append("location", order.getLocation());
        try (InputStream streamToUploadFrom = new FileInputStream(order.getLeaseAgreement()) ) {
            GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1048576);//1MB chunck size
            ObjectId fileId = gridFSBucket.uploadFromStream(order.getLeaseAgreement().getName(), streamToUploadFrom, options);
            System.out.println("The file id of the image file is: " + fileId.toHexString());
            d.append("leaseAgreementFileId", fileId.toHexString());
        }catch(Exception e){
            d.append("leaseAgreementFileId", "");
            e.printStackTrace();
        }
        collection.insertOne(d);
        return true;
    }

    @Override
    public boolean delete(String id) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", id);
        DeleteResult result=null;
        try{
            Document d = collection.find(eq("_id",id)).first();
            String leaseAgreementFileId=(String)d.get("leaseAgreementFileId");
            if(!leaseAgreementFileId.equals(""))gridFSBucket.delete(new ObjectId(leaseAgreementFileId));
            
            ArrayList<Document> arr=(ArrayList<Document>)d.get("items");
            for(Document item: arr){
                String sku=(String)item.get("sku");
                int quantity=(int)item.get("quantity");
                idao.increaseItemQuantity(sku,quantity);
            }
            
            result=collection.deleteOne(searchQuery);
            return result.getDeletedCount()>0;
        }catch(MongoException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(SuperDTO ob) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SuperDTO search(String id) {
        Document d = collection.find(eq("_id",id)).first();
        ArrayList<Document> items=(ArrayList<Document>) d.get("items");
        ArrayList<OrderItemDTO> itemdtos=new ArrayList<>();
        for(Document item: items){
            String sku=(String) item.get("sku");
            String name=(String) item.get("name");
            int quantity=(int) item.get("quantity");
            double totalPrice=(double) item.get("totalPrice");
            itemdtos.add(new OrderItemDTO(sku, name, quantity, totalPrice));
        }
        ArrayList<Document> installments=(ArrayList<Document>) d.get("installments");
        ArrayList<InstallmentDTO> installmentdtos=new ArrayList<>();
        for(Document installment: installments){
            int number=(int) installment.get("number");
            double date=(double) installment.get("amount");
            LocalDate installmentDate=LocalDate.parse(((Date)installment.get("date")).toInstant().atZone(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            boolean paid=(boolean) installment.get("paid");
            boolean isInvoiceIssued=(boolean) installment.get("isInvoiceIssued");
            installmentdtos.add(new InstallmentDTO(number, date, installmentDate, isInvoiceIssued,paid));
        }
        String location=(String) d.get("location");
        String billingAddress=(String) d.get("billingAddress");
        String customerId=(String) d.get("customerId");
        LocalDate requestedFrom=LocalDate.parse(((Date)d.get("requestedFrom")).toInstant().atZone(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        LocalDate requestedTo=LocalDate.parse(((Date)d.get("requestedTo")).toInstant().atZone(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        LocalDate orderDate=LocalDate.parse(((Date)d.get("orderDate")).toInstant().atZone(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        String status=(String) d.get("status");
        double totalPriceForItems=(double) d.get("totalPriceForItems");
        double deliveryCharges=(double) d.get("deliveryCharges");
        double discount=(double) d.get("discount");
        double netTotal=(double) d.get("netTotal");
        boolean deliver=(boolean) d.get("deliver");
        boolean pickup=(boolean) d.get("pickup");
        ObjectId leaseAgreementFileId=new ObjectId((String)d.get("leaseAgreementFileId"));
        Bson q1 = Filters.eq("_id", leaseAgreementFileId);
        GridFSFile d1= gridFSBucket.find(q1).first();
        String leaseAgreementFileName=null;
        try{
            leaseAgreementFileName=d1.getFilename();
        }catch(NullPointerException e){
            System.out.println("Null lease agreement file");   
        }
        File directory = new File(System.getProperty("user.home") + "/Desktop/OrderFiles");

        if (! directory.exists()){
            directory.mkdir();
        }else{
            try {
                FileUtils.cleanDirectory(directory);
            } catch (IOException ex) {
                Logger.getLogger(OrderDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        File leaseAgreement=null;
        if(leaseAgreementFileName!=null)
        try (FileOutputStream streamToDownloadTo = new FileOutputStream(System.getProperty("user.home") + "/Desktop/OrderFiles/" + leaseAgreementFileName)) {
            gridFSBucket.downloadToStream(leaseAgreementFileId, streamToDownloadTo);
            streamToDownloadTo.flush();
            leaseAgreement=new File(System.getProperty("user.home") + "/Desktop/OrderFiles/" + leaseAgreementFileName);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new OrderDTO(customerId, id, requestedFrom, requestedTo, totalPriceForItems, deliveryCharges, discount, netTotal, itemdtos, installmentdtos, deliver, pickup, leaseAgreement, orderDate, status,location,billingAddress);
    }

    @Override
    public ArrayList getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getNewOrderId() {
        String generatedId="O";
        while(true){
            String characters = "0123456789";
            generatedId += RandomStringUtils.random( 9, characters );
            if(collection.find(eq("_id",generatedId)).first()==null){
                break;
            }
            generatedId="O";
        }
        return generatedId;
    }

    @Override
    public void changeCustomerId(String before, String after) {
        BasicDBObject search=new BasicDBObject();
        search.append("customerId", before);
        BasicDBObject update=new BasicDBObject("$set",new BasicDBObject()
        .append("customerId", after));
        collection.updateMany(search, update);
    }

    @Override
    public ArrayList<OrderDTO> search(String orderId, int placedDate, LocalDate orderPlacedFromDate, LocalDate orderPlacedToDate, int itemRequested, LocalDate itemRequestedFromDate, LocalDate itemRequestedToDate, int statusIndex, String nicOrEmail) {

        ArrayList<OrderDTO> orders=new ArrayList<>();
        FindIterable<Document> find=null;
        if(!orderId.equals("")){
            find=collection.find(eq("_id",orderId));       
        }else if(placedDate==0 && itemRequested==0){
            if(statusIndex==0){
                if(nicOrEmail.equals("")){
                    find = collection.find(eq("status","open"));
                }else{
                    find = collection.find(Filters.and(eq("status","open"),eq("customerId",nicOrEmail)));
                }
            }
            if(statusIndex==1){
                if(nicOrEmail.equals("")){
                    find = collection.find(eq("status","closed"));
                }else{
                    
                    find = collection.find(Filters.and(eq("status","closed"),eq("customerId",nicOrEmail)));
                }
            }
            if(statusIndex==2){
                if(nicOrEmail.equals("")){
                    find = collection.find();
                }else{
                    find = collection.find(eq("customerId",nicOrEmail));
                }
            }
        }else if(placedDate == 0 && itemRequested == 1){
            if(statusIndex==0){
                if(nicOrEmail.equals("")){
                    BasicDBObject query = new BasicDBObject();
                    query.put("status","open");
                    query.put("requestedFrom", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    find=collection.find(query).sort(new BasicDBObject("requestedFrom", -1));
                }else{
                   
                        BasicDBObject query = new BasicDBObject();
                        query.put("status","open");
                        query.put("customerId",nicOrEmail);
                        query.put("requestedFrom", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                        find=collection.find(query).sort(new BasicDBObject("requestedFrom", -1));
                    
                }
            }
            if(statusIndex==1){
                if(nicOrEmail.equals("")){
                    BasicDBObject query = new BasicDBObject();
                    query.put("status","closed");
                    query.put("requestedFrom", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    find=collection.find(query).sort(new BasicDBObject("requestedFrom", -1));
                }else{
                    BasicDBObject query = new BasicDBObject();
                    query.put("status","closed");
                    query.put("customerId",nicOrEmail);
                    query.put("requestedFrom", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    find=collection.find(query).sort(new BasicDBObject("requestedFrom", -1));
                }
            }
            if(statusIndex==2){
                if(nicOrEmail.equals("")){
                    BasicDBObject query = new BasicDBObject();
                    query.put("requestedFrom", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    find=collection.find(query).sort(new BasicDBObject("requestedFrom", -1));
                }else{
                    BasicDBObject query = new BasicDBObject();
                    query.put("customerId",nicOrEmail);
                    query.put("requestedFrom", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    find=collection.find(query).sort(new BasicDBObject("requestedFrom", -1));
                }
            }
        }else if(placedDate == 1 && itemRequested == 0){
            if(statusIndex==0){
                if(nicOrEmail.equals("")){
                    BasicDBObject query = new BasicDBObject();
                    query.put("status","open");
                    query.put("orderDate", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    find=collection.find(query).sort(new BasicDBObject("orderDate", -1));
                }else{
                   
                        BasicDBObject query = new BasicDBObject();
                        query.put("status","open");
                        query.put("customerId",nicOrEmail);
                        query.put("orderDate", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                        find=collection.find(query).sort(new BasicDBObject("orderDate", -1));
                    
                }
            }
            if(statusIndex==1){
                if(nicOrEmail.equals("")){
                    BasicDBObject query = new BasicDBObject();
                    query.put("status","closed");
                    query.put("orderDate", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    find=collection.find(query).sort(new BasicDBObject("orderDate", -1));
                }else{
                    BasicDBObject query = new BasicDBObject();
                    query.put("status","closed");
                    query.put("customerId",nicOrEmail);
                    query.put("orderDate", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    find=collection.find(query).sort(new BasicDBObject("orderDate", -1));
                }
            }
            if(statusIndex==2){
                if(nicOrEmail.equals("")){
                    BasicDBObject query = new BasicDBObject();
                    query.put("orderDate", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    find=collection.find(query).sort(new BasicDBObject("orderDate", -1));
                }else{
                    BasicDBObject query = new BasicDBObject();
                    query.put("customerId",nicOrEmail);
                    query.put("orderDate", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    find=collection.find(query).sort(new BasicDBObject("orderDate", -1));
                }
            }
        }else if(placedDate == 1 && itemRequested == 1){
            if(statusIndex==0){
                if(nicOrEmail.equals("")){
                    BasicDBObject query = new BasicDBObject();
                    query.put("status","open");
                    query.put("orderDate", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    query.put("requestedFrom", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    find=collection.find(query).sort(new BasicDBObject("orderDate", -1));
                }else{
                   
                        BasicDBObject query = new BasicDBObject();
                        query.put("status","open");
                        query.put("customerId",nicOrEmail);
                        query.put("orderDate", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                        query.put("requestedFrom", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                        find=collection.find(query).sort(new BasicDBObject("orderDate", -1));
                    
                }
            }
            if(statusIndex==1){
                if(nicOrEmail.equals("")){
                    BasicDBObject query = new BasicDBObject();
                    query.put("status","closed");
                    query.put("orderDate", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    query.put("requestedFrom", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    find=collection.find(query).sort(new BasicDBObject("orderDate", -1));
                }else{
                    BasicDBObject query = new BasicDBObject();
                    query.put("status","closed");
                    query.put("customerId",nicOrEmail);
                    query.put("orderDate", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    query.put("requestedFrom", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    find=collection.find(query).sort(new BasicDBObject("orderDate", -1));
                }
            }
            if(statusIndex==2){
                if(nicOrEmail.equals("")){
                    BasicDBObject query = new BasicDBObject();
                    query.put("orderDate", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    query.put("requestedFrom", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    find=collection.find(query).sort(new BasicDBObject("orderDate", -1));
                }else{
                    BasicDBObject query = new BasicDBObject();
                    query.put("customerId",nicOrEmail);
                    query.put("orderDate", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    query.put("requestedFrom", BasicDBObjectBuilder.start("$gte", orderPlacedFromDate).add("$lte", orderPlacedToDate).get());
                    find=collection.find(query).sort(new BasicDBObject("orderDate", -1));
                }
            }
        }
        MongoCursor<Document> cursor = find.cursor();
        while(cursor.hasNext()){
            Document d = cursor.next();
            
            String id=(String)d.get("_id");
            String customerId=(String) d.get("customerId");
            LocalDate orderDate=LocalDate.parse(((Date)d.get("orderDate")).toInstant().atZone(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            String status=(String) d.get("status");
            
            orders.add(new OrderDTO(customerId, id, null, null, 0, 0, 0, 0, null, null, false, false, null, orderDate, status,null,null));
        }
        return orders;
    }

    @Override
    public boolean setInstallmentPayment(String orderId, int installmentNo, boolean status) {
        Bson filter = and(eq("_id", orderId), eq("installments.number", installmentNo));
        Bson update = set("installments.$.paid", status);
        UpdateResult result = collection.updateOne(filter, update);
        return result.getModifiedCount()>0;
    }

    @Override
    public ArrayList<OrderTransportDTO> getTransportOrders(LocalDate from, LocalDate to) {
        ArrayList<OrderTransportDTO> orderTransportDtos=new ArrayList<>();
        BasicDBObject query = new BasicDBObject();
        query.put("deliver",true);
        query.put("requestedFrom", BasicDBObjectBuilder.start("$gte", from).add("$lte", to).get());
        FindIterable<Document> sort = collection.find(query);
        MongoCursor<Document> cursor = sort.cursor();
        while(cursor.hasNext()){
            Document d= cursor.next();
            String id= (String)d.get("_id");
            String deliverOrPick="deliver";
            boolean status=(boolean) d.get("delivered");
            LocalDate date=LocalDate.parse(((Date)d.get("requestedFrom")).toInstant().atZone(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            String location= (String)d.get("location");
            String deliverDetails= (String)d.get("deliverDetails");
            orderTransportDtos.add(new OrderTransportDTO(id, deliverOrPick, status, date, location,deliverDetails));
        }
        BasicDBObject query2 = new BasicDBObject();
        query2.put("pickup",true);
        query2.put("requestedTo", BasicDBObjectBuilder.start("$gte", from).add("$lte", to).get());
        FindIterable<Document> sort2 = collection.find(query2);
        MongoCursor<Document> cursor2 = sort2.cursor();
        while(cursor2.hasNext()){
            Document d= cursor2.next();
            String id= (String)d.get("_id");
            String deliverOrPick="pickup";
            boolean status=(boolean) d.get("pickedUp");
            LocalDate date=LocalDate.parse(((Date)d.get("requestedTo")).toInstant().atZone(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            String location= (String)d.get("location");
            String pickupDetails= (String)d.get("pickupDetails");
            orderTransportDtos.add(new OrderTransportDTO(id, deliverOrPick, status, date, location,pickupDetails));
        }
        return orderTransportDtos;
    }

    @Override
    public boolean setTransportDetails(OrderTransportDTO transport) {
        if(transport.getDeliverOrPick().equals("deliver")){
            BasicDBObject search=new BasicDBObject();
            search.append("_id", transport.getId());
            BasicDBObject update=new BasicDBObject("$set",new BasicDBObject()
            .append("delivered", transport.isStatus())
            .append("deliverDetails", transport.getDetails()));
            collection.updateOne(search, update);
            return true;
        }else{
            BasicDBObject search=new BasicDBObject();
            search.append("_id", transport.getId());
            BasicDBObject update=new BasicDBObject("$set",new BasicDBObject()
            .append("pickedUp", transport.isStatus())
            .append("pickupDetails", transport.getDetails()));
            collection.updateOne(search, update);
            return true;
        }    
    }

    @Override
    public boolean setOrderStatus(String id, String status) {
        BasicDBObject search=new BasicDBObject();
        search.append("_id", id);
        BasicDBObject update=new BasicDBObject("$set",new BasicDBObject()
        .append("status", status));
        collection.updateMany(search, update);
        return true;
    }

    @Override
    public boolean setInstallmentInvoice(String orderId, int installmentNo, boolean invoiceIssued) {
        Bson filter = and(eq("_id", orderId), eq("installments.number", installmentNo));
        Bson update = set("installments.$.isInvoiceIssued", invoiceIssued);
        UpdateResult result = collection.updateOne(filter, update);
        return result.getModifiedCount()>0;
    }

    @Override
    public ArrayList<InstallmentDTO> getPaymentsForTheDate(LocalDate date) {
        Bson filter=Filters.and(eq("installments.date",date),eq("installments.isInvoiceIssued",false));     
        FindIterable<Document> sort = collection.find(filter).sort(new BasicDBObject("installments.date", -1));
        MongoCursor<Document> cursor = sort.cursor();
        ArrayList<InstallmentDTO> arr=new ArrayList<>();
        while(cursor.hasNext()){
            Document d= cursor.next();
            String id= (String)d.get("_id");
            ArrayList<Document> installments=(ArrayList<Document>) d.get("installments");
            for(Document installment: installments){
                Date installmentDate = (Date)installment.get("date");
                LocalDate parse = LocalDate.parse(installmentDate.toInstant().atZone(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                if(!(boolean)installment.get("paid") && date.isEqual(parse)){
                    int number=(int)installment.get("number");
                    boolean sent=(boolean) installment.get("isInvoiceIssued");
                    double amount= (double) installment.get("amount");
                    InstallmentDTO dto=new InstallmentDTO();
                    dto.setOrderId(id);
                    dto.setAmount(amount);
                    dto.setInvoiceIssued(sent);
                    dto.setNumber(number);
                    dto.setDate(parse);
                    arr.add(dto);
                }
            }
        }
        return arr;
    }

    @Override
    public ArrayList<InstallmentDTO> getOverduePayments(LocalDate date) {
        Bson filter=Filters.and(eq("installments.paid",false),lt("installments.date",date));     
        FindIterable<Document> sort = collection.find(filter).sort(new BasicDBObject("installments.date", -1));
        MongoCursor<Document> cursor = sort.cursor();
        ArrayList<InstallmentDTO> arr=new ArrayList<>();
        while(cursor.hasNext()){
            Document d= cursor.next();
            String id= (String)d.get("_id");
            ArrayList<Document> installments=(ArrayList<Document>) d.get("installments");
            for(Document installment: installments){
                Date installmentDate = (Date)installment.get("date");
                LocalDate parse = LocalDate.parse(installmentDate.toInstant().atZone(ZoneId.of("GMT")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                if(!(boolean)installment.get("paid")){
                    int number=(int)installment.get("number");
                    boolean sent=(boolean) installment.get("isInvoiceIssued");
                    double amount= (double) installment.get("amount");
                    InstallmentDTO dto=new InstallmentDTO();
                    dto.setOrderId(id);
                    dto.setAmount(amount);
                    dto.setInvoiceIssued(sent);
                    dto.setNumber(number);
                    dto.setDate(parse);
                    arr.add(dto);
                }
            }
        }
        return arr;
    }
    
    @Override
    public boolean sendReceiptAsMail(File file,String to){
        try{
            MailConnection mail=MailConnection.getConnection();
            mail.setSubject("Buildco Receipt");
            mail.setBody("Dear customer, Your payment have been received");
            mail.setFile(file);
            mail.sendMail(to, "buildcoserviceslk@gmail.com", "udffgvqezewcxeif");
            return true;
        }catch(Exception e){
                        e.printStackTrace();
        }
        return false;
    
    }
    @Override
    public boolean sendInvoiceAsMail(File file,String to){
        try{
            MailConnection mail=MailConnection.getConnection();
            mail.setSubject("Buildco Invoice");
            mail.setBody("Dear customer, Please check the attachment for invoice");
            mail.setFile(file);
            mail.sendMail(to, "buildcoserviceslk@gmail.com", "udffgvqezewcxeif");
            return true;
        }catch(Exception e){
                        e.printStackTrace();
        }
        return false;
    
    }
    
}
