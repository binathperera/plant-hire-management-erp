/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.buildcostaffapplication.dao.custom;

import java.util.ArrayList;
import lk.buildcostaffapplication.dao.SuperDAO;
import lk.buildcostaffapplication.dto.SuperDTO;

/**
 *
 * @author Binath Perera
 */
public interface UserDAO extends SuperDAO<SuperDTO>{
    ArrayList<SuperDTO> getUsersIdsAndNames();
}
