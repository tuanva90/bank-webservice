/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Tracking.Tracking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SPRING GRASS
 */
public class MySQLConnection {

    /**
     * Set driver class
     */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String urlconnect = "jdbc:mysql://ec2-174-129-255-219.compute-1.amazonaws.com:3551/bank_db";
    private static String username = "user";
    private static String password = "123456";
    private Connection conn = null;


    /**
     *
     * @param url = "jdbc:mysql://" + hostname + "/" + dbname"
     * @param user_name
     * @param pass_word
     */
    public static void setDBConnection(String url, String user_name, String pass_word) {
        if (url != null) {
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


    /**
     * create a Preparedstament
     * @param sql
     * @return successfull : prepareStatement instance
     *         failed: null
     *
     */
    public PreparedStatement createPreparedstament(String sql){
        conn = getConnection();
        try {
            return conn.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }


    /************************************************/
    public static void main(String[] args) {
        //Test connection
        String url = "jdbc:mysql://ec2-174-129-255-219.compute-1.amazonaws.com:3551/bank_db";
        MSSQLConnection.setDBConnection(url, "user", "123456");
        Connection conn = MSSQLConnection.getConnection();
        if (conn == null) {
            Tracking.trace("keet noi that bai");
        } else {
            Tracking.trace("Ket noi thanh cong");
        }
    }
}
