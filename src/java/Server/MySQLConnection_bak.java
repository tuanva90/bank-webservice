/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Tracking.Tracking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SPRING GRASS
 */
public class MySQLConnection_bak {

    private static String driver = "jdbc:mysql://";
    private static String hostname = "localhost";
    private static String dbname = "bank_db";
    private static String username = "root";
    private static String password = "123456";
    private static String location = "";
    private static String classname = "com.mysql.jdbc.Driver";
    private static boolean is_connected = false;
    private Connection conn = null;

    public MySQLConnection_bak() {
        location = driver  + hostname + "/" + dbname;
    }

    /**
     * Set inforntion for connection
     * @param host_name
     * @param db_name
     * @param user_name
     * @param pass_word
     */
    public static void setDBConnection(String class_name,String host_name, String db_name, String user_name, String pass_word) {
        if(class_name != null){
           classname = class_name;
        }
        if(host_name != null){
            hostname = host_name;
        }
        if(db_name != null){
           dbname = db_name;
        }
        if(user_name != null){
           username = user_name;
        }
        if(pass_word != null){
            password = pass_word;
        }        
        location = driver  + hostname + "/" + dbname;

    }

    /**
     *
     * @return java.sql.Connection;
     */
    public static Connection getDBConnection() {
        try {
            Class.forName(classname);
            location = driver  + hostname + "/" + dbname;
            Connection conn = DriverManager.getConnection(location, username, password);
            return conn;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection_bak.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnection_bak.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Test Connect
     * @return  1 if successfull
     *       or 0 failed
     */
    public static boolean testDBConnect() {
        try {
            Class.forName(classname);
            location = driver  + hostname + "/" + dbname;
            Connection conn = DriverManager.getConnection(location, username, password);
            if (conn == null) {
                return false;
            }
            conn.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection_bak.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnection_bak.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Execute: SELECT
     * @param sql
     * @return ResultSet bject that contains the data produced
     */
    public ResultSet query(String sql) {
        try {
            conn = getDBConnection();
            Statement stCmd = conn.createStatement();
            ResultSet rs = stCmd.executeQuery(sql);
            //conn.close();
            return rs;
        } catch (SQLException ex) {

            Logger.getLogger(MySQLConnection_bak.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param sql
     * @param arr
     * @return
     */
    public ResultSet preStatementQuery(String sql, String[] arr) {
        try {
            conn = getDBConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < arr.length; i++) {
                String str = arr[i];
                ps.setString(i, str);
            }
            ResultSet rs = ps.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection_bak.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Execute: Insert, Update
     * @param sql
     * @return (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 for SQL statements that return nothing
     */
    public int update(String sql) {
        try {
            conn = getDBConnection();
            Statement stCmd = conn.createStatement();
            int count = stCmd.executeUpdate(sql);
            conn.close();
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection_bak.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Close
     * @param args
     */
    public void close() {
        if (conn == null) {
            return;
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            Tracking.trace("Khong the dong ket noi");
        }
    }

    /************************************************/
    public static void main(String[] args) {
        //Test connection
        if (MySQLConnection_bak.testDBConnect()) {
            Tracking.trace("ket noi thanh cong");
        }
    }
}
