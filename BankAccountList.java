import java.io.Serializable;

public class BankAccountList implements Serializable {

   private BankAccount2[] BAList;
   private int ListSize = 0;

   public BankAccountList(int size) {

      ListSize = 0;
      BAList = new BankAccount2[size];
   }

   public void add(BankAccount2 acc) {

      BAList[ListSize] = acc;
      ListSize++;
   }

   public BankAccount2 searchByIndex(int number) {

      if (number >= 0 && number < ListSize && BAList[number] != null) {

         return BAList[number];
      }

      else {

         return null;
      }
   }

   public BankAccount2 searchByAccountNumber(String accNumber) {

      for (BankAccount2 number : BAList) {

         if (number != null && number.getAccountNumber().equals(accNumber)) {

            return number;
         }
      }

      return null;
   }

   public BankAccount2 searchByAccountName(String accName) {

      for (BankAccount2 name : BAList) {

         if (name != null && name.getAccountName().equals(accName)) {

            return name;
         }
      }

      return null;
   }

   public boolean update(BankAccount2 acc, String accountName, String accountNumber) {

      for (int i = 0; i < BAList.length; i++) {

         if (BAList[i] != null & BAList[i] == acc) {

            BAList[i].setAccountName(accountName);
            BAList[i].setAccountNumber(accountNumber);

            return true;
         }
      }

      return false;
   }

   public boolean delete(BankAccount2 acc) {

      for (int i = 0; i < BAList.length; i++) {

         if (BAList[i] != null & BAList[i] == acc) {

            BAList[i] = null;

            reAdjust();

            return true;
         }
      }

      return false;
   }

   public boolean compareAccountName(String accName) {

      for (BankAccount2 acc : BAList) {

         if (acc != null) {

            if (acc.getAccountName().equals(accName)) {

               return true;
            }

            else {
               return false;
            }
         }
      }

      return false;
   }

   public boolean compareAccountNumber(String accNumber) {

      for (BankAccount2 acc : BAList) {

         if (acc != null) {

            if (acc.getAccountNumber().equals(accNumber)) {

               return true;
            }
         }
      }

      return false;
   }

   public void interestAdd() {

      for (BankAccount2 acc : BAList) {

         if (acc != null && acc.getBalance() >= 5000) {

            acc.addInterest();
         }
      }
   }

   public void reAdjust() {

      BankAccount2[] temp = new BankAccount2[BAList.length];
      int j = 0;

      for (int i = 0; i < BAList.length; i++) {

         if (BAList[i] != null) {

            temp[j] = BAList[i];
            j++;
         }
      }

      BAList = temp.clone();
      ListSize--;

   }

   public void displayAllBA() {

      String block = """
            |--------------------|
            |                    |
            |  All Bank Account  |
            |                    |
            |--------------------|
            """;

      System.out.println(block);

      for (BankAccount2 acc : BAList) {

         if (acc != null) {
            System.out.println("--------------------------");
            System.out.println(acc);
            System.out.println("--------------------------");
         }
      }
   }

   public StringBuilder toBuilder() {

      StringBuilder list = new StringBuilder();

      for (BankAccount2 accDetails : BAList) {
         if (accDetails != null) {
            list.append(" " + "\n" + "------------------------------------------" + "\n" +
                  "Account Number : " + accDetails.getAccountNumber() + "\n" +
                  "Account Name   : " + accDetails.getAccountName() + "\n" +
                  "Account Balance: " + accDetails.getBalance() + "\n" +
                  "------------------------------------------" + "\n");
         }
      }

      return list;
   }
}