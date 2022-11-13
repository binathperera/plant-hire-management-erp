/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dto;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Binath Perera
 */
public class EnquiryDTO implements SuperDTO{
    private LocalDate date;
    private String id,customerName,companyName,telephone,locationOfHire,status,
            email,nic,message,response;
    private ArrayList<String> itemList;  
    public EnquiryDTO(){}
    public EnquiryDTO(String id,LocalDate date, String customerName,
            String companyName, String telephone, String locationOfHire,
            String status, String email, String nic, String message,
            String response, ArrayList<String> itemList) {
        this.id=id;
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
        this.itemList = itemList;
    }
    public EnquiryDTO(LocalDate date, String customerName, String companyName,
            String telephone, String locationOfHire, String status,
            String email, String nic, String message, String response,
            ArrayList<String> itemList) {        
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
        this.itemList = itemList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLocationOfHire() {
        return locationOfHire;
    }

    public void setLocationOfHire(String locationOfHire) {
        this.locationOfHire = locationOfHire;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public ArrayList<String> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<String> itemList) {
        this.itemList = itemList;
    }
    
}
