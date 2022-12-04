package com.softmeth.pizzeria;
//
//import java.util.ArrayList;
//

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * The Controller class part of the MVC design pattern. This will
 * handle all the button on click events and will perform the necessary
 * checks to determine which methods should be called. Upon determination
 * it will call the corresponding model to create/alter that data
 *
 * @author Peter Chen, Jonathon Lopez
 */
public class CurrentOrderController extends AppCompatActivity {
    private ListView ListOfAllCurrentOrders;
    private ArrayList<String> myCurrentOrder;
    private TextView subtotal, salesTax, orderTotal, OrderOutput;
    private final static double TAX_RATE = 0.06625;
    private static int Empty = 0;
    private Button clearOrder, placeOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_current_orders_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Current Orders");

        ListOfAllCurrentOrders = findViewById(R.id.ListOfAllCurrentOrders);
        subtotal = findViewById(R.id.subtotal);
        salesTax = findViewById(R.id.salesTax);
        orderTotal = findViewById(R.id.orderTotal);
        placeOrder = findViewById(R.id.placeOrder);
        clearOrder = findViewById(R.id.clearOrder);

        showAllCurrentOrders();
        ListOfAllCurrentOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pizzaOrder = myCurrentOrder.get(i);
                removePizza(pizzaOrder);
                Log.i("testing", pizzaOrder);
            }
        });

        clearOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearOrder();
            }
        });
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeOrder();
            }
        });
    }


    /**
     * Event Handler for Clears all items in current order
     */
    private void clearOrder() {
        Order.currentOrder.clear();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<>());
        ListOfAllCurrentOrders.setAdapter(arrayAdapter);

        subtotal.setText(R.string.zero);
        salesTax.setText(R.string.zero);
        orderTotal.setText(R.string.zero);
    }
    /**
     * removes the pizza from the current order
     */
    private void removePizza(String pizzaToDelete) {
        for (Pizza p : Order.currentOrder) {
            if (p.toString().equals(pizzaToDelete)) {
                Order.currentOrder.remove(p);
                showAllCurrentOrders();
                Toast.makeText(CurrentOrderController.this,"Pizza Removed From Order",Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    /**
     * this shows all the current orders in the GUI interface
     */
    @SuppressLint("DefaultLocale")
    private void showAllCurrentOrders() {
        myCurrentOrder = new ArrayList<>();
        double totalPrice = 0;
        for (Pizza p : Order.currentOrder) {
            myCurrentOrder.add(p.toString());
            totalPrice += p.price();
        }
        double tax = totalPrice * TAX_RATE;
        subtotal.setText(String.format("%.2f", totalPrice));
        salesTax.setText(String.format("%.2f", tax));
        orderTotal.setText(String.format("%.2f", totalPrice + tax));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                myCurrentOrder);
        ListOfAllCurrentOrders.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Event Handler for Placing the entire order
     */
    private void placeOrder() {
        if (Order.currentOrder.size() == Empty)
            Toast.makeText(CurrentOrderController.this, "No Orders To Place", Toast.LENGTH_SHORT).show();
        else {
            String total = orderTotal.getText().toString();
            StoreOrder.allOrders.add(new Order(Double.parseDouble(total)));
            Order.currentOrder.clear();
            clearOrder();
            Toast.makeText(CurrentOrderController.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
        }
    }



}
