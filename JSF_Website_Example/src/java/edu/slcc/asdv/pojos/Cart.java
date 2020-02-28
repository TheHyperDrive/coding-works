/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.slcc.asdv.pojos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author micha
 */
public class Cart {
    private List<Item> cart;
    
    public Cart(){
        cart = new ArrayList<>();
    }

    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }
    
    public void addToCart(Item i){
        cart.add(i);
    }
    
    public void removeFromCart(Item i){
        cart.remove(i);
    }
}
