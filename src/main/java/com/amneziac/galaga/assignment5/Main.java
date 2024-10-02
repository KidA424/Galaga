
package assignment5;

import account.AccountList;
import account.CheckingAccount;
import galaga.Game;
import galaga.Level1;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;
import javax.swing.*;
import transaction.*;


public class Main {

    public static DecimalFormat format = new DecimalFormat("#0.00");
    
    static CheckingAccount currentAccount;
    static AccountList<CheckingAccount> accounts = new AccountList<CheckingAccount>();
    
    public static Game game;
    public static JFrame gameFrame;
    public static int wager = 0;
    
    public static CheckingFrame checkFrame;
    static JTextArea textArea = new JTextArea();
    static WindowListener frameListener;
    static Listener actionListener;

    private static String tempStr = null;
    
    public static final Pattern intPattern = Pattern.compile("^\\d+$");
    public static final Pattern doublePattern = Pattern.compile("-?\\d+(.\\d+)?");
    
    
    public static void main(String[] args) {
                
        textArea.setPreferredSize(new Dimension(515,300));
        textArea.setOpaque(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setBorder(null);
        
        frameListener = new FrameListener();
        actionListener = new Listener();
        
        checkFrame = new CheckingFrame("Checking Account Operations", actionListener);
        checkFrame.setSize(450,300);
        checkFrame.setLocationRelativeTo(checkFrame.getRootPane());
        checkFrame.getContentPane().add(textArea);
        checkFrame.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
        checkFrame.pack();
        checkFrame.addWindowListener(frameListener);
                
        checkFrame.setVisible(true);
    }
    
    public static void addAccount()
    {
        try
        {
            // OBTAIN ACCOUNT HOLDER NAME

            String name;

            name = JOptionPane.showInputDialog("Enter the name of the account holder:");

            while (name.trim().equals(""))
            {
                name = JOptionPane.showInputDialog("Account holder name is required for "
                        + "account initialization.\nPlease enter a valid name:");
            }


            // OBTAIN INITIAL BALANCE

            tempStr = JOptionPane.showInputDialog("Enter your initial balance:");

            while (!isValidDouble(tempStr))
            {
                tempStr = JOptionPane.showInputDialog("Invalid input.\n"
                        + "Please enter a valid initial balance:");
            }

            double initialBalance = Double.parseDouble(tempStr);

            currentAccount = new CheckingAccount(name, initialBalance);
            accounts.add(currentAccount);

            displayInitialOutput(currentAccount, initialBalance);
        }

        catch (NullPointerException npe){}
    }
    
    public static void performTransaction(CheckingAccount account)
    {
        
        int transCode;
        int checkNum = 0;
        double transAmount = 0.0;
      
//MENU
        try
        {
            String transString = JOptionPane.showInputDialog(
                    CheckingAccount.END_ID     + ": End Transactions\n"
                    + CheckingAccount.CHECK_ID   + ": Process Check\n"
                    + CheckingAccount.DEPOSIT_ID + ": Make a Deposit\n\n"
                    + "Enter transaction code:");

    // Validate user's selection.      

            while (!transString.equals(CheckingAccount.END_ID + "") &&
                !transString.equals(CheckingAccount.CHECK_ID + "") &&
                !transString.equals(CheckingAccount.DEPOSIT_ID + ""))
            {
                transString = JOptionPane.showInputDialog("That is an invalid transaction code.\n"
                        + "Please enter a valid transaction code:");
            }

            transCode = Integer.parseInt(transString);

    // Obtain check number.

            if (transCode == CheckingAccount.CHECK_ID)
            {
                boolean validInput = false;

                String str = JOptionPane.showInputDialog(
                            "Enter the check number:");
                while (!isValidInt(str))
                {
                        str = JOptionPane.showInputDialog("Invalid input.\n"
                                + "Please enter a valid check number.");
                }

                checkNum = Integer.parseInt(str);

            }

            if(transCode != CheckingAccount.END_ID)
            {
                tempStr = JOptionPane.showInputDialog(
                        "Enter the transaction amount:");

                while (!isValidDouble(tempStr))
                {
                    tempStr = JOptionPane.showInputDialog("Invalid input.\n"
                            + "Please enter a valid transaction amount:");
                }

                transAmount = Double.parseDouble(tempStr);
            }  

            switch (transCode)
            {
                case CheckingAccount.CHECK_ID:
                    account.processCheck(transAmount, checkNum);
                    displayCheckOutput(account);
                    accounts.setSaved(false);
                    break;
                case CheckingAccount.DEPOSIT_ID:
                    account.processDeposit(transAmount);
                    displayDepositOutput(account);
                    accounts.setSaved(false);
                    break;
                case CheckingAccount.END_ID:
                    displayEndOutput(account);
                    System.exit(0);
            }
        }

        catch (NullPointerException npe)
        {
            System.exit(0);
        }
    }

    public static void displayInitialOutput(CheckingAccount account, double initialBalance)
    {
        StringBuilder output = new StringBuilder(account.getName() + "'s Account\n")
                .append("Initial Balance: $")
                .append(format.format(initialBalance))
                .append("\n");
                
        if (account.getBalance() < CheckingAccount.SERVICE_CHARGE_THRESHOLD)
        {
            output.append("Current Balance: $")
                  .append(format.format(account.getBalance()))
                  .append("\n")
                  .append("Service Charge: Service --- $")
                  .append(format.format(CheckingAccount.SERVICE_CHARGE))
                  .append("\n");
        }
        
        if (account.getBalance() < 0.0)
        {
            output.append("Service Charge: Overdraw --- $")
                  .append(format.format(OverdraftCharge.AMOUNT))
                  .append("\n");
        }
        
        output.append("Total Service Charge: $")
              .append(format.format(account.getTotalServiceCharges()));
        
        textArea.setText(output.toString());
    }
    
    public static void displayCheckOutput(CheckingAccount account)
    {
        Check check = null;
        int i = account.getTransCount() - 1;
        
        while (check == null)
        {
            if (account.getTrans(i).getClass().equals(Check.class))
            {
                check = (Check)account.getTrans(i);
            }
            
            i--;
        }
        
        StringBuilder output =
                
            new StringBuilder(account.getName())
              .append("'s Account\n");
                
        output.append("Transaction: Check #")
              .append(check.getCheckNum())
              .append(" in amount of $")
              .append(format.format(check.getTransAmt()))
              .append("\n");
        
        output.append("Current Balance: $")
              .append(format.format(account.getBalance()))
              .append("\n");
        
        output.append("Service Charge: Check --- $")
              .append(format.format(CheckingAccount.CHECK_CHARGE))
              .append("\n");
        
        if (check.causedServiceCharge())
        {
            output.append("Service Charge: Service --- $")
                  .append(format.format(ServiceCharge.AMOUNT))
                  .append("\n");
        }
        
        if (check.causedOverdrawCharge())
        {
            output.append("Service Charge: Overdraw --- $")
                  .append(format.format(OverdraftCharge.AMOUNT))
                  .append("\n");
        }
        
        output.append("Total Service Charge: $")
              .append(format.format(account.getTotalServiceCharges()));
        
        textArea.setText(output.toString());
//        JOptionPane.showMessageDialog(null, output.toString());
    }
    
    public static void displayDepositOutput(CheckingAccount account)
    {
        Deposit deposit = null;
        int i = account.getTransCount() - 1;
        
        while (deposit == null)
        {
            if (account.getTrans(i).getClass().equals(Deposit.class))
            {
                deposit = (Deposit)account.getTrans(i);
            }
            
            i--;
        }        
        StringBuilder output = new StringBuilder(
                account.getName())
                .append("'s Account\n")
                .append("Transaction: Deposit in amount of $")
                .append(format.format(deposit.getTransAmt()))
                .append("\n")
                .append("Current Balance: $")
                .append(format.format(account.getBalance()))
                .append("\n") 
                .append("Service Charge: Deposit --- $")
                .append(format.format(CheckingAccount.DEPOSIT_CHARGE))
                .append("\n");
            
            if (deposit.causedServiceCharge())
            {
                output.append("Service Charge: Service --- $")
                      .append(format.format(ServiceCharge.AMOUNT))
                      .append("\n");
            }
        
            output.append("Total Service Charge: $")
                  .append(format.format(account.getTotalServiceCharges()));
        
        textArea.setText(output.toString());
//        JOptionPane.showMessageDialog(null, output.toString());
    }
      
    public static void displayEndOutput(CheckingAccount account)
    {
        String output = 
                
                account.getName() + "'s Account"
                    + "\n" +
                "Transaction: End"
                    + "\n" +
                "Current Balance: $" + format.format(account.getBalance())
                    + "\n" + 
                "Total Service Charge: $"
                    + format.format(account.getTotalServiceCharges());
        
        JOptionPane.showMessageDialog(null, output.toString());
    }
    
    public static void displayTransList(CheckingAccount account)
    {        
        if (account.getTransCount() == 0)
            tempStr = "No transactions have been processed on "
                    + account.getName()
                    + "'s account.";
        
        else
        {
            tempStr = "Transaction List\n"
                    + "Account Holder: " + account.getName()
                    + "\nBalance: $" + format.format(account.getBalance())
                    + "\n-------------------------------"
                    + "\nID\t Type\t         Amount\n";

            for (int i = 0; i < account.getTransCount(); i++)
            {
                Transaction trans = account.getTrans(i);
                
                tempStr += String.format("%-2d       %-11s    %7.2f\n", i, 
                            trans.getTransType(), trans.getTransAmt());
            }
        }
        
        textArea.setText(tempStr);
    }
    
    public static void displayCheckList(CheckingAccount account)
    {
        if (account.getCheckCount() == 0)
            tempStr = "No checks have been processed on "
                    + account.getName()
                    + "'s account.\n";
        
        else
        {
            tempStr = "Checks List \n"
                    + "Account Holder: " + account.getName()
                    + "\nBalance: $" + format.format(account.getBalance())
                    + "\n-------------------------------\n"
                    + "ID          Chk#         Amount\n";

            for (int i = 0; i < account.getTransCount(); i++)
            {
                if (account.getTrans(i).getClass().equals(Check.class))
                {
                    Check trans = (Check)account.getTrans(i);
                
                    tempStr += String.format("%-2d        %6d        %7.2f\n",
                            trans.getTransNum(), trans.getCheckNum(),
                                trans.getTransAmt());
                }
            }
        }
        
        textArea.setText(tempStr);
    }
    
    public static void displayDepositList(CheckingAccount account)
    {
        if (account.getDepositCount() == 0)
            tempStr = "No deposits have been processed on "
                    + account.getName() + "'s account.";
        
        else
        {
            tempStr = "Deposits List \n"
                    + "Account Holder: " + account.getName()
                    + "\nBalance: $" + format.format(account.getBalance())
                    + "\n-----------------------\n"
                    + "ID             Amount\n";

            for (int i = 0; i < account.getTransCount(); i++)
            {
                          
                if (account.getTrans(i).getClass().equals(Deposit.class))
                {
                    Deposit trans = (Deposit)account.getTrans(i);
                    
                    tempStr += String.format("%-2d            %7.2f\n", trans.getTransNum(), 
                                trans.getTransAmt());
                }
            }
        }
        
        textArea.setText(tempStr);
    }
                    
    public static boolean isValidInt(String str)
    {
        return intPattern.matcher(str).matches();
    }
    
    public static boolean isValidDouble(String str)
    {
        return doublePattern.matcher(str).matches();
    }
    
    public static AccountList readAccountList(AccountList accounts)
    {
        int selection = JOptionPane.NO_OPTION;
        
        if (!accounts.isSaved())
        {
            selection = JOptionPane.showConfirmDialog(null, "The current Account List contains unsaved data.\n"
                    + "Would you like to save this account list before loading another one?");
            
            if (selection == JOptionPane.YES_OPTION)
                writeAccountList(accounts);
        }
        
        boolean validFile = false;
            
        JFileChooser chooser = new JFileChooser();

        if (selection != JOptionPane.CANCEL_OPTION)
        {
            ObjectInputStream stream;

            while (!validFile)
            {
                selection = chooser.showOpenDialog(null);
                
                if (selection == JFileChooser.APPROVE_OPTION)
                {
                    try
                    {
                        stream = new ObjectInputStream(new FileInputStream(chooser.getSelectedFile()));
                        accounts = (AccountList)stream.readObject();
                        validFile = true;
                        stream.close();                    
                    }

                    catch (IOException ex)
                    {
                        JOptionPane.showMessageDialog(null, "There was an error reading from that file.\n"
                                + "Please choose a valid file to read an Account List from.");
                    }

                    catch(ClassNotFoundException ex)
                    {
                        JOptionPane.showMessageDialog(null, "That file does not contain an Account List.\n"
                                + "Please choose a valid file to read a checking account from.");
                    }
                }
                
                else
                {
                    validFile = true;
                }
            }
        }
        
        currentAccount = (CheckingAccount)accounts.get(0);
        textArea.setText("File opened.\n"
                + "Active account: " + currentAccount.getName());

        return accounts;
    }
    
    public static void writeAccountList(AccountList accounts)
    {
        boolean validFile = false;
            
            JFileChooser chooser = new JFileChooser();

            ObjectOutputStream stream = null;

            if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
            {

                while (!validFile)
                {
                    try
                    {
                        stream = new ObjectOutputStream(new FileOutputStream(chooser.getSelectedFile()));
                        stream.writeObject(accounts);
                        stream.close();
                        validFile = true;
                    }

                    catch (IOException ex)
                    {
                        JOptionPane.showMessageDialog(null, "There was an error writing to that file.\n"
                                + "Please choose a valid file to write an Account List to.");
                    }
                }
            }
            
        accounts.setSaved(true);
    }
    
    public static void findAccount()
    {
        if (accounts.isEmpty())
            textArea.setText("No accounts in current file.");
                
        else
        {
            tempStr = JOptionPane.showInputDialog("Enter the name on the account you want to open:");
            int index = -1;

            for (int i = 0; i < accounts.size(); i++)
            {
                if (tempStr.equals(accounts.get(i).getName()))
                {
                    index = i;
                }
            }

            if (index == -1)
            textArea.setText("Account not found.");

            else
            {
                currentAccount = accounts.get(index);
                textArea.setText("Account found.");
            }
        }
    }
    
    public static void listAccounts()
    {
        Collections.sort(accounts);
                
        if (accounts.isEmpty())
            tempStr = "No accounts in current file.";

        else
        {
            tempStr = "Accounts in Current File:\n";

            for (CheckingAccount account : accounts)
            {
                tempStr += "\n" + account.getName();
            }
        }

        textArea.setText(tempStr);
    }
    
    private static class FrameListener implements WindowListener
    {
        @Override
        public void windowOpened(WindowEvent we){}

        @Override
        public void windowClosing(WindowEvent we) {
            
            if(!accounts.isSaved())
            {
                int selection = JOptionPane.showConfirmDialog(null, "The current Account List contains unsaved data.\n"
                        + "Would you like to save before exiting?");
                
                switch(selection)
                {
                    case JOptionPane.YES_OPTION:
                        writeAccountList(accounts);
                    case JOptionPane.NO_OPTION:
                        checkFrame.dispose();
                    default:
                        break;
                }
            }
            
            else
                checkFrame.dispose();
        }

        @Override
        public void windowClosed(WindowEvent we) {System.exit(0);}

        @Override
        public void windowIconified(WindowEvent we) {}

        @Override
        public void windowDeiconified(WindowEvent we) {}

        @Override
        public void windowActivated(WindowEvent we) {}

        @Override
        public void windowDeactivated(WindowEvent we) {}
    }
    
    private static class Listener implements ActionListener
    {
    
        @Override
        public void actionPerformed (ActionEvent e)
        {
            Object source = e.getSource();

            checkFrame.setVisible(false);

            if (source == checkFrame.addAccount)
            {
                addAccount();
                checkFrame.setVisible(true);
            }
            
            else if (source == checkFrame.findAccount)
            {
                findAccount();
                checkFrame.setVisible(true);
            }
            
            else if (source == checkFrame.listAccounts)
            {
                listAccounts();
                checkFrame.setVisible(true);
            }
            
            else if (source == checkFrame.read)
            {
                accounts = readAccountList(accounts);
                checkFrame.setVisible(true);
            }
            
            else if (source == checkFrame.write)
            {
                writeAccountList(accounts);
                checkFrame.setVisible(true);
            }
            
            else if (source == checkFrame.galaga)
            {
                playGalaga();
            }
            
            else if (currentAccount == null)
            {
                textArea.setText("No account selected.\n"
                        + "Use \"Find Account\" or \"Create Account\" to activate an account.");
                checkFrame.setVisible(true);
            }
            
            else
            {
                if (source == checkFrame.addTrans)
                {
                    performTransaction(currentAccount);
                    checkFrame.setVisible(true);
                }

                else if (source == checkFrame.listTrans)
                {
                    displayTransList(currentAccount);
                    checkFrame.setVisible(true);
                }

                else if (source == checkFrame.listChecks)
                {
                    displayCheckList(currentAccount);
                    checkFrame.setVisible(true);
                }

                else if (source == checkFrame.listDeposits)
                {
                    displayDepositList(currentAccount);
                    checkFrame.setVisible(true);
                }
            }
        }
    }
    
    public static void playGalaga()
    {
        boolean play = false, bet = false;
        
        int userSelection = JOptionPane.showConfirmDialog(textArea,
                "This is a high-stakes version of the classic arcade game Galaga."
            + "\n\nIf you elect to wager on this game, you will be prompted to input an score you believe"
            + "\nyou can reach in Galaga.  If you reach this score, an equivalent dollar amount will be"
            + "\ndeposited into your account.  However, if you fail, this amount will be withdrawn."
            + "\n\nDo you wish to wager your hard earned livings?  Or are you too chicken?");
        
        switch (userSelection)
        {
            case JOptionPane.CANCEL_OPTION:
                play = false;
                bet = false;
                break;
                
            case JOptionPane.YES_OPTION:
                play = true;
                bet = true;
                break;
                
                
                
                
            case JOptionPane.NO_OPTION:
                play = true;
                bet = false;
                break;
        }
        
        if (play && bet)
        {
            if(currentAccount != null)
                {
                    String wagerStr = JOptionPane.showInputDialog(checkFrame, "Place your bet:");

                    while (!isValidInt(wagerStr))
                    {
                        wagerStr = JOptionPane.showInputDialog(checkFrame, "Invalid input."
                                + "\nPlace your bet:");
                    }
                    wager = Integer.parseInt(wagerStr);
                    JOptionPane.showMessageDialog(null, "MOVE: Arrow Keys"
                                            + "\nPAUSE: P"
                                            + "\nSHOOT: Space", "Galaga Instructions", JOptionPane.PLAIN_MESSAGE);
                    game = new Game(wager);
                }
                
                else
                {
                    JOptionPane.showMessageDialog(null, "No account activated.  Galaga will proceed without wagering.");
                    JOptionPane.showMessageDialog(null, "MOVE: Arrow Keys"
                                            + "\nPAUSE: P"
                                            + "\nSHOOT: Space", "Galaga Instructions", JOptionPane.PLAIN_MESSAGE);
                    wager = 0;
                    game = new Game();
                }
        }
        
        else if (play)
        {
            JOptionPane.showMessageDialog(null, "MOVE: Arrow Keys"
                                            + "\nPAUSE: P"
                                            + "\nSHOOT: Space", "Galaga Instructions", JOptionPane.PLAIN_MESSAGE);
            game = new Game();
        }

        if (play)
        {
            gameFrame = new JFrame();
            gameFrame.setTitle("Galaga");
            gameFrame.setUndecorated(true);
            gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            gameFrame.setVisible(true);
            gameFrame.requestFocus();
            gameFrame.add(game);
        }
    }
    
    public static void endGalaga(int score)
    {
        gameFrame.dispose();
        gameFrame = null;
        game = null;
        Level1.audio.stop();
        checkFrame.setVisible(true);
        checkFrame.requestFocus();
        
        if (wager != 0 && score >= wager)
        {
            textArea.setText("You win...this time.\n"
                           + "$" + wager + " has been deposited into your account.");
            currentAccount.processDeposit(wager);
        }
        
        else if (wager != 0)
        {
            textArea.setText("You fail.\n"
                           + "$" + wager + " has been withdrawn from your account.");
                        currentAccount.processCheck(wager, wager);

        }
    }
       
}