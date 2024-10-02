/*
 * 
 */
package transaction;

import assignment5.Main;
import java.io.Serializable;

public abstract class Transaction implements Serializable{
    
    protected int transNum = 0;
    protected double transAmt = 0.0;
    protected String transType = "Transaction";
    
    public Transaction ()
    {
    }
    
    public Transaction (int transNum, double transAmt)
    {
        this.transNum = transNum;
        this.transAmt = transAmt;
    }
    
    public int getTransNum()
    {
        return transNum;
    }
    
    public double getTransAmt()
    {
        return transAmt;
    }
    
    public String getTransType()
    {
        return transType;
    }
    
    public String toString()
    {        
        String str = "";
        
        str = String.format(" %-1d       %-11s    %5.2f", transNum, transType, transAmt);

        return str;
    }
}