import CurrencyValue.CurrencyValue;
import account.Account;
import account.AccountCrud;
import category.Category;
import category.CategoryCrud;
import currency.Currency;
import currency.CurrencyCrud;
import transaction.Transaction;
import transaction.TransactionCrud;
import transfer.Transfer;
import transfer.TransferCrud;
import CurrencyValue.CurrencyValueOperatons;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBconnection.getConnection();
        Currency currency = new Currency("euro","eur");

      /*  Transaction transaction = new Transaction(5,"salary",10000,"credit");
        TransactionCrud insert = new TransactionCrud(connection);
       System.out.println(insert.transaction(transaction));*/

      // CurrencyValue currencyValue = new CurrencyValue(2,1,4800);
      //  CurrencyValueOperatons currencyValueOperatons = new CurrencyValueOperatons(connection);
      // currencyValueOperatons.insert(currencyValue);
      // System.out.println(currencyValueOperatons.ConvertByAvarage(7, 6, 1));


       // Account account = new Account("banque2" , currency ,30000,"mobile1");
        Transfer transfer = new Transfer(7,6,1);
        CurrencyValueOperatons currencyValueOperatons = new CurrencyValueOperatons(connection);
        TransferCrud crud = new TransferCrud(connection , currencyValueOperatons);
        crud.execute(transfer);
       // System.out.println(crud.findAll());
       // AccountCrud accountCrud = new AccountCrud(connection);
       // accountCrud.insert(account);

        // System.out.println(insert.insert(account));

        /*Category category = new Category("get_money" , "credit");
        CategoryCrud categoryCrud = new CategoryCrud(connection);
        categoryCrud.insert(category);*/

       /* Transaction transaction = new Transaction(6,category,10000);

        TransactionCrud transactionCrud = new TransactionCrud(connection);
        System.out.println(transactionCrud.transaction(transaction));*/
    }
}