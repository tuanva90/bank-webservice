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
    public static boolean confirmCard(int cardID, int cardPin){
        Connection conn = MySQLConnection.getConnection();
        String sql = "SELECT * FROM card WHERE card_id=? AND card_pin=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cardID);
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
            ps.setInt(3, card.getCardExpiredate());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
       if(DAOCard.confirmCard(1, 123456)){
           System.out.println("Da chung thuc");
       }else{
           System.out.println("Khong chung thuc");
       }

      /// DAOCard.insertCard(new Card(0,123456,20,1000));
        
    }

}
