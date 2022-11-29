package com.softmeth.pizzeria;

/**
 * It is as specific Deluxe Type of Pizza
 *
 * @author Peter Chen, Jonathon Lopez
 */
public class Deluxe extends Pizza{

    private double price;
    private String pizzaType;

    /**
     * Set all the Attributes of a Deluxe Pizza
     *
     * @param toppings the list of all the Toppings of a pizza
     * @param crust the Crust of the pizza
     * @param  pizzaType whether the pizza os chicago or NY style
     */
    public Deluxe(String[] toppings, Crust crust, String pizzaType) {
        super(toppings, crust);
        this.pizzaType = pizzaType;
    }
    /**
     * This method gets the type of pizza and returns it
     * is NEw york or chicago
     */
    @Override
    public String getPizzaType(){
        return this.pizzaType;
    }
    /**
     * setter method for Price
     * sets the price of the Deluxe Pizza
     * @param price the price of the Pizza
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * This method adds an object to something
     * @param obj the Object being added
     */
    @Override
    public boolean add(Object obj) {
        return false;
    }

    /**
     * This method removes an object from something
     * @param obj the Object being removed
     */
    @Override
    public boolean remove(Object obj) {
        return false;
    }

    /**
     * Getter method for Price
     *
     * @return returns the price for the Deluxe Pizza
     */
    @Override
    public double price() {
        return this.price;
    }
}
