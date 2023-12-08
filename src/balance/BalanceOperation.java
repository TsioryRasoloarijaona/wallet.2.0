package balance;

import account.Account;

import java.sql.Timestamp;
import java.util.List;

public interface BalanceOperation {
    double getAmount(Timestamp dataTime , Account account);
    double getAmountNow(  Account account);

    List<Balance> getAmountBetween(Account account , Timestamp start , Timestamp end);
}
