package com.softmeth.pizzeria;

/**
 * This is the Topping enum class which contains all the different Toppings
 * that are being offered for a pizza with each of them stored as values
 *
 * @author Peter Chen,Jonathan Lopez
 */
public enum Topping {
    PINEAPPLE("Pineapple"),
    CHICKEN("Chicken"),
    SAUSAGE("Sausage"),
    PEPPERONI("Pepperoni"),
    BEEF("Beef"),
    HAM("Ham"),
    GREEN_PEPPER("Green Pepper"),
    GREEN_PEPPERS("Green Peppers"),
    PEPPERS("Peppers"),
    MUSHROOM("Mushroom"),
    ONION("Onion"),
    BBQ_CHICKEN("BBQ Chicken"),
    SPINACH("Spinach"),
    PROVOLONE("Provolone"),
    CHEDDAR("Cheddar"),
    OLIVES("Olives"),
    BACON("Bacon"),
    INVALID("None");

    public static final String[] deluxeToppings = {"Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom"};
    public static final String[] meatzzaToppings = {"Sausage", "Pepperoni", "Beef", "Ham"};
    public static final String[] bbqToppings = {"BBQ Chicken", "Green Pepper", "Provolone", "Cheddar"};
    private final String topping;
    /**
     * gets the toppings that we want as string
     * @return returns the string of the current topping
     */
    public String getTopping(){
        return this.topping;
    }
    /**
     * Simple constructor for each of our enum values that records the topping
     * being placed on a pizza and sets the topping
     * @param topping is the topping to set the current topping object equal to
     */
    Topping(String topping) {
        this.topping=topping;
    }

    /**
     * Take in a string topping like Pepperoni and change it to a numeric Topping
     *
     * @param topping The String that is being changed to numeric
     * @return The numeric Topping
     */
    public static Topping findTopping(String topping) {
        Topping[] allToppings = Topping.values();

        for (Topping t : allToppings) {
            if (t.topping.equalsIgnoreCase(topping)) return t;
        }
        return INVALID;
    }

    /**
     * Capitalize the first letter of a word and make the rest lower case
     * @param word, the word that needs to be capitalized
     */
    private String capitalize(String word) {
        return word.substring(0, 1).toUpperCase()
                + word.substring(1).toLowerCase();
    }
}