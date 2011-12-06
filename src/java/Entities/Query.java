/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

public class Query {

    private int id;
    private int accountId;
    private String date;

    public Query() {
    }

    public Query(Integer id) {
        this.id = id;
    }

    public Query(Integer id, int accountId, String date) {
        this.id = id;
        this.accountId = accountId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
