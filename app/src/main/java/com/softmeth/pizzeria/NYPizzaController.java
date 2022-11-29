package com.softmeth.pizzeria;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/**
 * The Controller class part of the MVC design pattern. This will
 * handle all the button on click events and will perform the necessary
 * checks to determine which methods should be called. Upon determination,
 * it will call the corresponding model to create/alter that data
 *
 * @author Peter Chen, Jonathon Lopez
 */
public class NYPizzaController {
    @FXML
    private ToggleGroup NYPizzaSize;

    @FXML
    private ImageView NYPizzaImage;

    @FXML
    private ListView<String> ToppingsListNY, MyToppingsListNY;
    @FXML
    private ComboBox<String> ChoosePizzaNY;

    @FXML
    private Button NYAddButton, NYRemoveButton;

    @FXML
    private Text TypeOfCrustNY, PriceOfPizzaNY;

    @FXML
    private TextArea NYOutput;

    private RadioButton pizzaSize;

    private double price;
    private Set<Topping> toppings = new HashSet<Topping>();
    private static int Empty = 0;
    private static int Invalid_Topping_Size = 7;
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

    /**
     * Event Handler for Updating the price of a Build Your Own Pizza as toppings are being added
     * @param e the action that triggered the event
     */
    @FXML
    private void updatePricing(ActionEvent e){
        String pizzaType = ChoosePizzaNY.getValue();
        if (pizzaType == null)
            pizzaType = "Build Your Own Pizza";
        setPizzaPrice(pizzaType,((RadioButton) NYPizzaSize.getSelectedToggle()).getText());
    }


    /**
     *  Event Handler for the Adding Toppings to a Build Your Own Pizza
     * @param e the action that triggered the event
     */
    @FXML
    private void AddToppingToPizza(ActionEvent e) {
        String topping =
                ToppingsListNY.getSelectionModel().getSelectedItem();
        if (topping == null)
            return;
        if (!toppings.contains(Topping.findTopping(topping))) {
            MyToppingsListNY.getItems().add(topping);
        }

        toppings.add(Topping.findTopping(topping));
        if (MyToppingsListNY.getItems().size() == Invalid_Topping_Size) {
            NYAddButton.setDisable(true);
        } else {
            NYAddButton.setDisable(false);
        }
        setPizzaPrice("Build Your Own Pizza", ((RadioButton) NYPizzaSize.getSelectedToggle()).getText());
    }

    /**
     *  Event Handler for the Removing Toppings from a Build Your Own Pizza
     *  @param e the action that triggered
     *  the event
     */
    @FXML
    private void RemoveToppingFromPizza(ActionEvent e) {
        String topping =
                MyToppingsListNY.getSelectionModel().getSelectedItem();
        if (topping == null)
            return;
        NYAddButton.setDisable(false);
        if (toppings.contains(Topping.findTopping(topping))) {
            MyToppingsListNY.getItems().remove(topping);
        }
        toppings.remove(Topping.findTopping(topping));
        setPizzaPrice("Build Your Own Pizza", ((RadioButton) NYPizzaSize.getSelectedToggle()).getText());
    }



    /**
     * Event Handler for the Add to order button
     */
    @FXML
    private void AddPizzaToOrder() {
        pizzaSize = (RadioButton) NYPizzaSize.getSelectedToggle();
        String sizeOfPizza = pizzaSize.getText();
        String selectedPizzaType = ChoosePizzaNY.getValue();
        PizzaFactory myPizza = new NYPizza();
        Pizza newPizza;
        if (selectedPizzaType == null || selectedPizzaType.equals(
                "Build Your Own Pizza")) {
            newPizza = myPizza.createBuildYourOwn();
            newPizza.setToppings(toppings);
        } else if (selectedPizzaType.equals("Deluxe"))
            newPizza = myPizza.createDeluxe();
        else if (selectedPizzaType.equals("BBQ Chicken"))
            newPizza = myPizza.createBBQChicken();
        else newPizza = myPizza.createMeatzza();

        Size size = Size.findSize(sizeOfPizza);
        newPizza.setSize(size);
        String pizzaType = ChoosePizzaNY.getValue();
        if (pizzaType == null)
            pizzaType = "Build Your Own Pizza";
        setPizzaPrice(pizzaType,((RadioButton) NYPizzaSize.getSelectedToggle()).getText());
        newPizza.setPrice(price);

        NYOutput.setText(NYOutput.getText() + "Successfully added Pizza To Order \n");
        Order.currentOrder.add(newPizza);
    }

    /**
     *  Sets enables or disables the add and remove toppings button
     *
     * @param state the state of the add and remove buttons to set
     */
    private void setButtons(Boolean state) {
        NYAddButton.setDisable(state);
        NYRemoveButton.setDisable(state);
    }

    /**
     *  Sets the price depending on toppings and pizza chosen
     *
     * @param  pizzaType The type of pizza to set the price of
     * @param  size the size of the pizza to determine its price
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
     *  Sets the toppings depending on what pizza is chosen
     *
     * @param pizzaType the type of pizza to set the toppings for
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
        MyToppingsListNY.setItems(
                FXCollections.observableList(toppingsAsString));
    }

    /**
     * Event Handler It updates all output shown and whether a user hs the ability to chose toppings
     *
     * @param e the action that triggered the event
     */
    @FXML
    void ChangePizzaSelection(ActionEvent e) {
        String pizzaType = ChoosePizzaNY.getValue();
        setToppings(pizzaType);
        if (pizzaType.equals("Build Your Own Pizza")) {
            setButtons(false);
            TypeOfCrustNY.setText("Hand Tossed");
            setPizzaPrice(pizzaType,
                    ((RadioButton) NYPizzaSize.getSelectedToggle()).getText());
            NYPizzaImage.setImage(new Image(getClass().getResource("newyork.jpg").toString(), true));
        } else if (pizzaType.equals("Deluxe")) {
            setButtons(true);
            TypeOfCrustNY.setText("Brooklyn");
            setPizzaPrice(pizzaType,
                    ((RadioButton) NYPizzaSize.getSelectedToggle()).getText());
            NYPizzaImage.setImage(new Image(getClass().getResource("deluxeny.jpeg").toString(), true));
        } else if (pizzaType.equals("BBQ Chicken")) {
            setButtons(true);
            TypeOfCrustNY.setText("Thin");
            setPizzaPrice(pizzaType,
                    ((RadioButton) NYPizzaSize.getSelectedToggle()).getText());
            NYPizzaImage.setImage(new Image(getClass().getResource("bbqny.jpg").toString(), true));
        } else if (pizzaType.equals("Meatzza")) {
            setButtons(true);
            TypeOfCrustNY.setText("Hand Tossed");
            setPizzaPrice(pizzaType,
                    ((RadioButton) NYPizzaSize.getSelectedToggle()).getText());
            NYPizzaImage.setImage(new Image(getClass().getResource("meatzzany.jpg").toString(), true));
        }
    }
}