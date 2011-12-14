/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Entities.Atm;
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
public class DAOAtm {

    public static void insertAtm(Atm atm){
        Connection conn = MySQLConnection.getConnection();
        String sql = "INSERT INTO atm (atm_place, atm_bankname, atm_bankaddress, atm_log"
                + ",atm_iswitchon, atm_isinsertedcard)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, atm.getAtmPlace());
            ps.setString(2, atm.getAtmBankname());
            ps.setString(3, atm.getAtmBankaddress());
            ps.setString(4, atm.getAtmLog());
            ps.setBoolean(5, atm.getAtmIswitchon());
            ps.setBoolean(6, atm.getAtmIsinsertedcard());
            boolean rs = ps.execute();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get information Atm from Db
     * @param atmId
     * @return
     */
    public static Atm getAtm(int atmId) {
        Atm atm = null;
        Connection conn = MySQLConnection.getConnection();
        String sql = "SELECT * FROM atm WHERE atm_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, atmId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                atm = new Atm();
                atm.setAtmId(atmId);
                atm.setAtmPlace(rs.getString("atm_place"));
                atm.setAtmBankname(rs.getString("atm_bankname"));
                atm.setAtmBankaddress(rs.getString("atm_bankaddress"));
                atm.setAtmLog(rs.getString("atm_log"));
                atm.setAtmIswitchon(rs.getBoolean("atm_iswitchon"));
                atm.setAtmIsinsertedcard(rs.getBoolean("atm_isinsertedcard"));
            }
            return atm;
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Cập nhật lại thông tin của máy ATM
     * @param atm
     */
    public static void updateAtm(Atm atm){
        Connection conn = MySQLConnection.getConnection();
        String sql = "UPDATE atm SET atm_place = ?, atm_bankname = ?, atm_bankaddress = ?, atm_log = ?"
                + ",atm_iswitchon = ?, atm_isinsertedcard = ? "
                + "WHERE atmId = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, atm.getAtmPlace());
            ps.setString(2, atm.getAtmBankname());
            ps.setString(3, atm.getAtmBankaddress());
            ps.setString(4, atm.getAtmLog());
            ps.setBoolean(5, atm.getAtmIswitchon());
            ps.setBoolean(6, atm.getAtmIsinsertedcard());
            ps.setInt(7, atm.getAtmId());
            boolean rs = ps.execute();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
