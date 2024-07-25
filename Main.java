public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addAccount(new Account("user1", "1234", 1000));
        bank.addAccount(new Account("user2", "5678", 500));

        ATM atm = new ATM(bank);
        atm.start();
    }
}
