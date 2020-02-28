package edu.slcc.asdv.bl;

import com.mysql.cj.conf.ConnectionUrlParser;
import edu.slcc.asdv.pojos.Item;
import edu.slcc.asdv.pojos.Keyable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASDV2
 * @param <K>
 * @param <V>
 */
public class WarehouseSingleton<K, V extends Keyable>

{
    private static ProductsForSale<String, Keyable> productsForSale;
    private static WarehouseSingleton warehouse;
    String message;

    private WarehouseSingleton() throws SQLException
    {
        productsForSale = getData();
        message = productsForSale.message;

    }

    public static WarehouseSingleton instantiateWarehouse() throws SQLException
    {
        if (warehouse == null)
          {
            warehouse = new WarehouseSingleton();
          }

        return warehouse;
    }

    public ProductsForSale<String, Keyable> getProducts()
    {
        return productsForSale;
    }
    
    
    private ProductsForSale<String, Keyable> getData() throws SQLException {
        ProductsForSale<String, Keyable> productsForSaleTemp = new ProductsForSale<>();
        ConnectionEstablish con = new ConnectionEstablish();
        ConnectionUrlParser.Pair<ResultSet, Integer> results = PullData.getResultSet(con, "SELECT * FROM inventory");
        ResultSet result = results.left;
        int columnsNumber = results.right;
        while (result.next()) {
            //>>Assigns temporary product, and adds it to product list.
            for (int i = 1; i <= columnsNumber;) {
                Item temp = new Item(
                        result.getString(i),        //id
                        result.getString(i + 1),    //name
                        result.getString(i + 2),    //desc
                        result.getInt(i + 3),       //qty
                        result.getDouble(i + 4),    //price
                        result.getString(i + 5),    //image
                        result.getString(i + 6),    //type
                        result.getString(i + 7)     //search
                );
                productsForSaleTemp.create(temp);
                i += 8;
            }
        }
        con.closeConnection();
        return productsForSaleTemp;
    }

}
