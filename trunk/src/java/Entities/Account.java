/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author SPRING GRASS
 */
public class Account {

    private int accountId;
    private double accountBalance;
    private int customerId;

    public Account() {
    }

    public Account(int accountId) {
        this.accountId = accountId;
    }

    public Account(int accountId, double accountBalace, int customerId) {
        this.accountId = accountId;
        this.accountBalance = accountBalace;
        this.customerId = customerId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
