
package com.amneziac.galaga.account;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.amneziac.galaga.transaction.*;


public class CheckingAccount extends Account{
    
    public static final double DEPOSIT_CHARGE = 0.10;
    public static final double CHECK_CHARGE = 0.15;
    public static final double SERVICE_CHARGE_THRESHOLD = 500.00;
    public static final double SERVICE_CHARGE = 5.00;
    public static final double WARNING_THRESHOLD = 50.00;
    public static final double OVERDRAFT_CHARGE = 10.00;
    
    public static final int CHECK_ID = 1;
    public static final int DEPOSIT_ID = 2;
    public static final int END_ID = 0;

    private double totalServiceCharges = 0.0;
    private boolean serviceChargeAssessed = false;
    
    private ArrayList<Transaction> transaction = new ArrayList();
    
    public static DecimalFormat format = new DecimalFormat("#0.00");
    
    
/////////////////////////////// CONSTRUCTOR ////////////////////////////////////
    
    
    public CheckingAccount(String name, double balance)
    {
        super(name, balance);
        
        this.balance = WARNING_THRESHOLD;
        setBalance(balance);
        totalServiceCharges = 0.0;
        
        if (balance < 0.0)
        {
            assessOverdrawCharge();
        }
    }

    
    
/////////////////////////////  GET/SET METHODS  ////////////////////////////////
    
    
    private void setBalance(double newBalance)
    {
        if (balance >= WARNING_THRESHOLD && newBalance < WARNING_THRESHOLD)
        {
            JOptionPane.showMessageDialog(null, 
                    "WARNING!!!  Your account balance has just dropped below "
                    + "$50.00.\n"
                    + "If your balance drops below $0.00, an overdraw "
                    + "fee will be assessed.");
        }
        
        balance = newBalance;
        
        if (balance < SERVICE_CHARGE_THRESHOLD)
        {
            assessServiceCharge();
        }
        
    }
    
    @Override
    public double getBalance()
    {
        return balance;
    }
    
    public double getTotalServiceCharges()
    {
        return totalServiceCharges;
    }
    
    public Transaction getTrans(int transNum)
    {
        try
        {
            while(transNum < 0)
            {
                transNum = Integer.parseInt(JOptionPane.showInputDialog(null, "Invalid transaction number."
                        + "\nPlease enter a positive transaction number:")) - 1;
            }

            while(transNum > transaction.size() - 1)
            {
                transNum = Integer.parseInt(JOptionPane.showInputDialog(null, "Invalid transaction number."
                        + "\nOnly " + transaction.size() + " transactions have been made"
                        + " on this account."
                        + "\nPlease enter a valid transaction number:")) - 1;
            }
        }
        
        catch (NullPointerException npe)
        {
            System.exit(0);
        }
        
        return transaction.get(transNum);
    }
    
    public int getTransCount()
    {
        return transaction.size();
    }
    
    public int getCheckCount()
    {
        int count = 0;
        
        for (Transaction trans : transaction)
        {
            if (trans.getClass().equals(Check.class))
                count++;
        }
        
        return count;
    }
    
        public int getDepositCount()
    {
        int count = 0;
        
        for (Transaction trans : transaction)
        {
            if (trans.getClass().equals(Deposit.class))
                count++;
        }
        
        return count;
    }


/////////////////////////  TRANSACTION METHODS  ////////////////////////////////

    
    public void processCheck(double amount, int checkNum)
    {
        boolean overdraw = false;
        boolean service = false;
        
        if (balance - amount < SERVICE_CHARGE_THRESHOLD && !serviceChargeAssessed)
            service = true;
        
        if (balance < 0.0)
            overdraw = true;
        
        transaction.add(new Check(getTransCount(), checkNum, amount,
                service, overdraw));
        
        transaction.add(new CheckCharge(getTransCount()));
        
        setBalance(balance - amount - CHECK_CHARGE);
        totalServiceCharges += CHECK_CHARGE;
        
        if (balance < 0.0)
        {
            assessOverdrawCharge();
        }
        
    }
    
    public void processDeposit(double amount)
    {
        boolean service = false;
        
        if (balance - amount < SERVICE_CHARGE_THRESHOLD && !serviceChargeAssessed)
            service = true;
        
        transaction.add(new Deposit(getTransCount(), amount, service));
        
        transaction.add(new DepositCharge(getTransCount()));

        
        setBalance(balance + amount - DepositCharge.AMOUNT);
        totalServiceCharges += DepositCharge.AMOUNT;
        
    }
    
    

///////////////////////  SERVICE CHARGE METHODS  ///////////////////////////////
    
    private void assessServiceCharge()
    {
        if (balance < SERVICE_CHARGE_THRESHOLD && !serviceChargeAssessed)
        {          
            serviceChargeAssessed = true;
            
            setBalance(balance - ServiceCharge.AMOUNT);
            totalServiceCharges += ServiceCharge.AMOUNT;
            
            transaction.add(new ServiceCharge(getTransCount()));
        }
    }
    
    private void assessOverdrawCharge()
    {
        setBalance(balance - OverdraftCharge.AMOUNT);
        totalServiceCharges += OverdraftCharge.AMOUNT;
        
        transaction.add(new OverdraftCharge(getTransCount()));
    }
}