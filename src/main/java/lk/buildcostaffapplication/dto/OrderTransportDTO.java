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
public class OrderTransportDTO {
    private String id,location;
    private LocalDate date;
    private String deliverOrPick;
    private String details;
    boolean status;
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public OrderTransportDTO(String id, String deliverOrPick ,boolean status , LocalDate date,String location,String details) {
        this.id = id;
        this.location = location;
        this.date = date;
        this.deliverOrPick = deliverOrPick;
        this.status=status;
        this.details=details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDeliverOrPick() {
        return deliverOrPick;
    }

    public void setDeliverOrPick(String deliverOrPick) {
        this.deliverOrPick = deliverOrPick;
    }
}
