package transaction;

import account.Account;

public interface TransactionOperation {
        void insertBalance (Transaction transaction);

        void inserttransaction (Transaction transaction);
        Account transaction (Transaction transaction);
        double getAmount (Transaction transaction);

        String getAccountType (Transaction transaction);

        Account getAccount(Transaction transaction);
        int getCategory (Transaction transaction);
}
