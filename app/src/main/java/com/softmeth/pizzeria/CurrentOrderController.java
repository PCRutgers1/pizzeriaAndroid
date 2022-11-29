package com.softmeth.pizzeria;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.AccessibleAction;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * The Controller class part of the MVC design pattern. This will
 * handle all the button on click events and will perform the necessary
 * checks to determine which methods should be called. Upon determination
 * it will call the corresponding model to create/alter that data
 *
 * @author Peter Chen, Jonathon Lopez
 */
public class CurrentOrderController {
    @FXML
    private ListView<String> ListOfAllCurrentOrders;

    @FXML
    private Text subtotal, salesTax, orderTotal, OrderOutput;
    private final static double TAX_RATE = 0.06625;
    private static int Empty = 0;

    /**
     * Event Handler for Placing the entire order
     * @param e the action event that triggered the method
     */
    @FXML
    private void placeOrder(ActionEvent e) {
        if (Order.currentOrder.size() == Empty)
            OrderOutput.setText("No orders to place");
        else {
            String total = orderTotal.getText();
            StoreOrder.allOrders.add(new Order(Double.parseDouble(total)));
            Order.currentOrder.clear();
            clearOrder(e);
            OrderOutput.setText("Order Place Successfully");
        }
    }

    /**
     * It loads all the Pizza into a list view
     */
    public void initialize() {
        showAllCurrentOrders();
    }

    /**
     * this shows all the current orders in the GUI interface
     */
    private void showAllCurrentOrders() {
        ArrayList<String> myCurrentOrder = new ArrayList<>();
        double totalPrice = 0;
        for (Pizza p : Order.currentOrder) {
            myCurrentOrder.add(p.toString());
            totalPrice += p.price();
        }
        double tax = totalPrice * TAX_RATE;
        subtotal.setText(String.format("%.2f", totalPrice));
        salesTax.setText(String.format("%.2f", tax));
        orderTotal.setText(String.format("%.2f", totalPrice + tax));
        ListOfAllCurrentOrders.setItems(
                FXCollections.observableList(myCurrentOrder));
    }

    /**
     * Event Handler for Clears all items in current order
     * @param e the action event that triggered the method
     */
    @FXML
    private void clearOrder(ActionEvent e) {
        Order.currentOrder.clear();
        ListOfAllCurrentOrders.setItems(
                FXCollections.observableList(new ArrayList<>()));
        subtotal.setText("0");
        salesTax.setText("0");
        orderTotal.setText("0");
    }

    /**
     * removes the pizza from the current order
     * @param e the action event that triggered the method
     */
    @FXML
    private void removePizza(ActionEvent e) {
        String pizzaToDelete = ListOfAllCurrentOrders.getSelectionModel().getSelectedItem();

        for (Pizza p : Order.currentOrder) {
            if (p.toString().equals(pizzaToDelete)) {
                Order.currentOrder.remove(p);
                showAllCurrentOrders();
                return;
            }
        }
    }

}
