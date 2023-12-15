package transactionSum;

import java.sql.Timestamp;

public class TransactionSum {
    private int id_account;
    private Timestamp start , end ;

    public TransactionSum(int id_account, Timestamp start, Timestamp end) {
        this.id_account = id_account;
        this.start = start;
        this.end = end;
    }

    public int getId_account() {
        return id_account;
    }

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getEnd() {
        return end;
    }
}
