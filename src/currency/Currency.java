package currency;

import java.security.Timestamp;

public class Currency{
    private int currency_id ;
   private String name;
   private String code;

    public Currency(int currency_id, String name, String code) {
        this.currency_id = currency_id;
        this.name = name;
        this.code = code;
    }

    public Currency(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Currency(String name) {
        this.name = name;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currency_id=" + currency_id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
