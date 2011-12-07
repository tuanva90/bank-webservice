/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entities.Account;
import Server.MySQLConnection;
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
public class DAOAcount {

    public DAOAcount() {
    }

    /**
     * Get customer from customer id
     * @param AccountID)
     * @return custem instance
     */
    public static Account getAccount(int AccountID){
        Account account = null;
        Connection conn = MySQLConnection.getConnection();
        String sql = "SELECT * FROM bank_account WHERE account_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, AccountID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                account = new Account();
                account.setAccountId(AccountID);
                account.setAccountBalance(rs.getDouble("account_double"));
                account.setCustomerId(rs.getInt("customer_id"));
            }
            return account;
        } catch (SQLException ex) {
            Logger.getLogger(DAOAcount.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public static void insertAccount(Account account){
        Connection conn = MySQLConnection.getConnection();
        String sql = "INSERT INTO account (account_balance, customer_id)"
                + "VALUES (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, account.getAccountBalance());
            ps.setInt(2, account.getCustomerId());
            ps.execute();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAcount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Update tai khoan sau khi thuc hien giao dich
     * @param account
     */
     public static void updateAccount(Account account){
        Connection conn = MySQLConnection.getConnection();
        String sql = "UPDATE account SET account_balance=? WHERE account_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, account.getAccountBalance());
            ps.setInt(2, account.getAccountId());
            ps.execute();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAcount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
         // Test insert  customer
        Account  account = getAccount(101);
        System.out.println(account);

    }
}
