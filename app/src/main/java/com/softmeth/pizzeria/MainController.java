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
    }


    /**
     * Event Handler to open New York Order Screen
     *
     * @param v the action event that triggered the method
     */
    public void orderNYPizza(View v) {
        Intent newActivity = new Intent(this,NYPizzaController.class);
        startActivity(newActivity);

    }

    /**
     * Event Handler to open Current Order Screen
     *
     * @param v the action event that triggered the method
     */
    public void viewCurrentOrder(View v) {
        Intent newActivity = new Intent(this,CurrentOrderController.class);
        startActivity(newActivity);


    }

    /**
     * Event Handler to open All Store Order Screen
     *
     * @param v the action event that triggered the method
     */
    public void viewStoreOrders(View v) {
        Intent newActivity = new Intent(this,StoreOrderController.class);
        startActivity(newActivity);

    }
}
