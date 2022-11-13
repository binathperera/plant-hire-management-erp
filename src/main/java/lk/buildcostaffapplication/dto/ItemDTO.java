/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.dto;

import java.io.File;

/**
 *
 * @author Binath Perera
 */
public class ItemDTO implements SuperDTO{
    private String sku,depot,name,overview,specifications,attachments,safetyEquipment;
    private int totalQty,existingQty,qtyChange;
    private double originalVal,currentVal,rate;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    private File image,userManual,serviceManual;
    public ItemDTO(String sku, String depot, String name, String overview, String specifications, String attachments, String safetyEquipment, int totalQty, int existingQty, int qtyChange, double originalVal, double currentVal, File image, File userManual, File serviceManual,double rate) {
        this.sku = sku;
        this.depot = depot;
        this.name = name;
        this.overview = overview;
        this.specifications = specifications;
        this.attachments = attachments;
        this.safetyEquipment = safetyEquipment;
        this.totalQty = totalQty;
        this.existingQty = existingQty;
        this.qtyChange = qtyChange;
        this.originalVal = originalVal;
        this.currentVal = currentVal;
        this.image = image;
        this.userManual = userManual;
        this.serviceManual = serviceManual;
        this.rate=rate;
    }
    public ItemDTO(String sku, String name){
        this.sku=sku;
        this.name=name;
    }
    public ItemDTO(String sku, String name,int existingQty,double rate){
        this.sku=sku;
        this.name=name;
        this.existingQty = existingQty;
        this.rate=rate;
    }
    public String getSafetyEquipment() {
        return safetyEquipment;
    }

    public void setSafetyEquipment(String safetyEquipment) {
        this.safetyEquipment = safetyEquipment;
    }
    
    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public int getExistingQty() {
        return existingQty;
    }

    public void setExistingQty(int existingQty) {
        this.existingQty = existingQty;
    }

    public int getQtyChange() {
        return qtyChange;
    }

    public void setQtyChange(int qtyChange) {
        this.qtyChange = qtyChange;
    }

    public double getOriginalVal() {
        return originalVal;
    }

    public void setOriginalVal(double originalVal) {
        this.originalVal = originalVal;
    }

    public double getCurrentVal() {
        return currentVal;
    }

    public void setCurrentVal(double currentVal) {
        this.currentVal = currentVal;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public File getUserManual() {
        return userManual;
    }

    public void setUserManual(File userManual) {
        this.userManual = userManual;
    }

    public File getServiceManual() {
        return serviceManual;
    }

    public void setServiceManual(File serviceManual) {
        this.serviceManual = serviceManual;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }
}
