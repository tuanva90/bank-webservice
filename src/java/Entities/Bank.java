/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author SPRING GRASS
 */
public class Bank {

    private static final long serialVersionUID = 1L;
    private int bankId;
    private String bankName;
    private String bankAddress;

    public Bank() {
    }

    public Bank(int bankId) {
        this.bankId = bankId;
    }

    public Bank(int bankId, String bankName, String bankAddress) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.bankAddress = bankAddress;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }
}
