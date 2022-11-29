package com.softmeth.pizzeria;

/**
 * It's an Interface that provides a template for the add and remove
 *
 * @author Peter Chen, Jonathon Lopez
 */
public interface Customizable<E> {
    /**
     * Template other classes must implement
     * @param obj the object to add
     * @return true if successfully added object, false otherwise
     */
    boolean add(Object obj);
    /**
     * Template other classes must implement
     * @param obj the object to add
     * @return true if successfully added object, false otherwise
     */
    boolean remove(Object obj);
}