/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIpages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class StoreItem {

    public static int itemId;
    public static String ItemName;
    public static double ItemPrice;
    public static String category;
//    ingridiients    
//    public static int ingreId;
//    public static int unit;
    public static ArrayList<Map<Integer, Integer>> ingredientsList = new ArrayList<>();


    public static void addIngredient(int ingreId, int unit) {
        Map<Integer, Integer> ingredient = new HashMap<>();
        ingredient.put(ingreId, unit);
        ingredientsList.add(ingredient);
        System.out.println("Added ingredient: ID = " + ingreId + ", Unit = " + unit);
    }

    public static ArrayList<Map<Integer, Integer>> getIngredients() {
        return ingredientsList;
    }
    public static void clearIngredients() {
        ingredientsList.clear();
    }

}
