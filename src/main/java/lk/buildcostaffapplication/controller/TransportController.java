/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import lk.buildcostaffapplication.dao.DAOFactory;
import lk.buildcostaffapplication.dao.custom.OrderDAO;
import lk.buildcostaffapplication.dto.OrderDTO;
import lk.buildcostaffapplication.dto.OrderTransportDTO;

/**
 *
 * @author Binath Perera
 */
public class TransportController {
    OrderDAO odao;
    public TransportController(){
        odao=(OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtype.ORDER);
    }
    public ArrayList<OrderTransportDTO> search(LocalDate from,LocalDate to){
        return odao.getTransportOrders(from,to);
    }
    public OrderDTO search(String id){
        return (OrderDTO)odao.search(id);
    }
    public boolean setTransportDetails(OrderTransportDTO record){
        return odao.setTransportDetails(record);
    }
}
