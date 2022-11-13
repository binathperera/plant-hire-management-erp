/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import lk.buildcostaffapplication.dao.DAOFactory;
import lk.buildcostaffapplication.dao.custom.EnquiryDAO;
import lk.buildcostaffapplication.dto.EnquiryDTO;


/**
 *
 * @author Binath Perera
 */
public class EnquiryController {
    private EnquiryDAO edao;
    public EnquiryController(){
        edao=(EnquiryDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOtype.ENQUIRY);
    }
    public boolean deleteEnquiry(String id){
        return edao.delete(id);
    }
    public boolean addEnquiry(EnquiryDTO e){
        return edao.add(e);
    }
    public boolean updateEnquiry(EnquiryDTO e){
        return edao.update(e);
    }
    public EnquiryDTO getEnquiryByID(String id){
        return (EnquiryDTO)edao.search(id);
    }
    public int getOpenEnquiryCount(){
        return edao.getOpenEnquiryCount();
    }
    public ArrayList<EnquiryDTO> search(int timeIndex,int statusIndex,String nicOrEmail,LocalDate fromDate,LocalDate toDate){
        return edao.search(timeIndex,statusIndex,nicOrEmail,fromDate,toDate);
    }
}
