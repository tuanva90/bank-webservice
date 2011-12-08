/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DAO.DAOAcount;
import DAO.DAOQuery;
import DAO.DAOTransfer;
import DAO.DAOWithdraw;
import Entities.Account;
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
    public Account getAcountAction(@WebParam(name = "accountNumber")
    int accountNumber) {
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
    public int withdrawAction(@WebParam(name = "accountId")
    int accountId, @WebParam(name = "amount")
    int amount) {
        //Lay thong tin tai khoan
        Account account = new Account();
        account=DAOAcount.getAccount(accountId);

        //kiem tra so tien trong tai khoan
        if(amount > account.getAccountBalance()) return -1;

        //kiem tra gioi han rut tien mot ngay
        if(DAOWithdraw.checkDailyLimit(accountId, amount) == false) return 0;

        //update thong tin tai khoan
        double cash =account.getAccountBalance() - amount;
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
     * return -1 neu so tien chuyen lon hon so tien trong tai khoan
     * return 0 neu tai khoan dich khong ton tai
     * return 1 neu yeu cau duoc chap nhan
     * Web service operation
     */
    @WebMethod(operationName = "transferAction")
    public int transferAction(@WebParam(name = "sourceAccountId")
    int sourceAccountId, @WebParam(name = "destinationAccountId")
    int destinationAccountId, @WebParam(name = "amount")
    int amount) {
        // Lay thong tin tai khoan nguon
        Account sourceAccount = new Account();
        sourceAccount = DAOAcount.getAccount(sourceAccountId);

        // Kiem tra so tien trong tai khoan nguon
        if(amount > sourceAccount.getAccountBalance()) return -1;

        // Kiem tra su ton tai cua tai khoan dich
        Account destinationAccount = new Account();
        destinationAccount = DAOAcount.getAccount(destinationAccountId);
        if(destinationAccount == null) return 0;

        // Yeu cau duoc chap nhan
        // Update tai khoan nguon
        double cash =sourceAccount.getAccountBalance() - amount;
        sourceAccount.setAccountBalance(cash);
        DAOAcount.updateAccount(sourceAccount);

        // Update tai khoan dich
        cash =destinationAccount.getAccountBalance() + amount;
        destinationAccount.setAccountBalance(cash);
        DAOAcount.updateAccount(destinationAccount);

        // Luu thong tin chuyen tien
        Transfer transfer = new Transfer(0, sourceAccountId, destinationAccountId, amount, null);
        DAOTransfer.insertTransfer(transfer);

        return 1;
    }
}