package com.softmeth.pizzeria;

/**
 * It's an Interface that provides a template for the creations of different types of pizza
 *
 * @author Peter Chen, Jonathon Lopez
 */
public interface PizzaFactory {
    /**
     * Template other classes must implement
     * @return retuns the new pizza created
     */
    Pizza createDeluxe();
    /**
     * Template other classes must implement
     * @return retuns the new pizza created
     */
    Pizza createMeatzza();
    /**
     * Template other classes must implement
     * @return retuns the new pizza created
     */
    Pizza createBBQChicken();
    /**
     * Template other classes must implement
     * @return retuns the new pizza created
     */
    Pizza createBuildYourOwn();
}