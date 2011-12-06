/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DAO.DAOCustomer;
import Entities.Customer;
import Tracking.Tracking;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
        char i = 'A';
        Customer customer;
            while (i != 'K') {
            customer = new Customer(0, "Nguyen Van " + i, "Address" + i, "01689932623" + i, "thaonx2890@gmail.com" + i);
            i++;
            DAOCustomer.insertCustomer(customer);
        }
//            reparedStatement ps = conn.prepareStatement(sql);

//                String sql = "INSERT card(customerid,pin,expiredate) value(?,?,?)";
//                PreparedStatement ps = conn.prepareStatement(sql);
//                ps.setInt(1, 20);
//                ps.setInt(2, 123321);
//                java.util.Date now = new java.util.Date();
//                java.sql.Date date = new Date(now.getTime());
//                ps.setInt(3, 1000);
//                //ps.execute();
//                System.out.println(date);
           
        
    }
}
