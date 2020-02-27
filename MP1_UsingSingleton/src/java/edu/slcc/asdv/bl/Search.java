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
public class Search {
    private static boolean check = false;
    private static List<Item> searchResults;

    public static List<Item> getSearchResults() {
        return searchResults;
    }
    
    
    
    public static List<Item> searchFunction(String search, ProductsForSale<String, Keyable> p){
        searchResults = new ArrayList<>();
        if (search == null){
            searchResults = RandomLoad.randomLoad(p);
        }
        else{
            Collection<Keyable> temp = p.findAll();
            temp.forEach(key -> {
                System.out.println(((Item)key).getSearch());
                if(((Item)key).getSearch().contains(search)){
                    searchResults.add((Item)key);
                    check = true;
                }
            });
            if (!check){
                searchResults = RandomLoad.randomLoad(p);
            }
        }
        return searchResults;
    }
}
