/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import lk.buildcostaffapplication.dao.DAOFactory;
import lk.buildcostaffapplication.dao.SuperDAO;
import lk.buildcostaffapplication.dao.custom.EmployeeDAO;
import lk.buildcostaffapplication.dao.custom.WorkRecordDAO;
import lk.buildcostaffapplication.dto.EmployeeDTO;
import lk.buildcostaffapplication.dto.SuperDTO;
import lk.buildcostaffapplication.dto.WorkRecordDTO;

/**
 *
 * @author Binath Perera
 */
public class EmployeeController {
    EmployeeDAO edao;
    WorkRecordDAO wdao;
    public EmployeeController(){
        edao = (EmployeeDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOtype.EMPLOYEE);
        wdao = (WorkRecordDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOtype.WORKRECORD);
    }
    public SuperDTO getWorkRecordByIDandDay(String ID,LocalDate date){
        return wdao.getWorkRecordByIDandDay(ID,date);
    }
    public boolean addWorkRecord(WorkRecordDTO ob){
        if(wdao.getWorkRecordByIDandDay(ob.getEmployeeID(), ob.getDay())==null)
            return wdao.add(ob);
        return wdao.update(ob);
    }
    public boolean deleteWorkRecord(String ID,LocalDate date){
        return wdao.deleteWorkRecord(ID,date);
    }
    public boolean updateEmployeeContactDetails(EmployeeDTO employee){
        return edao.updateContactDetails(employee);
    }
    public boolean updateEmployeeAttendance(String id,boolean stat){
        return edao.updateEmployeeAttendance(id,stat);
    }
    public String[] getAllEmployeeIDs(String id){
        return edao.getAllEmployeeIDs(id);
    }
    public ArrayList<SuperDTO> getEmployeeIdsAndNames(String userId){
        return edao.getEmployeeIdsAndNames(userId);
    }
    public ArrayList<SuperDTO> getAllEmployees(){
        return edao.getAll();
    }
    public boolean addNewEmployee(EmployeeDTO nedto){
        return edao.add(nedto);
    }
    public SuperDTO getEmployeeByID(String ID){
        return edao.search(ID);
    }
    public String getIDforUsername(String username){
        return edao.getIDforUsername(username);
    }
    public boolean updateEmployeePersonalAndEmploymentDetails(EmployeeDTO e){
        return edao.updatePersonalAndEmploymentDetails(e);
    }
    public boolean deleteEmployee(String id){
        boolean employeeDeleteResult= edao.delete(id);
        boolean employeeWorkRecordDeleteResult= wdao.delete(id);
        return employeeDeleteResult && employeeWorkRecordDeleteResult;
    }
}
