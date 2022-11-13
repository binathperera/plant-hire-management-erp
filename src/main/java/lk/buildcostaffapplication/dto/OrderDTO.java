/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dto;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Binath Perera
 */
public class OrderDTO implements SuperDTO{
    private String customerId, orderId,status,deliverDetails,pickupDetails,location,billingAddress;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private LocalDate from,to,orderDate;
    private double totalPriceForItems,deliveryCharges,discount,netTotal;
    private ArrayList<OrderItemDTO> items;
    private ArrayList<InstallmentDTO> installments;
    private boolean deliver,pickup,delivered,pickedUp;



    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
    
    
    private File leaseAgreement;
    
    public OrderDTO(String customerId, String orderId, LocalDate from, 
            LocalDate to, double totalPriceForItems, double deliveryCharges, 
            double discount, double netTotal, ArrayList<OrderItemDTO> items,
            ArrayList<InstallmentDTO> installments, boolean deliver, 
            boolean pickup,File leaseAgreement,LocalDate orderDate,String status,String location,String billingAddress) {
        this.billingAddress=billingAddress;
        this.location=location;
        this.customerId = customerId;
        this.orderId = orderId;
        this.from = from;
        this.to = to;
        this.totalPriceForItems = totalPriceForItems;
        this.deliveryCharges = deliveryCharges;
        this.discount = discount;
        this.netTotal = netTotal;
        this.items = items;
        this.installments = installments;
        this.deliver = deliver;
        this.pickup = pickup;
        this.leaseAgreement=leaseAgreement;
        this.orderDate=orderDate;
        this.status=status;
    }
    public String getDeliverDetails() {
        return deliverDetails;
    }

    public void setDeliverDetails(String deliverDetails) {
        this.deliverDetails = deliverDetails;
    }

    public String getPickupDetails() {
        return pickupDetails;
    }

    public void setPickupDetails(String pickupDetails) {
        this.pickupDetails = pickupDetails;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    
    public File getLeaseAgreement() {
        return leaseAgreement;
    }

    public void setLeaseAgreement(File leaseAgreement) {
        this.leaseAgreement = leaseAgreement;
    }
    

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public double getTotalPriceForItems() {
        return totalPriceForItems;
    }

    public void setTotalPriceForItems(double totalPriceForItems) {
        this.totalPriceForItems = totalPriceForItems;
    }

    public double getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(double deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(double netTotal) {
        this.netTotal = netTotal;
    }

    public ArrayList<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItemDTO> items) {
        this.items = items;
    }

    public ArrayList<InstallmentDTO> getInstallments() {
        return installments;
    }

    public void setInstallments(ArrayList<InstallmentDTO> installments) {
        this.installments = installments;
    }

    public boolean isDeliver() {
        return deliver;
    }

    public void setDeliver(boolean deliver) {
        this.deliver = deliver;
    }

    public boolean isPickup() {
        return pickup;
    }

    public void setPickup(boolean pickup) {
        this.pickup = pickup;
    }
    
    public OrderDTO(){}
}
