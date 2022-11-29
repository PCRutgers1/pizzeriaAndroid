package com.softmeth.pizzeria;

/**
 * It allows you to create a specific type of Chicago Style Pizza
 *
 * @author Peter Chen, Jonathon Lopez
 */
public class ChicagoPizza implements PizzaFactory{

    /**
     * Creates a new pizza of type Deluxe and corresponding
     * toppings of Sausage, Pepperoni, Green Pepper, Onion, and Mushroom
     * and crust of Deep Dish
     * @return returns the new Deluxe pizza
     */
    @Override
    public Pizza createDeluxe() {
        String [] toppings = {"Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom"};
        Crust crust = Crust.DEEP_DISH;
        return new Deluxe(toppings,crust, "Chicago");
    }

    /**
     * Creates a new pizza of type Meatzaa and corresponding
     * toppings of Sausage, Pepperoni, Beef, and Ham
     * and crust of Stuffed
     * @return returns the new Meatzza pizza
     */
    @Override
    public Pizza createMeatzza() {
        String [] toppings = {"Sausage", "Pepperoni", "Beef", "Ham"};
        Crust crust = Crust.STUFFED;
        Pizza meatzza = new Meatzza(toppings,crust, "Chicago");
        return meatzza;
    }

    /**
     * Creates a new pizza of type BBQ Chicken and corresponding
     * toppings of BBQ Chicken, Green Pepper, Provolone, and Cheddar
     * and crust of Pan
     * @return returns the new BBQ Chicken pizza
     */
    @Override
    public Pizza createBBQChicken() {
        String [] toppings = {"BBQ Chicken", "Green Pepper", "Provolone", "Cheddar"};
        Crust crust = Crust.PAN;
        Pizza bbq = new BBQChicken(toppings,crust, "Chicago");
        return bbq;
    }

    /**
     * Creates a new pizza of type Build Your Own and corresponding
     * toppings of users choosing and crust of Pan
     * @return returns the new Build Your Own pizza
     */
    @Override
    public Pizza createBuildYourOwn() {
        Crust crust = Crust.PAN;
        Pizza myPizza = new BuildYourOwn(crust, "Chicago");
        return myPizza;
    }
}