/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.slcc.asdv.utilities;

import com.mysql.cj.conf.ConnectionUrlParser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author micha
 */
public class Database {

    public static Connection con = null;

    //>Creates a connection to the database.
    public static Connection connection() //throws InstantiationException, IllegalAccessException
    {
        String databaseName = "store_db";
        String userName = "root";
        String password = "";
        String URL = "com.mysql.cj.jdbc.Driver";
        //String URL2 = "com.mysql.cj.jdbc.Driver";
        con = null;
        try {// Load Sun's jdbc driver
            Class.forName(URL).newInstance();
            System.out.println("JDBC Driver loaded!");
        } catch (Exception e) // driver not found
        {
            System.err.println("Unable to load database driver");
            System.err.println("Details : " + e);
            return null;
        }
        String ip = "localhost"; //internet connection
        String url = "jdbc:mysql://" + ip + ":3308/" + databaseName;
        try {
            con = DriverManager.getConnection(url, userName, password);
            con.setReadOnly(false);
        } catch (Exception e) {
            System.err.println(e.toString());
            return null;
        }
        System.out.println("connection successfull");
        return con;
    }
}
