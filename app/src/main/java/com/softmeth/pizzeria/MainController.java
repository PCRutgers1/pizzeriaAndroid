package com.softmeth.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * The Controller class part of the MVC design pattern. This will
 * handle all the button on click events and will perform the necessary
 * checks to determine which methods should be called. Upon determination,
 * it will call the corresponding model to create/alter that data
 *
 * @author Peter Chen, Jonathon Lopez
 */
public class MainController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
    }

    /**
     * Event Handler to open Chicago Order Screen
     *
     * @param v the action event that triggered the method
     */
    public void orderChicagoPizza(View v) {
        Intent newActivity = new Intent(this,ChicagoPizzaController.class);
        startActivity(newActivity);
        Toast.makeText(MainController.this, "1", Toast.LENGTH_LONG).show();
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("ChicagoView.fxml"));
//            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
//            Stage stage = new Stage();
//            stage.setTitle("Order Chicago Style Pizza");
//            stage.setResizable(false);
//            stage.setScene(scene);
//            stage.show();
//        }catch(Exception ignored){
//        }
    }


    /**
     * Event Handler to open New York Order Screen
     *
     * @param v the action event that triggered the method
     */
    public void orderNYPizza(View v) {
        Intent newActivity = new Intent(this,NYPizzaController.class);
        startActivity(newActivity);
        Toast.makeText(MainController.this, "222", Toast.LENGTH_LONG).show();

//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("NYView.fxml"));
//            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
//            Stage stage = new Stage();
//            stage.setTitle("Order New York Style Pizza");
//            stage.setResizable(false);
//            stage.setScene(scene);
//            stage.show();
//        }catch(Exception ignored){
//        }
    }

    /**
     * Event Handler to open Current Order Screen
     *
     * @param v the action event that triggered the method
     */
    public void viewCurrentOrder(View v) {
        Intent newActivity = new Intent(this,CurrentOrderController.class);
        startActivity(newActivity);
        Toast.makeText(MainController.this, "333", Toast.LENGTH_LONG).show();

//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("CurrentOrderView.fxml"));
//            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
//            Stage stage = new Stage();
//            stage.setResizable(false);
//            stage.setTitle("View Current Order");
//            stage.setScene(scene);
//            stage.show();
//        }catch(Exception ignored){
//        }

    }

    /**
     * Event Handler to open All Store Order Screen
     *
     * @param v the action event that triggered the method
     */
    public void viewStoreOrders(View v) {
        Intent newActivity = new Intent(this,StoreOrderController.class);
        startActivity(newActivity);
        Toast.makeText(MainController.this, "333", Toast.LENGTH_LONG).show();

//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("StoreOrderView.fxml"));
//            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
//            Stage stage = new Stage();
//            stage.setResizable(false);
//            stage.setTitle("View ALl Store Orders");
//            stage.setScene(scene);
//            stage.show();
//        }catch(Exception ignored){
//        }
    }
}
