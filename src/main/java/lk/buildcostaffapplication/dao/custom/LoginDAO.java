/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.buildcostaffapplication.dao.custom;

import javax.swing.JPanel;
import lk.buildcostaffapplication.dao.SuperDAO;
import lk.buildcostaffapplication.dto.LoginDTO;

/**
 *
 * @author Binath Perera
 */
public interface LoginDAO extends SuperDAO{
    LoginDTO logUser(JPanel panel,String username,String password);
    void forgotPassword(JPanel panel,String username);
}
