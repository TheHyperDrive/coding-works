/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.slcc.asdv.bl;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import edu.slcc.asdv.pojos.Keyable;
import edu.slcc.asdv.pojos.User;
import edu.slcc.asdv.utilities.DESUtil;
import java.io.IOException;
import javax.faces.context.FacesContext;

/**
 *
 * @author micha
 */
public class LoginValidate {
    public static Pair<String, String> login(String email, String password, UsersOnSite<String, Keyable> u) throws IOException{
        String login = "";
        String isLoggedIn = "false";
        String loadTemplate="";
        FacesContext context = FacesContext.getCurrentInstance();
        if (u.find(new User(email)) != null) {
            User temp = (User) u.find(new User(email));
            String pass = DESUtil.decrypt(temp.getUserPass());
            System.out.println(pass);
            if (pass.equals(password)) {
                login = "template/loggedIn.xhtml";
                isLoggedIn = "true";
                return new Pair<>(login, isLoggedIn);
            }
        }
        loadTemplate = "template/register.xhtml";
        return new Pair<>(loadTemplate, isLoggedIn);
    }
}
