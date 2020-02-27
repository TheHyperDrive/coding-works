/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.slcc.asdv.beans;

import edu.slcc.asdv.bl.LoginValidate;
import edu.slcc.asdv.bl.ProductsForSale;
import edu.slcc.asdv.bl.ReloadPage;
import edu.slcc.asdv.bl.UsersOnSite;
import edu.slcc.asdv.bl.UsersSingleton;
import edu.slcc.asdv.bl.WarehouseSingleton;
import edu.slcc.asdv.pojos.Cart;
import edu.slcc.asdv.pojos.Item;
import edu.slcc.asdv.pojos.Keyable;
import edu.slcc.asdv.pojos.Reciept;
import edu.slcc.asdv.pojos.User;
import edu.slcc.asdv.utilities.DESUtil;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.MenuItem;
import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import edu.slcc.asdv.bl.RandomLoad;
import edu.slcc.asdv.bl.Search;

/**
 *
 * @author michaSessionScoped
 */
@Named(value = "function")
@SessionScoped
public class FunctionBean implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Variables">
    WarehouseSingleton ws;
    UsersSingleton us;
    static ProductsForSale<String, Keyable> p;
    static UsersOnSite<String, Keyable> u;
    List<Item> storeFront = new ArrayList<>();
    Item currentItem;
    User currentUser;
    Cart cart;
    Reciept reciept;
    String loadTemplate = "template/storeFront.xhtml";
    String login = "template/notLoggedIn.xhtml";
    String email;
    String password;
    String search; 
    boolean isLoggedIn = false;
    double total = 0;
    //</editor-fold>

    /**
     * Creates a new instance of FunctionBean
     *
     * @throws java.sql.SQLException
     */
    public FunctionBean() throws SQLException {
        ws = InitializationBean.getWarehouse();
        us = UsersSingleton.instantiateUsers();
        p = ws.getProducts();
        u = us.getUsers();
        cart = new Cart();
        reciept = new Reciept();
        randomInv();
    }

    //<editor-fold defaultstate="collapsed" desc="Setters/Getters">
    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }

    public List<Item> getStoreFront() {
        return storeFront;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getLoadTemplate() {
        return loadTemplate;
    }

    public void setLoadTemplate(String loadTemplate) {
        this.loadTemplate = loadTemplate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cart getCart() {
        return cart;
    }

    public Reciept getReciept() {
        return reciept;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    //</editor-fold>
    /**
     *
     * @param event
     * @throws java.io.IOException
     */
    public void menuChangeItem(MenuActionEvent event) throws IOException {
        MenuItem menuItem = event.getMenuItem();
        Object[] keys = menuItem.getParams().keySet().toArray();
        String key = keys[0].toString();
        String id = menuItem.getParams().get(key).get(0);
        currentItem = (Item) p.find(new Item(id));
        loadTemplate = "template/product.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");
    }

    public void changeItem(Item item) throws IOException {
        currentItem = item;
        loadTemplate = "template/product.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");
    }

    public void changeItem(String id) throws IOException {
        currentItem = (Item) p.find(new Item(id));
        loadTemplate = "template/product.xhtml";
        FacesContext context = FacesContext.getCurrentInstance();
        ReloadPage.reloadPage(context);
    }

    public void nextProduct() throws IOException {
        List<Item> currList = new ArrayList<>();
        Collection<Keyable> temp = p.findAll();
        temp.forEach(keyable -> {
            if (((Item) keyable).getCategory().equals(currentItem.getCategory())) {
                currList.add((Item) keyable);
            }
        });
        int i = currList.indexOf(currentItem);
        if (i < currList.size() - 1) {
            currentItem = currList.get(i + 1);
        } else {
            currentItem = currList.get(0);
        }
    }

    public void previousProduct() throws IOException {
        List<Item> currList = new ArrayList<>();
        Collection<Keyable> temp = p.findAll();
        temp.forEach(keyable -> {
            if (((Item) keyable).getCategory().equals(currentItem.getCategory())) {
                currList.add((Item) keyable);
            }
        });
        int i = currList.indexOf(currentItem);
        if (i > 0) {
            currentItem = currList.get(i - 1);
        } else {
            currentItem = currList.get(currList.size() - 1);
        }
    }

    public void loginToSite() throws IOException {
        Pair<String, String> loginVal = LoginValidate.login(email, password, u);
        String layout = loginVal.left;
        isLoggedIn = Boolean.parseBoolean(loginVal.right);
        if (isLoggedIn) {
            login = layout;
        } else {
            loadTemplate = layout;
        }
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public void register() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        u.create(new User(email, DESUtil.encrypt(password), "user"));
        currentUser = (User) u.find(new User(email));
        loadTemplate = "template/default/contentDefault.xhtml";
        context.getExternalContext().redirect("index.xhtml");
    }

    public void buy() {
        cart.addToCart(currentItem);
    }

    public void remove(Item i) {
        cart.removeFromCart(i);
        ReloadPage.reloadPage(FacesContext.getCurrentInstance());
    }

    public String totalCart() {
        for (Item item : cart.getCart()) {
            total += item.getPrice();
        }
        return String.format("%.2f", total);
    }

    public void checkout() throws IOException {
        reciept.setCart(new ArrayList<>());
        if (!isLoggedIn) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("You must be logged in to purchase"));
            return;
        }
        reciept.setCart(cart.getCart());
        cart.setCart(new ArrayList<>());
        for (Item item : reciept.getCart()) {
            p.update(new Item(item.getId(), item.getName(), item.getDescription(), item.getQty() - 1, item.getPrice(), item.getImage(), item.getCategory(), item.getSearch()));
        }
        loadTemplate = "template/receipt.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public void randomInv() {
        storeFront = RandomLoad.randomLoad(p);
    }

    public void loadPage(String page) throws IOException {
        loadTemplate = "template/" + page + ".xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public void search() throws IOException {
        storeFront.clear();
        storeFront.addAll(Search.searchFunction(search, p));
    }


}
