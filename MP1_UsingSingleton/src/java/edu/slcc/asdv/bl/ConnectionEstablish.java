/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.slcc.asdv.bl;

import edu.slcc.asdv.utilities.Database;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author micha
 */
public class ConnectionEstablish {
    private Connection con;
    
    public Connection getConnection() {
        con = Database.connection();
        if (con == null) {
            System.out.println("Cannot connect to database");
            return null;
        } else {
            return con;
        }
    }
    
    public void closeConnection() throws SQLException{
        con.close();
    }
}
