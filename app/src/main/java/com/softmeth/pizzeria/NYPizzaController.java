package com.softmeth.pizzeria;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The Controller class part of the MVC design pattern. This will
 * handle all the button on click events and will perform the necessary
 * checks to determine which methods should be called. Upon determination,
 * it will call the corresponding model to create/alter that data
 *
 * @author Peter Chen, Jonathon Lopez
 */
public class NYPizzaController extends AppCompatActivity {
    private RadioGroup NYPizzaSize;
    private String pizzaSize;
    public boolean canAddToppings = true;
    private Spinner ChoosePizzaNY;
    private RecyclerView ToppingsListNY, MyToppingsListNY;
    private Button AddToOrder;

    private TextView TypeOfCrustNY, PriceOfPizzaNY;

    private NYToppingAdapter toppingsAdapter, myToppingsAdapter;

    private ImageView NYPizzaImage;

    private Context context;
    private double price;
    private Set<Topping> toppings = new HashSet<Topping>();
    private int Empty = 0;
    private int Invalid_Topping_Size = 7;
    public static final double PIZZA_TOPPING_PRICE = 1.59;
    public static final double PIZZA_LARGE_PRICE = 17.99;
    public static final double PIZZA_SMALL_PRICE = 8.99;
    public static final double PIZZA_MEDIUM_PRICE = 15.99;
    public static final double PIZZA_SUPER_PRICE = 19.99;
    public static final double PIZZA_FOURTEEN_PRICE = 14.99;
    public static final double PIZZA_THIRTEEN_PRICE = 13.99;
    public static final double PIZZA_TWELVE_PRICE = 12.99;
    public static final double PIZZA_EIGHTEEN_PRICE = 18.99;
    public static final double PIZZA_TEN_PRICE = 10.99;
    public static final double PIZZA_SIXTEEN_PRICE = 16.99;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_ny_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order NY Pizza");
        context = NYPizzaController.this;

        populateSpinner();
        populateToppings();
        TypeOfCrustNY = findViewById(R.id.TypeOfCrustNY);
        ChoosePizzaNY = findViewById(R.id.ChoosePizzaNY);
        NYPizzaSize = findViewById(R.id.NYPizzaSize);
        PriceOfPizzaNY = findViewById(R.id.PriceOfPizzaNY);
        MyToppingsListNY = findViewById(R.id.MyToppingsListNY);
        NYPizzaImage = findViewById(R.id.NYPizzaImage);
        AddToOrder = findViewById(R.id.AddToOrder);
        AddToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPizzaToOrder();
            }
        });
        ChoosePizzaNY.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updatePricing();
                ChangePizzaSelection();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        NYPizzaSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                updatePricing();
            }
        });
    }

    private void populateToppings() {
        RecyclerView toppingChoices = findViewById(R.id.ToppingsListNY);
        RecyclerView myToppingChoices = findViewById(R.id.MyToppingsListNY);

        toppingChoices.setLayoutManager(new LinearLayoutManager(this));
        myToppingChoices.setLayoutManager(new LinearLayoutManager(this));
        String[] toppings = getResources().getStringArray(R.array.toppings_array);

        toppingsAdapter = new NYToppingAdapter(this, Arrays.asList(toppings));
        ;
        toppingChoices.setAdapter(toppingsAdapter);

        myToppingsAdapter = new NYToppingAdapter(this, null);
        ;
        myToppingChoices.setAdapter(myToppingsAdapter);


        toppingsAdapter.setClickListener(new NYToppingAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String pizzaType = ChoosePizzaNY.getSelectedItem().toString();

                if (pizzaType.equals("Build Your Own Pizza")) {
                    String top = toppingsAdapter.NYToppings.get(position);
                    AddToppingToPizza(top);
                }
            }
        });

        myToppingsAdapter.setClickListener(new NYToppingAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String pizzaType = ChoosePizzaNY.getSelectedItem().toString();
                if (pizzaType.equals("Build Your Own Pizza")) {
                    String top = myToppingsAdapter.NYToppings.get(position);
                    RemoveToppingFromPizza(top);
                }
            }
        });
    }

    private void populateSpinner() {
        Spinner pizzaChoices = (Spinner) findViewById(R.id.ChoosePizzaNY);

        String[] pizzaOptions = getResources().getStringArray(R.array.pizza_options);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        pizzaOptions); //selected item will look like a spinner set from XML

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        pizzaChoices.setAdapter(spinnerArrayAdapter);
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
     * Event Handler for Updating the price of a Build Your Own Pizza as toppings are being added
     */

    private void updatePricing() {
        String pizzaType = ChoosePizzaNY.getSelectedItem().toString();
        if (pizzaType == null || pizzaType.equals(""))
            pizzaType = "Build Your Own Pizza";

        int index = NYPizzaSize.indexOfChild(findViewById(NYPizzaSize.getCheckedRadioButtonId()));

        RadioButton r = (RadioButton) NYPizzaSize.getChildAt(index);
        pizzaSize = r.getText().toString();
        setPizzaPrice(pizzaType, pizzaSize);
    }

    /**
     * Sets the price depending on toppings and pizza chosen
     *
     * @param pizzaType the pizzatype for the pizza
     * @param size      the size of the pizza
     */
    private void setPizzaPrice(String pizzaType, String size) {
        if (pizzaType.equals("Build Your Own Pizza")) {
            if (size.equals("Large")) price = PIZZA_TWELVE_PRICE;
            else if (size.equals("Medium")) price = PIZZA_TEN_PRICE;
            else price = PIZZA_SMALL_PRICE;
            if (toppings != null && toppings.size() > Empty) {
                price = price + toppings.size() * PIZZA_TOPPING_PRICE;
            }
        } else if (pizzaType.equals("Deluxe")) {
            if (size.equals("Large")) price = PIZZA_EIGHTEEN_PRICE;
            else if (size.equals("Medium")) price = PIZZA_SIXTEEN_PRICE;
            else price = PIZZA_FOURTEEN_PRICE;
        } else if (pizzaType.equals("BBQ Chicken")) {
            if (size.equals("Large")) price = PIZZA_LARGE_PRICE;
            else if (size.equals("Medium")) price = PIZZA_MEDIUM_PRICE;
            else price = PIZZA_THIRTEEN_PRICE;
        } else if (pizzaType.equals("Meatzza")) {
            if (size.equals("Large")) price = PIZZA_SUPER_PRICE;
            else if (size.equals("Medium")) price = PIZZA_LARGE_PRICE;
            else price = PIZZA_MEDIUM_PRICE;
        }
        PriceOfPizzaNY.setText(String.format("%.2f", price));

    }


    /**
     * Event Handler for the Adding Toppings to a Build Your Own Pizza
     *
     * @param topping the topping to add to our pizza
     */

    public void AddToppingToPizza(String topping) {
        if (myToppingsAdapter.getSize() >= Invalid_Topping_Size) {
            canAddToppings = false;
            new AlertDialog.Builder(context)
                    .setTitle("Reached Topping Limit")
                    .setMessage("You are only allowed to add 7 toppings, please remove one before adding another topping")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        } else {
            canAddToppings = true;
        }

        if (topping == null)
            return;
        if (!toppings.contains(Topping.findTopping(topping))) {
            myToppingsAdapter.addItem(topping);
            Toast.makeText(context, topping + " has been successfully added as a topping", Toast.LENGTH_SHORT).show();
        }

        toppings.add(Topping.findTopping(topping));

        setPizzaPrice("Build Your Own Pizza", pizzaSize);
    }

    /**
     * Event Handler for the Removing Toppings from a Build Your Own Pizza
     */

    private void RemoveToppingFromPizza(String topping) {
        if (topping == null)
            return;

        if (toppings.contains(Topping.findTopping(topping))) {
            myToppingsAdapter.removeItem(topping);
            Toast.makeText(context, topping + " has been successfully removed from your pizza", Toast.LENGTH_SHORT).show();
        }
        toppings.remove(Topping.findTopping(topping));
        setPizzaPrice("Build Your Own Pizza", pizzaSize);
    }


    /**
     * Event Handler for the Add to order button
     */

    private void AddPizzaToOrder() {
//        pizzaSize = (RadioButton) NYPizzaSize.getSelectedToggle();
//        String sizeOfPizza = pizzaSize.getText();
        String selectedPizzaType = ChoosePizzaNY.getSelectedItem().toString();
        PizzaFactory myPizza = new NYPizza();
        Pizza newPizza;
        if ( selectedPizzaType.equals("")|| selectedPizzaType.equals(
                "Build Your Own Pizza")) {
            newPizza = myPizza.createBuildYourOwn();
            newPizza.setToppings(toppings);
        } else if (selectedPizzaType.equals("Deluxe"))
            newPizza = myPizza.createDeluxe();
        else if (selectedPizzaType.equals("BBQ Chicken"))
            newPizza = myPizza.createBBQChicken();
        else newPizza = myPizza.createMeatzza();

        Size size = Size.findSize(pizzaSize);
        newPizza.setSize(size);
//        String pizzaType = ChoosePizzaNY.getValue();
        if (selectedPizzaType == null)
            selectedPizzaType = "Build Your Own Pizza";
        setPizzaPrice(selectedPizzaType, pizzaSize);
        newPizza.setPrice(price);
        new AlertDialog.Builder(context)
                .setTitle("Pizza Has Been Added to Order")
                .setMessage("Your pizza has been successfully added to your current order")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.checkbox_on_background)
                .show();
        Order.currentOrder.add(newPizza);
    }

//    /**
//     * Sets enables or disables the add and remove toppings button
//     *
//     * @param state the state to set the buttons to
//     */
//    private void setButtons(Boolean state) {
//        NYAddButton.setDisable(state);
//        NYRemoveButton.setDisable(state);
//    }
//
//

    /**
     * Sets the toppings depending on what pizza is chosen
     *
     * @param pizzaType the type of pizza to go along with toppings
     */
    private void setToppings(String pizzaType) {
        toppings = new HashSet<>();
        ArrayList<String> toppingsAsString = new ArrayList<String>();
        if (pizzaType.equals("Deluxe")) {
            for (String t : Topping.deluxeToppings) {
                toppings.add(Topping.findTopping(t));
                toppingsAsString.add(t);
            }
        } else if (pizzaType.equals("Meatzza")) {
            for (String t : Topping.meatzzaToppings) {
                toppings.add(Topping.findTopping(t));
                toppingsAsString.add(t);
            }
        } else if (pizzaType.equals("BBQ Chicken")) {
            for (String t : Topping.bbqToppings) {
                toppings.add(Topping.findTopping(t));
                toppingsAsString.add(t);
            }
        }
        myToppingsAdapter.setItems(toppingsAsString);
    }

    /**
     * Event Handler It updates all output shown and whether a user hs the ability to chose toppings
     */
    void ChangePizzaSelection() {
        String pizzaType = ChoosePizzaNY.getSelectedItem().toString();
        setToppings(pizzaType);
        if (pizzaType.equals("Build Your Own Pizza")) {
            TypeOfCrustNY.setText(R.string.hand_tossed);
            setPizzaPrice(pizzaType,
                    pizzaSize);
            NYPizzaImage.setImageResource(R.drawable.newyork);
        } else if (pizzaType.equals("Deluxe")) {
            TypeOfCrustNY.setText(R.string.brooklyn);
            setPizzaPrice(pizzaType,
                    pizzaSize);
            NYPizzaImage.setImageResource(R.drawable.deluxeny);
        } else if (pizzaType.equals("BBQ Chicken")) {
            TypeOfCrustNY.setText(R.string.thin);
            setPizzaPrice(pizzaType, pizzaSize);
            NYPizzaImage.setImageResource(R.drawable.bbqny);
        } else if (pizzaType.equals("Meatzza")) {
            TypeOfCrustNY.setText(R.string.hand_tossed);
            setPizzaPrice(pizzaType, pizzaSize);
            NYPizzaImage.setImageResource(R.drawable.meatzzany);
        }
    }
}
