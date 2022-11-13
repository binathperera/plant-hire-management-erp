/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dao;

/**
 *
 * @author Binath Perera
 *
 */

import java.util.ArrayList;

import lk.buildcostaffapplication.dto.SuperDTO;

public interface SuperDAO<T extends SuperDTO>{
    public boolean add(T ob);
    public boolean delete(String id);
    public boolean update(T ob);
    public T search(String id);
    public ArrayList<T> getAll();
}
