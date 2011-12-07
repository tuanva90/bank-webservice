/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author SPRING GRASS
 */
public class Atm {

    private int atmId;
    private String atmPlace;
    private String atmBankname;
    private String atmBankaddress;
    private String atmLog;
    private boolean atmIswitchon;
    private boolean atmIsinsertedcard;

    public Atm() {
    }

    public Atm(int atmId) {
        this.atmId = atmId;
    }

    public Atm(int atmId, String atmPlace, String atmBankname, String atmBankaddress, String atmLog, boolean atmIswitchon, boolean atmIsinsertedcard) {
        this.atmId = atmId;
        this.atmPlace = atmPlace;
        this.atmBankname = atmBankname;
        this.atmBankaddress = atmBankaddress;
        this.atmLog = atmLog;
        this.atmIswitchon = atmIswitchon;
        this.atmIsinsertedcard = atmIsinsertedcard;
    }

    public int getAtmId() {
        return atmId;
    }

    public void setAtmId(int atmId) {
        this.atmId = atmId;
    }

    public String getAtmPlace() {
        return atmPlace;
    }

    public void setAtmPlace(String atmPlace) {
        this.atmPlace=atmPlace;
    }

    public String getAtmBankname() {
        return atmBankname;
    }

    public void setAtmBankname(String atmBankname) {
        this.atmBankname = atmBankname;
    }

    public String getAtmBankaddress() {
        return atmBankaddress;
    }

    public void setAtmBankaddress(String atmBankaddress) {
        this.atmBankaddress = atmBankaddress;
    }

    public String getAtmLog() {
        return atmLog;
    }

    public void setAtmLog(String atmLog) {
        this.atmLog = atmLog;
    }

    public boolean getAtmIswitchon() {
        return atmIswitchon;
    }

    public void setAtmIswitchon(boolean atmIswitchon) {
        this.atmIswitchon = atmIswitchon;
    }

    public boolean getAtmIsinsertedcard() {
        return atmIsinsertedcard;
    }

    public void setAtmIsinsertedcard(boolean atmIsinsertedcard) {
        this.atmIsinsertedcard = atmIsinsertedcard;
    }
}
