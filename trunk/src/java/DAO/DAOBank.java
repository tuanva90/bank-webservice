/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entities.Bank;
import Server.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DinhBao
 */
public class DAOBank {

    public static void insertBank(Bank bank){
        Connection conn = MySQLConnection.getConnection();
        String sql = "INSERT INTO bank (bank_id, bank_name, bank_address)"
                + "VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bank.getBankId());
            ps.setString(2, bank.getBankName());
            ps.setString(3, bank.getBankAddress());
            boolean rs = ps.execute();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get information Bank from Db
     * @param bankId
     * @return
     */
    public static Bank getBank(int bankId) {
        Bank bank = null;
        Connection conn = MySQLConnection.getConnection();
        String sql = "SELECT * FROM bank WHERE bank_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bankId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bank = new Bank();
                bank.setBankId(bankId);
                bank.setBankName(rs.getString("bank_name"));
                bank.setBankAddress(rs.getString("bank_address"));
            }
            return bank;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * update lai thong tin cua ngan hang
     * @param bank
     */
    public static void updateBank(Bank bank){
        Connection conn = MySQLConnection.getConnection();
        String sql = "UPDATE bank SET bank_name = ?, bank_address =?"
                + "WHERE bank_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bank.getBankName());
            ps.setString(2, bank.getBankAddress());
            ps.setInt(3, bank.getBankId());
            boolean rs = ps.execute();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
