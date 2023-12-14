package CurrencyValue;

import java.sql.Timestamp;

public class CurrencyValue {
    private int currencyValueId , currencySourceId , CurrencyDestinationId ;
    private double amount ;
    private Timestamp dateEffect ;

    public CurrencyValue(int currencyValueId, int currencySourceId, int currencyDestinationId, double amount, Timestamp dateEffect) {
        this.currencyValueId = currencyValueId;
        this.currencySourceId = currencySourceId;
        CurrencyDestinationId = currencyDestinationId;
        this.amount = amount;
        this.dateEffect = dateEffect;
    }

    public CurrencyValue(int currencySourceId, int currencyDestinationId, double amount) {
        this.currencySourceId = currencySourceId;
        CurrencyDestinationId = currencyDestinationId;
        this.amount = amount;
    }

    public int getCurrencyValueId() {
        return currencyValueId;
    }

    public int getCurrencySourceId() {
        return currencySourceId;
    }

    public int getCurrencyDestinationId() {
        return CurrencyDestinationId;
    }

    public double getAmount() {
        return amount;
    }

    public Timestamp getDateEffect() {
        return dateEffect;
    }

}
