/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DAO.DAOAcount;
import DAO.DAOAtm;
import DAO.DAOCard;
import DAO.DAOQuery;
import DAO.DAOTransfer;
import DAO.DAOWithdraw;
import Entities.Account;
import Entities.Atm;
import Entities.Query;
import Entities.Transfer;
import Entities.Withdraw;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author SPRING GRASS
 */
@WebService()
public class BankServer {

    static {
        String url = "jdbc:mysql://ec2-174-129-255-219.compute-1.amazonaws.com:3551/bank_db";
        MSSQLConnection.setDBConnection(url, "user", "123456");
    }

    /**
     * Lay thong tin trong tai khoan
     * Web service operation
     */
    @WebMethod(operationName = "getAccountAction")
    public Account getAcountAction(@WebParam(name = "accountNumber") int accountNumber) {
        //TODO write your implementation code here:
        Query query = new Query(accountNumber);
        DAOQuery.insertQuery(query);    //Thuc hien viec luu thong tin truy van
        return DAOAcount.getAccount(accountNumber);
    }

    /**
     * Thuc hien thao tac rut tien
     * return -1 neu so tien rut lon hon so tai khoan
     * return 0 neu qua gioi han rut trong 1 ngay
     * return 1 neu yeu cau duoc chap nhan
     * Web service operation
     */
    @WebMethod(operationName = "withdrawAction")
    public int withdrawAction(@WebParam(name = "accountId") int accountId, @WebParam(name = "amount") int amount) {
        //Lay thong tin tai khoan
        Account account = new Account();
        account = DAOAcount.getAccount(accountId);

        //kiem tra so tien trong tai khoan
        if (amount > account.getAccountBalance()) {
            return -1;
        }

        //kiem tra gioi han rut tien mot ngay
        if (DAOWithdraw.checkDailyLimit(accountId, amount) == false) {
            return 0;
        }

        //update thong tin tai khoan
        double cash = account.getAccountBalance() - amount;
        account.setAccountBalance(cash);
        DAOAcount.updateAccount(account);

        //luu thong tin rut tien
        Withdraw withdraw = new Withdraw();
        withdraw.setAccountId(accountId);
        withdraw.setAmount(amount);
        DAOWithdraw.insertWithdraw(withdraw);

        return 1;
    }

    /**
     * Thuc hien viec chuyen tien
     * @return -1 neu so tien chuyen lon hon so tien trong tai khoan
     * @return 0 neu tai khoan dich khong ton tai
     * @return 1 neu yeu cau duoc chap nhan
     * Web service operation
     */
    @WebMethod(operationName = "transferAction")
    public int transferAction(@WebParam(name = "sourceAccountId") int sourceAccountId, @WebParam(name = "destinationAccountId") int destinationAccountId, @WebParam(name = "amount") int amount) {
        // Lay thong tin tai khoan nguon
        Account sourceAccount = new Account();
        sourceAccount = DAOAcount.getAccount(sourceAccountId);

        // Kiem tra so tien trong tai khoan nguon
        if (amount > sourceAccount.getAccountBalance()) {
            return -1;
        }

        // Kiem tra su ton tai cua tai khoan dich
        Account destinationAccount = new Account();
        destinationAccount = DAOAcount.getAccount(destinationAccountId);
        if (destinationAccount == null) {
            return 0;
        }

        // Yeu cau duoc chap nhan
        // Update tai khoan nguon
        double cash = sourceAccount.getAccountBalance() - amount;
        sourceAccount.setAccountBalance(cash);
        DAOAcount.updateAccount(sourceAccount);

        // Update tai khoan dich
        cash = destinationAccount.getAccountBalance() + amount;
        destinationAccount.setAccountBalance(cash);
        DAOAcount.updateAccount(destinationAccount);

        // Luu thong tin chuyen tien
        Transfer transfer = new Transfer(0, sourceAccountId, destinationAccountId, amount, null);
        DAOTransfer.insertTransfer(transfer);

        return 1;
    }

    /**
     * Thuc hien viec kiem tra ma the, ma pin va han su dung
     * return -1 neu ma pin khong dung
     * return 0 neu het han su dung
     * return 1 neu viec kiem tra duoc chung thuc
     * Web service operation
     */
    @WebMethod(operationName = "checkCardAction")
    public int checkCardAction(@WebParam(name = "cardId") int cardId, @WebParam(name = "cardPin") int cardPin) {
        // Kiem tra ma Pin
        if (DAOCard.confirmCard(cardId, cardPin) == false) {
            return -1;
        }

        //Kiem tra han su dung
        if (DAOCard.checkExpireDay(cardId) == false) {
            return 0;
        }

        return 1;
    }

    /***********************************************/
    /*******************ATM Service**************************/
    /**
     * Web service operation
     * Ham lay ten khach hang tu Ma The
     */
    @WebMethod(operationName = "getCustomerName")
    public String getCustomerName(@WebParam(name = "cardID") int cardID) {
        //Viet code o day
        return "Nguyen Xuan Thao";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getBalance")
    public double getBalance(@WebParam(name = "cardID")
    int cardID) {
        //TODO write your implementation code here:
        return 0.0;
    }

    /**
     * Thêm một máy Atm vào hệ thống
     * Web service operation
     */
    @WebMethod(operationName = "addAtmAction")
    public int addAtmAction(@WebParam(name = "atmPlace")
    String atmPlace, @WebParam(name = "atmBankname")
    String atmBankname, @WebParam(name = "atmBankaddress")
    String atmBankaddress) {
        //TODO write your implementation code here:
        Atm atm = new Atm(1, atmPlace, atmBankname, atmBankaddress, "", false, false);
        DAOAtm.insertAtm(atm);
        return 1;
    }

    /**
     * update lai thong tin cua may ATM
     * Web service operation
     */
    @WebMethod(operationName = "updateAtmAction")
    public int updateAtmAction(@WebParam(name = "atmId")
    int atmId, @WebParam(name = "atmPlace")
    String atmPlace, @WebParam(name = "atmBankname")
    String atmBankname, @WebParam(name = "atmBankaddress")
    String atmBankaddress, @WebParam(name = "atmLog")
    String atmLog, @WebParam(name = "atmIswitchon")
    Boolean atmIswitchon, @WebParam(name = "atmIsinsertedcard")
    Boolean atmIsinsertedcard) {
        //TODO write your implementation code here:
        Atm atm = new Atm(atmId, atmPlace, atmBankname, atmBankaddress, atmLog, atmIswitchon, atmIsinsertedcard);
        DAOAtm.updateAtm(atm);
        return 1;
    }
}
