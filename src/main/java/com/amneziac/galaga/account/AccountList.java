/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import java.util.ArrayList;


public class AccountList<Account> extends ArrayList<Account> {
    
    protected boolean saved = false;
    
    public AccountList() {super();}
    
    public boolean isSaved()
    {
        return saved;
    }
    
    public void setSaved(boolean saved)
    {
        this.saved = saved;
    }
    
}
