/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Customer;
import Server.MySQLConnection;
import Tracking.Tracking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SPRING GRASS
 */
public class DAOCustomer {

    public DAOCustomer() {
    }

    /**
     * Get customer from customer id
     * @param CustomerID
     * @return custem instance
     */
    public static Customer getCustomer(int CustomerID) {
        Customer customer = null;
        Connection conn = MySQLConnection.getConnection();
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, CustomerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(CustomerID);
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setCustomerAddress(rs.getString("customer_address"));
                customer.setCustomerEmail(rs.getString("customer_email"));
                customer.setCustomerPhone(rs.getString("customer_phone"));
                            }
            return customer;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void insertCustomer(Customer customer) {
        Connection conn = MySQLConnection.getConnection();
        String sql = "INSERT INTO customer (customer_name, customer_address, customer_phone, customer_email)"
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getCustomerAddress());
            ps.setString(3, customer.getCustomerPhone());
            ps.setString(4, customer.getCustomerEmail());
            boolean rs = ps.execute();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        // Test insert  customer
//        char i = 'A';
        Customer customer;
//        while (i != 'K') {
//            customer = new Customer(0, "Nguyen Van " + i, "Address" + i, "01689932623" + i, "thaonx2890@gmail.com" + i);
//            i++;
//            DAOCustomer.insertCustomer(customer);
//        }
        customer = DAOCustomer.getCustomer(20);
        Tracking.trace(customer.getCustomerName());

    }
}
