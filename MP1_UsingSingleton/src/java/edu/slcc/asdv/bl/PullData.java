/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.slcc.asdv.bl;

import com.mysql.cj.conf.ConnectionUrlParser;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author micha
 */
public class PullData {
    
    public static ConnectionUrlParser.Pair<ResultSet, Integer> getResultSet(ConnectionEstablish con, String sqlQuery) throws SQLException {
        Statement stmt = con.getConnection().createStatement();
        ResultSet result = stmt.executeQuery(sqlQuery);
        ResultSetMetaData rsmd = result.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        return new ConnectionUrlParser.Pair<>(result, columnsNumber);
    }
}
