/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entities.Query;
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
public class DAOQuery {

    public static void insertQuery(Query query){
        Connection conn = MySQLConnection.getConnection();
        String sql = "INSERT INTO query (account_id,date)"
                + "VALUES (?,NOW())";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, query.getAccountId());
         
            boolean rs = ps.execute();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Query getQuery(int accountId) {
        Query query = null;
        Connection conn = MySQLConnection.getConnection();
        String sql = "SELECT * FROM query WHERE account_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                query = new Query();
                query.setAccountId(accountId);
                query.setId(rs.getInt("id"));
                query.setDate(rs.getDate("date"));
                
                            }
            return query;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
}
