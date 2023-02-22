/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.buildcostaffapplication.view;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lk.buildcostaffapplication.controller.CustomerController;
import lk.buildcostaffapplication.dto.CustomerDTO;

/**
 *
 * @author Binath Perera
 */
public class CustomersPanel extends javax.swing.JPanel {

    /**
     * Creates new form CustomerPanel
     */
    CustomerController controller;
    public CustomersPanel() {
        controller= new CustomerController();
        initComponents();
        deleteCustomerButton.setEnabled(false);
        updateButton.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        searchPanel = new javax.swing.JPanel();
        searchComboBox = new javax.swing.JComboBox<>();
        searchBarTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        searchResultsTable = new javax.swing.JTable();
        deleteCustomerButton = new javax.swing.JButton();
        generalDetailsPanel = new javax.swing.JPanel();
        NicLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nicTextField = new javax.swing.JTextField();
        nameTextField = new javax.swing.JTextField();
        billingAddressLabel = new javax.swing.JLabel();
        billingAddressTextField = new javax.swing.JTextField();
        locationAddressLabel = new javax.swing.JLabel();
        locationTextField = new javax.swing.JTextField();
        contactNumberLabel = new javax.swing.JLabel();
        contactNumberTextField = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        editEmailButton = new javax.swing.JButton();
        passwordLabel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JTextField();
        editPasswordButton = new javax.swing.JButton();
        companyNameLabel = new javax.swing.JLabel();
        companyNameTextField = new javax.swing.JTextField();

        jLabel1.setText("jLabel1");

        setPreferredSize(new java.awt.Dimension(703, 700));

        searchPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        searchComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Get all", "ID/Email", " " }));

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        searchResultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIC", "Email", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        searchResultsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchResultsTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(searchResultsTable);

        deleteCustomerButton.setText("Delete Customer and Order History");
        deleteCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCustomerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addComponent(searchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchBarTextField)
                        .addGap(18, 18, 18)
                        .addComponent(searchButton)
                        .addGap(16, 16, 16))
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteCustomerButton)
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteCustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        NicLabel.setText("NIC");

        nameLabel.setText("Name");

        nicTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nicTextFieldActionPerformed(evt);
            }
        });

        billingAddressLabel.setText("Billing Address");

        locationAddressLabel.setText("Location Address");

        locationTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationTextFieldActionPerformed(evt);
            }
        });

        contactNumberLabel.setText("Contact Number");

        contactNumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactNumberTextFieldActionPerformed(evt);
            }
        });

        updateButton.setText("Update Details");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        emailLabel.setText("E-mail");

        emailTextField.setEditable(false);

        editEmailButton.setText("edit");
        editEmailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editEmailButtonActionPerformed(evt);
            }
        });

        passwordLabel.setText("Web account password");

        passwordTextField.setEditable(false);

        editPasswordButton.setText("edit");
        editPasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPasswordButtonActionPerformed(evt);
            }
        });

        companyNameLabel.setText("Company Name");

        javax.swing.GroupLayout generalDetailsPanelLayout = new javax.swing.GroupLayout(generalDetailsPanel);
        generalDetailsPanel.setLayout(generalDetailsPanelLayout);
        generalDetailsPanelLayout.setHorizontalGroup(
            generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDetailsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(updateButton)
                .addGap(30, 30, 30))
            .addGroup(generalDetailsPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(generalDetailsPanelLayout.createSequentialGroup()
                        .addComponent(companyNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(companyNameTextField)
                        .addContainerGap())
                    .addGroup(generalDetailsPanelLayout.createSequentialGroup()
                        .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(generalDetailsPanelLayout.createSequentialGroup()
                                .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(contactNumberLabel)
                                    .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(locationAddressLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(billingAddressLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(generalDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(contactNumberTextField)
                                        .addGap(428, 428, 428))
                                    .addComponent(locationTextField)
                                    .addComponent(billingAddressTextField)))
                            .addGroup(generalDetailsPanelLayout.createSequentialGroup()
                                .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(generalDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(passwordLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(generalDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(emailLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(editPasswordButton)
                                    .addComponent(editEmailButton))
                                .addGap(8, 8, 8)))
                        .addContainerGap())
                    .addGroup(generalDetailsPanelLayout.createSequentialGroup()
                        .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel)
                            .addComponent(NicLabel))
                        .addGap(18, 18, 18)
                        .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nicTextField)
                            .addComponent(nameTextField))
                        .addGap(222, 222, 222))))
        );
        generalDetailsPanelLayout.setVerticalGroup(
            generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(generalDetailsPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NicLabel)
                    .addComponent(nicTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(generalDetailsPanelLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(billingAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, generalDetailsPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(companyNameLabel)
                            .addComponent(companyNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(billingAddressLabel)))
                .addGap(18, 18, 18)
                .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationAddressLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contactNumberLabel)
                    .addComponent(contactNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editEmailButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(generalDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editPasswordButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(updateButton)
                .addGap(62, 62, 62))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(generalDetailsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(generalDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        deleteCustomerButton.setEnabled(false);
        updateButton.setEnabled(false);
        
        int index = searchComboBox.getSelectedIndex();
        String searchItem = searchBarTextField.getText();
        DefaultTableModel dtm=(DefaultTableModel)searchResultsTable.getModel();
        dtm.setRowCount(0);
        if(index==0){
            ArrayList<CustomerDTO> customers;
            customers= controller.getAll();
            for(CustomerDTO customer: customers){
                String nic=customer.getNic();
                String email=customer.getEmail();
                String name=customer.getName();
                dtm.addRow(new Object[]{nic,email,name});
            }
        }else if(index==1){
            CustomerDTO customer= controller.search(searchBarTextField.getText());
            String nic=customer.getNic();
            String email=customer.getEmail();
            String name=customer.getName();
            dtm.addRow(new Object[]{nic,email,name});
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void searchResultsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchResultsTableMouseClicked
        deleteCustomerButton.setEnabled(true);
        updateButton.setEnabled(true);
        emailTextField.setEditable(false);
        passwordTextField.setEditable(false);
        
        int selectedRow = searchResultsTable.getSelectedRow();
        String ID;
        if(String.valueOf(searchResultsTable.getValueAt(selectedRow,1)).equals(""))
            ID= String.valueOf(searchResultsTable.getValueAt(selectedRow,0));
        else
            ID= String.valueOf(searchResultsTable.getValueAt(selectedRow,1));
        CustomerDTO user = controller.search(ID);
        nicTextField.setText(user.getNic());
        nameTextField.setText(user.getName());
        companyNameTextField.setText(user.getCompanyName());
        billingAddressTextField.setText(user.getBillingAddress());
        locationTextField.setText(user.getDestinationAddress());
        contactNumberTextField.setText(user.getContact());
        passwordTextField.setText(user.getPassword());
        emailTextField.setText(user.getEmail());
    
    }//GEN-LAST:event_searchResultsTableMouseClicked

    private void deleteCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCustomerButtonActionPerformed

        deleteCustomerButton.setEnabled(false);
        updateButton.setEnabled(false);
        

        int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Delete this customer? order history will also be deleted");
        if(showConfirmDialog!=0){return;}
        int selectedRow = searchResultsTable.getSelectedRow();
        if(selectedRow==-1){
            return;
        }
        boolean status=controller.deleteCustomer(String.valueOf(searchResultsTable.getValueAt(selectedRow, 0)));
        if(status){
            JOptionPane.showMessageDialog(this, "Deleted customer and related order details");
            DefaultTableModel model = (DefaultTableModel)searchResultsTable.getModel();
            model.removeRow(selectedRow);
            deleteCustomerButton.setEnabled(false);
            updateButton.setEnabled(false);
            
            clearForm();
        }else{
            JOptionPane.showMessageDialog(this, "Deleted customer and related order details");
        }
    }//GEN-LAST:event_deleteCustomerButtonActionPerformed
    private void clearForm(){}
    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Update this customer?");
        if(showConfirmDialog!=0){return;}
        String nic=nicTextField.getText();
        String name=nameTextField.getText();
        String companyName=companyNameTextField.getText();
        String billingAddress=billingAddressTextField.getText();
        String location=locationTextField.getText();
        String contactNumber=contactNumberTextField.getText();
        String password=passwordTextField.getText();
        String email=emailTextField.getText();
        //String nic, String name, String companyName, String email, String password,String billingAddress, String destinationAddress, String contact
        boolean status=controller.setGeneralDetails(new CustomerDTO(nic, name, companyName, email, password, billingAddress, location, contactNumber));
        if(status){
            JOptionPane.showMessageDialog(this, "Customer details updated");
            clearForm();
        }else{
            JOptionPane.showMessageDialog(this, "Failed to update details");
        }
        
    }//GEN-LAST:event_updateButtonActionPerformed

    private void contactNumberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactNumberTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactNumberTextFieldActionPerformed

    private void nicTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nicTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nicTextFieldActionPerformed

    private void locationTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_locationTextFieldActionPerformed

    private void editEmailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editEmailButtonActionPerformed
        emailTextField.setEditable(true);
    }//GEN-LAST:event_editEmailButtonActionPerformed

    private void editPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPasswordButtonActionPerformed
        passwordTextField.setEditable(true);
    }//GEN-LAST:event_editPasswordButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NicLabel;
    private javax.swing.JLabel billingAddressLabel;
    private javax.swing.JTextField billingAddressTextField;
    private javax.swing.JLabel companyNameLabel;
    private javax.swing.JTextField companyNameTextField;
    private javax.swing.JLabel contactNumberLabel;
    private javax.swing.JTextField contactNumberTextField;
    private javax.swing.JButton deleteCustomerButton;
    private javax.swing.JButton editEmailButton;
    private javax.swing.JButton editPasswordButton;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JPanel generalDetailsPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel locationAddressLabel;
    private javax.swing.JTextField locationTextField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField nicTextField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JTextField searchBarTextField;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox<String> searchComboBox;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JTable searchResultsTable;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}