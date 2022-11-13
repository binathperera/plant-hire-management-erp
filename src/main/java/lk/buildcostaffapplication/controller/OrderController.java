/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.controller;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import lk.buildcostaffapplication.dao.DAOFactory;
import lk.buildcostaffapplication.dao.custom.OrderDAO;
import lk.buildcostaffapplication.dto.InstallmentDTO;
import lk.buildcostaffapplication.dto.OrderDTO;

/**
 *
 * @author Binath Perera
 */
public class OrderController {
    OrderDAO odao;
    public OrderController(){
        odao=(OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOtype.ORDER);
    }
    public String getNewOrderId(){
        return odao.getNewOrderId();
    }
    public boolean add(OrderDTO order){
        return odao.add(order);
    }
    public boolean delete(String orderId){
        return odao.delete(orderId);
    }
    public OrderDTO search(String id){
        return (OrderDTO)odao.search(id);
    }
    public ArrayList<OrderDTO> search(String orderId,int placedDate,LocalDate orderPlacedFromDate,LocalDate orderPlacedToDate,int itemRequested,LocalDate itemRequestedFromDate,LocalDate itemRequestedToDate,int statusIndex,String nicOrEmail){
        return odao.search(orderId,placedDate,orderPlacedFromDate,orderPlacedToDate,itemRequested,itemRequestedFromDate,itemRequestedToDate,statusIndex,nicOrEmail);
    }
    public boolean setInstallmentPayment(String orderId,int installmentNo,boolean status){
        return odao.setInstallmentPayment(orderId,installmentNo,status);
    }
    public boolean setOrderStatus(String id, String status){
        return odao.setOrderStatus(id,status);
    }
    public boolean setInstallmentInvoice(String orderId,int installmentNo,boolean invoiceIssued){
        return odao.setInstallmentInvoice(orderId,installmentNo,invoiceIssued);
    }
    public ArrayList<InstallmentDTO> getPaymentsForTheDate(LocalDate date){
        return odao.getPaymentsForTheDate(date);
    }
    public ArrayList<InstallmentDTO> getOverduePayments(LocalDate date){
        return odao.getPaymentsForTheDate(date);
    }
    public boolean sendReceiptAsEmail(File file , String from){
        return odao.sendReceiptAsMail(file, from);
    }
    public boolean sendInvoiceAsEmail(File file , String from){
        return odao.sendInvoiceAsMail(file, from);
    }
}
