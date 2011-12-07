/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author SPRING GRASS
 */
public class Withdraw {

    private int id;
    private int accountId;
    private int amount;
    private Date date;

    public Withdraw() {
    }

    public Withdraw(int id) {
        this.id = id;
    }

    public Withdraw(int id, int accountId, int amount, Date date) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
