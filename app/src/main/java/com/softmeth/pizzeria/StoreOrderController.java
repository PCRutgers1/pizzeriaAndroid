package com.softmeth.pizzeria;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * The Controller class part of the MVC design pattern. This will
 * handle all the button on click events and will perform the necessary
 * checks to determine which methods should be called. Upon determination,
 * it will call the corresponding model to create/alter that data
 *
 * @author Peter Chen, Jonathon Lopez
 */
public class StoreOrderController {
    @FXML
    private ComboBox<Integer> OrderNumber;
    @FXML
    private ListView<String> StoreOrderList;

    @FXML
    private Text OrderTotalStore, outputStoreOrders;
    private Order selectedOrder;

    /**
     * the method that gets called on initialization of the GUI interface
     */
    public void initialize() {
        setComboboxValues();
    }


    /**
     * sets the values for the combo box dropdown list with IDs
     */
    private void setComboboxValues() {
        ArrayList<Integer> AllOrderNums = new ArrayList<>();

        for (Order o : StoreOrder.allOrders) {
            AllOrderNums.add(o.getOrderId());
        }

        OrderNumber.setItems(FXCollections.observableList(AllOrderNums));
    }

    /**
     * The method that gets called to update the interface when a new
     * order is gets selected
     *
     * @param e action event that triggered the method
     */
    @FXML
    public void OrderNumChange(ActionEvent e) {
        if (OrderNumber.getValue() != null)
            updateListView(OrderNumber.getValue());
    }

    /**
     * updates the list view in the GUI with the new items for the current ID
     *
     * @param orderValue the order ID value that is selected
     */
    private void updateListView(int orderValue) {
        ArrayList<String> newOrderList = new ArrayList<>();
        for (Order o : StoreOrder.allOrders) {
            if (o.getOrderId() == orderValue) {
                selectedOrder = o;
                break;
            }
        }

        for (Pizza p : selectedOrder.getCurrentOrderPizzas()) {
            newOrderList.add(p.toString());
        }

        StoreOrderList.setItems(FXCollections.observableList(newOrderList));
        OrderTotalStore.setText(String.format("%.2f", selectedOrder.getOrderTotal()));
    }

    /**
     * The method that handles when an order gets cnaceled and takes it out of the database
     *
     * @param e the action event that triggered the method
     */
    @FXML
    private void cancelOrder(ActionEvent e) {
        if (OrderNumber.getValue() == null)
            return;
        int orderToDelete = OrderNumber.getValue();
        for (Order n : StoreOrder.allOrders) {
            if (n.getOrderId() == orderToDelete) {
                StoreOrder.allOrders.remove(n);
                break;
            }
        }
        OrderNumber.getSelectionModel().selectFirst();
        setComboboxValues();
        StoreOrderList.setItems(FXCollections.observableList(new ArrayList<>()));
        OrderTotalStore.setText("0.00");
        outputStoreOrders.setText("Successfully Canceled Order");

    }

    /**
     * This method exports all of the orders with their IDs and pizzas to the text file
     * named storeorders.txt in the root directory
     */
    @FXML
    private void exportList() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("StoreOrders.txt"), StandardCharsets.UTF_8))) {
            for (Order ord : StoreOrder.allOrders) {
                writer.write("Order ID of " + ord.getOrderId() + "\n");
                for (Pizza p : ord.getCurrentOrderPizzas()) {
                    writer.write(p.toString() + "\n");
                }
                writer.write("Order Total With Tax: $" + ord.getOrderTotal() + "\n");
                outputStoreOrders.setText("Successfully Exported List");
            }
        } catch (Exception e) {

        }
    }

}