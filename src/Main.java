import account.Account;
import account.AccountCrud;
import currency.Currency;
import currency.CurrencyCrud;
import transaction.Transaction;
import transaction.TransactionCrud;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBconnection.getConnection();
        Currency currency = new Currency("ariary","Ar");

       /* Transaction transaction = new Transaction(4,"salary",10000,"credit");
        TransactionCrud insert = new TransactionCrud(connection);
       System.out.println(insert.transaction(transaction));*/

        /*Account account = new Account("banque1" , currency ,30000,"mobile1");
        AccountCrud insert = new AccountCrud(connection);
        System.out.println(insert.insert(account));*/
    }
}