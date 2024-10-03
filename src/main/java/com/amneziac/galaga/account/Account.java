/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amneziac.galaga.account;

import java.io.Serializable;

public class Account implements Serializable, Comparable {
    
    protected String name;
    protected double balance;
    protected boolean saved = false;
        
    public Account (String name, double balance)
    {
        this.name = name;
        this.balance = balance;
    }
    
    public String getName()
    {
        return name;
    }
    
    public double getBalance()
    {
        return balance;
    }
    
    public void setSaved(boolean saved)
    {
        this.saved = saved;
    }
    
    public boolean isSaved()
    {
        return saved;
    }
    
    public String toString()
    {
        return name;
    }

    @Override
    public int compareTo(Object t) {
        return name.compareTo(t.toString());
    }
}
