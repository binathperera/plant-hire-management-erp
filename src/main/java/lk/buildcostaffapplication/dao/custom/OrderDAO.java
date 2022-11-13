/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.buildcostaffapplication.dao.custom;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import lk.buildcostaffapplication.dao.SuperDAO;
import lk.buildcostaffapplication.dto.InstallmentDTO;
import lk.buildcostaffapplication.dto.OrderDTO;
import lk.buildcostaffapplication.dto.OrderTransportDTO;

/**
 *
 * @author Binath Perera
 */
public interface OrderDAO extends SuperDAO{
    String getNewOrderId();
    void changeCustomerId(String before, String after);
    ArrayList<OrderDTO> search(String orderId,int placedDate,LocalDate orderPlacedFromDate,LocalDate orderPlacedToDate,int itemRequested,LocalDate itemRequestedFromDate,LocalDate itemRequestedToDate,int statusIndex,String nicOrEmail);
    boolean setInstallmentPayment(String orderId,int installmentNo,boolean status);
    ArrayList<OrderTransportDTO> getTransportOrders(LocalDate from,LocalDate to);
    boolean setTransportDetails(OrderTransportDTO transport);
    boolean setOrderStatus(String id, String status);
    boolean setInstallmentInvoice(String orderId,int installmentNo,boolean invoiceIssued);
    ArrayList<InstallmentDTO> getPaymentsForTheDate(LocalDate date);
    ArrayList<InstallmentDTO> getOverduePayments(LocalDate date);
    public boolean sendReceiptAsMail(File file,String from);
    public boolean sendInvoiceAsMail(File file,String from);
}
