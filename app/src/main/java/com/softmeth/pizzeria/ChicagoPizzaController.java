package com.softmeth.pizzeria;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * The Controller class part of the MVC design pattern. This will
// * handle all the button on click events and will perform the necessary
// * checks to determine which methods should be called. Upon determination,
// * it will call the corresponding model to create/alter that data
// *
// * @author Peter Chen, Jonathon Lopez
// */
public class ChicagoPizzaController extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_chicago_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Order Chicago Pizza");

        populateSpinner();
        populateToppings();
    }

    private void populateToppings(){
        RecyclerView toppingChoices = findViewById(R.id.ToppingsListChicago);
        toppingChoices.setLayoutManager(new LinearLayoutManager(this));
        String[] toppings = getResources().getStringArray(R.array.toppings_array);

        ChicagoToppingAdapter adapter = new ChicagoToppingAdapter(this, Arrays.asList(toppings));
        toppingChoices.setAdapter(adapter);


    }

    private void populateSpinner() {
        Spinner pizzaChoices = (Spinner) findViewById(R.id.ChoosePizzaChicago);

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
    //    @FXML
//    private ToggleGroup ChicagoPizzaSize;
//
//    @FXML
//    private ListView<String> ToppingsListChicago, MyToppingsListChicago;
//    @FXML
//    private ComboBox<String> ChoosePizzaChicago;
//
//    @FXML
//    private Button ChicagoAddButton, ChicagoRemoveButton;
//
//    @FXML
//    private Text TypeOfCrustChicago, PriceOfPizzaChicago;
//
//    @FXML
//    private TextArea ChicagoOutput;
//
//    @FXML
//    private ImageView ChicagoPizzaImage;
//
//    private RadioButton pizzaSize;
//
//    private double price;
//    private Set<Topping> toppings = new HashSet<Topping>();
//    private static int Empty = 0;
//    private static int Invalid_Topping_Size = 7;
//    public static final double PIZZA_TOPPING_PRICE = 1.59;
//    public static final double PIZZA_LARGE_PRICE = 17.99;
//    public static final double PIZZA_SMALL_PRICE = 8.99;
//    public static final double PIZZA_MEDIUM_PRICE = 15.99;
//    public static final double PIZZA_SUPER_PRICE = 19.99;
//    public static final double PIZZA_FOURTEEN_PRICE = 14.99;
//    public static final double PIZZA_THIRTEEN_PRICE = 13.99;
//    public static final double PIZZA_TWELVE_PRICE = 12.99;
//    public static final double PIZZA_EIGHTEEN_PRICE = 18.99;
//    public static final double PIZZA_TEN_PRICE = 10.99;
//    public static final double PIZZA_SIXTEEN_PRICE = 16.99;
//
//    /**
//     * Event Handler for Updating the price of a Build Your Own Pizza as toppings are being added
//     *
//     * @param e the action event that triggered the method
//     */
//    @FXML
//    private void updatePricing(ActionEvent e) {
//        String pizzaType = ChoosePizzaChicago.getValue();
//        if (pizzaType == null)
//            pizzaType = "Build Your Own Pizza";
//        setPizzaPrice(pizzaType, ((RadioButton) ChicagoPizzaSize.getSelectedToggle()).getText());
//    }
//
//
//    /**
//     * Event Handler for the Adding Toppings to a Build Your Own Pizza
//     *
//     * @param e the action event that triggered the method
//     */
//    @FXML
//    private void AddToppingToPizza(ActionEvent e) {
//        String topping =
//                ToppingsListChicago.getSelectionModel().getSelectedItem();
//        if (topping == null)
//            return;
//        if (!toppings.contains(Topping.findTopping(topping))) {
//            MyToppingsListChicago.getItems().add(topping);
//        }
//
//        toppings.add(Topping.findTopping(topping));
//        if (MyToppingsListChicago.getItems().size() >= Invalid_Topping_Size) {
//            ChicagoAddButton.setDisable(true);
//        } else {
//            ChicagoAddButton.setDisable(false);
//        }
//        setPizzaPrice("Build Your Own Pizza", ((RadioButton) ChicagoPizzaSize.getSelectedToggle()).getText());
//    }
//
//    /**
//     * Event Handler for the Removing Toppings from a Build Your Own Pizza
//     *
//     * @param e the action event that triggered the method
//     */
//    @FXML
//    private void RemoveToppingFromPizza(ActionEvent e) {
//        String topping =
//                MyToppingsListChicago.getSelectionModel().getSelectedItem();
//        if (topping == null)
//            return;
//        ChicagoAddButton.setDisable(false);
//
//        if (toppings.contains(Topping.findTopping(topping))) {
//            MyToppingsListChicago.getItems().remove(topping);
//        }
//        toppings.remove(Topping.findTopping(topping));
//        setPizzaPrice("Build Your Own Pizza", ((RadioButton) ChicagoPizzaSize.getSelectedToggle()).getText());
//    }
//
//
//    /**
//     * Event Handler for the Add to order button
//     */
//    @FXML
//    private void AddPizzaToOrder() {
//        pizzaSize = (RadioButton) ChicagoPizzaSize.getSelectedToggle();
//        String sizeOfPizza = pizzaSize.getText();
//        String selectedPizzaType = ChoosePizzaChicago.getValue();
//        PizzaFactory myPizza = new ChicagoPizza();
//        Pizza newPizza;
//        if (selectedPizzaType == null || selectedPizzaType.equals(
//                "Build Your Own Pizza")) {
//            newPizza = myPizza.createBuildYourOwn();
//            newPizza.setToppings(toppings);
//        } else if (selectedPizzaType.equals("Deluxe"))
//            newPizza = myPizza.createDeluxe();
//        else if (selectedPizzaType.equals("BBQ Chicken"))
//            newPizza = myPizza.createBBQChicken();
//        else newPizza = myPizza.createMeatzza();
//
//        Size size = Size.findSize(sizeOfPizza);
//        newPizza.setSize(size);
//        String pizzaType = ChoosePizzaChicago.getValue();
//        if (pizzaType == null)
//            pizzaType = "Build Your Own Pizza";
//        setPizzaPrice(pizzaType, ((RadioButton) ChicagoPizzaSize.getSelectedToggle()).getText());
//        newPizza.setPrice(price);
//
//        ChicagoOutput.setText(ChicagoOutput.getText() + "Successfully added Pizza To Order \n");
//        Order.currentOrder.add(newPizza);
//    }
//
//    /**
//     * Sets enables or disables the add and remove toppings button
//     *
//     * @param state the state to set the buttons to
//     */
//    private void setButtons(Boolean state) {
//        ChicagoAddButton.setDisable(state);
//        ChicagoRemoveButton.setDisable(state);
//    }
//
//    /**
//     * Sets the price depending on toppings and pizza chosen
//     *
//     * @param pizzaType the pizzatype for the pizza
//     * @param size      the size of the pizza
//     */
//    private void setPizzaPrice(String pizzaType, String size) {
//        if (pizzaType.equals("Build Your Own Pizza")) {
//            if (size.equals("Large")) price = PIZZA_TWELVE_PRICE;
//            else if (size.equals("Medium")) price = PIZZA_TEN_PRICE;
//            else price = PIZZA_SMALL_PRICE;
//            if (toppings != null && toppings.size() > Empty) {
//                price = price + toppings.size() * PIZZA_TOPPING_PRICE;
//            }
//        } else if (pizzaType.equals("Deluxe")) {
//            if (size.equals("Large")) price = PIZZA_EIGHTEEN_PRICE;
//            else if (size.equals("Medium")) price = PIZZA_SIXTEEN_PRICE;
//            else price = PIZZA_FOURTEEN_PRICE;
//        } else if (pizzaType.equals("BBQ Chicken")) {
//            if (size.equals("Large")) price = PIZZA_LARGE_PRICE;
//            else if (size.equals("Medium")) price = PIZZA_MEDIUM_PRICE;
//            else price = PIZZA_THIRTEEN_PRICE;
//        } else if (pizzaType.equals("Meatzza")) {
//            if (size.equals("Large")) price = PIZZA_SUPER_PRICE;
//            else if (size.equals("Medium")) price = PIZZA_LARGE_PRICE;
//            else price = PIZZA_MEDIUM_PRICE;
//        }
//        PriceOfPizzaChicago.setText(String.format("%.2f", price));
//
//    }
//
//    /**
//     * Sets the toppings depending on what pizza is chosen
//     *
//     * @param pizzaType the type of pizza to go along with toppings
//     */
//    private void setToppings(String pizzaType) {
//        toppings = new HashSet<>();
//        ArrayList<String> toppingsAsString = new ArrayList<String>();
//        if (pizzaType.equals("Deluxe")) {
//            for (String t : Topping.deluxeToppings) {
//                toppings.add(Topping.findTopping(t));
//                toppingsAsString.add(t);
//            }
//        } else if (pizzaType.equals("Meatzza")) {
//            for (String t : Topping.meatzzaToppings) {
//                toppings.add(Topping.findTopping(t));
//                toppingsAsString.add(t);
//            }
//        } else if (pizzaType.equals("BBQ Chicken")) {
//            for (String t : Topping.bbqToppings) {
//                toppings.add(Topping.findTopping(t));
//                toppingsAsString.add(t);
//            }
//        }
//        MyToppingsListChicago.setItems(
//                FXCollections.observableList(toppingsAsString));
//    }
//
//    /**
//     * Event Handler It updates all output shown and whether a user hs the ability to chose toppings
//     *
//     * @param e the action event that triggered the method
//     */
//    @FXML
//    void ChangePizzaSelection(ActionEvent e) {
//        String pizzaType = ChoosePizzaChicago.getValue();
//        setToppings(pizzaType);
//        if (pizzaType.equals("Build Your Own Pizza")) {
//            setButtons(false);
//            TypeOfCrustChicago.setText("Pan");
//            setPizzaPrice(pizzaType,
//                    ((RadioButton) ChicagoPizzaSize.getSelectedToggle()).getText());
//            ChicagoPizzaImage.setImage(new Image(getClass().getResource("chicago2.jpg").toString(), true));
//        } else if (pizzaType.equals("Deluxe")) {
//            setButtons(true);
//            TypeOfCrustChicago.setText("Deep Dish");
//            setPizzaPrice(pizzaType,
//                    ((RadioButton) ChicagoPizzaSize.getSelectedToggle()).getText());
//            ChicagoPizzaImage.setImage(new Image(getClass().getResource("deluxe.jpg").toString(), true));
//        } else if (pizzaType.equals("BBQ Chicken")) {
//            setButtons(true);
//            TypeOfCrustChicago.setText("Pan");
//            setPizzaPrice(pizzaType,
//                    ((RadioButton) ChicagoPizzaSize.getSelectedToggle()).getText());
//            ChicagoPizzaImage.setImage(new Image(getClass().getResource("bbq.jpg").toString(), true));
//        } else if (pizzaType.equals("Meatzza")) {
//            setButtons(true);
//            TypeOfCrustChicago.setText("Stuffed");
//            setPizzaPrice(pizzaType,
//                    ((RadioButton) ChicagoPizzaSize.getSelectedToggle()).getText());
//            ChicagoPizzaImage.setImage(new Image(getClass().getResource("meatzza.jpeg").toString(), true));
//        }
//    }
}