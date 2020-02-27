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
public class Reciept {
    private List<Item> cart;
    
    public Reciept(){
        cart = new ArrayList<>();
    }

    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

}
