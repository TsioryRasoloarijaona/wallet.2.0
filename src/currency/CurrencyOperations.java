package currency;

import java.util.List;

public interface CurrencyOperations {
    Currency insert (Currency currency);
    List<Currency> findAll ();
    List<Currency> findByName(String name);
}
