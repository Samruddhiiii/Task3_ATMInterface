import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getUserId(), account);
    }

    public Account authenticate(String userId, String userPin) {
        Account account = accounts.get(userId);
        if (account != null && account.getUserPin().equals(userPin)) {
            return account;
        }
        return null;
    }

    public Account getAccount(String userId) {
        return accounts.get(userId);
    }
}
