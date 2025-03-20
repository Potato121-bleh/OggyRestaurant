package GUIpages;

import backendHander.DBAppHandler;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.*;

public class Order_menu {

    private JFrame frame;
    private JPanel menuPanel, cartPanel, cartItemsPanel;
    private JLabel totalLabel;
    private Map<String, Integer> cartItems;
    private Map<String, Double> itemPrices;
    private JComboBox<String> comboBox;

    public Order_menu() {
        frame = new JFrame("Oggy Restaurant");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(1, 2));

        cartItems = new HashMap<>();
        itemPrices = new HashMap<>();

        // Menu Panel (LEFT)
//        JLabel Title = new JLabel("Menu", SwingConstants.CENTER); 
//        menuPanel = new JPanel(new GridLayout(0, 1, 10, 10));
//        menuPanel.add(Title);
//        JScrollPane menuScrollPane = new JScrollPane(menuPanel);
        ///new 
        JPanel menuContainer = new JPanel(new BorderLayout());
        JLabel Title = new JLabel("️ Menu", SwingConstants.CENTER);
        Title.setFont(new Font("Arial", Font.BOLD, 20)); //font
        Title.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add spacing

        ///row , col 
        menuPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        JScrollPane menuScrollPane = new JScrollPane(menuPanel);
        menuScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 0));
        menuContainer.add(Title, BorderLayout.NORTH);

        menuContainer.add(menuScrollPane, BorderLayout.CENTER);
        // Cart Panel (RIGHT)
        /// combobox
        JPanel comboBoxPanel = new JPanel();
        JLabel comboBoxLabel = new JLabel("Choose Table:");
        comboBox = new JComboBox<>();
        comboBoxPanel.add(comboBoxLabel);
        comboBoxPanel.add(comboBox);
        //end combobox

        cartPanel = new JPanel(new BorderLayout());
        cartItemsPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JScrollPane cartScrollPane = new JScrollPane(cartItemsPanel);
        totalLabel = new JLabel("Total: $0.00", SwingConstants.CENTER);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> checkout());
        checkoutButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JLabel cartLabel = new JLabel("🛒 Your Cart", SwingConstants.CENTER);
        cartLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        cartPanel.add(cartLabel, BorderLayout.NORTH);
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new GridLayout(3, 1));
        bottomPanel.add(comboBoxPanel);
        bottomPanel.add(totalLabel);
        bottomPanel.add(checkoutButton);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Add spacing for panel
        cartPanel.add(bottomPanel, BorderLayout.SOUTH);

        ///
        frame.add(menuContainer);
        frame.add(cartPanel);

        DisplayItemNameComboBox();
        loadMenuItems();
        frame.setVisible(true);
    }

    private void loadMenuItems() {
        DBAppHandler db = new DBAppHandler();
        java.util.List<java.util.List<Object>> menuItems = db.getMenuItem();
        if (menuItems == null) {
            JOptionPane.showMessageDialog(frame, "Error loading menu!", "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        menuPanel.removeAll();

        for (java.util.List<Object> item : menuItems) {
            String itemName = (String) item.get(1);
            double itemPrice = Double.parseDouble((String) item.get(2));
            itemPrices.put(itemName, itemPrice);

            String formattedPrice = String.format("%.2f", itemPrice);

            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel nameLabel = new JLabel(itemName + " - $" + formattedPrice);
            JButton addButton = new JButton("Buy");

            addButton.addActionListener(e -> addToCart(itemName));

            itemPanel.add(nameLabel, BorderLayout.CENTER);
            itemPanel.add(addButton, BorderLayout.EAST);

            menuPanel.add(itemPanel);
        }

        menuPanel.revalidate();
        menuPanel.repaint();
    }

    private void addToCart(String item) {
        cartItems.put(item, cartItems.getOrDefault(item, 0) + 1);
        updateCartDisplay();
    }

    private void removeFromCart(String item) {
        if (cartItems.containsKey(item)) {
            int quantity = cartItems.get(item);
            if (quantity > 1) {
                cartItems.put(item, quantity - 1);
            } else {
                cartItems.remove(item);
            }
        }
        updateCartDisplay();
    }

    private void updateCartDisplay() {
        cartItemsPanel.removeAll();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
//        double total = 0.0;
        BigDecimal total = BigDecimal.ZERO;

        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            
//            double price = itemPrices.get(itemName) * quantity;
//        String formattedPrice = String.format("%.2f", price);

            BigDecimal price = BigDecimal.valueOf(itemPrices.get(itemName))
                    .multiply(BigDecimal.valueOf(quantity))
                    .setScale(2, RoundingMode.HALF_UP);

            JPanel cartItemPanel = new JPanel(new BorderLayout());
            cartItemPanel.setMaximumSize(new Dimension(300, 40));
            JLabel cartItemLabel = new JLabel(itemName + " x" + quantity + " - $" + price);
            JButton removeButton = new JButton("Remove");

            removeButton.addActionListener(e -> removeFromCart(itemName));
            //(top, left, bottom, right)
            cartItemPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
            cartItemPanel.add(cartItemLabel, BorderLayout.WEST);
            cartItemPanel.add(removeButton, BorderLayout.EAST);
            cartItemsPanel.add(cartItemPanel);
//            total += price;
            total = total.add(price);

        }
        // totalLabel.setText("Total: $" + String.format("%.2f", total)); 
        totalLabel.setText("Total: $" + total.setScale(2, RoundingMode.HALF_UP));
        cartItemsPanel.revalidate();   ////use to tell Layoutmanager to update
        cartItemsPanel.repaint(); /// use to redraw the panel

        ///so what happen if we don't use 2 methods above : 
        //The layout won't update immediately, so new components might not appear, and removed components might still be visible.
    }

    private void checkout() {
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Your cart is empty!", "Checkout Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

//        int tableId = 1;
        String selected = (String) comboBox.getSelectedItem();
        if(selected == null || selected.isEmpty()){
            JOptionPane.showMessageDialog(frame,"Please Choose table before checkout!!", "Table not selected", JOptionPane.WARNING_MESSAGE);
        }
        String[] id_name = selected.split(" - ");
        int tableId = Integer.parseInt(id_name[0]);   
        String tableName = id_name[1];
        System.out.println(tableId + tableName);

        ArrayList<Map<Integer, Integer>> orderList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();
            int menuId = getItemId(itemName);

            System.out.println(itemName);
            System.out.println(quantity);

            Map<Integer, Integer> orderItem = new HashMap<>();
//            orderItem.put("menuId",menuId);
//            orderItem.put("quantity", quantity);
            orderItem.put(menuId, quantity);
            orderList.add(orderItem);
        }
        DBAppHandler dbHandler = new DBAppHandler();
        boolean transactionSuccess = dbHandler.executeTransaction(tableId, orderList);

        if (transactionSuccess) {
            JOptionPane.showMessageDialog(frame, "Order Placed Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            cartItems.clear();
            updateCartDisplay();
        } else {
            JOptionPane.showMessageDialog(frame, "Transaction Failed! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getItemId(String itemName) {
        DBAppHandler db = new DBAppHandler();
        java.util.List<java.util.List<Object>> menuItems = db.getMenuItem();
        for (java.util.List<Object> item : menuItems) {
            if (item.get(1).equals(itemName)) {
                return (int) item.get(0);
            }
        }
        return -1;
    }

    private void DisplayItemNameComboBox() {

        DBAppHandler dbHandler = new DBAppHandler();
        java.util.List<java.util.List<Object>> table = dbHandler.getTable();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for (java.util.List<Object> row : table) {
            int itemId = (int) row.get(0);
            String itemName = (String) row.get(1);
            comboBoxModel.addElement(itemId + " - " + itemName);
        }
        comboBox.setModel(comboBoxModel);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Order_menu::new);

    }
}
