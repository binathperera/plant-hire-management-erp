/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dto;

import java.util.ArrayList;

/**
 *
 * @author Binath Perera
 */
public class UserDTO implements SuperDTO{
    private String id,username, password, email, firstName,middleName,lastName;

   
    private ArrayList<String> roles;
    public UserDTO(){}
    public UserDTO(String id,String username, String password, String email, String firstName, String middleName, String lastName, ArrayList<String> roles) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.email=email;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public UserDTO(String id,String username, String firstName, String middleName, String lastName) {
        this.id=id;
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id= id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String LastName) {
        this.lastName = LastName;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }
   
}
