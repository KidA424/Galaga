/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transaction;

import assignment5.Main;

/**
 *
 * @author KidA
 */
public class Deposit extends Transaction{
    
    protected boolean service = false;
    
    public Deposit (int transNum, double transAmt, boolean service)
    {
        super(transNum, transAmt);
        transType = "Deposit";
        this.service = service;
    }

    public boolean causedServiceCharge()
    {
        return service;
    }
    
//    @Override
//    public String toString()
//    {
//        String str = "Transaction #" + (transNum + 1) + " (Deposit): $"
//                + Assignment2.format.format(transAmt);
//        
//        return str;
//    }
    
}
