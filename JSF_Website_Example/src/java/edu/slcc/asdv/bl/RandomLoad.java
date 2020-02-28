/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.slcc.asdv.bl;

import edu.slcc.asdv.pojos.Item;
import edu.slcc.asdv.pojos.Keyable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author micha
 */
public class RandomLoad {

    public static List<Item> randomLoad(ProductsForSale<String, Keyable> pfs) {
        Collection<Keyable> store = pfs.findAll();
        List<Item> items = new ArrayList<>();
        store.forEach(keyable -> {
            items.add((Item)keyable);
        });
        List<Item> storeFront = new ArrayList<>();
        for(int i = 0; i < 4; ++i){
            Item item = items.get((int)((Math.random() * items.size() - 1) + 1));
            if (storeFront.contains(item))
                i--;
            else
                storeFront.add(item);
        }
        return storeFront;
    }
}
