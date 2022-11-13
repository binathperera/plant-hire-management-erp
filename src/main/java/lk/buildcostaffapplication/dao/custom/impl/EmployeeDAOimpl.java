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
import com.mongodb.client.result.DeleteResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.buildcostaffapplication.dao.custom.EmployeeDAO;
import lk.buildcostaffapplication.dto.EmployeeDTO;
import lk.buildcostaffapplication.dto.SuperDTO;
import lk.buildcostaffapplication.connection.DBconnection;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.apache.commons.io.FileUtils;
/**
 *
 * @author Binath Perera
 */
public class EmployeeDAOimpl implements EmployeeDAO{
    private static DBconnection connection;
    private static MongoClient mc;
    MongoDatabase database ;
    MongoCollection<Document> collection;
    GridFSBucket gridFSBucket;
    public EmployeeDAOimpl(){
        connection=DBconnection.getConnection();
        mc=connection.getMongoClient();
        database = mc.getDatabase("plantHireManagement");
        collection = database.getCollection("staffMember");
        gridFSBucket= GridFSBuckets.create(database,"employee");
    }
    @Override
    public boolean add(SuperDTO ob) {
        EmployeeDTO nedto= (EmployeeDTO)ob; 
        Document newEmployee=new Document("_id",nedto.getEmployeeID());
        newEmployee.append("nic", nedto.getNic());
        Document name=new Document("first",nedto.getFirstName()).append("middle", nedto.getMiddleName()).append("last",nedto.getLastName());
        newEmployee.append("name", name);
        newEmployee.append("birthday", nedto.getBirthday());
        newEmployee.append("nationality", nedto.getNationality());
        newEmployee.append("bloodGroup", nedto.getBloodGroup());
        newEmployee.append("gender", nedto.getGender());
        newEmployee.append("married", nedto.isMaritalStatus());
        Document contact=new Document("tel",nedto.getContactNumber());
        contact.append("email", nedto.getEmail());
        contact.append("correspondanceAddress", nedto.getAddressOfCorrespondance());
        newEmployee.append("contact", contact);
        Document emContact=new Document("tel",nedto.getEmergencyContactNumber());
        emContact.append("name", nedto.getEmergencyContactPerson());
        emContact.append("relationship", nedto.getRelationshipWithEmergencyContactPerson());
        newEmployee.append("emContact", emContact);
        newEmployee.append("address", nedto.getPermanantAddress());
        newEmployee.append("user", false); 
        newEmployee.append("employed",nedto.isEmploymentStatus()); 
        
        Document employmentDetails=new Document("department",nedto.getDepartment());
        employmentDetails.append("joined",nedto.getDateOfJoining());
        employmentDetails.append("designation",nedto.getDesignation());
        employmentDetails.append("joined",nedto.getDateOfJoining());
        employmentDetails.append("managerID",nedto.getManagerID());
        employmentDetails.append("salary",nedto.getSalary());
        employmentDetails.append("otrate",nedto.getOtrate());
        employmentDetails.append("hpd",nedto.getHpd());
        employmentDetails.append("workweek",nedto.getWorkWeek());
        employmentDetails.append("clockedIn",false);
        employmentDetails.append("left","");
         
        //Qualifications
        try (InputStream streamToUploadFrom = new FileInputStream(nedto.getQualifications()) ) {
            GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1048576);//1MB chunck size
            ObjectId fileId = gridFSBucket.uploadFromStream(nedto.getQualifications().getName(), streamToUploadFrom, options);
            System.out.println("The file id of the uploaded file is: " + fileId.toHexString());
            newEmployee.append("qualificationsFileId", fileId.toHexString());
        }catch(Exception e){
            newEmployee.append("qualificationsFileId", "");
            e.printStackTrace();
        }
        //Agreement
        try (InputStream streamToUploadFrom = new FileInputStream(nedto.getEmploymentAgreement()) ) {
            GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1048576);//1MB chunck size
            ObjectId fileId = gridFSBucket.uploadFromStream(nedto.getEmploymentAgreement().getName(), streamToUploadFrom, options);
            System.out.println("The file id of the uploaded file is: " + fileId.toHexString());
            newEmployee.append("agreementFileId", fileId.toHexString());
        }catch(Exception e){
            newEmployee.append("agreementFileId", "");
            e.printStackTrace();
        }
        newEmployee.append("employment", employmentDetails);
        Document bank=new Document("number",nedto.getBankAccountNumber());
        bank.append("name",nedto.getBankName());
        bank.append("branch",nedto.getBankBranch());
        newEmployee.append("bank", bank);
       
        collection.insertOne(newEmployee);
        return true;
    }

    @Override
    public boolean delete(String id) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", id);
        DeleteResult result=null;
        try{
            Document d= collection.find(eq("_id",id)).first();
            String qualificationsFileId=(String)d.get("qualificationsFileId");
            String agreementFileId=(String)d.get("agreementFileId");
            if(!qualificationsFileId.equals(""))gridFSBucket.delete(new ObjectId(qualificationsFileId));
            if(!agreementFileId.equals(""))gridFSBucket.delete(new ObjectId(agreementFileId));
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
    public ArrayList<SuperDTO> getAll(){
        FindIterable<Document> fi = collection.find();
        MongoCursor<Document> cursor = fi.iterator();
        ArrayList<SuperDTO> list=new ArrayList<SuperDTO>();
        int index=0;
        try {
            while(cursor.hasNext()) {               
                Document d=cursor.next();
                String employeeID=(String)d.get("_id");
                if(employeeID.equals("S001")){continue;}
                String nic=(String)d.get("nic");
                
                String birthday=(String)d.get("birthday");
                String nationality=(String)d.get("nationality");
                
                Document name=(Document)d.get("name");
                String firstName=(String)name.get("first");
                String middleName=(String)name.get("middle");
                String lastName=(String)name.get("last");
                
                String bloodGroup=(String)d.get("bloodGroup"); 
                String Gender=(String)d.get("gender");
                String permanantAddress=(String)d.get("address");
                
                Document contact=(Document)d.get("contact");
                String addressOfCorrespondance=(String)contact.get("correspondanceAddress"); 
                String contactNumber=(String)contact.get("tel");
                String email=(String)contact.get("email");
                
                Document emcontact=(Document)d.get("contact");
                String emergencyContactNumber=(String)emcontact.get("tel");
                String emergencyContactPerson=(String)emcontact.get("name");
                String relationshipWithEmergencyContactPerson=(String)emcontact.get("relationship");
                
                Document employment=(Document)d.get("employment");
                String department=(String)employment.get("department"); 
                String designation=(String)employment.get("designation"); 
                 
                String managerID=(String)employment.get("managerID"); 
                String dateOfJoining=(String)employment.get("joined"); 
                String dateOfLeaving=(String)employment.get("left"); 
                double otrate =(double)employment.get("otrate");
                double hpd=(double)employment.get("hpd");  
                ArrayList<String> workWeek=(ArrayList<String>)employment.get("workweek"); 
                double salary=(double)employment.get("salary"); 
                boolean clockedIn=(boolean)employment.get("clockedIn");
                
                Document bank=(Document)d.get("bank");
                String bankAccountNumber=(String)bank.get("number");
                String bankName=(String)bank.get("name"); 
                String bankBranch=(String)bank.get("branch");
                
                boolean maritalStatus=(boolean)d.get("married");
                boolean employmentStatus=(boolean)d.get("employed");
                
                ObjectId qualificationsFileId = new ObjectId((String)d.get("qualificationsFileId"));
                ObjectId employmentAgreementFileId = new ObjectId((String)d.get("agreementFileId"));
                
                
                Bson q1 = Filters.eq("_id", qualificationsFileId);
                GridFSFile d1= gridFSBucket.find(q1).first();
                String qualificationsFileName=d1.getFilename();
                
                Bson q2 = Filters.eq("_id", employmentAgreementFileId);
                GridFSFile d2= gridFSBucket.find(q2).first();
                String agreementFileName=d2.getFilename();
                File directory = new File(System.getProperty("user.home") + "/Desktop/EmployeeFiles");
                
                if (! directory.exists()){
                    directory.mkdir();
                    // If you require it to make the entire directory path including parents,
                    // use directory.mkdirs(); here instead.
                }else{
                    FileUtils.cleanDirectory(directory);
                }

                File qualifications = new File(System.getProperty("user.home") + "/Desktop/EmployeeFiles/" + qualificationsFileName);
                
                if(qualificationsFileName!=null)
                try (FileOutputStream streamToDownloadTo = new FileOutputStream(qualifications)) {
                    gridFSBucket.downloadToStream(qualificationsFileId, streamToDownloadTo);
                    streamToDownloadTo.flush();
                }catch(Exception e){
                    e.printStackTrace();
                }
                
                File employmentAgreement= new File(System.getProperty("user.home") + "/Desktop/EmployeeFiles/" + agreementFileName);
                if(agreementFileName!=null)
                try (FileOutputStream streamToDownloadTo = new FileOutputStream(employmentAgreement)) {
                    gridFSBucket.downloadToStream(employmentAgreementFileId, streamToDownloadTo);
                    streamToDownloadTo.flush();
                }catch(Exception e){
                    e.printStackTrace();
                }
                
                SuperDTO employee=new EmployeeDTO(nic,birthday,nationality,
                    firstName,middleName, lastName,
                    bloodGroup, Gender,  permanantAddress,
                    addressOfCorrespondance,  contactNumber,  email,
                    emergencyContactNumber, emergencyContactPerson,
                    relationshipWithEmergencyContactPerson,  department, 
                    designation, employeeID,  managerID,
                    dateOfJoining,  bankAccountNumber, bankName, 
                    bankBranch,  maritalStatus,  employmentStatus,
                    salary,  otrate ,  hpd, 
                    workWeek,qualifications, employmentAgreement,
                    clockedIn, dateOfLeaving);
                list.add(employee);
            }
        } catch (IOException ex) {
            Logger.getLogger(EmployeeDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cursor.close();
        }
        return list;
    }

    @Override
    public String[] getAllEmployeeIDs(String user) {
        String[] ids=new String[(int)collection.countDocuments()];
        FindIterable<Document> fi = collection.find();
        MongoCursor<Document> cursor = fi.iterator();
        int index=0;
        try {
            while(cursor.hasNext()) {               
                Document d=cursor.next();
                String id = (String)d.get("_id");
                ids[index++]=id;
            }
        } finally {
            cursor.close();
        }
        return ids;
    }

    @Override
    public SuperDTO search(String ID)  {
        Document d= (Document)collection.find(eq("_id",ID)).first();
        EmployeeDTO employee=null;
        //try {
            String id = (String)d.get("_id");
            String m=id;
            Document user= (Document)collection.find(eq("username",DBconnection.getUsername())).first();
            ArrayList<String> access= (ArrayList<String>)user.get("access");
            /*if(!access.contains("allEmployees"))
            while(true){
                m=getIdOfManager(m);
                if(m==null)return null;
                if(m.equals(ID))break;
            }*/
            String employeeID=(String)d.get("_id");

            String nic=(String)d.get("nic");

            String birthday=(String)d.get("birthday");
            String nationality=(String)d.get("nationality");

            Document name=(Document)d.get("name");
            String firstName=(String)name.get("first");
            String middleName=(String)name.get("middle");
            String lastName=(String)name.get("last");

            String bloodGroup=(String)d.get("bloodGroup"); 
            String Gender=(String)d.get("gender");
            String permanantAddress=(String)d.get("address");

            Document contact=(Document)d.get("contact");
            String addressOfCorrespondance=(String)contact.get("correspondanceAddress"); 
            String contactNumber=(String)contact.get("tel");
            String email=(String)contact.get("email");

            Document emcontact=(Document)d.get("emContact");
            String emergencyContactNumber=(String)emcontact.get("tel");
            String emergencyContactPerson=(String)emcontact.get("name");
            String relationshipWithEmergencyContactPerson=(String)emcontact.get("relationship");

            Document employment=(Document)d.get("employment");
            String department=(String)employment.get("department"); 
            String designation=(String)employment.get("designation"); 

            String managerID=(String)employment.get("managerID"); 


            String dateOfJoining=(String)employment.get("joined"); 
            String dateOfLeaving=(String)employment.get("left"); 
            double otrate =(double)employment.get("otrate");
            double hpd=(double)employment.get("hpd");  
            ArrayList<String> workWeek=(ArrayList<String>)employment.get("workweek"); 
            double salary=(double)employment.get("salary"); 
            boolean clockedIn=(boolean)employment.get("clockedIn");

            Document bank=(Document)d.get("bank");
            String bankAccountNumber=(String)bank.get("number");
            String bankName=(String)bank.get("name"); 
            String bankBranch=(String)bank.get("branch");

            boolean maritalStatus=(boolean)d.get("married");
            boolean employmentStatus=(boolean)d.get("employed");


            ObjectId qualificationsFileId = new ObjectId();
            ObjectId employmentAgreementFileId = new ObjectId();
            String qualificationsFileIdString=(String)d.get("qualificationsFileId");
            String agreementFileIdString=(String)d.get("agreementFileId");
            if(!qualificationsFileIdString.equals(""))qualificationsFileId=new ObjectId(qualificationsFileIdString);
            if(!agreementFileIdString.equals(""))employmentAgreementFileId=new ObjectId(agreementFileIdString);
            Bson q1 = Filters.eq("_id", qualificationsFileId);
            GridFSFile d1= gridFSBucket.find(q1).first();
            Bson q2 = Filters.eq("_id", employmentAgreementFileId);
            GridFSFile d2= gridFSBucket.find(q2).first();
            String qualificationsFileName= null;
            String agreementFileName= null;      
            try{
                qualificationsFileName=d1.getFilename();
            }catch(NullPointerException e){
                System.out.println("Null qualification file");   
            }
            try{
                agreementFileName=d2.getFilename();
            }catch(NullPointerException e){
                 System.out.println("Null agreement file");      
            }

            File directory = new File(System.getProperty("user.home") + "/Desktop/EmployeeFiles");
                
            if (! directory.exists()){
                directory.mkdir();
            }else{
                try {
                    FileUtils.cleanDirectory(directory);
                } catch (IOException ex) {
                    Logger.getLogger(EmployeeDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            
            File qualifications=null;
            File employmentAgreement=null;

            if(qualificationsFileName!=null)
            try (FileOutputStream streamToDownloadTo = new FileOutputStream(System.getProperty("user.home") + "/Desktop/EmployeeFiles/" + qualificationsFileName)) {
                gridFSBucket.downloadToStream(qualificationsFileId, streamToDownloadTo);
                streamToDownloadTo.flush();
                qualifications=new File(System.getProperty("user.home") + "/Desktop/EmployeeFiles/" + qualificationsFileName);
            }catch(Exception e){
                e.printStackTrace();
            }

            if(agreementFileName!=null)
            try (FileOutputStream streamToDownloadTo = new FileOutputStream(System.getProperty("user.home") + "/Desktop/EmployeeFiles/" + agreementFileName)) {
                gridFSBucket.downloadToStream(employmentAgreementFileId, streamToDownloadTo);
                streamToDownloadTo.flush();
                employmentAgreement=new File(System.getProperty("user.home") + "/Desktop/EmployeeFiles/" + agreementFileName);
            }catch(Exception e){
                e.printStackTrace();
            }
            employee=new EmployeeDTO(nic,birthday,nationality,
            firstName,middleName, lastName,
            bloodGroup, Gender,  permanantAddress,
            addressOfCorrespondance,  contactNumber,  email,
            emergencyContactNumber, emergencyContactPerson,
            relationshipWithEmergencyContactPerson,  department, 
            designation, employeeID,  managerID,
            dateOfJoining,  bankAccountNumber, bankName, 
            bankBranch,  maritalStatus,  employmentStatus,
            salary,  otrate ,  hpd, 
            workWeek,qualifications, employmentAgreement,
            clockedIn, dateOfLeaving);

            
        //} catch (IOException ex) {
        //    Logger.getLogger(EmployeeDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        //}
        return employee;
    }
    ArrayList<SuperDTO> idsAndNames=new ArrayList<>();
    public ArrayList<SuperDTO> getEmployeeIdsAndNamesUnderManager(String ID){
        BasicDBObject query=new BasicDBObject();
        query.put("employment.managerID",ID);
        FindIterable<Document> fi = collection.find(query);
        MongoCursor<Document> cursor = fi.iterator();

        try {
            while(cursor.hasNext()) {               
                Document d=cursor.next();
                
                String id = (String)d.get("_id");
                System.out.println(id);
                Document name= (Document)d.get("name");
                String first = (String)name.get("first");
                String middle = (String)name.get("middle");
                String last = (String)name.get("last");
                idsAndNames.add(new EmployeeDTO(id,first,middle,last));
                Document employmentDetails=(Document)d.get("employment");
                String managerID=(String)employmentDetails.get("managerID");
                if(managerID.equals(""))return null;
                getEmployeeIdsAndNamesUnderManager(id);//Recursion
            }
        } finally {
            cursor.close();
        }
        return idsAndNames;
    }
    
    public String getIdOfManager(String Id){
        try{
            Id=(String)collection.find(eq("employment.managerID",Id)).first().get("_id");
        }catch(NullPointerException e){
            return null;
        }
        return Id;
    }
    public ArrayList<SuperDTO> getAllEmployeeIdsAndNames(String ID){
        
        FindIterable<Document> fi = collection.find();
        MongoCursor<Document> cursor = fi.iterator();

        try {
            while(cursor.hasNext()) {               
                Document d=cursor.next();
                
                String id = (String)d.get("_id");
                Document name= (Document)d.get("name");
                String first = (String)name.get("first");
                String middle = (String)name.get("middle");
                String last = (String)name.get("last");
                idsAndNames.add(new EmployeeDTO(id,first,middle,last));
            }
        } finally {
            cursor.close();
        }
        return idsAndNames;
    }
    @Override
    public  ArrayList<SuperDTO> getEmployeeIdsAndNames(String user) {
        Document userDocument = collection.find(eq("_id",user)).first();
        ArrayList<String> access= (ArrayList<String>)userDocument.get("access");
        for(String module:access){
            if(module.equals("allEmployees")){
                ArrayList<SuperDTO> arr=getAllEmployeeIdsAndNames(user);
                idsAndNames=new ArrayList<>();
                return arr;
            }
        }
        ArrayList<SuperDTO> arr=getEmployeeIdsAndNamesUnderManager(user);
        idsAndNames=new ArrayList<>();
        return arr;
    }
    
    @Override
    public boolean updateEmployeeAttendance(String id,boolean stat) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", id);

        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",new BasicDBObject().append("employment.clockedIn",stat));
        collection.updateOne(searchQuery, updateQuery);
        return true;
    }

    @Override
    public boolean updateContactDetails(EmployeeDTO employee) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", employee.getEmployeeID());

        BasicDBObject updateQuery = new BasicDBObject();
        updateQuery.append("$set",new BasicDBObject()
                .append("contact.tel",employee.getContactNumber())
                .append("contact.email",employee.getEmail())
                .append("contact.correspondanceAddress",employee.getAddressOfCorrespondance())
                .append("emContact.tel",employee.getEmergencyContactNumber())
                .append("emContact.name",employee.getEmergencyContactPerson())
                .append("emContact.relationship",employee.getRelationshipWithEmergencyContactPerson())
        );
        collection.updateOne(searchQuery, updateQuery);
        return true;
    }

    @Override
    public boolean updatePersonalAndEmploymentDetails(EmployeeDTO nedto) {
        Document d= collection.find(eq("_id",nedto.getEmployeeID())).first();
        String qFileId=(String)d.get("qualificationsFileId");
        String aFileId=(String)d.get("agreementFileId");
        if(!qFileId.equals(""))gridFSBucket.delete(new ObjectId(qFileId));
        if(!aFileId.equals(""))gridFSBucket.delete(new ObjectId(aFileId));
        
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.append("_id", nedto.getEmployeeID());
        BasicDBObject updateQuery = new BasicDBObject();
        
        Document name=new Document("first",nedto.getFirstName()).append("middle", nedto.getMiddleName()).append("last",nedto.getLastName());
        Document employmentDetails=new Document("department",nedto.getDepartment());
        employmentDetails.append("joined",nedto.getDateOfJoining());
        employmentDetails.append("designation",nedto.getDesignation());
        employmentDetails.append("joined",nedto.getDateOfJoining());
        employmentDetails.append("managerID",nedto.getManagerID());
        employmentDetails.append("salary",nedto.getSalary());
        employmentDetails.append("otrate",nedto.getOtrate());
        employmentDetails.append("hpd",nedto.getHpd());
        employmentDetails.append("workweek",nedto.getWorkWeek());
        employmentDetails.append("clockedIn",false);
        employmentDetails.append("left","");
        Document bank=new Document("number",nedto.getBankAccountNumber());
        bank.append("name",nedto.getBankName());
        bank.append("branch",nedto.getBankBranch());
        
        //Qualifications
        ObjectId qualificationsFileId=null;
        try (InputStream streamToUploadFrom = new FileInputStream(nedto.getQualifications()) ) {
            GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1048576);//1MB chunck size
            qualificationsFileId = gridFSBucket.uploadFromStream(nedto.getQualifications().getName(), streamToUploadFrom, options);
            System.out.println("The file id of the uploaded file is: " + qualificationsFileId.toHexString());
        }catch(FileNotFoundException ex){
            System.out.println("File not found");
        }catch(IOException ex){
            System.out.println("IO exception");
        }catch(NullPointerException ex){
            System.out.println("Null pointer exception");
        }
        //Agreement
        ObjectId agreementFileId=null;
        try (InputStream streamToUploadFrom = new FileInputStream(nedto.getEmploymentAgreement()) ) {
            GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1048576);//1MB chunck size
            agreementFileId = gridFSBucket.uploadFromStream(nedto.getEmploymentAgreement().getName(), streamToUploadFrom, options);
            System.out.println("The file id of the uploaded file is: " + agreementFileId.toHexString());
        }catch(FileNotFoundException ex){
            System.out.println("File not found");
        }catch(IOException ex){
            System.out.println("IO exception");
        }catch(NullPointerException ex){
            System.out.println("Null pointer exception");
        }
        updateQuery.append("$set",new BasicDBObject()
        .append("nic", nedto.getNic())
        .append("name", name)
        .append("birthday", nedto.getBirthday())
        .append("nationality", nedto.getNationality())
        .append("bloodGroup", nedto.getBloodGroup())
        .append("gender", nedto.getGender())
        .append("married", nedto.isMaritalStatus())
        .append("address", nedto.getPermanantAddress())
        .append("employed",nedto.isEmploymentStatus())
        .append("employment",employmentDetails)
        .append("bank", bank)
        .append("qualificationsFileId", (qualificationsFileId!=null)?qualificationsFileId.toHexString():"")
        .append("agreementFileId", (agreementFileId!=null)?agreementFileId.toHexString():"")
        );
        return collection.updateOne(searchQuery, updateQuery).getMatchedCount()>0;
    }

    @Override
    public String getIDforUsername(String username) {
        Document first = collection.find(eq("username", username)).first();
        return (String)first.get("_id");
    }
   
}
