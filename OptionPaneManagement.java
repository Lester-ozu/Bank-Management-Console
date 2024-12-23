import javax.naming.NameNotFoundException;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class OptionPaneManagement implements Serializable {

   static BankAccountList BAList = new BankAccountList(50);
   static boolean home, running = true, restart, firstTime = true;

   public static void main(String[] args) {

      try {

         FileInputStream fileIn = new FileInputStream("BAccountInfo.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         BAList = (BankAccountList) in.readObject();
         in.close();
         fileIn.close();
      } catch (IOException e) {

         JOptionPane.showMessageDialog(null, "Failed to load data!", "ERROR", JOptionPane.ERROR_MESSAGE);
      } catch (ClassNotFoundException e) {

         JOptionPane.showMessageDialog(null, "Info file not found!", "ERROR", JOptionPane.ERROR_MESSAGE);
      }

      while (running) {

         String accountNumber, accountName, tempHolder;
         double deposit = 0;
         int choice, option, index;
         BankAccount2 acc;
         home = false;
         restart = false;
         ImageIcon image = new ImageIcon("bank3.png");

         Font font = new Font("Montserrat", Font.BOLD, 14);

         UIManager.put("OptionPane.messageFont", font);

         String[] choice1 = { "Creaate Account", "Search Account", "Display All Account", "Set Interest", "Exit" };

         option = JOptionPane.showOptionDialog(null,
               "What would you like to do?",
               "Bank Account Management System",
               JOptionPane.DEFAULT_OPTION,
               JOptionPane.QUESTION_MESSAGE,
               image,
               choice1,
               choice1[0]);

         switch (option) {

            case 0:
               accountNumber = JOptionPane.showInputDialog("Account Number");

               if (accountNumber == null) {

                  continue;
               }

               while (accountNumber.equals("") || BAList.compareAccountNumber(accountNumber)
                     || !accountNumber.matches("\\d+") || !accountNumber.matches("^[1-9]\\d{7}$")) {

                  if (BAList.compareAccountNumber(accountNumber)) {

                     JOptionPane.showMessageDialog(null, "Account Number already exist!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     accountNumber = JOptionPane.showInputDialog("Account Number");
                  }

                  else if (accountNumber.equals("")) {

                     JOptionPane.showMessageDialog(null, "Please Input!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     accountNumber = JOptionPane.showInputDialog("Account Number");
                  }

                  else if (!accountNumber.matches("\\d+")) {

                     JOptionPane.showMessageDialog(null, "Please input numbers only!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);

                     accountNumber = JOptionPane.showInputDialog("Account Number");
                  }

                  else if (!accountNumber.matches("^[1-9]\\d{7}$")) {

                     JOptionPane.showMessageDialog(null, "Please input exactly 8 numbers!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);

                     accountNumber = JOptionPane.showInputDialog("Account Number");
                  }

                  if (accountNumber == null) {

                     restart = true;
                     break;
                  }
               }

               if (restart) {

                  continue;
               }

               accountName = JOptionPane.showInputDialog("Account Name");

               if (accountName == null) {

                  continue;
               }

               while (BAList.compareAccountName(accountName) || accountName.equals("") || accountName.matches("\\d+")) {

                  if (BAList.compareAccountName(accountName)) {

                     JOptionPane.showMessageDialog(null, "Account Name already exist!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     accountName = JOptionPane.showInputDialog("Account Name");
                  }

                  else if (accountName.equals("")) {

                     JOptionPane.showMessageDialog(null, "Please Input!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     accountName = JOptionPane.showInputDialog("Account Name");
                  }

                  else if (accountName.matches("\\d+")) {

                     JOptionPane.showMessageDialog(null, "Please do not input numbers!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     accountName = JOptionPane.showInputDialog("Account Name");
                  }

                  if (accountName == null) {

                     restart = true;
                     break;
                  }
               }

               if (restart) {

                  continue;
               }

               tempHolder = JOptionPane.showInputDialog("Initial Deposit");

               if (tempHolder == null) {

                  continue;
               }

               while (!tempHolder.matches("\\d+") || tempHolder.equals("")) {

                  if (tempHolder.equals("")) {

                     JOptionPane.showMessageDialog(null, "Please Input!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     tempHolder = JOptionPane.showInputDialog("Initial Deposit");
                  }

                  else if (!tempHolder.matches("\\d+")) {

                     JOptionPane.showMessageDialog(null, "Please enter a valid input!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     tempHolder = JOptionPane.showInputDialog("Initial Deposit");
                  }

                  if (accountName == null) {

                     restart = true;
                     break;
                  }
               }

               if (restart) {

                  continue;
               }

               deposit = Double.parseDouble(tempHolder);

               BankAccount2 newAccount = new BankAccount2(accountName, accountNumber);
               newAccount.setBalance(deposit);

               if (newAccount.getBalance() >= 5000) {

                  newAccount.addInterest();
               }

               BAList.add(newAccount);

               int decide = JOptionPane.showConfirmDialog(null,
                     "Would you like to make another transaction?",
                     "Confirm",
                     JOptionPane.YES_NO_OPTION);

               switch (decide) {

                  case 0:
                     continue;

                  case 1:

                     running = false;
                     break;

                  case -1:

                     running = false;
                     break;
               }

               break;

            case 1:

               String[] choice2 = { "By Index", "By Account Name", "By Account Number" };
               choice = JOptionPane.showOptionDialog(null,
                     "Please pick your preferred option",
                     "Search Account",
                     JOptionPane.DEFAULT_OPTION,
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     choice2,
                     choice2[0]);

               switch (choice) {

                  case 0:

                     tempHolder = JOptionPane.showInputDialog("Search Account: By Index");

                     if (tempHolder == null) {

                        continue;
                     }

                     while (!tempHolder.matches("\\d+")) {

                        JOptionPane.showMessageDialog(null, "Please input a valid number!", "ERROR",
                              JOptionPane.ERROR_MESSAGE);
                        tempHolder = JOptionPane.showInputDialog("Search Account: By Index");

                        if (tempHolder == null) {

                           restart = true;
                           break;
                        }
                     }

                     if (restart) {

                        continue;
                     }

                     index = Integer.parseInt(tempHolder);
                     acc = BAList.searchByIndex(index);

                     if (acc != null) {

                        accountOptions(acc);
                     }

                     else {

                        JOptionPane.showMessageDialog(null, "Account does not exist!", "ERROR",
                              JOptionPane.ERROR_MESSAGE);
                     }

                     if (home) {

                        continue;
                     }
                     break;

                  case 1:

                     accountName = JOptionPane.showInputDialog("Search Account: By Account Name");

                     if (accountName == null) {

                        continue;
                     }

                     while (accountName.matches("\\d+") || accountName.equals("")) {

                        if (accountName.matches("\\d+")) {

                           JOptionPane.showMessageDialog(null, "Please input!", "ERROR",
                                 JOptionPane.ERROR_MESSAGE);
                           accountName = JOptionPane.showInputDialog("Search Account: By Account Name");

                        }

                        else if (accountName.matches("\\d+")) {

                           JOptionPane.showMessageDialog(null, "Please input a valid number!", "ERROR",
                                 JOptionPane.ERROR_MESSAGE);
                           accountName = JOptionPane.showInputDialog("Search Account: By Account Name");

                        }

                        if (accountName == null) {

                           restart = true;
                           break;
                        }
                     }

                     if (restart) {

                        continue;
                     }

                     acc = BAList.searchByAccountName(accountName);

                     if (acc != null) {

                        accountOptions(acc);
                     }

                     else {

                        JOptionPane.showMessageDialog(null, "Account does not exist!", "ERROR",
                              JOptionPane.ERROR_MESSAGE);
                     }

                     if (home) {

                        continue;
                     }
                     break;

                  case 2:

                     accountNumber = JOptionPane.showInputDialog("Search Account: By Account Number");

                     if (accountNumber == null) {

                        continue;
                     }

                     while (!accountNumber.matches("\\d+") || !accountNumber.matches("^[1-9]\\d{7}$")) {

                        if (!accountNumber.matches("\\d+")) {

                           JOptionPane.showMessageDialog(null, "Please enter a valid input!", "ERROR",
                                 JOptionPane.ERROR_MESSAGE);
                           accountNumber = JOptionPane.showInputDialog("Search Account: By Account Number");
                        }

                        else if (!accountNumber.matches("^[1-9]\\d{7}$")) {

                           JOptionPane.showMessageDialog(null, "Please input exactly 8 numbers!", "ERROR",
                                 JOptionPane.ERROR_MESSAGE);
                           accountNumber = JOptionPane.showInputDialog("Search Account: By Account Number");
                        }

                        if (accountNumber == null) {

                           restart = true;
                           break;
                        }
                     }

                     if (restart) {

                        continue;
                     }

                     acc = BAList.searchByAccountNumber(accountNumber);

                     if (acc != null) {

                        accountOptions(acc);
                     }

                     else {

                        JOptionPane.showMessageDialog(null, "Account does not exist!", "ERROR",
                              JOptionPane.ERROR_MESSAGE);
                     }

                     if (home) {

                        continue;
                     }
                     break;

                  case -1:
                     break;
               }

               break;

            case 2:

               // String[] data = BAList.toArray();

               if (BAList.searchByIndex(0) == null) {

                  JOptionPane.showMessageDialog(null, "There are no existing accounts!",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
               }

               else {

                  StringBuilder list = BAList.toBuilder();

                  JTextArea textArea = new JTextArea(list.toString());
                  textArea.setEditable(false);
                  textArea.setLineWrap(false);
                  textArea.setWrapStyleWord(false);

                  JScrollPane scrollPane = new JScrollPane(textArea);
                  scrollPane.setPreferredSize(new Dimension(400, 300));

                  JOptionPane.showMessageDialog(null, scrollPane, "Bank Account List", JOptionPane.PLAIN_MESSAGE);
               }

               decide = JOptionPane.showConfirmDialog(null,
                     "Would you like to make another transaction?",
                     "Confirm",
                     JOptionPane.YES_NO_OPTION);

               switch (decide) {
                  case 0:
                     continue;

                  case 1:
                     running = false;
                     break;

                  case -1:
                     running = false;
                     break;
               }

            case 4:
               running = false;

               try {

                  FileOutputStream fileOut = new FileOutputStream("BAccountInfo.ser");
                  ObjectOutputStream out = new ObjectOutputStream(fileOut);
                  out.writeObject(BAList);
                  out.close();
                  fileOut.close();

               } catch (IOException e) {

                  JOptionPane.showMessageDialog(null, "Failed to save data!", "ERROR", JOptionPane.ERROR_MESSAGE);
               }

               break;

            case 3:

               tempHolder = JOptionPane.showInputDialog("Set Interest Rate");

               if (tempHolder == null) {

                  continue;
               }

               while (tempHolder.equals("") || !tempHolder.matches("\\d+")) {

                  if (!tempHolder.equals("")) {

                     JOptionPane.showMessageDialog(null, "Please input!", "ERROR", JOptionPane.ERROR_MESSAGE);
                     tempHolder = JOptionPane.showInputDialog("Set Interest Rate");
                  }

                  else if (tempHolder.matches("\\d+")) {

                     JOptionPane.showMessageDialog(null, "Please enter a valid input!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     tempHolder = JOptionPane.showInputDialog("Set Interest Rate");
                  }

                  if (tempHolder == null) {

                     restart = true;
                     break;
                  }
               }

               if (restart) {

                  continue;
               }

               double rate = Double.parseDouble(tempHolder);

               BankAccount2.setInterestRate(rate);

               BAList.interestAdd();

               JOptionPane.showMessageDialog(null,
                     "All accounts got a " + rate + "% increased based on their current balance",
                     "Set Interest Rate Transaction", JOptionPane.INFORMATION_MESSAGE);

               break;

            case -1:

               try {

                  FileOutputStream fileOut = new FileOutputStream("BAccountInfo.ser");
                  ObjectOutputStream out = new ObjectOutputStream(fileOut);
                  out.writeObject(BAList);
                  JOptionPane.showMessageDialog(null, "Data saved successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                  out.close();
                  fileOut.close();

               } catch (IOException e) {

                  JOptionPane.showMessageDialog(null, "Failed to save data!", "ERROR", JOptionPane.ERROR_MESSAGE);
               }

               running = false;
               break;
         }
      }

      try {

         FileOutputStream fileOut = new FileOutputStream("BAccountInfo.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(BAList);
         JOptionPane.showMessageDialog(null, "Data saved successfully!", "Success",
               JOptionPane.INFORMATION_MESSAGE);
         out.close();
         fileOut.close();

      } catch (IOException e) {

         JOptionPane.showMessageDialog(null, "Failed to save data!", "ERROR", JOptionPane.ERROR_MESSAGE);
      }

   }

   public static void accountOptions(BankAccount2 acc) {

      boolean running = true;

      while (running) {

         String accountName, accountNumber, tempHolder;
         int choice;
         restart = false;

         String[] choice1 = { "Deposit", "Withdraw", "Update", "Home", "Close" };

         choice = JOptionPane.showOptionDialog(null,
               "\nAccount Number : " + acc.getAccountNumber() + "\n" +
                     "Account Name     :  " + acc.getAccountName() + "\n" +
                     "Account Balance : " + acc.getBalance() + "\n",
               "Account Details and Options",
               JOptionPane.DEFAULT_OPTION,
               JOptionPane.QUESTION_MESSAGE,
               null,
               choice1,
               choice1[0]);

         switch (choice) {

            case 0:

               tempHolder = JOptionPane.showInputDialog("How much do you want to deposit?");

               if (tempHolder == null) {

                  continue;
               }

               while (!tempHolder.matches("\\d+")) {

                  JOptionPane.showMessageDialog(null, "Please input a valid number!", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                  tempHolder = JOptionPane.showInputDialog("How much do you want to deposit?");

                  if (tempHolder == null) {

                     restart = true;
                     break;
                  }
               }

               if (restart) {

                  continue;
               }

               double deposit = Double.parseDouble(tempHolder);

               acc.deposit(deposit);

               if (acc.getBalance() >= 5000) {

                  JOptionPane.showMessageDialog(null, "Your account got a " +
                        acc.getBalance() * acc.getInterestRate() +
                        " increased!", "Deposit Transaction", JOptionPane.INFORMATION_MESSAGE);

                  acc.addInterest();
               }
               break;

            case 1:

               tempHolder = JOptionPane.showInputDialog("How much do you want to withdraw?");

               if (tempHolder == null) {

                  continue;
               }

               while (!tempHolder.matches("\\d+")) {

                  JOptionPane.showMessageDialog(null, "Please input a valid number!", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                  tempHolder = JOptionPane.showInputDialog("How much do you want to withdraw?");

                  if (tempHolder == null) {

                     restart = true;
                     break;
                  }
               }

               if (restart) {

                  continue;
               }

               double withdraw = Double.parseDouble(tempHolder);

               if (acc.withdraw(withdraw)) {

                  JOptionPane.showMessageDialog(null, "Withdraw Successful!", "Withdraw Transaction",
                        JOptionPane.INFORMATION_MESSAGE);
               }

               else {

                  JOptionPane.showMessageDialog(null, "Withdraw Failed", "Withdraw Transaction",
                        JOptionPane.INFORMATION_MESSAGE);
               }

               if (acc.getBalance() >= 5000) {

                  JOptionPane.showMessageDialog(null, "Your account got a " +
                        acc.getBalance() * acc.getInterestRate() +
                        " increased!", "Deposit Transaction", JOptionPane.INFORMATION_MESSAGE);

                  acc.addInterest();
               }

               break;

            case 2:

               accountName = JOptionPane.showInputDialog("Current Account Number : " + acc.getAccountNumber() + "\n" +
                     "Current Account Name   : " + acc.getAccountName() + "\n" +
                     "\n" + "New Account Name : ");

               if (accountName == null) {

                  continue;
               }

               while (accountName.equals("") || BAList.compareAccountName(accountName) || accountName.matches("\\d+")) {

                  if (accountName.equals("")) {
                     JOptionPane.showMessageDialog(null, "Please input!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     accountName = JOptionPane
                           .showInputDialog("Current Account Number: " + acc.getAccountNumber() + "\n" +
                                 "Current Account Name  : " + acc.getAccountName() + "\n" +
                                 "\n" + "New Account Name : ");
                  }

                  else if (BAList.compareAccountName(accountName)) {

                     JOptionPane.showMessageDialog(null, "Account Name already exist!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     accountName = JOptionPane
                           .showInputDialog("Current Account Number: " + acc.getAccountNumber() + "\n" +
                                 "Current Account Name  : " + acc.getAccountName() + "\n" +
                                 "\n" + "New Account Name : ");
                  }

                  else if (accountName.matches("\\d+")) {

                     JOptionPane.showMessageDialog(null, "Please enter a valid input!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     accountName = JOptionPane
                           .showInputDialog("Current Account Number: " + acc.getAccountNumber() + "\n" +
                                 "Current Account Name  : " + acc.getAccountName() + "\n" +
                                 "\n" + "New Account Name : ");
                  }

                  if (accountName == null) {

                     restart = true;
                     break;
                  }
               }

               if (restart) {

                  continue;
               }

               accountNumber = JOptionPane.showInputDialog("Current Account Number: " + acc.getAccountNumber() + "\n" +
                     "Current Account Name  : " + acc.getAccountName() + "\n" +
                     "\n" + "New Account Number : ");

               if (accountNumber == null) {

                  continue;
               }

               while (BAList.compareAccountNumber(accountNumber) || !accountNumber.matches("\\d+")
                     || !accountNumber.matches("^[1-9]\\d{7}$")) {

                  if (BAList.compareAccountNumber(accountNumber)) {
                     JOptionPane.showMessageDialog(null, "Account Number already exist!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     accountNumber = JOptionPane
                           .showInputDialog("Current Account Number: " + acc.getAccountNumber() + "\n" +
                                 "Current Account Name  : " + acc.getAccountName() + "\n" +
                                 "\n" + "New Account Number : ");
                  }

                  else if (!accountNumber.matches("\\d+")) {
                     JOptionPane.showMessageDialog(null, "Please enter a valid input!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     accountNumber = JOptionPane
                           .showInputDialog("Current Account Number: " + acc.getAccountNumber() + "\n" +
                                 "Current Account Name  : " + acc.getAccountName() + "\n" +
                                 "\n" + "New Account Number : ");
                  }

                  else if (!accountNumber.matches("^[1-9]\\d{7}$")) {
                     JOptionPane.showMessageDialog(null, "Please input exactly 8 numbers!", "ERROR",
                           JOptionPane.ERROR_MESSAGE);
                     accountNumber = JOptionPane
                           .showInputDialog("Current Account Number: " + acc.getAccountNumber() + "\n" +
                                 "Current Account Name  : " + acc.getAccountName() + "\n" +
                                 "\n" + "New Account Number : ");
                  }

                  if (accountNumber == null) {

                     restart = true;
                     break;
                  }
               }

               if (restart) {

                  continue;
               }

               if (BAList.update(acc, accountName, accountNumber)) {

                  JOptionPane.showMessageDialog(null, "Accoun Updated Successfuly!", "Account Update Transaction",
                        JOptionPane.INFORMATION_MESSAGE);
               }

               else {

                  JOptionPane.showMessageDialog(null, "Accoun Update Failed!", "Account Update Transaction",
                        JOptionPane.ERROR_MESSAGE);
               }

               break;

            case 3:

               home = true;
               running = false;
               break;

            case 4:

               choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the account?",
                     "Account Delete Transaction", JOptionPane.YES_NO_OPTION);

               if (choice == 0) {

                  if (BAList.delete(acc)) {

                     JOptionPane.showMessageDialog(null, "Account Deleted Successfully", "Account Delete Transaction",
                           JOptionPane.INFORMATION_MESSAGE);
                     running = false;
                  }

                  else {

                     JOptionPane.showMessageDialog(null, "Account Delete Failed!", "Account Delete Transaction",
                           JOptionPane.ERROR_MESSAGE);
                  }
               }

               if (choice == -1) {

                  break;
               }

               else {

                  continue;
               }

            case -1:

               running = false;
               break;
         }
      }
   }
}