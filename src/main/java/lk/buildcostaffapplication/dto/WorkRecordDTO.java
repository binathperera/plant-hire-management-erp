/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dto;

import java.time.LocalTime;
import java.time.LocalDate;



/**
 *
 * @author Binath Perera
 */
public class WorkRecordDTO implements SuperDTO{
    String employeeID;
    LocalDate day;
    LocalTime clockedIn;
    LocalTime clockedOut;
    String workLocation;
    String workDescription;
    String comment;
    public WorkRecordDTO(LocalDate day,String employeeID,LocalTime clockedIn, LocalTime clockedOut, String workLocation,
            String workDescription, String comment){
        this.employeeID = employeeID;
        this.day = day;
        this.clockedIn = clockedIn;
        this.clockedOut = clockedOut;
        this.workLocation = workLocation;
        this.workDescription = workDescription;
        this.comment = comment;
    }
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public LocalTime getClockedIn() {
        return clockedIn;
    }

    public void setClockedIn(LocalTime clockedIn) {
        this.clockedIn = clockedIn;
    }

    public LocalTime getClockedOut() {
        return clockedOut;
    }

    public void setClockedOut(LocalTime clockedOut) {
        this.clockedOut = clockedOut;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }
    
}
