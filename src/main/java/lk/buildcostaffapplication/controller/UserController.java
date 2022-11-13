/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.controller;

import java.util.ArrayList;
import lk.buildcostaffapplication.dao.DAOFactory;
import lk.buildcostaffapplication.dao.custom.UserDAO;
import lk.buildcostaffapplication.dto.SuperDTO;
import lk.buildcostaffapplication.dto.UserDTO;

/**
 *
 * @author Binath Perera
 */
public class UserController {
    private UserDAO udao;
    public UserController(){
        udao=(UserDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOtype.USER);
    }
    public boolean add(UserDTO dto){
        return udao.add(dto);
    }
    public UserDTO getUserByID(String id){
        return (UserDTO)udao.search(id);
    }
    public ArrayList<SuperDTO> getUsersIdsAndNames(){
        return udao.getUsersIdsAndNames();
    }
    public boolean deleteUser(String id){
        return udao.delete(id);
    }
    public boolean addUser(UserDTO dto){
        return udao.add(dto);
    }
    public boolean updateUser(UserDTO dto){
        return udao.update(dto);
    }
}
