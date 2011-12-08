/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

public class Card {

    private int cardId;
    private int cardPin;
    private int customerId;
    private Date carExpiredate;

    public Card() {
    }

    public Card(int cardId) {
        this.cardId = cardId;
    }

    public Card(int cardId, int cardPin, int customerId, Date carExpiredate) {
        this.cardId = cardId;
        this.cardPin = cardPin;
        this.customerId = customerId;
        this.carExpiredate = carExpiredate;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCardPin() {
        return cardPin;
    }

    public void setCardPin(int cardPin) {
        this.cardPin = cardPin;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getCardExpiredate() {
        return carExpiredate;
    }

    public void setCarExpiredate(Date carExpiredate) {
        this.carExpiredate = carExpiredate;
    }
}
