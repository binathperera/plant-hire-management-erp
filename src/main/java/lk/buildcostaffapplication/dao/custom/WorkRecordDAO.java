/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.buildcostaffapplication.dao.custom;

import java.sql.Date;
import java.time.LocalDate;
import lk.buildcostaffapplication.dao.SuperDAO;
import lk.buildcostaffapplication.dto.WorkRecordDTO;

/**
 *
 * @author Binath Perera
 */
public interface WorkRecordDAO extends SuperDAO{
    WorkRecordDTO getWorkRecordByIDandDay(String id,LocalDate day);
    boolean deleteWorkRecord(String id, LocalDate d);
}
