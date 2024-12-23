import java.util.ArrayList;

public class Part3 {

   public static void main(String[] args) {

      BankAccountList ListBA = new BankAccountList(10);

      BankAccount2 acc1 = new BankAccount2("PNB", "9160026");
      BankAccount2 acc2 = new BankAccount2("BPI", "891006");
      BankAccount2 acc3 = new BankAccount2("CB", "9167026");
      BankAccount2 acc4 = new BankAccount2("BPI", "847006");
      BankAccount2 acc5 = new BankAccount2("PNB", "980026");
      BankAccount2 acc6 = new BankAccount2("BPI", "841006");
      BankAccount2 acc7 = new BankAccount2("PNB", "9960026");
      BankAccount2 acc8 = new BankAccount2("BPI", "841006");
      BankAccount2 acc9 = new BankAccount2("PNB", "989760026");
      BankAccount2 acc10 = new BankAccount2("BPI", "84543006");

      ListBA.add(acc1);
      ListBA.add(acc2);
      ListBA.add(acc3);
      ListBA.add(acc4);
      ListBA.add(acc5);
      ListBA.add(acc6);
      ListBA.add(acc7);
      ListBA.add(acc8);
      ListBA.add(acc9);
      ListBA.add(acc10);

      System.out.println(ListBA.searchByIndex(1));
      System.out.println();

      System.out.println(ListBA.searchByAccountNumber("841006"));
      System.out.println();

      System.out.println(ListBA.searchByAccountName("PNB"));
      System.out.println();

      if (ListBA.update(ListBA.searchByIndex(1), "PNB", "12345678")) {

         System.out.println("Successfully Updated");
      }

      if (ListBA.delete(ListBA.searchByIndex(1))) {

         System.out.println("Successfully Deleted");
      }
   }
}