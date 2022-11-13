/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.buildcostaffapplication.controller;

import java.util.ArrayList;
import lk.buildcostaffapplication.view.MainFrame;

/**
 *
 * @author Binath Perera
 */
public class MainFrameGenerator {
    private static MainFrame mf;

    public static MainFrame getMf() {
        return mf;
    }

    public MainFrameGenerator(String first,ArrayList<String> list){
        mf=new MainFrame(first,list);
    }
}
