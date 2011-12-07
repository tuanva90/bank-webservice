/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

public class Query {

    private int id;
    private int accountId;
    private Date date;

    public Query() {
    }

    public Query(int id) {
        this.id = id;
    }

    public Query(int id, int accountId, Date date) {
        this.id = id;
        this.accountId = accountId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
