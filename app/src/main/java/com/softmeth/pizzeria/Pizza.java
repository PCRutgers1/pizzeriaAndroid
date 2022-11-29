package com.softmeth.pizzeria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
/**
 * It creates an instance of Pizza
 *
 * @author Peter Chen, Jonathon Lopez
 */
public abstract class Pizza implements Customizable {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    public abstract double price();

    public abstract String getPizzaType();

    /**
     * Abstract class template other classes must follow
     */
    public abstract void setPrice(double price);

    /**
     * Set all the Attributes of a Pizza
     *
     * @param crust The Crust of the pizza
     */
    public Pizza(Crust crust) {
        this.crust = crust;
    }

    /**
     * setter method for toppings
     * sets the toppings of a Pizza
     *
     * @param toppings the Toppings of a Pizza
     */
    public void setToppings(Set<Topping> toppings) {
        this.toppings = new ArrayList<>();
        this.toppings.addAll(toppings);
    }

    /**
     * Converts a pizza object to a string
     *
     * @return converts a pizza to a string
     */
    public String toString() {
        String pizzaType = "";
        if (this instanceof Deluxe)
            pizzaType = "Deluxe";
        else if (this instanceof Meatzza)
            pizzaType = "Meatzza";
        else if (this instanceof BBQChicken)
            pizzaType = "BBQ Chicken";
        else
            pizzaType = "Build Your Own Pizza";
        return pizzaType + "(" + this.getPizzaType() + " Style - " + this.crust.getCrust() +
                ")" + this.toppingsToString() + this.size + ", $" + String.format("%.2f", this.price());
    }

    /**
     * Converts a topping object to a string
     *
     * @return converts toppings to a string
     */
    public String toppingsToString() {
        String top = "";
        for (Topping t : this.toppings)
            top = top  + t.getTopping()+ ", ";
        return top;

    }
    /**
     * getter method for toppings
     *
     * @return toppings the Toppings of a Pizza
     */
    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }
    /**
     * Set all the Attributes of a Pizza
     *
     * @param toppings A string list of all the Toppings
     * @param crust    The Crust of the pizza
     */
    public Pizza(String[] toppings, Crust crust) {
        this.crust = crust;
        this.toppings = new ArrayList<>();
        if (toppings != null)
            for (String t : toppings) {
                this.toppings.add(Topping.findTopping(t));
            }
    }

    /**
     * setter method for Size
     * sets the size of a Pizza
     *
     * @param size the Size of a Pizza
     */
    public void setSize(Size size) {
        this.size = size;
    }

}
