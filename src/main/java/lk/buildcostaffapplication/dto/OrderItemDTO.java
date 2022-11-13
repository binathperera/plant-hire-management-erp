/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dto;

/**
 *
 * @author Binath Perera
 */
public class OrderItemDTO {
    private String sku,name;
    private int qty;
    private double totalPrice;

    public OrderItemDTO(String sku, String name, int qty, double totalPrice) {
        this.sku = sku;
        this.name = name;
        this.qty = qty;
        this.totalPrice = totalPrice;
    }
    
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
