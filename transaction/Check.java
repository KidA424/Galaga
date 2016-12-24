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
public class Check extends Transaction {
    
    private boolean service;
    private boolean overdraw;
    private int checkNum;
    
    public Check (int transNum, int checkNum, double transAmt, boolean service, boolean overdraw)
    {
        super(transNum, transAmt);
        this.checkNum = checkNum;
        this.service = service;
        this.overdraw = overdraw;
        transType = "Check";
    }
        
    public int getCheckNum()
    {
        return checkNum;
    }
    
    public boolean causedServiceCharge()
    {
        return service;
    }
    
    public boolean causedOverdrawCharge()
    {
        return overdraw;
    }
}
