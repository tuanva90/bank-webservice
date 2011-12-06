/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DAO.DAOCustomer;
import Entities.Customer;
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
     * Web service operation
     * @param a
     * @param b
     * @return a + b
     */
    @WebMethod(operationName = "Add")
    public int Add(@WebParam(name = "a") int a, @WebParam(name = "b") int b) {
        return a + b;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "login")
    public boolean login(@WebParam(name = "mathe")int mathe, @WebParam(name = "pin")int pin) {
        
        return false;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCustomer")
    public Customer getCustomer(@WebParam(name = "CustomerID")
    int CustomerID) {
        return  DAOCustomer.getCustomer(CustomerID);
    }
}
