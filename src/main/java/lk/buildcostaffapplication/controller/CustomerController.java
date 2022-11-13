/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.controller;

import java.util.ArrayList;
import lk.buildcostaffapplication.dao.DAOFactory;
import lk.buildcostaffapplication.dao.custom.CustomerDAO;
import lk.buildcostaffapplication.dto.CustomerDTO;

/**
 *
 * @author Binath Perera
 */
public class CustomerController {
    CustomerDAO cdao;
    public CustomerController(){
        cdao= (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtype.CUSTOMER);
    }
    public boolean deleteCustomer(String id){
        return cdao.delete(id);
    }
    public ArrayList<CustomerDTO> getAll(){
        return cdao.getSomeDetailsOfAllCustomers();
    }
    public CustomerDTO search(String id){
        return (CustomerDTO)cdao.search(id);
    }
    public boolean setGeneralDetails(CustomerDTO customer){
        return cdao.setGeneralDetails(customer);
    }
    public boolean setDetailsWithoutPassword(CustomerDTO customer){
        return cdao.setDetailsWithoutPassword(customer);
    }
}
