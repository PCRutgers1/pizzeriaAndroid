package com.softmeth.pizzeria;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * The Controller class part of the MVC design pattern. This will
 * handle all the button on click events and will perform the necessary
 * checks to determine which methods should be called. Upon determination,
 * it will call the corresponding model to create/alter that data
 *
 * @author Peter Chen, Jonathon Lopez
 */
public class StoreOrderController extends AppCompatActivity {
    private Spinner OrderNumber;
    private ListView StoreOrderList;

    private TextView OrderTotalStore, outputStoreOrders;
    private Order selectedOrder;
    private Button cancelOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_store_orders_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Store Orders");

        OrderNumber = findViewById(R.id.OrderNumber);
        StoreOrderList = findViewById(R.id.StoreOrderList);
        OrderTotalStore = findViewById(R.id.OrderTotalStore);
        cancelOrder = findViewById(R.id.cancelOrder);
        setComboboxValues();

        OrderNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                OrderNumChange();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelOrder();
            }
        });
    }
    /**
     * sets the values for the combo box dropdown list with IDs
     */
    private void setComboboxValues() {
        ArrayList<String> AllOrderNums = new ArrayList<>();

        for (Order o : StoreOrder.allOrders) {
            AllOrderNums.add(Integer.toString(o.getOrderId()));
        }
        ArrayAdapter<String> orderAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                AllOrderNums);
        OrderNumber.setAdapter(orderAdapter);
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
     * The method that gets called to update the interface when a new
     * order is gets selected
     *
     */
    public void OrderNumChange() {
        if (OrderNumber.getSelectedItem() != null)
            updateListView(OrderNumber.getSelectedItem().toString());
    }

    /**
     * updates the list view in the GUI with the new items for the current ID
     *
     * @param orderValue the order ID value that is selected
     */
    @SuppressLint("DefaultLocale")
    private void updateListView(String orderValue) {
        ArrayList<String> newOrderList = new ArrayList<>();
        for (Order o : StoreOrder.allOrders) {
            if (Integer.toString(o.getOrderId()).equals(orderValue)) {
                selectedOrder = o;
                break;
            }
        }

        for (Pizza p : selectedOrder.getCurrentOrderPizzas()) {
            newOrderList.add(p.toString());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                newOrderList);
        StoreOrderList.setAdapter(arrayAdapter);
        OrderTotalStore.setText(String.format("%.2f", selectedOrder.getOrderTotal()));
    }

    /**
     * The method that handles when an order gets cnaceled and takes it out of the database
     *
     */
    private void cancelOrder() {
        if (OrderNumber.getSelectedItem() == null)
            return;
        String orderToDelete = OrderNumber.getSelectedItem().toString();
        for (Order n : StoreOrder.allOrders) {
            if (Integer.toString(n.getOrderId()).equals(orderToDelete)) {
                StoreOrder.allOrders.remove(n);
                break;
            }
        }

        setComboboxValues();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<>());
        StoreOrderList.setAdapter(arrayAdapter);
        OrderTotalStore.setText(R.string.zero);
        Toast.makeText(StoreOrderController.this,"Successfully canceled order", Toast.LENGTH_SHORT).show();

    }

//    /**
//     * This method exports all of the orders with their IDs and pizzas to the text file
//     * named storeorders.txt in the root directory
//     */
//    private void exportList() {
//        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
//                new FileOutputStream("StoreOrders.txt"), StandardCharsets.UTF_8))) {
//            for (Order ord : StoreOrder.allOrders) {
//                writer.write("Order ID of " + ord.getOrderId() + "\n");
//                for (Pizza p : ord.getCurrentOrderPizzas()) {
//                    writer.write(p.toString() + "\n");
//                }
//                writer.write("Order Total With Tax: $" + ord.getOrderTotal() + "\n");
//                outputStoreOrders.setText("Successfully Exported List");
//            }
//        } catch (Exception e) {
//
//        }
//    }

}