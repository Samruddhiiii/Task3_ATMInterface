import java.util.Scanner;

public class ATM {
    private Bank bank;
    private Scanner scanner;

    public ATM(Bank bank) {
        this.bank = bank;
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM");

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String userPin = scanner.nextLine();

        Account account = bank.authenticate(userId, userPin);
        if (account != null) {
            showMenu(account);
        } else {
            System.out.println("Invalid user ID or PIN.");
        }
    }

    private void showMenu(Account account) {
        while (true) {
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showTransactionHistory(account);
                    break;
                case 2:
                    withdraw(account);
                    break;
                case 3:
                    deposit(account);
                    break;
                case 4:
                    transfer(account);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void showTransactionHistory(Account account) {
        for (Transaction transaction : account.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    private void withdraw(Account account) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            account.addTransaction(new Transaction("Withdraw", amount, "2024-07-24"));
            System.out.println("Withdraw successful.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private void deposit(Account account) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            account.addTransaction(new Transaction("Deposit", amount, "2024-07-24"));
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private void transfer(Account account) {
        System.out.print("Enter recipient user ID: ");
        String recipientId = scanner.next();

        Account recipientAccount = bank.getAccount(recipientId);
        if (recipientAccount == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            recipientAccount.setBalance(recipientAccount.getBalance() + amount);
            account.addTransaction(new Transaction("Transfer", amount, "2024-07-24"));
            recipientAccount.addTransaction(new Transaction("Received Transfer", amount, "2024-07-24"));
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}
