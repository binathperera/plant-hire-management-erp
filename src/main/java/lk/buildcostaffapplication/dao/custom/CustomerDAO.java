/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.buildcostaffapplication.dao.custom;

import java.util.ArrayList;
import lk.buildcostaffapplication.dao.SuperDAO;
import lk.buildcostaffapplication.dto.CustomerDTO;

/**
 *
 * @author Binath Perera
 */
public interface CustomerDAO extends SuperDAO{
    public ArrayList<CustomerDTO> getSomeDetailsOfAllCustomers();
    public boolean setGeneralDetails(CustomerDTO customer);
    public boolean setDetailsWithoutPassword(CustomerDTO customer);
}
