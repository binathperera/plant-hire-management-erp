/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dao.custom;

import java.util.ArrayList;
import lk.buildcostaffapplication.dao.SuperDAO;
import lk.buildcostaffapplication.dto.EmployeeDTO;
import lk.buildcostaffapplication.dto.SuperDTO;

/**
 *
 * @author Binath Perera
 */
public interface EmployeeDAO extends SuperDAO<SuperDTO>{
    String[] getAllEmployeeIDs(String userId);
    ArrayList<SuperDTO> getEmployeeIdsAndNames(String userId);
    boolean updateEmployeeAttendance(String id,boolean stat);
    boolean updateContactDetails(EmployeeDTO employee);
    boolean updatePersonalAndEmploymentDetails(EmployeeDTO e);
    String getIDforUsername(String username);
}
