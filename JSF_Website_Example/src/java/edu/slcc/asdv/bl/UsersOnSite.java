package edu.slcc.asdv.bl;

import edu.slcc.asdv.pojos.Keyable;
import edu.slcc.asdv.pojos.Product;
import edu.slcc.asdv.pojos.User;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersOnSite<K, V extends Keyable>
        implements Product<Keyable> {

    private Map<K, V> map = new HashMap<>();
    ConnectionEstablish con = new ConnectionEstablish();
    String message = "";
    User user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void create(Keyable t) {
        try {
            System.out.println("Trying");
            map.put((K) t.getKey(), (V) t);
            user = (User) map.get((K) t.getKey());
            System.out.println(user);
            DataUpdate.manipulate(con, "INSERT INTO `logins` (`email`, `password`, `type`) VALUES ('" + user.getUserEmail() + "', '" + user.getUserPass() + "', '" + user.getCategory() + "');");
        } catch (SQLException ex) {
            Logger.getLogger(UsersOnSite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Keyable t) {
        map.remove(t.getKey());
        user = (User) map.get((K) t.getKey());
        try {
            DataUpdate.manipulate(con, "DELETE FROM inventory WHERE email = '" + user.getUserEmail() + "';");
        } catch (SQLException ex) {
            Logger.getLogger(UsersOnSite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Keyable t) {
        map.replace((K) t.getKey(), (V) t);
        user = (User) map.get((K) t.getKey());
        try {
            DataUpdate.manipulate(con, "UPDATE inventory SET email = '" + user.getUserEmail() + "', password = '" + user.getUserPass() + "', type = '"
                    + user.getCategory() + "WHERE email = '" + user.getUserEmail() + "';");
        } catch (SQLException ex) {
            Logger.getLogger(UsersOnSite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Keyable find(Keyable t) {
        return map.get(t.getKey());
    }

    @Override

    public Collection<Keyable> findAll() {
        return (Collection<Keyable>) map.values();
    }

}
