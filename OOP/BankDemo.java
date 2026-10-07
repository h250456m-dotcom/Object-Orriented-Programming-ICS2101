import java.util.ArrayList;
import java.util.List;

public class BankDemo {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("SAV-001", 1000.00, 200.00));
        accounts.add(new CurrentAccount("CUR-001", 500.00, 300.00));
        accounts.add(new SavingsAccount("SAV-002", 300.00, 200.00));
        accounts.add(new CurrentAccount("CUR-002", 100.00, 250.00));

        System.out.println("=== Opening balances ===");
        for (Account a : accounts) {
            System.out.printf("%s: %.2f%n", a.getAccountNumber(), a.getBalance());
        }

        System.out.println("\n=== Deposits (including an invalid one) ===");
        accounts.get(0).deposit(250.00);
        accounts.get(1).deposit(-50.00);

        System.out.println("\n=== Polymorphic withdrawals (via Account reference) ===");
        double[] withdrawals = {400.00, 700.00, 250.00, 300.00};
        for (int i = 0; i < accounts.size(); i++) {
            accounts.get(i).withdraw(withdrawals[i]);
        }
        // Edge case 1: SAV-002 (300, min 200) withdrawing 250 above is REJECTED.
        // Edge case 2: CUR-001 (500, limit 300) withdrawing 700 goes to -200 (overdraft within limit).

        System.out.println("\n=== Extra edge case: overdraft limit exceeded ===");
        accounts.get(1).withdraw(200.00);

        System.out.println("\n=== End of month processing ===");
        for (Account a : accounts) {
            a.endOfMonth();
        }

        System.out.println("\n=== Closing balances ===");
        for (Account a : accounts) {
            System.out.printf("%s: %.2f%n", a.getAccountNumber(), a.getBalance());
        }
    }
}
