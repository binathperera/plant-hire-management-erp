/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dao;


import lk.buildcostaffapplication.dao.custom.LoginDAO;
import lk.buildcostaffapplication.dao.custom.impl.CustomerDAOimpl;
import lk.buildcostaffapplication.dao.custom.impl.EmployeeDAOimpl;
import lk.buildcostaffapplication.dao.custom.impl.ItemDAOimpl;
import lk.buildcostaffapplication.dao.custom.impl.SettingsDAOimpl;
import lk.buildcostaffapplication.dao.custom.impl.UserDAOimpl;
import lk.buildcostaffapplication.dao.custom.impl.WorkRecordDAOimpl;
import lk.buildcostaffapplication.dao.custom.impl.EnquiryDAOimpl;
import lk.buildcostaffapplication.dao.custom.impl.LoginDAOimpl;
import lk.buildcostaffapplication.dao.custom.impl.OrderDAOimpl;

/**
 *
 * @author Binath Perera
 */
public class DAOFactory {
    public enum DAOtype{EMPLOYEE,WORKRECORD, USER ,SETTINGS, ITEM,ENQUIRY,ORDER , CUSTOMER,LOGIN};
    private static DAOFactory d;
    private static EmployeeDAOimpl edao;
    private static WorkRecordDAOimpl wdao;
    private static UserDAOimpl udao;
    private static SettingsDAOimpl sdao;
    private static ItemDAOimpl idao;
    private static EnquiryDAOimpl qdao;
    private static OrderDAOimpl odao;
    private static CustomerDAOimpl cdao;
    private static LoginDAOimpl ldao;
    public static LoginDAO getLoginDAO(){
        if(ldao==null){
            ldao=new LoginDAOimpl();
        }
        return ldao;
    }
    public static DAOFactory getInstance(){
        if(d==null){
            d=new DAOFactory();
        }
        return d;
    }
    private DAOFactory(){
        edao=new EmployeeDAOimpl();
        wdao=new WorkRecordDAOimpl();
        udao=new UserDAOimpl();
        sdao=new SettingsDAOimpl();
        idao=new ItemDAOimpl();
        qdao=new EnquiryDAOimpl();
        odao=new OrderDAOimpl();
        cdao=new CustomerDAOimpl();
    }
    public SuperDAO getDAO(DAOtype e){
        switch(e){
            case EMPLOYEE:return edao;
            case WORKRECORD:return wdao;
            case USER:return udao;
            case SETTINGS:return sdao;
            case ITEM:return idao;
            case ENQUIRY:return qdao;
            case ORDER:return odao;
            case CUSTOMER:return cdao;
            default :return null;
        }
    }
}
