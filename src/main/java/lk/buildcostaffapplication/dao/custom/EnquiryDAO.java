/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.buildcostaffapplication.dao.custom;

import java.time.LocalDate;
import java.util.ArrayList;
import lk.buildcostaffapplication.dao.SuperDAO;
import lk.buildcostaffapplication.dto.EnquiryDTO;

/**
 *
 * @author Binath Perera
 */
public interface EnquiryDAO extends SuperDAO{
    int getOpenEnquiryCount();
    ArrayList<EnquiryDTO> search(int timeIndex,int statusIndex,String nicOrEmail,LocalDate fromDate,LocalDate toDate);
}
