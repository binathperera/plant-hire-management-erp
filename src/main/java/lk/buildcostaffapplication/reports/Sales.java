/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.reports;

/**
 *
 * @author Binath Perera
 */
public class Sales{
        private double amount;
        private String month;

        public Sales(double amount, String month) {
            this.amount = amount;
            this.month = month;
        }
        
        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        } 
}
