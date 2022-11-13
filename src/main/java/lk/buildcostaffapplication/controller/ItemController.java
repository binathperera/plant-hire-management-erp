/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.controller;

import java.util.ArrayList;
import lk.buildcostaffapplication.dao.DAOFactory;
import lk.buildcostaffapplication.dao.custom.ItemDAO;
import lk.buildcostaffapplication.dto.ItemDTO;

/**
 *
 * @author Binath Perera
 */
public class ItemController {
    ItemDAO idao;
    public ItemController(){
        idao = (ItemDAO)DAOFactory.getInstance().getDAO(DAOFactory.DAOtype.ITEM);
    }
    public boolean deleteItem(String sku){
        return idao.delete(sku);
    }
    public boolean addItem(ItemDTO item){
        return idao.add(item);
    }
    public boolean updateItem(ItemDTO item){
        return idao.update(item);
    }
    public ArrayList<ItemDTO> getItemSKUsAndNames(){
        return idao.getItemSKUsAndNames();
    }
    public ArrayList<ItemDTO> getItemForOrders(){
        return idao.getItemForOrders();
    }
    public ItemDTO getItemBySKU(String sku){
        return (ItemDTO)idao.search(sku);
    }
}
