//package com.softmeth.pizzeria;
//
///**
// * It allows you to create a specific type of New York Style Pizza
// *
// * @author Peter Chen, Jonathon Lopez
// */
//public class NYPizza implements PizzaFactory{
//
//    /**
//     * Creates a new pizza of type Deluxe and corresponding
//     * toppings of Sausage, Pepperoni, Green Pepper, Onion, and Mushroom
//     * and crust of Brooklyn
//     * @return returns the new Deluxe pizza
//     */
//    @Override
//    public Pizza createDeluxe() {
//        String [] toppings = {"Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom"};
//        Crust crust = Crust.BROOKLYN;
//        return new Deluxe(toppings,crust, "New York");
//    }
//
//    /**
//     * Creates a new pizza of type Meatzaa and corresponding
//     * toppings of Sausage, Pepperoni, Beef, and Ham
//     * and crust of And Tossed
//     * @return returns the new Meatzza pizza
//     */
//    @Override
//    public Pizza createMeatzza() {
//        String [] toppings = {"Sausage", "Pepperoni", "Beef", "Ham"};
//        Crust crust = Crust.HAND_TOSSED;
//        Pizza meatzza = new Meatzza(toppings,crust, "New York");
//        return meatzza;
//    }
//
//    /**
//     * Creates a new pizza of type BBQ Chicken and corresponding
//     * toppings of BBQ Chicken, Green Pepper, Provolone, and Cheddar
//     * and crust of Thin
//     * @return returns the new BBQ Chicken pizza
//     */
//    @Override
//    public Pizza createBBQChicken() {
//        String [] toppings = {"BBQ Chicken", "Green Pepper", "Provolone", "Cheddar"};
//        Crust crust = Crust.THIN;
//        Pizza bbq = new BBQChicken(toppings,crust, "New York");
//        return bbq;
//    }
//
//    /**
//     * Creates a new pizza of type Build Your Own and corresponding
//     * toppings of users choosing and crust of Hand Tossed
//     * @return returns the new Build Your Own pizza
//     */
//    @Override
//    public Pizza createBuildYourOwn() {
//        Crust crust = Crust.HAND_TOSSED;
//        Pizza myPizza = new BuildYourOwn(crust, "New York");
//        return myPizza;
//    }
//}
