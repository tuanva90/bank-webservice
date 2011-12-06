/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Tracking.Tracking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SPRING GRASS
 */
public class MSSQLConnection {

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MSSQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static String urlconnect;// = "jdbc:sqlserver://"+localhost+":"+1433+";databaseName="+db
    private static String username;
    private static String password;
   
    private Connection conn = null;

    public static void setDBConnection(String url, String user_name, String pass_word) {
        if(url != null){
            urlconnect = url;
        }
        username = user_name;
        password = pass_word;
    }

    /**
     * Get conneection
     * @return java.sql.Connection;
     */
    public static Connection getConnection() {
        if (urlconnect == null) {
            Tracking.trace(" Not yet set Url connection");
            return null;
        }
        try {
            return DriverManager.getConnection(urlconnect, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(MSSQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /************************************************/
    public static void main(String[] args) {
        //Test connection
        String url = "jdbc:sqlserver://localhost:1433;databaseName=bank_db1";
        MSSQLConnection.setDBConnection(url, "sa", "sa");
        Connection conn = MSSQLConnection.getConnection();
        if(conn == null){
            Tracking.trace("keet noi that bai");
        }else{
            Tracking.trace("Ket noi thanh cong");
        }
    }
}
