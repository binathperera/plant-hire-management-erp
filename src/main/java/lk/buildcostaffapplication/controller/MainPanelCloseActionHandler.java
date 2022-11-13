/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author Binath Perera
 */
public class MainPanelCloseActionHandler implements ActionListener{
    private String title;
    public MainPanelCloseActionHandler(String title){
        this.title=title;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int index= MainFrameGenerator.getMf().getjTabbedPane().indexOfTab(title);
        
        MainFrameGenerator.getMf().getjTabbedPane().removeTabAt(index);
        
    }
    
}
