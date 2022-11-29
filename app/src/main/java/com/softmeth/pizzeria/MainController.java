package com.softmeth.pizzeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * The Controller class part of the MVC design pattern. This will
 * handle all the button on click events and will perform the necessary
 * checks to determine which methods should be called. Upon determination,
 * it will call the corresponding model to create/alter that data
 *
 * @author Peter Chen, Jonathon Lopez
 */
public class MainController {

    /**
     * Event Handler to open Chicago Order Screen
     * @param e the action event that triggered the method
     */
    @FXML
    void orderChicagoPizza(ActionEvent e){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("ChicagoView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.setTitle("Order Chicago Style Pizza");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }catch(Exception ignored){
        }
    }

    /**
     * Event Handler to open New York Order Screen
     * @param e the action event that triggered the method
     */
    @FXML
    void orderNYPizza(ActionEvent e){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("NYView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.setTitle("Order New York Style Pizza");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        }catch(Exception ignored){
        }
    }

    /**
     * Event Handler to open Current Order Screen
     * @param e the action event that triggered the method
     */
    @FXML
    void viewCurrentOrder(ActionEvent e){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("CurrentOrderView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("View Current Order");
            stage.setScene(scene);
            stage.show();
        }catch(Exception ignored){
        }

    }

    /**
     * Event Handler to open All Store Order Screen
     * @param e the action event that triggered the method
     */
    @FXML
    void viewStoreOrders(ActionEvent e){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("StoreOrderView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("View ALl Store Orders");
            stage.setScene(scene);
            stage.show();
        }catch(Exception ignored){
        }
    }
}
