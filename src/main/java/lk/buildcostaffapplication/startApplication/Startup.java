/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lk.buildcostaffapplication.startApplication;
import java.util.ArrayList;
import lk.buildcostaffapplication.view.LoginFrame;
import lk.buildcostaffapplication.view.MainFrame;

/**
 *
 * @author Binath Perera
 */
public class Startup{

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
    public static void main(String user,ArrayList<String>access) {
        new MainFrame(user,access).setVisible(true);
    }
}
