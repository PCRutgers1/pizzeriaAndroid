package com.softmeth.pizzeria;

/**
 * This is the Size enum class which contains all the Sizes and their Prices
 * that are being offered for a pizza with each of them stored as values
 *
 * @author Peter Chen,Jonathan Lopez
 */
public enum Size {
    DELUXE_SMALL("Deluxe Small",14.99),
    DELUXE_MEDIUM("Deluxe Medium",16.99),
    DELUXE_LARGE("Deluxe Large",18.99),
    BBQ_SMALL("BBQ Small",13.99),
    BBQ_MEDIUM("BBQ Medium",15.99),
    BBQ_LARGE("BBQ Large",17.99),
    MEATZZA_SMALL("Meatzza Samll",15.99),
    MEATZZA_MEDIUM("Meatzza Medium",17.99),
    MEATZZA_LARGE("Meatzza Large",18.99),
    SMALL("Small",8.99),
    MEDIUM("Medium",10.99),
    LARGE("Large",12.99),INVALID("",0);

    private final String size;
    private final double price;

    /**
     * Simple constructor for each of our enum values that records the size and base price
     * of a pizza and sets the size and price
     * @param s is the size to set the current size object equal to
     * @param v is the price of the size set the current price object equal to
     */
    Size(String s, double v) {
        this.size=s;
        this.price=v;
    }

    /**
     * Take in a string size like Deluxe Small and change it to a numeric Size
     *
     * @param size The String that is being changed to numeric
     * @return The numeric Size
     */
    public static Size findSize(String size) {
        Size[] allSizes = Size.values();

        for (Size c : allSizes) {
            if (c.size.equalsIgnoreCase(size)) return c;
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