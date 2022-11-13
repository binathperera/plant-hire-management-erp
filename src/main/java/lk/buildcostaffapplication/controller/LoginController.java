/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.controller;

import javax.swing.JPanel;
import lk.buildcostaffapplication.dao.DAOFactory;
import lk.buildcostaffapplication.dao.custom.LoginDAO;
import lk.buildcostaffapplication.dao.custom.impl.LoginDAOimpl;
import lk.buildcostaffapplication.dto.LoginDTO;

/**
 *
 * @author Binath Perera
 */
public class LoginController {
    LoginDAO ldao;
    public LoginController(){
        ldao=DAOFactory.getLoginDAO();
    }
    public LoginDTO logUser(JPanel panel,String username,String password){
        return ldao.logUser(panel,username,password);
    }
    public void forgotPassword(JPanel panel,String username){
        ldao.forgotPassword(panel,username);
    }
}
