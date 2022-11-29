package com.softmeth.pizzeria;

/**
 * This is the Crust enum class which contains all the different Crusts
 * that are being offered for a pizza with each of them stored as values
 *
 * @author Peter Chen,Jonathan Lopez
 */
public enum Crust {
    BROOKLYN("Brooklyn"), THIN("Thin"), HAND_TOSSED("Hand Tossed"), PAN("Pan"),
    STUFFED("Stuffed"), DEEP_DISH("Deep Dish"), INVALID("None");
    private final String crust;
    /**
     * gets the string name for the current pizza crust
     * @return returns the string name of the crust
     */
    public String getCrust(){
        return this.crust;
    }
    /**
     * Simple constructor for each of our enum values that records the crust
     * of a pizza and sets the crust
     * @param crust is the crust to set the current crust object equal to
     */
    private Crust(String crust) {
        this.crust = crust;
    }

    /**
     * Take in a string crust like Pan and change it to a numeric Crust
     *
     * @param crust The String that is being changed to numeric
     * @return The numeric Crust
     */
    public static Crust findCrust(String crust) {
        Crust[] allCrust = Crust.values();

        for (Crust c : allCrust) {
            if (c.name().equalsIgnoreCase(crust)) return c;
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
