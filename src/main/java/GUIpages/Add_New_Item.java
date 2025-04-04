/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIpages;

import backendHander.DBAppHandler;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Add_New_Item extends javax.swing.JFrame {

    /**
     * Creates new form Add_New_Item
     */
    public Add_New_Item() {
        initComponents();
        ButtonGroup btngp = new ButtonGroup();
        btngp.add(selectedFood);
        btngp.add(selectedDrink);
        txt_name.setText(StoreItem.ItemName);
        txt_price.setText(String.valueOf(StoreItem.ItemPrice));
        if ("food".equalsIgnoreCase(StoreItem.category)) {
            selectedFood.setSelected(true);
        } else if ("drink".equalsIgnoreCase(StoreItem.category)) {
            selectedDrink.setSelected(true);
        } else {
            selectedDrink.setSelected(false);
            selectedFood.setSelected(false);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_price = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_save = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        selectedFood = new javax.swing.JRadioButton();
        selectedDrink = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txt_price.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_priceActionPerformed(evt);
            }
        });

        txt_name.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("New Item");

        jLabel2.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabel2.setText("Add new Item");

        btn_save.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_save.setText("Add Ingredient");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_back.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_back.setText("Back");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Item Price:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Item Name:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Item Choice:");

        selectedFood.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        selectedFood.setText("Food");
        selectedFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedFoodActionPerformed(evt);
            }
        });

        selectedDrink.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        selectedDrink.setText("Drink");
        selectedDrink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedDrinkActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Develop By |  DeveloperZin");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(71, 71, 71)
                                    .addComponent(selectedFood, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(selectedDrink)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(245, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btn_back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectedDrink)
                    .addComponent(selectedFood)
                    .addComponent(jLabel5))
                .addGap(26, 26, 26)
                .addComponent(btn_save)
                .addGap(42, 42, 42)
                .addComponent(jLabel6)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(776, 509));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void txt_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_priceActionPerformed

    private void selectedFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedFoodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectedFoodActionPerformed

    private void selectedDrinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedDrinkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectedDrinkActionPerformed
    String getCategory() {
        if (selectedFood.isSelected()) {
            return "food";

        } else if (selectedDrink.isSelected()) {
            return "drink";
        } else {
            return null;
        }
    }
    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        String itemName = txt_name.getText().trim();
        String itemPrice = txt_price.getText().trim();
        String category = getCategory();
        if (itemName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the item name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double price;
        try {

            price = Double.parseDouble(itemPrice);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter the item price.(Input Number Only!!)", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
//        if (price == 0.00) {
//            JOptionPane.showMessageDialog(this, "Item price should not be 0", "Input Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
        if (price < 1.00) {
            JOptionPane.showMessageDialog(this, "Item price should be at least $1", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (price > 20) {
            JOptionPane.showMessageDialog(this, "Item price is too expensive.(Please Input below 20)", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

//        if (itemPrice.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Please enter the item price.", "Input Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        try {
//            Double.valueOf(itemPrice);
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Please enter a valid price.", "Input Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
        if (category == null) {
            JOptionPane.showMessageDialog(this, "Please Select Category!!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


//        DBAppHandler dbhadler = new DBAppHandler();
//        boolean add = dbhadler.add(category, itemName, price);
//        if (add) {
//            JOptionPane.showMessageDialog(this, itemName + " has added Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
//            txt_name.setText("");
//            txt_price.setText("");
//            selectedFood.setSelected(false);
//            selectedDrink.setSelected(false);
//        } else {
//
//            JOptionPane.showMessageDialog(this, "Failed to add item", "Insert Failed", JOptionPane.ERROR_MESSAGE);
//        }
        if (checkNameExist(itemName)) {
            JOptionPane.showMessageDialog(this, itemName + " is already Exit!!", "Duplicate menu", JOptionPane.ERROR_MESSAGE);

        } else {
            int id = GetMenuID();
            StoreItem.itemId = id;
            StoreItem.ItemName = itemName;
            StoreItem.ItemPrice = price;
            StoreItem.category = category;
            System.out.println(StoreItem.ItemName);
            System.out.println(StoreItem.ItemPrice);
            System.out.println(StoreItem.category);
//            System.out.println(StoreItem.itemId);
            AddIngredient.main(null);
            this.dispose();

        }


    }//GEN-LAST:event_btn_saveActionPerformed

//    get menu id 
    private int GetMenuID() {
        DBAppHandler dbhandler = new DBAppHandler();
        int lastInsertedId = 0;

        List<List<Object>> menuItems = dbhandler.getMenuItem();

        if (!menuItems.isEmpty()) {
            List<Object> lastRow = menuItems.get(menuItems.size() - 1);
            if (!lastRow.isEmpty()) {
                lastInsertedId = (int) lastRow.get(0);
            }
        }

        return lastInsertedId + 1;
    }
//  this function to check menu is exist??

    private boolean checkNameExist(String menuName) {
        DBAppHandler db = new DBAppHandler();
        List<List<Object>> menuItems = db.getMenuItem();
        for (List<Object> item : menuItems) {
//            System.out.println(item.get(1));
            if (!item.isEmpty() && item.get(1) != null && item.get(1) instanceof String) {
                String name = (String) item.get(1);
                if (name.equalsIgnoreCase(menuName)) {
                    return true;
                }
            }
        }
        return false;
    }


    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
        Dashboard.main(null);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Add_New_Item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Add_New_Item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Add_New_Item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_New_Item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Add_New_Item().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_save;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton selectedDrink;
    private javax.swing.JRadioButton selectedFood;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    // End of variables declaration//GEN-END:variables
}
