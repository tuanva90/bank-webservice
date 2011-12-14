/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Card;
import Server.MySQLConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SPRING GRASS
 */
public class DAOCard {

    public DAOCard() {
    }

   /**
    *
    * @param cardID
    * @param cardPin
    * @return
    */
    public static boolean confirmCard(int cardId, int cardPin){
        Connection conn = MySQLConnection.getConnection();
        String sql = "SELECT * FROM card WHERE card_id=? AND card_pin=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cardId);
            ps.setInt(2, cardPin);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOCard.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

    /**
     *
     * @param card
     */
    public static void insertCard(Card card){
        Connection conn = MySQLConnection.getConnection();
        String sql = "INSERT card(card_pin,customer_id,card_expiredate) value (?,?,?)";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, card.getCardPin());
            ps.setInt(2, card.getCustomerId());
            ps.setDate(3, card.getCardExpiredate());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Update lai thong tin card
     * @param card
     */
    public static void updateCard(Card card){
        Connection conn = MySQLConnection.getConnection();
        String sql = "UPDATE card SET card_pin = ?,customer_id = ?,card_expiredate = ? WHERE card_id = ?";
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, card.getCardPin());
            ps.setInt(2, card.getCustomerId());
            ps.setDate(3, card.getCardExpiredate());
            ps.setInt(4, card.getCardId());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ham kiem tra ngay su dung cua card
     * @param cardId
     * @return
     */
    public static boolean checkExpireDay(int cardId){
        Connection conn = MySQLConnection.getConnection();
        String sql = "SELECT * FROM card WHERE card_id = ? and DATE(card_expiredate) >= DATE(NOW())";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cardId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               return true;
            }else{
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOCard.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
