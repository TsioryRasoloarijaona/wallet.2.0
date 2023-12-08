package transaction;


import java.sql.Timestamp;

public class Transaction {
    private int transaction_id;

    private int account_id;
    private String label ;
    private double amount ;
    private Timestamp data_time;
    private String transaction_type;



    public Transaction(int transaction_id, String label, double amount, Timestamp data_time, String transaction_type) {
        this.transaction_id = transaction_id;
        this.label = label;
        this.amount = amount;
        this.data_time = data_time;
        this.transaction_type = transaction_type;
    }

    public Transaction(int account_id, String label, double amount, String transaction_type) {
        this.account_id = account_id;
        this.label = label;
        this.amount = amount;
        this.transaction_type = transaction_type;
    }

    public Transaction(String label, double amount, Timestamp data_time, String transaction_type) {
        this.label = label;
        this.amount = amount;
        this.data_time = data_time;
        this.transaction_type = transaction_type;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public String getLabel() {
        return label;
    }

    public double getAmount() {
        return amount;
    }

    public Timestamp getData_time() {
        return data_time;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public int getAccount_id() {
        return account_id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_id=" + transaction_id +
                ", label='" + label + '\'' +
                ", amount=" + amount +
                ", data_time=" + data_time +
                ", transaction_type='" + transaction_type + '\'' +
                '}';
    }
}
