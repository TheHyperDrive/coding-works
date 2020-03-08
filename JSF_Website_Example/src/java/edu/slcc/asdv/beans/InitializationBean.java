/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.slcc.asdv.beans;

import edu.slcc.asdv.bl.JSONobj;
import edu.slcc.asdv.bl.JsonSupplier;
import static edu.slcc.asdv.bl.JsonSupplier.createJsonObjectForSupplier;
import edu.slcc.asdv.bl.ProductsForSale;
import edu.slcc.asdv.bl.UsersSingleton;
import edu.slcc.asdv.bl.WarehouseSingleton;
import edu.slcc.asdv.pojos.Item;
import edu.slcc.asdv.pojos.Keyable;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

/**
 *
 * @author micha
 */
@Named(value = "initBean")
@ApplicationScoped
public class InitializationBean {

    private static WarehouseSingleton ws;
    private static UsersSingleton us;
    ProductsForSale<String, Keyable> pfs;

    public void in(@Observes @Initialized(ApplicationScoped.class) Object init) throws SQLException {
        System.out.println("Initializing singletons");
        ws = WarehouseSingleton.instantiateWarehouse();
        pfs = ws.getProducts();
        addToMenu();
        createJsonPrintout();
    }

    public static WarehouseSingleton getWarehouse() {
        return ws;
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object init) {
    }

    public InitializationBean() {
    }

    public ProductsForSale<String, Keyable> getPfs() {
        return pfs;
    }

    public void setPfs(ProductsForSale<String, Keyable> pfs) {
        this.pfs = pfs;
    }

    DefaultMenuModel compMenu = new DefaultMenuModel();
    DefaultMenuModel mobileMenu = new DefaultMenuModel();
    DefaultSubMenu laptops = new DefaultSubMenu("Laptops");
    DefaultSubMenu desktops = new DefaultSubMenu("Desktops");
    DefaultSubMenu phones = new DefaultSubMenu("Phones");
    DefaultSubMenu tablets = new DefaultSubMenu("Tablets");
    static int menuId = 1;

    public void setCompMenu(DefaultMenuModel compMenu) {
        this.compMenu = compMenu;
    }

    public void setMobileMenu(DefaultMenuModel mobileMenu) {
        this.mobileMenu = mobileMenu;
    }

    public void setLaptops(DefaultSubMenu laptops) {
        this.laptops = laptops;
    }

    public void setDesktops(DefaultSubMenu desktops) {
        this.desktops = desktops;
    }

    public void setPhones(DefaultSubMenu phones) {
        this.phones = phones;
    }

    public void setTablets(DefaultSubMenu tablets) {
        this.tablets = tablets;
    }

    public DefaultMenuModel getCompMenu() {
        return compMenu;
    }

    public DefaultMenuModel getMobileMenu() {
        return mobileMenu;
    }

    public DefaultSubMenu getLaptops() {
        return laptops;
    }

    public DefaultSubMenu getDesktops() {
        return desktops;
    }

    public DefaultSubMenu getPhones() {
        return phones;
    }

    public DefaultSubMenu getTablets() {
        return tablets;
    }

    public void addToMenu() {
        Collection<Keyable> temp = pfs.findAll();
        temp.forEach(keyable -> {
            DefaultMenuItem menuItem = new DefaultMenuItem(((Item) keyable).getName());
            String key = ((Item) keyable).getId();
            menuItem.setCommand("#{function.menuChangeItem}");
            menuItem.setParam("id" + menuId++, key);
            switch (((Item) keyable).getCategory()) {
                case "laptop":
                    laptops.addElement(menuItem);
                    break;
                case "desktop":
                    desktops.addElement(menuItem);
                    break;
                case "phone":
                    phones.addElement(menuItem);
                    break;
                case "tablet":
                    tablets.addElement(menuItem);
                    break;
            }
        });
        compMenu.addElement(laptops);
        compMenu.addElement(desktops);
        mobileMenu.addElement(phones);
        mobileMenu.addElement(tablets);
    }

    public void createJsonPrintout(){
        ArrayList<LinkedHashMap<String, String>> allItems = JsonSupplier.createMapOfItems(pfs);
        JsonObject j = JsonSupplier.createJsonObjectForSuppliers(allItems);
        StringWriter strWtr = new StringWriter();
        JsonWriter jsonWtr = Json.createWriter(strWtr);
        jsonWtr.writeObject(j);
        jsonWtr.close();
        
        JSONobj.readJASONdataUsingParser(strWtr.toString());
        JsonObject o = createJsonObjectForSupplier(strWtr.toString());
        System.out.println("----------------");
        JSONobj.writeObjectModelToStream(o);
    }
}
