package com.softmeth.pizzeria;

import java.util.Set;

/**
 * It is as specific Build Your Own Type of Pizza
 *
 * @author Peter Chen, Jonathon Lopez
 */
public class BuildYourOwn extends Pizza {
    private double price;
    private String pizzaType;
    private static final int Zero = 0;

    /**
     * Set all the Attributes of a Build Your Own Pizza
     *
     * @param crust     the Crust of the pizza
     * @param pizzaType is the type of pizza whether NY or Chicago
     */
    public BuildYourOwn(Crust crust, String pizzaType) {
        super(crust);
        this.pizzaType = pizzaType;
    }

    /**
     * This method gets the type of pizza and returns it
     * is NEw york or chicago
     */
    @Override
    public String getPizzaType() {
        return this.pizzaType;
    }

    /**
     * setter method for Price
     * sets the price of the Build Your Own Pizza
     *
     * @param price the price of the Pizza
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * This method adds a topping to the pizza
     *
     * @param obj the topping that we are to add
     * @return whether the topping was successfully added
     */
    @Override
    public boolean add(Object obj) {
        try {
            this.getToppings().add((Topping) obj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This method removes a pizza topping form the pizza
     *
     * @param obj the topping to remove
     * @return whether the topping was successfully removed
     */
    @Override
    public boolean remove(Object obj) {
        try {
            this.getToppings().remove((Topping) obj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Getter method for Price
     *
     * @return returns the price for the Build Your Own Pizza
     */
    @Override
    public double price() {
        return this.price;
    }

    /**
     * Price setter method that validates and sets the price for the pizza
     * @param size is the size of the pizza
     * @param toppings is the arraylist of all of the toppings for the pizza
     * @return the double price amount of the pizza
     */
    public double setPizzaPrice(String size, Set<Topping> toppings){

            if (size.equals("Large")) price = ChicagoPizzaController.PIZZA_TWELVE_PRICE;
            else if (size.equals("Medium")) price = ChicagoPizzaController.PIZZA_TEN_PRICE;
            else price = ChicagoPizzaController.PIZZA_SMALL_PRICE;
            if (toppings != null && toppings.size()>Zero){
                price = price + toppings.size() * ChicagoPizzaController.PIZZA_TOPPING_PRICE;
            }
        return price;
    }
}
