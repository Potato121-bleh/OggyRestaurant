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
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ahpka
 */
// Passed data:
// db.add("user_info", "id", "name", "price")
public class DBAppHandler {

//    String connectionString = "jdbc:postgresql://localhost:5432/oggyshop";
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

    public List<List<Object>> getTable() {
        Connection Conn = null;
        List<List<Object>> resultData = new ArrayList<>();
        try {
            Conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
            PreparedStatement pstm = Conn.prepareStatement("Select * from rsttable");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                row.add(rs.getInt("table_id"));
                row.add(rs.getString("table_name"));
                resultData.add(row);
            }

        } catch (SQLException e) {
            System.out.println("message" + e);
            return null;

        }
        return resultData;
    }

    public List<List<Object>> getIngre() {
        Connection Conn = null;
        List<List<Object>> resultData = new ArrayList<>();
        try {
            Conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
            PreparedStatement pstm = Conn.prepareStatement("Select * from ingredient_db");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                row.add(rs.getInt("ingredient_id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("total_unit"));
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

    public boolean add(String categoryName, String itemName, double itemPrice, List<Map<Integer, Integer>> menuIng) {
        System.out.println(itemName);

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

            PreparedStatement queryMenuIdPstmt = Conn.prepareStatement("SELECT item_id FROM menu_item WHERE item_name = ?");
//                        PreparedStatement queryMenuIdPstmt = Conn.prepareStatement("SELECT item_id FROM menu_item");
            queryMenuIdPstmt.setString(1, itemName);
            ResultSet queriedMenuId = queryMenuIdPstmt.executeQuery();
            int menuId = 0;
//            System.out.println(queriedMenuId.getInt("item_id"));
            while (queriedMenuId.next()) {
                System.out.println(queriedMenuId.getInt("item_id"));

                if (queriedMenuId.getInt("item_id") == 0) {
                    throw new Exception("menu failed to insert");
                }

//                     
//            while() {
                menuId = queriedMenuId.getInt("item_id");
//            };
            }

            // Construct the insert values
            String ingStmt = "INSERT INTO recipe_db ( menu_item_id , ingredient_id , request_unit ) VALUES";

//            [ {1=2}, {3=7} ]
//             Expected value: ( 1, 1, 2 )
            for (Map<Integer, Integer> map : menuIng) {
                for (Map.Entry<Integer, Integer> mapEntry : map.entrySet()) {
                    String prepStmt = " ( " + menuId + " , " + mapEntry.getKey() + " , " + mapEntry.getValue() + " ) ,";
                    ingStmt += prepStmt;
                }
            }

            System.out.println(ingStmt);

            String[] ingStmtArr = ingStmt.split(" ");
            System.out.println(Arrays.toString(ingStmtArr));

            String[] newIngStmtArr = Arrays.copyOf(ingStmtArr, ingStmtArr.length - 1);
            String newIngStmt = String.join(" ", newIngStmtArr);

            System.out.println(Arrays.toString(newIngStmtArr));

//          System.out.println("New ing stmt: ");
            System.out.println(newIngStmt);
//            newIngStmt = newIngStmt.replace("[", "");
//            newIngStmt = newIngStmt.replace("]", "");
//            
//            System.out.println(newIngStmt);

            PreparedStatement pstmtIng = Conn.prepareStatement(newIngStmt);
            int executeRecipeFlag = pstmtIng.executeUpdate();
            if (executeRecipeFlag != menuIng.size()) {
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

            //delete recipe
            PreparedStatement pstmRe = Conn.prepareStatement("DELETE FROM recipe_db WHERE menu_item_id = ?");
            pstmRe.setObject(1, itemId);
            pstmRe.executeUpdate();

            ///end delete recipe
            ////delete menu_items
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

    // original
    public boolean executeTransaction(int tbId, List<Map<Integer, Integer>> menuId) {
        Connection Conn = null;
        try {
            Conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
            Conn.setAutoCommit(false);
            System.out.println("Phrase 1");
            // The operation started work on individual menu
            for (int i = 0; i < menuId.size(); i++) {

                Map<Integer, Integer> selectedMenu = menuId.get(i);          // { menuId: 1, quantity: 2 }  | key: value 
                Map.Entry<Integer, Integer> selectedMenuEntry = selectedMenu.entrySet().iterator().next();
                List<Map<Integer, Integer>> recipeList = new ArrayList<Map<Integer, Integer>>();
                List<Map<Integer, Integer>> originIngredientList = new ArrayList<Map<Integer, Integer>>();
                System.out.println(menuId.get(i));
                System.out.println("Phrase 2");
                // get selectedMenu ingredient
//                menuId.get(menuMapIndex).entrySet().iterator().next();
                System.out.println(selectedMenuEntry.getKey());   /// null
                PreparedStatement PRecipeStmt = Conn.prepareStatement("SELECT * FROM recipe_db WHERE menu_item_id = ?");
                PRecipeStmt.setObject(1, selectedMenuEntry.getKey());
                ResultSet queriedRecipeRow = PRecipeStmt.executeQuery();
                System.out.println("Phrase 3");

//                Map<Integer, Integer> selectedMenu = menuId.get(i);          // { menuId: 1, quantity: 2 }  | key: value 
//                List<Map<Integer, Integer>> recipeList = new ArrayList<Map<Integer, Integer>>();
//                List<Map<Integer, Integer>> originIngredientList = new ArrayList<Map<Integer, Integer>>();
//                System.out.println(menuId.get(i));
//                        System.out.println("Phrase 2");
//                // get selectedMenu ingredient
//                System.out.println(selectedMenu.get("menuId"));   /// null
//                PreparedStatement PRecipeStmt = Conn.prepareStatement("SELECT * FROM recipe_db WHERE menu_item_id = ?");
//                PRecipeStmt.setObject(1, selectedMenu.get("menuId"));
//                ResultSet queriedRecipeRow = PRecipeStmt.executeQuery();
//                        System.out.println("Phrase 3");
                // create table recipe_db(
                // recipe_id Serial primary key, 
                // menu_item_id int references menu_item(item_id), 
                // ingredient_id int references ingredient_db(ingredient_id), 
                // request_unit int 
                // )
                while (queriedRecipeRow.next()) {
//                    recipeList.add(selectedMenu)

                    System.out.println("run loop");
                    Map<Integer, Integer> prepRecipe = new HashMap();
                    prepRecipe.put(
                            queriedRecipeRow.getInt("ingredient_id"),
                            queriedRecipeRow.getInt("request_unit")
                    );
//                    queriedRecipeRow.getInt("ingredient_id");
//                    queriedRecipeRow.getInt("request_unit");
                    recipeList.add(prepRecipe);
                }
                System.out.println("Phrase 4");
                // as now as we got the all required ingredient for our menu
                // we can retrieve the all required ingredient from original stock
                String queryIngredientStmt = "SELECT * FROM ingredient_db WHERE ingredient_id IN (";
                System.out.println(recipeList.size());
                for (int j = 0; j < recipeList.size(); j++) {

                    Map.Entry<Integer, Integer> entry = recipeList.get(j).entrySet().iterator().next();
                    queryIngredientStmt += " " + entry.getKey() + " ,";
                }
                System.out.println("Phrase zin");
                // SELECT * FROM ingredient_db WHERE ingredient_id IN (1, 5, 2, 6, 10)
                String cleanedQueriedIngStmt = queryIngredientStmt.substring(0, queryIngredientStmt.length() - 1);

                cleanedQueriedIngStmt += ")";

                System.out.println(cleanedQueriedIngStmt);

                PreparedStatement PIngreStmt = Conn.prepareStatement(cleanedQueriedIngStmt);
                ResultSet queriedOriginIngredientRow = PIngreStmt.executeQuery();
                System.out.println("Phrase 5");
                while (queriedOriginIngredientRow.next()) {
                    System.out.println("loop end");
                    Map<Integer, Integer> prepOriginIngredient = new HashMap();
                    prepOriginIngredient.put(
                            queriedOriginIngredientRow.getInt("ingredient_id"),
                            queriedOriginIngredientRow.getInt("total_unit")
                    );
                    originIngredientList.add(prepOriginIngredient);
                }
                System.out.println(originIngredientList.toString());

                //create table ingredient_db(
                // ingredient_id Serial primary key, 
                // name varchar(100), 
                // total_unit int
                // )
                // As we got data from both side we can start to do validation to confirm the purchase
                // before we start we have to make sure from all side they must have the same length of list
//                List<Map<Integer, Integer>> recipeList = new ArrayList<Map<Integer, Integer>>();
//                List<Map<Integer, Integer>> originIngredientList = new ArrayList<Map<Integer, Integer>>();
System.out.println("Phrase 6");
                if (recipeList.size() != originIngredientList.size()) {
                    throw new Exception("RecipeList & OriginIngredientList size does not match");
                }

                for (int recipeIndex = 0; recipeIndex < recipeList.size(); recipeIndex++) {
                    Map.Entry<Integer, Integer> cleanRecipeEle = recipeList.get(recipeIndex).entrySet().iterator().next();
                    for (int originIndex = 0; originIndex < originIngredientList.size(); originIndex++) {
                        Map.Entry<Integer, Integer> cleanOriginIngEle = originIngredientList.get(originIndex).entrySet().iterator().next();

                        if (cleanRecipeEle.getKey() == cleanOriginIngEle.getKey()) {
                            int totalRequiredUnit = cleanRecipeEle.getValue() * selectedMenuEntry.getValue();
                            if (totalRequiredUnit > cleanOriginIngEle.getValue()) {
                                throw new Exception("Not Enough Stock: Due to Ingredient id " + cleanRecipeEle.getKey());
                            }
                            // calculate the remaining stock
                            int remainingStock = cleanOriginIngEle.getValue() - totalRequiredUnit;
                            originIngredientList.get(originIndex).put(cleanRecipeEle.getKey(), remainingStock);
                            break;
                        }
                    }
                }
                // { menuId: 1, Quantity: 10}

                // SQL STATEMENT BUILDING PROCESS
                // As the ingredient is done, we have to update all of them to table
                String updateCalculatedIng = "UPDATE ingredient_db SET total_unit = CASE ";
                String dynamicIngId = "";
                // we have to dynamically build the update stmt
                for (int originIndex = 0; originIndex < originIngredientList.size(); originIndex++) {
                    Map.Entry<Integer, Integer> cleanOriginIngEle = originIngredientList.get(originIndex).entrySet().iterator().next();
                    updateCalculatedIng += " WHEN ingredient_id = " + cleanOriginIngEle.getKey() + " THEN " + cleanOriginIngEle.getValue();
                    dynamicIngId += " " + cleanOriginIngEle.getKey() + " ,";
                }

                // clean up the string as we done built it
                String cleanDynamicId  = dynamicIngId.substring(0, dynamicIngId.length() - 1);
                updateCalculatedIng += " END WHERE ingredient_id IN ( " + cleanDynamicId + " );";
                              System.out.println(updateCalculatedIng);

                PreparedStatement PUpdateIng = Conn.prepareStatement(updateCalculatedIng);
                int affectedRow = PUpdateIng.executeUpdate();
                if (affectedRow != originIngredientList.size()) {
                    throw new Exception("Unexpected Row Affected from database: Due to row affected not equal to size of origin arrayList");
                }
  

                // CREATE TABLE order_transaction (
                // transaction_id SERIAL PRIMARY KEY,
                // table_id INT REFERENCES rstTable(table_id) ON DELETE CASCADE,
                // item_id INT REFERENCES menu_item(item_id) ON DELETE CASCADE,
                // qty INT 
                // );
                //  UPDATE your_table
//                  SET column_name = CASE 
//                  WHEN id = 1 THEN 1
//                  WHEN id = 2 THEN 92
//                  WHEN id = 3 THEN 15
//                  WHEN id = 50 THEN 66
//                  -- Add more cases here
//                  END
//                  WHERE id IN (1, 2, 3, 50); -- List only the IDs that are being updated
//                  COMMIT;  
            }

            // As the validation & calculation of ingredient stock is done, 
            // we can working on insert new transaction into transaction table
            String PrepInsertTransactionStmt = "INSERT INTO order_transaction (table_id, item_id, qty) VALUES ";
            for (int menuMapIndex = 0; menuMapIndex < menuId.size(); menuMapIndex++) {
                PrepInsertTransactionStmt += " ( ";
                Map.Entry<Integer, Integer> selectedMapEntry = menuId.get(menuMapIndex).entrySet().iterator().next();
                PrepInsertTransactionStmt += tbId + ", "+ selectedMapEntry.getKey() + ", " + selectedMapEntry.getValue();
                PrepInsertTransactionStmt += " ) ,";
            }

            String cleanedPrepTransactionStmt = PrepInsertTransactionStmt.substring(0, PrepInsertTransactionStmt.length() - 1);  
            System.out.println(cleanedPrepTransactionStmt);
            PreparedStatement PInsertTransactionStmt = Conn.prepareStatement(cleanedPrepTransactionStmt);
            int InsertTxAffectedRow = PInsertTransactionStmt.executeUpdate();
            if (InsertTxAffectedRow != menuId.size()) {
                throw new Exception("Unexpected Row affected from inserting transaction");
            }

            System.out.println(cleanedPrepTransactionStmt);

            // AS everything has updated successfully done.
            // We can commit our transaction.
            Conn.commit();
            Conn.setAutoCommit(true);
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
            return false;
        } finally {
            try {
                if (Conn != null) {
                    Conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("Something went wrong, Could be closing connection failed");
                return false;
            }

        }
        return true;
    }

    // test method
//    public boolean executeTransaction(int tbId, List<Map<Integer, Integer>> menuId) {
//        Connection Conn = null;
//        try {
//            Conn = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
//            Conn.setAutoCommit(false);
//
//            // The operation started work on individual menu
//            for (int i = 0; i < menuId.size(); i++) {
//                Map<Integer, Integer> selectedMenu = menuId.get(i);          // { menuId: 1, quantity: 2 }  | key: value 
//                for (int p = 0; p < selectedMenu.get("quantity"); p++) {
//                    List<Map<Integer, Integer>> recipeList = new ArrayList<Map<Integer, Integer>>();
//                    List<Map<Integer, Integer>> originIngredientList = new ArrayList<Map<Integer, Integer>>();
//
//                    // get selectedMenu ingredient
//                    PreparedStatement PRecipeStmt = Conn.prepareStatement("SELECT * FROM recipe_db WHERE menu_item_id = ?");
//                    PRecipeStmt.setObject(1, selectedMenu.get("menuId"));
//                    ResultSet queriedRecipeRow = PRecipeStmt.executeQuery();
//
//                    // create table recipe_db(
//                    // recipe_id Serial primary key, 
//                    // menu_item_id int references menu_item(item_id), 
//                    // ingredient_id int references ingredient_db(ingredient_id), 
//                    // request_unit int 
//                    // )
//                    while (queriedRecipeRow.next()) {
////                    recipeList.add(selectedMenu)
//                        Map<Integer, Integer> prepRecipe = new HashMap();
//                        prepRecipe.put(
//                                queriedRecipeRow.getInt("ingredient_id"),
//                                queriedRecipeRow.getInt("request_unit")
//                        );
////                    queriedRecipeRow.getInt("ingredient_id");
////                    queriedRecipeRow.getInt("request_unit");
//                        recipeList.add(prepRecipe);
//                    }
//
//                    // as now as we got the all required ingredient for our menu
//                    // we can retrieve the all required ingredient from original stock
//                    String queryIngredientStmt = "SELECT * FROM ingredient_db WHERE ingredient_id IN (";
//                    for (int j = 0; j < recipeList.size(); j++) {
//                        Map.Entry<Integer, Integer> entry = recipeList.get(j).entrySet().iterator().next();
//                        queryIngredientStmt += " " + entry.getKey() + " ,";
//                    }
//                    // SELECT * FROM ingredient_db WHERE ingredient_id IN (1, 5, 2, 6, 10)
//                    String cleanedQueriedIngStmt = queryIngredientStmt.substring(0, queryIngredientStmt.length() - 1);
//                    cleanedQueriedIngStmt += ")";
//                    PreparedStatement PIngreStmt = Conn.prepareStatement(cleanedQueriedIngStmt);
//                    ResultSet queriedOriginIngredient = PIngreStmt.executeQuery();
//                    while (queriedOriginIngredient.next()) {
//                        Map<Integer, Integer> prepOriginIngredient = new HashMap();
//                        prepOriginIngredient.put(
//                                queriedRecipeRow.getInt("ingredient_id"),
//                                queriedRecipeRow.getInt("total_unit")
//                        );
//                        originIngredientList.add(prepOriginIngredient);
//                    }
//
//                    //create table ingredient_db(
//                    // ingredient_id Serial primary key, 
//                    // name varchar(100), 
//                    // total_unit int
//                    // )
//                    // As we got data from both side we can start to do validation to confirm the purchase
//                    // before we start we have to make sure from all side they must have the same length of list
////                List<Map<Integer, Integer>> recipeList = new ArrayList<Map<Integer, Integer>>();
////                List<Map<Integer, Integer>> originIngredientList = new ArrayList<Map<Integer, Integer>>();
//                    if (recipeList.size() != originIngredientList.size()) {
//                        throw new Exception("RecipeList & OriginIngredientList size does not match");
//                    }
//
//                    for (int recipeIndex = 0; recipeIndex < recipeList.size(); recipeIndex++) {
//                        Map.Entry<Integer, Integer> cleanRecipeEle = recipeList.get(recipeIndex).entrySet().iterator().next();
//                        for (int originIndex = 0; originIndex < originIngredientList.size(); originIndex++) {
//                            Map.Entry<Integer, Integer> cleanOriginIngEle = originIngredientList.get(originIndex).entrySet().iterator().next();
//
//                            if (cleanRecipeEle.getKey() == cleanOriginIngEle.getKey()) {
//                                if (cleanRecipeEle.getValue() > cleanOriginIngEle.getValue()) {
//                                    throw new Exception("Not Enough Stock: Due to Ingredient id " + cleanRecipeEle.getKey());
//                                }
//                                // calculate the remaining stock
//                                int remainingStock = cleanOriginIngEle.getValue() - cleanRecipeEle.getValue();
//                                originIngredientList.get(originIndex).put(cleanRecipeEle.getKey(), remainingStock);
//                                break;
//                            }
//                        }
//                    }
//                    // { menuId: 1, Quantity: 10}
//
//                    // SQL STATEMENT BUILDING PROCESS
//                    // As the ingredient is done, we have to update all of them to table
//                    String updateCalculatedIng = "UPDATE ingredient_db SET total_unit = CASE ";
//                    String dynamicIngId = "";
//                    // we have to dynamically build the update stmt
//                    for (int originIndex = 0; originIndex < originIngredientList.size(); originIndex++) {
//                        Map.Entry<Integer, Integer> cleanOriginIngEle = originIngredientList.get(originIndex).entrySet().iterator().next();
//                        updateCalculatedIng += " WHEN ingredient_id = " + cleanOriginIngEle.getKey() + " THEN " + cleanOriginIngEle.getValue();
//                        dynamicIngId += " " + cleanOriginIngEle.getKey() + " ,";
//                    }
//
//                    // clean up the string as we done built it
//                    dynamicIngId.substring(0, dynamicIngId.length() - 1);
//                    updateCalculatedIng += " END WHERE ingredient_id IN ( " + dynamicIngId + " );";
//
//                    PreparedStatement PUpdateIng = Conn.prepareStatement(updateCalculatedIng);
//                    int affectedRow = PUpdateIng.executeUpdate();
//                    if (affectedRow != originIngredientList.size()) {
//                        throw new Exception("Unexpected Row Affected from database: Due to row affected not equal to size of origin arrayList");
//                    }
//
//                    //  UPDATE your_table
////                  SET column_name = CASE 
////                  WHEN id = 1 THEN 1
////                  WHEN id = 2 THEN 92
////                  WHEN id = 3 THEN 15
////                  WHEN id = 50 THEN 66
////                  -- Add more cases here
////                  END
////                  WHERE id IN (1, 2, 3, 50); -- List only the IDs that are being updated
////                  COMMIT;  
//                }
//            }
//
//            // As the validation & calculation of ingredient stock is done, 
//            // we can working on insert new transaction into transaction table
//            String PrepInsertTransaction = "INSERT INTO order_transaction (table_id, item_id, qty) VALUES ";
//            
////            CREATE TABLE order_transaction (
////          transaction_id SERIAL PRIMARY KEY,
////          table_id INT REFERENCES rstTable(table_id) ON DELETE CASCADE,
////          item_id INT REFERENCES menu_item(item_id) ON DELETE CASCADE,
////          qty INT 
////          );
//    
//
//            // AS everything has updated successfully done.
//            // We can commit our transaction.
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
//            System.out.println("!!!!!!!!!!!!!!!!!!!! ERROR !!!!!!!!!!!!!!!!!!!!");
//            System.out.println("failed to perform Delete transasction");
//        } finally {
//            try {
//                if (Conn != null) {
//                    Conn.close();
//                }
//            } catch (SQLException e) {
//                System.out.println(e);
//                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                System.out.println("Something went wrong, Could be closing connection failed");
//            }
//
//        }
//        return true;
//    }
    private void While(boolean next) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
