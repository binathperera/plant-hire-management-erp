/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dto;

import java.time.LocalDate;

/**
 *
 * @author Binath Perera
 */
public class InstallmentDTO {
    private int number;
    private double amount;
    private LocalDate date;
    private boolean paid;
    private boolean invoiceIssued;
    private String orderId;

    public InstallmentDTO() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public boolean isInvoiceIssued() {
        return invoiceIssued;
    }

    public void setInvoiceIssued(boolean invoiceIssued) {
        this.invoiceIssued = invoiceIssued;
    }
    public InstallmentDTO(int number, double amount, LocalDate date, boolean invoiceIssued,boolean paid) {
        this.number = number;
        this.amount = amount;
        this.date = date;
        this.paid = paid;
        this.invoiceIssued=invoiceIssued;
    }

    public InstallmentDTO(int number, double amount, LocalDate date) {
        this.number = number;
        this.amount = amount;
        this.date = date;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    
}
