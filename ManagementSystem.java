import java.util.Scanner;

public class ManagementSystem {

   static BankAccountList BAList = new BankAccountList(50);
   static boolean home;

   public static void main(String[] args) {

      while (true) {
         char option = 'a';
         String accountNumber, accountName, tempHolder;
         double deposit = 0;
         int choice, index;
         BankAccount2 acc;
         home = false;

         Scanner scanner = new Scanner(System.in);

         while (true) {

            System.out.print("\nBank Account Management System " +
                  "\n\n[O]pen Account " +
                  "\n[S]earch Account " +
                  "\n[D]isplay All Accounts" +
                  "\n[E]xit" +
                  "\n\n> ");

            option = Character.toLowerCase(scanner.next().charAt(0));

            if (!String.valueOf(option).matches(".") || String.valueOf(option).matches("\\d+")) {

               System.out.println("\nPlease choose a valid option!");
               continue;
            }

            else if (String.valueOf(option).length() > 1) {

               System.out.println("\nPlease input only one character!");
               continue;
            }

            else {

               break;
            }
         }

         switch (option) {

            case 'o':
               System.out.println("\nOpen Account");

               System.out.print("\nAccount Number  : ");
               accountNumber = scanner.next();

               while (!accountNumber.matches("\\d+")) {

                  System.out.println("Please input valid characters!");
                  System.out.print("\nAccount Number  : ");
                  accountNumber = scanner.next();
               }

               System.out.print("Account Name    : ");
               accountName = scanner.next();

               System.out.print("Initial Deposit : ");
               tempHolder = scanner.next();

               while (!tempHolder.matches("\\d+")) {

                  System.out.println("Please input valid numbers!");
                  System.out.print("Initial Deposit : ");
                  tempHolder = scanner.next();
               }

               deposit = Double.parseDouble(tempHolder);

               BankAccount2 newAccount = new BankAccount2(accountName, accountNumber);
               newAccount.setBalance(deposit);

               BAList.add(newAccount);

               System.out.print("\nWould you like to make another transaction [y/n]: ");
               char decide = Character.toLowerCase(scanner.next().charAt(0));

               if (decide == 'y') {

                  continue;
               }

               else {

                  break;
               }

            case 's':

               System.out.print("\n[1] By Index" +
                     "\n[2] By Account Name" +
                     "\n[3] By Account Number" +
                     "\n\n> ");
               tempHolder = scanner.next();

               while (Integer.parseInt(tempHolder) > 3 || Integer.parseInt(tempHolder) < 1
                     || !tempHolder.matches("\\d+")) {

                  System.out.println("Please choose a valid option!");
                  System.out.println("[1] By Index" +
                        "\n[2] By Account Name" +
                        "\n[3] By Account Number" +
                        "\n\n> ");
                  tempHolder = scanner.next();
               }

               choice = Integer.parseInt(tempHolder);

               switch (choice) {

                  case 1:

                     System.out.print("\nSearch Account: By Index \n> ");
                     tempHolder = scanner.next();

                     while (!tempHolder.matches("\\d+")) {

                        System.out.println("Please input a valid number!");
                        System.out.print("\nSearch Account: By Index \n> ");
                        tempHolder = scanner.next();
                     }

                     int num = Integer.parseInt(tempHolder);

                     acc = BAList.searchByIndex(num);

                     if (acc != null) {

                        accountOptions(acc);
                     }

                     else {

                        System.out.println("\nAccount does not exist!");
                     }

                     if (home) {

                        continue;
                     }
                     break;

                  case 2:

                     System.out.print("\nSearch Account: By Account Name \n> ");
                     accountName = scanner.next();

                     while (accountName.matches("\\d+")) {

                        System.out.println("Please input valid characters!");
                        System.out.print("\nSearch Account: By Account Name \n> ");
                        accountName = scanner.next();
                     }

                     acc = BAList.searchByAccountName(accountName);

                     if (acc != null) {

                        accountOptions(acc);
                     }

                     else {

                        System.out.println("\nAccount does not index!");
                     }

                     if (home) {

                        continue;
                     }
                     break;

                  case 3:

                     System.out.print("\nSearch Account: By Account Number \n> ");
                     accountNumber = scanner.next();

                     while (!accountNumber.matches("\\d+")) {

                        System.out.println("Please input a valid number!");
                        System.out.print("\nSearch Account: By Account Number \n> ");
                        accountNumber = scanner.next();
                     }

                     acc = BAList.searchByAccountNumber(accountNumber);

                     if (acc != null) {

                        accountOptions(acc);
                     }

                     else {

                        System.out.println("\nAccount does not index!");
                     }

                     if (home) {

                        continue;
                     }
                     break;
               }

               break;

            case 'd':

               System.out.println();
               BAList.displayAllBA();

               System.out.print("\nWould you like to make another transaction [y/n]: ");
               decide = Character.toLowerCase(scanner.next().charAt(0));

               while (!String.valueOf(decide).matches(".") || String.valueOf(decide).matches("\\d+")) {

                  System.out.println("\nPlease choose a valid option!");
                  System.out.print("\nWould you like to make another transaction [y/n]: ");
                  decide = Character.toLowerCase(scanner.next().charAt(0));
               }

               if (decide == 'y') {

                  continue;
               }

               else {

                  break;
               }

            case 'e':
               System.exit(0);
               break;
         }
      }
   }

   public static void accountOptions(BankAccount2 acc) {

      boolean running = true;

      while (running) {

         String accountName, accountNumber, tempHolder;
         char choice;

         Scanner scanner = new Scanner(System.in);

         System.out.println("\nAccount Number: " + acc.getAccountNumber());
         System.out.println("Account Name: " + acc.getAccountName());

         System.out.print("\n[D]eposit" +
               "\n[W]ithdraw" +
               "\n[U]pdate " +
               "\n[H]ome" +
               "\n[C]lose" +
               "\n\n> ");

         choice = Character.toUpperCase(scanner.next().charAt(0));

         while (!String.valueOf(choice).matches(".") || String.valueOf(choice).matches("\\d+")
               || String.valueOf(choice).length() > 1) {

            System.out.println("\nPlease choose a valid option!");
            System.out.print("\n[D]eposit" +
                  "\n[W]ithdraw" +
                  "\n[U]pdate " +
                  "\n[H]ome" +
                  "\n[C]lose" +
                  "\n\n> ");

            choice = Character.toUpperCase(scanner.next().charAt(0));
         }

         switch (choice) {

            case 'D':

               System.out.print("How much do you want to deposit? \n\n> ");
               tempHolder = scanner.next();

               while (!tempHolder.matches("\\d+")) {

                  System.out.println("Please input a valid number!");
                  System.out.print("How much do you want to deposit? \n\n> ");
                  tempHolder = scanner.next();
               }

               double deposit = Double.parseDouble(tempHolder);

               acc.deposit(deposit);
               break;

            case 'W':

               System.out.print("How much do you want to withdraw? \n\n> ");
               tempHolder = scanner.next();

               while (!tempHolder.matches("\\d+")) {

                  System.out.println("Please input a valid number!");
                  System.out.print("How much do you want to withdraw? \n\n> ");
                  tempHolder = scanner.next();
               }

               double withdraw = Double.parseDouble(tempHolder);

               if (acc.withdraw(withdraw)) {

                  System.out.println("Withdraw Successful!");
               }

               else {

                  System.out.println("Withdraw Failed!");
               }
               break;

            case 'U':

               System.out.println("Update Account\n");
               System.out.println("Current Account Number: " + acc.getAccountNumber());
               System.out.println("Current Account Name: " + acc.getAccountName());

               System.out.print("\nNew Account Name  : ");
               accountName = scanner.next();

               while (accountName.matches("\\d+")) {

                  System.out.println("Please enter a valid input!");
                  System.out.print("\nNew Account Name  : ");
                  accountName = scanner.next();
               }

               System.out.print("New Account Number: ");
               accountNumber = scanner.next();

               while (!accountNumber.matches("\\d+")) {

                  System.out.println("Please enter a valid input!");
                  System.out.print("\nNew Account Number  : ");
                  accountNumber = scanner.next();
               }

               if (BAList.update(acc, accountName, accountNumber)) {

                  System.out.println("Account Update Successfuly!");
               }

               else {

                  System.out.println("Account Update Failed!");
               }

               break;

            case 'H':

               home = true;
               running = false;
               break;

            case 'C':

               System.out.print("\nAre you sure you want to close the account [y/n]: ");
               choice = Character.toLowerCase(scanner.next().charAt(0));

               if (choice == 'y') {

                  if (BAList.delete(acc)) {

                     System.out.println("\nAccount Deleted Successfuly!");
                     running = false;
                  }

                  else {

                     System.out.println("\nAccount Delete Failed!");
                  }
               }

               else {

                  continue;
               }

               break;
         }
      }
   }
}