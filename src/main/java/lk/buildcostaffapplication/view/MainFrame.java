/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lk.buildcostaffapplication.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import lk.buildcostaffapplication.controller.MainPanelCloseActionHandler;
import lk.buildcostaffapplication.startApplication.Startup;

/**
 *
 * @author Binath Perera
 */
class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    int noOfPanels=0;
    String mode="light";
    ImagePanel backgroundImage;
    ArrayList<String> access;
    String user;
    JFrame mainFrame;
    public void clock(){
        Thread c= new Thread(){
            
            @Override
            public void run(){
                while(true){
                    try {
                    Calendar cal = new GregorianCalendar();
                    dateAndTimeLabel.setText(""+cal.getTime());
                    
                    sleep(1000);
                    
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        c.start();
    }
    public MainFrame(String user,ArrayList<String> list) {
        mainFrame=this;
        BufferedImage myImage=null;
        this.user=user;
        try {
            if(mode.equals("light"))myImage = ImageIO.read(getClass().getResource("/light-mode-theme.jpg"));
            if(mode.equals("dark"))myImage = ImageIO.read(getClass().getResource("/dark-mode-theme.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        this.setContentPane(backgroundImage=new ImagePanel(myImage));
        initComponents();
        statusPanel.setBackground(new Color(255,255,255,80));
        iconPanel.setBackground(new Color(255,255,255,100));
        iconDescriptionPanel.setBackground(new Color(255,255,255,100));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        clock();
        welcomeLabel.setText(user);
        loadModules(list);
    }
    public void loadModules(ArrayList<String> list){
        access=list;
        try{
            for(String value:list){
                switch(value){
                    case "customers" :{
                        customers=new JLabel("Customers",SwingConstants.CENTER);
                        customersIcon=new JLabel("",SwingConstants.CENTER);
                        addIcon(customers,customersIcon , "/customers-icon_2x.png","/customers-icon.png");
                    };break;
                    case "items" :{
                        items=new JLabel("Items",SwingConstants.CENTER);
                        itemsIcon=new JLabel("",SwingConstants.CENTER);
                        addIcon(items,itemsIcon ,  "/manage_inventories_2x.png","/manage_inventories.png");
                    };break;
                    case "orders":{
                        orders=new JLabel("Orders",SwingConstants.CENTER);
                        ordersIcon=new JLabel("",SwingConstants.CENTER);
                        addIcon(orders,ordersIcon ,  "/manage_orders_2x.png","/manage_orders.png");
                    };break;  
                    case "enquiries":{
                        inquiries=new JLabel("Enquiries",SwingConstants.CENTER);
                        inquiriesIcon=new JLabel("",SwingConstants.CENTER);
                        addIcon(inquiries,inquiriesIcon ,  "/inquiry-icon_2x.png","/inquiry-icon.png");
                    };break; 
                    case "transport" :{
                        transport=new JLabel("Transport",SwingConstants.CENTER);
                        transportIcon=new JLabel("",SwingConstants.CENTER);
                        addIcon(transport,transportIcon , "/transport_2x.png","/transport.png");
                    };break;
                    case "users" :{
                        users=new JLabel("Users",SwingConstants.CENTER);
                        usersIcon=new JLabel("",SwingConstants.CENTER);
                        addIcon(users,usersIcon , "/users_2x.png","/users.png");
                    };break;
                    case "employees" :{
                        employees=new JLabel("Employees",SwingConstants.CENTER);
                        employeesIcon=new JLabel("",SwingConstants.CENTER);
                        addIcon(employees,employeesIcon , "/manage_employees_2x.png","/manage_employees.png");
                    };break;
                    
                    case "reports" :{
                        reports=new JLabel("Reports",SwingConstants.CENTER);
                        reportsIcon=new JLabel("",SwingConstants.CENTER);
                        addIcon(reports,reportsIcon , "/reports_2x.png","/reports.png");
                    };break;
                }
            }
            settings=new JLabel("Settings",SwingConstants.CENTER);
            settingsIcon=new JLabel("",SwingConstants.CENTER);
            addIcon(settings, settingsIcon,  "/settings_2x.png","/settings.png");
        }catch (Exception e){
            e.printStackTrace();
        }      
    }
    private void addIcon(final JLabel description ,final JLabel icon, String pathEntered , String pathExited){

        description.setFont(new java.awt.Font("Tahoma", 1, 16));
        description.setPreferredSize(new Dimension(150,20));
        
        icon.setPreferredSize(new Dimension(150,60));
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource(pathExited))); 
        
        iconDescriptionPanel.add(description);
        iconPanel.add(icon);
        icon.addMouseListener(new java.awt.event.MouseAdapter() {         
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                 icon.setIcon(new javax.swing.ImageIcon(getClass().getResource(pathEntered))); 
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                 icon.setIcon(new javax.swing.ImageIcon(getClass().getResource(pathExited))); 
            }
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                open(description.getText());
            }
        });
    }
    private void open(String text){
        switch(text){
            case "Customers" : 
                if(jTabbedPane.indexOfTab("Manage Customers") == -1){
                    openPanel("Manage Customers",customersPanel=new CustomersPanel());
                }else{
                    jTabbedPane.setSelectedIndex(jTabbedPane.indexOfTab("Manage Customers"));
                };break;
            case "Items" :
                if(jTabbedPane.indexOfTab("Manage Items") == -1){
                    openPanel("Manage Items",itemsPanel=new ItemsPanel());
                }else{
                    jTabbedPane.setSelectedIndex(jTabbedPane.indexOfTab("Manage Items"));
                };break;
            case "Orders":
                if(jTabbedPane.indexOfTab("Manage Orders") == -1){
                    openPanel("Manage Orders",ordersPanel=new OrdersPanel());
                }else{
                    jTabbedPane.setSelectedIndex(jTabbedPane.indexOfTab("Manage Orders"));
                };break;
            case "Enquiries":
                if(jTabbedPane.indexOfTab("Manage Enquiries") == -1){
                    openPanel("Manage Enquiries",enquiriesPanel=new EnquiriesPanel());
                }else{
                    jTabbedPane.setSelectedIndex(jTabbedPane.indexOfTab("Manage Orders"));
                };break;
            case "Transport":
                if(jTabbedPane.indexOfTab("Transport") == -1){
                    openPanel("Transport",transportPanel=new TransportPanel());
                }else{
                    jTabbedPane.setSelectedIndex(jTabbedPane.indexOfTab("Transport"));
                };break;   
            case "Users":
                if(jTabbedPane.indexOfTab("Manage Users") == -1){
                    openPanel("Manage Users",usersPanel=new UsersPanel());
                }else{
                    jTabbedPane.setSelectedIndex(jTabbedPane.indexOfTab("Manage Users"));
                };break;
            case "Employees":
                if(jTabbedPane.indexOfTab("Manage Employees") == -1){
                    openPanel("Manage Employees",employeesPanel=new EmployeesPanel());
                    for(String value:access){
                        switch(value){
                            case "newEmployees" :{
                                employeesPanel.newEmployeePanelVisibility(true);
                            };break;
                            case "existingEmployees" :{
                                employeesPanel.existingEmployeePanelVisibility(true);
                            };break;
                            case "employeeJobDetails" :{
                                employeesPanel.jobDetailsPanelVisibility(true);
                            };break;
                            case "employeeOtherDetails" :{
                                employeesPanel.otherDetailsPanelVisibility(true);
                            }
                        }
                    }
                }else{
                    jTabbedPane.setSelectedIndex(jTabbedPane.indexOfTab("Manage Employees"));
                };break;
            case "Reports":
                if(jTabbedPane.indexOfTab("Reports") == -1){
                    openPanel("Reports",reportsPanel=new ReportsPanel());
                }else{
                    jTabbedPane.setSelectedIndex(jTabbedPane.indexOfTab("Reports"));
                };break;
            case "Settings":
                if(jTabbedPane.indexOfTab("Settings") == -1){
                    openPanel("Settings",settingsPanel=new SettingsPanel());
                }else{
                    jTabbedPane.setSelectedIndex(jTabbedPane.indexOfTab("Settings"));
                };break;
        }
    }
    
    private void openPanel(String name,JPanel panel){
        JLabel title=new JLabel(name);
        JScrollPane scrollPane = new JScrollPane(panel);    
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jTabbedPane.add(name,scrollPane);
        
        scrollPane.setVisible(true);
        int index= jTabbedPane.indexOfTab(name);

        JPanel header=new JPanel();

        JLabel label=new JLabel(name);

        JButton b=new JButton("x");
        b.setBorder(null);
        b.setSize(20,20);
        b.setVisible(true);
        b.addActionListener(new MainPanelCloseActionHandler(name));

        header.add(label);
        header.add(b);

        jTabbedPane.setTabComponentAt(index, header);
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iconPanel =  new javax.swing.JPanel(){
            protected void paintComponent(Graphics g)
            {
                g.setColor( getBackground() );
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }};
            jTabbedPane = new javax.swing.JTabbedPane();
            statusPanel =  new javax.swing.JPanel()
            {
                protected void paintComponent(Graphics g)
                {
                    g.setColor( getBackground() );
                    g.fillRect(0, 0, getWidth(), getHeight());
                    super.paintComponent(g);
                }
            };
            welcomeLabel = new javax.swing.JLabel();
            dateAndTimeLabel = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            optionsButton = new javax.swing.JButton();
            modeButton = new javax.swing.JButton();
            iconDescriptionPanel =  new javax.swing.JPanel(){
                protected void paintComponent(Graphics g)
                {
                    g.setColor( getBackground() );
                    g.fillRect(0, 0, getWidth(), getHeight());
                    super.paintComponent(g);
                }};

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setBackground(new java.awt.Color(153, 153, 153));

                iconPanel.setBackground(new java.awt.Color(153, 153, 153));
                iconPanel.setOpaque(false);

                statusPanel.setBackground(new java.awt.Color(204, 204, 204));
                statusPanel.setOpaque(false);
                statusPanel.setPreferredSize(new java.awt.Dimension(199, 45));

                welcomeLabel.setBackground(new java.awt.Color(255, 255, 255));
                welcomeLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

                dateAndTimeLabel.setBackground(new java.awt.Color(255, 255, 255));
                dateAndTimeLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

                jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 28)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(0, 0, 0));
                jLabel2.setText("Buildco (Pvt) Ltd");

                optionsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login_user_logo.png"))); // NOI18N
                optionsButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        optionsButtonActionPerformed(evt);
                    }
                });

                modeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/light-mode-icon.png"))); // NOI18N
                modeButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        modeButtonActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
                statusPanel.setLayout(statusPanelLayout);
                statusPanelLayout.setHorizontalGroup(
                    statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(optionsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateAndTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(modeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                );
                statusPanelLayout.setVerticalGroup(
                    statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addGroup(statusPanelLayout.createSequentialGroup()
                                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateAndTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(optionsButton))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(modeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())
                );

                iconDescriptionPanel.setBackground(new java.awt.Color(153, 153, 153));
                iconDescriptionPanel.setOpaque(false);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                    .addComponent(iconDescriptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane)
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(iconPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(iconDescriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void optionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsButtonActionPerformed
        JPopupMenu menu = new JPopupMenu("Menu");

        JMenuItem signOut=new JMenuItem("Sign out");
        signOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Startup.main(null);
                mainFrame.setVisible(false);
                mainFrame.dispose();
            }
        });
        menu.add(signOut);
        
        JMenuItem restart=new JMenuItem("Restart");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Startup.main(user,access);
                mainFrame.setVisible(false);
                mainFrame.dispose();
            }
        });
        menu.add(restart);
        JMenuItem close=new JMenuItem("Close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
        menu.add(close);
        
        menu.show(optionsButton, optionsButton.getWidth()/2, optionsButton.getHeight()/2);
  
    }//GEN-LAST:event_optionsButtonActionPerformed
     class RestartThread implements Runnable  {    
        public void run()  {    
            
            Frame[] frames = Frame.getFrames();
            frames[0].setVisible(false);
            frames[0].dispose();
            new MainFrame(user, access);
        }
     }
     class SignOutThread implements Runnable  {    
        public void run()  {   
           
        }
     }
    private void modeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modeButtonActionPerformed
        BufferedImage myImage=null;
        
        try {
            if(mode.equals("dark")){
                mode="light";
                modeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/light-mode-icon.png")));
                myImage = ImageIO.read(getClass().getResource("/light-mode-theme.jpg"));
            }else{
                mode="dark";
                modeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dark-mode-icon.png")));
                myImage = ImageIO.read(getClass().getResource("/dark-mode-theme.jpg")); 
            }
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        backgroundImage.setImage(myImage);
        this.repaint();
    }//GEN-LAST:event_modeButtonActionPerformed

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dateAndTimeLabel;
    private javax.swing.JPanel iconDescriptionPanel;
    private javax.swing.JPanel iconPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JButton modeButton;
    private javax.swing.JButton optionsButton;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JLabel customers;
    private javax.swing.JLabel items;
    private javax.swing.JLabel orders;
    private javax.swing.JLabel inquiries;
    private javax.swing.JLabel users;
    private javax.swing.JLabel settings;
    private javax.swing.JLabel transport;
    private javax.swing.JLabel employees;
    private javax.swing.JLabel reports;
    
    private javax.swing.JLabel customersIcon;
    private javax.swing.JLabel itemsIcon;
    private javax.swing.JLabel ordersIcon;
    private javax.swing.JLabel inquiriesIcon;
    private javax.swing.JLabel usersIcon;
    private javax.swing.JLabel settingsIcon;
     private javax.swing.JLabel transportIcon;
    private javax.swing.JLabel employeesIcon;
    private javax.swing.JLabel reportsIcon;
    
    private CustomersPanel customersPanel;
    private ItemsPanel itemsPanel;
    private OrdersPanel ordersPanel;
    private EnquiriesPanel enquiriesPanel;
    private SettingsPanel settingsPanel;
    private TransportPanel transportPanel;
    private UsersPanel usersPanel;
    private EmployeesPanel employeesPanel;
    private ReportsPanel reportsPanel;
    
    public JTabbedPane getjTabbedPane() {
        return jTabbedPane;
    }
}
