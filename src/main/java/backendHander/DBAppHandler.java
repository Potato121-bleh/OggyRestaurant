/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendHander;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahpka
 */
// Passed data:
// db.add("user_info", "id", "name", "price")
public class DBAppHandler {
//    String connectionString = "jdbc:postgresql://localhost:5432/java_restaurant_project";
//    String dbUsername = "postgres";
//    String dbPassword = "nice123";

    String connectionString = "jdbc:postgresql://localhost:5432/OggyShop";
    String dbUsername = "postgres";
    String dbPassword = "123";

    public boolean validateUserCrediential(String inputUsername, String inputPassword) {
        Connection Conn = null;
        try {
            Conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
            System.out.println("Connection ............ successfully connected");
            // Create ourselve a statement first, java do this is because to prvent sql injection using with prepareStatement
            PreparedStatement pstmt = Conn.prepareStatement("SELECT * FROM user_auth WHERE user_name = ? ");
            pstmt.setObject(1, inputUsername);
            ResultSet rs = pstmt.executeQuery();
            String userPassword = "";
            while (rs.next()) {
                userPassword = rs.getString("user_password");
            }
            if (!userPassword.equals(inputPassword)) {
                throw new Exception("failed to validate the user, Please input the correct password");
            }
            return true;

        } catch (Exception e) {
            System.out.println("Something went wrong, Please make sure the crediential is correct");
            System.out.println(e);
            return false;
        } finally {
            System.out.println("It reach finally section");
            if (Conn != null) {
                try {
                    Conn.close();
                    System.out.println("Connection successfully Closed ... ok");
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
    }

    // Dataset = { {1, "item_name", "item_price"}, {} }
    public List<List<Object>> queryMenuItem(String categoryName) {
        if (!categoryName.equals("food") || !categoryName.equals("drink")) {
            System.out.println("Please make sure the table is correct, food_menu & drink_menu");
            return null;
        }
        Connection Conn = null;
        List<List<Object>> resultData = new ArrayList<>();
        try {
            Conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
            PreparedStatement pstmt = Conn.prepareStatement("SELECT * FROM menu_item WHERE item_category = ?");
            pstmt.setObject(1, categoryName);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                List<Object> prepList = new ArrayList<>();
                prepList.add(rs.getInt("item_id"));
                prepList.add(rs.getString("item_name"));
                prepList.add(rs.getDouble("item_price"));
                resultData.add(prepList);
            }
        } catch (SQLException e) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(e);
            return null;
        } finally {
            try {
                if (Conn != null) {
                    Conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Database connection failed to close");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println(e);
                return null;
            }
        }
        return resultData;
    }
//  Fix GetMneu Item in Jtble

    public List<List<Object>> getMenuItem() {
        Connection Conn = null;
        List<List<Object>> resultData = new ArrayList<>();
        try {
            Conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
            PreparedStatement pstm = Conn.prepareStatement("Select * from menu_item");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                row.add(rs.getInt("item_id"));
                row.add(rs.getString("item_name"));
                row.add(rs.getString("item_price"));
                row.add(rs.getString("item_category"));
                resultData.add(row);

            }

        } catch (SQLException e) {
            System.out.println("message" + e);
            return null;

        }
        return resultData;
    }
//new 

    public List<List<Object>> queryMenuItemInUpdatePage(int item_id) {
//        if (!item_name.equals("food") || !item_name.equals("drink")) {
//            System.out.println("Please make sure the table is correct, food_menu & drink_menu");
//            return null;
//        }
        Connection Conn = null;
        List<List<Object>> resultData = new ArrayList<>();
        try {
            Conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
            PreparedStatement pstmt = Conn.prepareStatement("SELECT * FROM menu_item WHERE item_id = ?");
            pstmt.setObject(1, item_id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                List<Object> prepList = new ArrayList<>();
                prepList.add(rs.getInt("item_id"));
                prepList.add(rs.getString("item_name"));
                prepList.add(rs.getDouble("item_price"));
                prepList.add(rs.getString("item_category"));
                resultData.add(prepList);
            }
        } catch (SQLException e) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println(e);
            return null;
        } finally {
            try {
                if (Conn != null) {
                    Conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Database connection failed to close");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println(e);
                return null;
            }
        }
        return resultData;
    }

    public boolean add(String categoryName, String itemName, double itemPrice) {
        Connection Conn = null;
        try {
            Conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
            Conn.setAutoCommit(false);
            PreparedStatement pstmt = Conn.prepareStatement(""
                    + "INSERT INTO menu_item "
                    + "(item_name, item_price, item_category) "
                    + "VALUES (?, ?, ?)"
            );
            pstmt.setObject(1, itemName);
            pstmt.setObject(2, itemPrice);
            pstmt.setObject(3, categoryName);
            int affectedRow = pstmt.executeUpdate();
            if (affectedRow != 1) {
                throw new Exception("unexpected behavior from database");
            }
            Conn.commit();
            return true;
        } catch (Exception e) {
            try {
                if (Conn != null) {
                    Conn.rollback();
                }
            } catch (SQLException rollbackErr) {
                System.out.println(rollbackErr);
                System.out.println("!!!!!!!!!!!!!!!!!!!! ERROR !!!!!!!!!!!!!!!!!!!!");
                System.out.println("Something went wrong, Could be rollback failed");
            }
            System.out.println(e);
            System.out.println("!!!!!!!!!!!!!!!!!!!! ERROR !!!!!!!!!!!!!!!!!!!!");
        } finally {
            if (Conn != null) {
                try {
                    Conn.setAutoCommit(true);
                    Conn.close();
                } catch (SQLException e) {
                    System.out.println("!!!!!!!!!!!!!!!!!!!! ERROR !!!!!!!!!!!!!!!!!!!!");
                    System.out.println(e);
                    System.out.println("Something went wrong, Could be closing connection failed");
                }

            }
        }
        return true;
    }

//    public boolean update(String categoryName, String itemName, double itemPrice, int itemId) {
//        Connection Conn = null;
//        try {
//            Conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
//            Conn.setAutoCommit(false);
//            // Retrieve data for the comparation
//            PreparedStatement queryPstmt = Conn.prepareStatement("SELECT * FROM menu_item WHERE item_id = ?");
//            queryPstmt.setObject(1, itemId);
//            System.out.println(queryPstmt);
//            ResultSet queryRS = queryPstmt.executeQuery();
//            System.out.println(queryRS);
//            queryRS.next();
//
//            categoryName = (categoryName.equals("")) ? (queryRS.getString("item_category")) : (categoryName);
//            itemName = (itemName.equals("")) ? (queryRS.getString("item_name")) : (itemName);
//            itemPrice = (itemPrice == 0.00) ? (queryRS.getDouble("item_price")) : (itemPrice);
//
//            PreparedStatement updatePstmt = Conn.prepareStatement(
//                    "UPDATE menu_item SET "
//                    + "item_name = ? ,"
//                    + "item_price = ? ,"
//                    + "item_category = ?"
//            );
//
//            updatePstmt.setObject(1, itemName);
//            updatePstmt.setObject(2, itemPrice);
//            updatePstmt.setObject(3, categoryName);
//            int updateAffectedRow = updatePstmt.executeUpdate();
//            if (updateAffectedRow != 1) {
//                throw new Exception("unexpected behavior from database");
//            }
//
//            Conn.commit();
//            return true;
//        } catch (Exception e) {
//            try {
//                if (Conn != null) {
//                    Conn.rollback();
//                }
//            } catch (SQLException rollbackErr) {
//                System.out.println(rollbackErr);
//                System.out.println("!!!!!!!!!!!!!!!!!!!! ERROR !!!!!!!!!!!!!!!!!!!!");
//                System.out.println("Something went wrong, Could be rollback failed");
//            }
//            System.out.println(e);
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            System.out.println("failed to perform update transaction");
//            return false;
//        } finally {
//            if (Conn != null) {
//                try {
//                    Conn.setAutoCommit(true);
//                    Conn.close();
//                } catch (SQLException e) {
//                    System.out.println(e);
//                    System.out.println("failed to perform update transaction");
//                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                }
//            }
//        }
//    }
    public boolean update(String categoryName, String itemName, double itemPrice, int itemId) {
        Connection Conn = null;
        try {
            Conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
            Conn.setAutoCommit(false);

            PreparedStatement queryPstmt = Conn.prepareStatement("SELECT * FROM menu_item WHERE item_id = ? ");
            queryPstmt.setObject(1, itemId);
            ResultSet queryRS = queryPstmt.executeQuery();

            if (queryRS.next()) {

                categoryName = (categoryName.equals("")) ? (queryRS.getString("item_category")) : (categoryName);
                itemName = (itemName.equals("")) ? (queryRS.getString("item_name")) : (itemName);
                itemPrice = (itemPrice == 0.00) ? (queryRS.getDouble("item_price")) : (itemPrice);

                PreparedStatement updatePstmt = Conn.prepareStatement(
                        "UPDATE menu_item SET item_name = ?, item_price = ?, item_category = ? WHERE item_id = ?");

                updatePstmt.setObject(1, itemName);
                updatePstmt.setObject(2, itemPrice);
                updatePstmt.setObject(3, categoryName);
                updatePstmt.setObject(4, itemId);

                int updateAffectedRow = updatePstmt.executeUpdate();
                if (updateAffectedRow != 1) {
                    throw new Exception("Unexpected behavior from database during update");
                }
                Conn.commit();
                return true;
            } else {
                throw new Exception("No item found with the specified item_id");
            }

        } catch (Exception e) {
            try {
                if (Conn != null) {
                    Conn.rollback();
                }
            } catch (SQLException rollbackErr) {
                System.out.println(rollbackErr);
                System.out.println("!!!!!!!!!!!!!!!!!!!! ERROR !!!!!!!!!!!!!!!!!!!!");
                System.out.println("Something went wrong, Could be rollback failed");
            }
            System.out.println(e);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("failed to perform update transaction");
            return false;
        } finally {
            if (Conn != null) {
                try {
                    Conn.setAutoCommit(true);
                    Conn.close();
                } catch (SQLException e) {
                    System.out.println(e);
                    System.out.println("failed to perform update transaction");
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }
            }
        }
    }

    public boolean delete(int itemId) {
        Connection Conn = null;
        try {
            Conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
            Conn.setAutoCommit(false);
            PreparedStatement pstmt = Conn.prepareStatement("DELETE FROM menu_item WHERE item_id = ?");
            pstmt.setObject(1, itemId);
            int deleteAffectedRow = pstmt.executeUpdate();
            if (deleteAffectedRow != 1) {
                throw new Exception("unexpected behavior from database");
            }
            Conn.commit();
            return true;
        } catch (Exception e) {
            try {
                if (Conn != null) {
                    Conn.rollback();
                }
            } catch (SQLException rollbackErr) {
                System.out.println(rollbackErr);
                System.out.println("!!!!!!!!!!!!!!!!!!!! ERROR !!!!!!!!!!!!!!!!!!!!");
                System.out.println("Something went wrong, Could be rollback failed");
            }
            System.out.println(e);
            System.out.println("!!!!!!!!!!!!!!!!!!!! ERROR !!!!!!!!!!!!!!!!!!!!");
            System.out.println("failed to perform Delete transasction");
        } finally {
            try {
                if (Conn != null) {
                    Conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("Something went wrong, Could be closing connection failed");
            }

        }
        return true;
    }

    private void While(boolean next) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
