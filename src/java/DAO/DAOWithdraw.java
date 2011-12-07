/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entities.Withdraw;
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
public class DAOWithdraw {

   public static void insertWithdraw(Withdraw withdraw){
        Connection conn = MySQLConnection.getConnection();
        String sql = "INSERT INTO withdraw (account_id, amount, date)"
                + "VALUES (?, ?, NOW())";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, withdraw.getAccountId());
            ps.setInt(2, withdraw.getAmount());
            boolean rs = ps.execute();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   /**
    * Get information withdraw from Db
    * @param accountId
    * @return
    */
   public static Withdraw getWithdraw(int accountId) {
        Withdraw withdraw = null;
        Connection conn = MySQLConnection.getConnection();
        String sql = "SELECT * FROM withdraw WHERE account_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                withdraw = new Withdraw();
                withdraw.setAccountId(accountId);
                withdraw.setId(rs.getInt("id"));
                withdraw.setAmount(rs.getInt("amount"));
                withdraw.setDate(rs.getDate("date"));
            }
            return withdraw;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
