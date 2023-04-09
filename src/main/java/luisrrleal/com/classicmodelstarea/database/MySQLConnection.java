package luisrrleal.com.classicmodelstarea.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author niluxer
 */
public class MySQLConnection {
    private static Connection conn = null;
    private static String dbname = "classicmodels";
    private static String dbport = "3306";
    private static String dbuser = "proyect";
    private static String dbpass = "proyect";


    public static void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String hostname = "localhost";
            conn = DriverManager.getConnection("jdbc:mysql://"+ hostname +":"+ dbport +"/" + dbname + "?serverTimezone=UTC", dbuser, dbpass);
            System.out.println("Successful database connection.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection()
    {
        if(conn == null) Connect();
        return conn;
    }

    public static void Disconnect() {
        try {
            conn.close();
            System.out.println("Database connection has been terminated.");
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
