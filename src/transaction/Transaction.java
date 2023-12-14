package transaction;


import category.Category;

import java.sql.Timestamp;

public class Transaction {
    private int transaction_id;

    private int account_id;
    private Category category;
    private double amount ;
    private Timestamp data_time;




    public Transaction(int transaction_id, String label, double amount, Timestamp data_time, String transaction_type) {
        this.transaction_id = transaction_id;

        this.amount = amount;
        this.data_time = data_time;

    }

    public Transaction(int account_id, Category category , double amount)  {
        this.account_id = account_id;
        this.category = category;
        this.amount = amount;

    }

    public Transaction( double amount, Timestamp data_time, Category category) {
        this.category = category;
        this.amount = amount;
        this.data_time = data_time;

    }

    public int getTransaction_id() {
        return transaction_id;
    }



    public double getAmount() {
        return amount;
    }

    public Timestamp getData_time() {
        return data_time;
    }

    public Category getCategory() {
        return category;
    }

    public int getAccount_id() {
        return account_id;
    }


}
