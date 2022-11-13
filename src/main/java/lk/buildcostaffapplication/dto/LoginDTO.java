/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dto;

import java.util.ArrayList;

/**
 *
 * @author Binath Perera
 */
public class LoginDTO implements SuperDTO{
    private ArrayList<String> list;
    private String name;

    public LoginDTO(ArrayList<String> list, String name) {
        this.list = list;
        this.name = name;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
