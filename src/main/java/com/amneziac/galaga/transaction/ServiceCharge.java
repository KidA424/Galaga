/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transaction;

import assignment5.Main;
import account.CheckingAccount;

/**
 *
 * @author KidA
 */
public class ServiceCharge extends Transaction {
    
    public static final double AMOUNT = 5.00;
    
    public ServiceCharge (int transNum)
    {
        super();
        this.transNum = transNum;
        this.transAmt = AMOUNT;
        this.transType = "srv.chrg.";
    }
    
//    @Override
//    public String toString()
//    {
//        String str = "Transaction #" + (transNum + 1) + " (Service Charge): $"
//                + Assignment2.format.format(transAmt);
//        
//        return str;
//    }
    
}
