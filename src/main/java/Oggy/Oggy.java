/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Oggy;

import GUIpages.Login;
import GUIpages.ManageRes;
import backendHander.DBAppHandler;
import navigation.controlPanel;

/**
 *
 * @author ahpka
 */

public class Oggy {
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        DBAppHandler authentication = new DBAppHandler();
        System.out.println(authentication.validateUserCrediential("potato-bleh", "kilozin123"));
        System.out.println(authentication.queryMenuItem("food_menu"));
//        Login s = new Login();
        ManageRes s = new ManageRes();

        s.setVisible(true);
    }  
}
