/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dto;

/**
 *
 * @author Binath Perera
 */
public class CustomerDTO implements SuperDTO{
    private String nic,name,companyName,email,password,billingAddress,destinationAddress,contact;

    
    public CustomerDTO(String nic, String name, String companyName, String email, String password, String billingAddress, String destinationAddress, String contact) {
        this.nic = nic;
        this.name = name;
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.billingAddress = billingAddress;
        this.destinationAddress = destinationAddress;
        this.contact = contact;
        
    }
    
   
    public CustomerDTO(){}
    public CustomerDTO(String nic, String name, String email){
        this.nic=nic;
        this.name=name;
        this.email=email;
    }
    
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
}
