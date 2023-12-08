package account;

import balance.Balance;
import currency.Currency;
import transaction.Transaction;

import java.util.List;

public class Account {
    private int account_id;
    private String account_name;
    private Currency currency;
    private Balance balance;
    private double intialAmount;
    private List<Transaction> transactions;
    private String account_type;

    public Account(int account_id, String account_name, Currency currency, Balance balance, List<Transaction> transactions, String account_type) {
        this.account_id = account_id;
        this.account_name = account_name;
        this.currency = currency;
        this.balance = balance;
        this.transactions = transactions;
        this.account_type = account_type;
    }

    public Account(String account_name, Currency currency, double intialAmount, String account_type) {
        this.account_name = account_name;
        this.currency = currency;
        this.intialAmount = intialAmount;
        this.account_type = account_type;
    }





    public Account(int account_id, String account_name, Currency currency, Balance balance, List<Transaction> transactions) {
        this.account_id = account_id;
        this.account_name = account_name;
        this.currency = currency;
        this.balance = balance;
        this.transactions = transactions;
    }

    public int getAccount_id() {
        return account_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Balance getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getAccount_type() {
        return account_type;
    }

    public double getIntialAmount() {
        return intialAmount;
    }

    @Override
    public String toString() {
        return "account{" +
                "account_id=" + account_id +
                ", account_name='" + account_name + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                ", transactions=" + transactions +
                ", account_type='" + account_type + '\'' +
                '}';
    }
}
