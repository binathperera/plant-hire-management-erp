/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.controller;

import lk.buildcostaffapplication.dao.DAOFactory;
import lk.buildcostaffapplication.dao.custom.SettingsDAO;

/**
 *
 * @author Binath Perera
 */
public class SettingsController {
    SettingsDAO sdao;
    public SettingsController(){
        sdao=(SettingsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtype.SETTINGS);
    }
    public boolean get2FA(String username){
        return sdao.get2FA(username);
    }
    public String getEmail(String username){
        return sdao.getEmail(username);
    }
    public boolean update2FA(boolean twoFA, String username){
        return sdao.update2FA(twoFA, username);
    }
    public boolean updatePassword(String username,String password){
        return sdao.updatePassword( username,password);
    }
    public String getPassword(String username){
        return sdao.getPassword(username);
    }
}
