/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.buildcostaffapplication.dao.custom;

import java.util.ArrayList;
import lk.buildcostaffapplication.dao.SuperDAO;
import lk.buildcostaffapplication.dto.ItemDTO;

/**
 *
 * @author Binath Perera
 */
public interface ItemDAO extends SuperDAO{
    ArrayList<ItemDTO> getItemSKUsAndNames();
    ArrayList<ItemDTO> getItemForOrders();
    boolean increaseItemQuantity(String sku,int quantity);
    boolean reduceItemQuantity(String sku,int quantity);
}
