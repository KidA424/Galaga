/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amneziac.galaga.transaction;

import assignment5.Main;
import account.CheckingAccount;

/**
 *
 * @author KidA
 */
public class CheckCharge extends ServiceCharge {
    
    public static final double AMOUNT = 0.15;
    
    public CheckCharge (int transNum)
    {
        super(transNum);
        this.transAmt = AMOUNT;
    }
    
//    @Override
//    public String toString()
//    {
//        String str = "Transaction #" + (transNum + 1) + " (Check Charge): $"
//                + Assignment2.format.format(transAmt);
//        
//        return str;
//    }
    
}
