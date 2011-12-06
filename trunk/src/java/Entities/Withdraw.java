/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author SPRING GRASS
 */
public class Withdraw {

    private Integer id;
    private int accountId;
    private int amount;
    private String date;

    public Withdraw() {
    }

    public Withdraw(Integer id) {
        this.id = id;
    }

    public Withdraw(Integer id, int accountId, int amount, String date) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
