package edu.slcc.asdv.bl;

import com.mysql.cj.conf.ConnectionUrlParser;
import edu.slcc.asdv.pojos.Keyable;
import edu.slcc.asdv.pojos.User;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ASDV2
 * @param <K>
 * @param <V>
 */
public class UsersSingleton<K, V extends Keyable>

{
    private static UsersOnSite<String, Keyable> usersOnSite;
    private static UsersSingleton users;
    String message;

    private UsersSingleton() throws SQLException
    {
        usersOnSite = getData();
        message = usersOnSite.message;

    }

    public static UsersSingleton instantiateUsers() throws SQLException
    {
        if (users == null)
          {
            users = new UsersSingleton();
          }

        return users;
    }

    public UsersOnSite<String, Keyable> getUsers()
    {
        return usersOnSite;
    }
    
    
    private UsersOnSite<String, Keyable> getData() throws SQLException {
        UsersOnSite<String, Keyable> usersTemp = new UsersOnSite<>();
        ConnectionEstablish con = new ConnectionEstablish();
        ConnectionUrlParser.Pair<ResultSet, Integer> results = PullData.getResultSet(con, "SELECT * FROM logins");
        ResultSet result = results.left;
        int columnsNumber = results.right;
        while (result.next()) {
            //>>Assigns temporary product, and adds it to product list.
            for (int i = 1; i <= columnsNumber;) {
                User temp = new User(
                        result.getString(i),
                        result.getString(i + 1),
                        result.getString(i + 2)
                );
                usersTemp.create(temp);
                i += 4;
            }
        }
        con.closeConnection();
        return usersTemp;
    }

}
