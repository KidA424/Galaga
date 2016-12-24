package assignment5;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CheckingFrame extends JFrame {
    
    JMenuBar menuBar;
    JMenu file, account, transactions;
    JMenuItem read, write, galaga,
              addAccount, listTrans, listChecks, listDeposits, findAccount, listAccounts,
              addTrans;
    
    CheckingFrame (String title, ActionListener listener)
    {
        super(title);
        
        menuBar = new JMenuBar();
        
        file = new JMenu("File");
        account = new JMenu("Account");
        transactions = new JMenu("Transactions");
        
        read = new JMenuItem("Read from File");
        write = new JMenuItem("Write to File");
        listAccounts = new JMenuItem("List Accounts");
        galaga = new JMenuItem("Play Galaga");
        
        addAccount = new JMenuItem("Add an Account");
        listTrans = new JMenuItem("List Transactions");
        listChecks = new JMenuItem("List Checks");
        listDeposits = new JMenuItem("List Deposits");
        findAccount = new JMenuItem("Find an Account");
        
        addTrans = new JMenuItem("Add a Transaction");
        
        
        // FILE BAR
        read.addActionListener(listener);
        write.addActionListener(listener);
        listAccounts.addActionListener(listener);
        galaga.addActionListener(listener);

        file.add(read);
        file.add(write);
        file.add(listAccounts);
        file.add(galaga);
        
        addAccount.addActionListener(listener);
        listTrans.addActionListener(listener);
        listChecks.addActionListener(listener);
        listDeposits.addActionListener(listener);
        findAccount.addActionListener(listener);
        
        
        // ACCOUNT BAR
        account.add(addAccount);
        account.add(listTrans);
        account.add(listChecks);
        account.add(listDeposits);
        account.add(findAccount);
                
        // TRANSACTION BAR
        addTrans.addActionListener(listener);
        
        transactions.add(addTrans);
        
        // Add components to menu
        
        menuBar.add(file);
        menuBar.add(account);
        menuBar.add(transactions);

        setJMenuBar(menuBar);
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
    }
    
}
