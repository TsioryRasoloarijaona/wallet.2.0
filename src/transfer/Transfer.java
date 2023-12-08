package transfer;

import java.sql.Timestamp;

public class Transfer {
    private int transfer_id , debit_account, credit_account ;
    private double amount;
    private Timestamp date_time;

    public Transfer(int transfer_id, int debit_account, int credit_account, double amount, Timestamp date_time) {
        this.transfer_id = transfer_id;
        this.debit_account = debit_account;
        this.credit_account = credit_account;
        this.amount = amount;
        this.date_time = date_time;
    }

    public Transfer(int debit_account, int credit_account, double amount) {
        this.debit_account = debit_account;
        this.credit_account = credit_account;
        this.amount = amount;
    }

    public Transfer(int debit_account, int credit_account, double amount, Timestamp date_time) {
        this.debit_account = debit_account;
        this.credit_account = credit_account;
        this.amount = amount;
        this.date_time = date_time;
    }

    public int getTransfer_id() {
        return transfer_id;
    }

    public int getDebit_account() {
        return debit_account;
    }

    public int getCredit_account() {
        return credit_account;
    }

    public double getAmount() {
        return amount;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transfer_id=" + transfer_id +
                ", debit_account=" + debit_account +
                ", credit_account=" + credit_account +
                ", amount=" + amount +
                ", date_time=" + date_time +
                '}';
    }
}
