//package com.softmeth.pizzeria;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
///**
// * Creates an instance Store Order that store all orders that have been placed by their order IDs
// *
// * @author Peter Chen, Jonathon Lopez
// */
//public class StoreOrder implements Customizable {
//
//    public static ArrayList<Order> allOrders = new ArrayList<>();
//
//    /**
//     * This method adds an object obj to the store order
//     * @param obj the Object being added
//     * @return true if the object is added, otherwise false
//     */
//    @Override
//    public boolean add(Object obj) {
//        try{
//            allOrders.add((Order) obj);
//            return true;
//        }catch (Exception e){
//        return false;}
//    }
//
//    /**
//     * This method removes an object obj to the store order
//     * @param obj the Object being removed
//     * @return true if the object is removed, otherwise false
//     */
//    @Override
//    public boolean remove(Object obj) {
//        try{
//            allOrders.remove((Order) obj);
//            return true;
//        }catch (Exception e){
//            return false;}
//    }
//}