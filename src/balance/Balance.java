package balance;

import java.sql.Timestamp;

public class Balance {
    private int balance_id ;
    private Timestamp date_time;
    private double amount;

    public Balance(int balance_id, Timestamp date_time, double amount) {
        this.balance_id = balance_id;
        this.date_time = date_time;
        this.amount = amount;
    }

    public Balance(double amount) {
        this.amount = amount;
    }

    public Balance(double amount, Timestamp date_time) {
        this.amount = amount;
        this.date_time = date_time;
    }

    public int getBalance_id() {
        return balance_id;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "balance_id=" + balance_id +
                ", date_time=" + date_time +
                ", amount=" + amount +
                '}';
    }
}
