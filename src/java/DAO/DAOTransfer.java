/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entities.Transfer;
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
public class DAOTransfer {

    public static void insertTransfer(Transfer transfer){
        Connection conn = MySQLConnection.getConnection();
        String sql = "INSERT INTO transfer (source_account_id, destination_account_id, amount, date)"
                + "VALUES (?, ?, ?, NOW())";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, transfer.getSourceAccountId());
            ps.setInt(2, transfer.getDestinationAccountId());
            ps.setInt(3, transfer.getAmount());
            boolean rs = ps.execute();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get information Transfer from Db
     * @param sourceAccountId
     * @return
     */
    public static Transfer getTransferSource(int sourceAccountId) {
        Transfer transfer = null;
        Connection conn = MySQLConnection.getConnection();
        String sql = "SELECT * FROM transfer WHERE source_account_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, sourceAccountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                transfer = new Transfer();
                transfer.setId(rs.getInt("id"));
                transfer.setSourceAccountId(sourceAccountId);
                transfer.setDestinationAccountId(rs.getInt("destination_account_id"));
                transfer.setAmount(rs.getInt("amount"));
                transfer.setDate(rs.getString("date"));
             }
            return transfer;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Get information Transfer from Db
     * @param destinationAccountId
     * @return
     */
    public static Transfer getTransferDestination(int destinationAccountId) {
        Transfer transfer = null;
        Connection conn = MySQLConnection.getConnection();
        String sql = "SELECT * FROM transfer WHERE source_account_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, destinationAccountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                transfer = new Transfer();
                transfer.setId(rs.getInt("id"));
                transfer.setSourceAccountId(rs.getInt("source_account_id"));
                transfer.setDestinationAccountId(rs.getInt("destination_account_id"));
                transfer.setAmount(rs.getInt("amount"));
                transfer.setDate(rs.getString("date"));
             }
            return transfer;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void main(String[] args){
        Transfer transfer = new Transfer(3,36,56,200,"bao2");
        insertTransfer(transfer);
        transfer=getTransferSource(34);
        System.out.print(transfer.getAmount());
        System.out.print(transfer.getDate());
        System.out.print(transfer.getDestinationAccountId());
        System.out.print(transfer.getSourceAccountId());
        System.out.print(transfer.getId());
    }
}

