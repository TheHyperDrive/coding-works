/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.slcc.asdv.pojos;

import edu.slcc.asdv.utilities.DESUtil;
import java.util.Objects;

/**
 *
 * @author micha
 */
public class User 
    implements Keyable<String>, Comparable<User>, Categorable<String>{
    private String userEmail;
    private String userPass;
    private String userAccess;

    public User(String userEmail){
        this.userEmail = userEmail;
    }
    
    public User(String userEmail, String userPass, String userAccess) {
        this.userEmail = userEmail;
        this.userPass = userPass;
        this.userAccess = userAccess;
    }
    
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserAccess() {
        return userAccess;
    }

    public void setUserAccess(String userAccess) {
        this.userAccess = userAccess;
    }

    @Override
    public String toString() {
        return "User{" + "userEmail=" + userEmail + ", userPass=" + userPass + ", userAccess=" + userAccess + '}';
    }

    @Override
    public String getKey() {
        return getUserEmail();
    }

    @Override
    public void setKey(String key) {
        setKey(key);
    }

    @Override
    public int compareTo(User o) {
        return this.userEmail.compareTo(o.userEmail);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.userEmail, other.userEmail)) {
            return false;
        }
        return true;
    }

    @Override
    public String getCategory() {
        return userAccess;
    }

    @Override
    public void setCategory(String t) {
        this.userAccess = t;
    }
}
