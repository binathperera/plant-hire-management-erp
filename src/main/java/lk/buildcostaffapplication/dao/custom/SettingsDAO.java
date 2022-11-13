/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.buildcostaffapplication.dao.custom;

import lk.buildcostaffapplication.dao.SuperDAO;

/**
 *
 * @author Binath Perera
 */
public interface SettingsDAO extends SuperDAO{
    boolean update2FA(boolean twoFA,String username);
    boolean get2FA(String username);
    String getEmail(String username);
    String getPassword(String username);
    boolean updatePassword(String username,String password);
}
