package transfer;

import java.util.List;

public interface TransferOprations {
    Transfer insertTransfert (Transfer transfer);
    Transfer insertBalance (Transfer transfer);
    Transfer insertTransaction (Transfer transfer);

    double getAmountDebit (Transfer transfer);
    double getAmountCredit (Transfer transfer);
    String getAccountType (Transfer transfer);

    Transfer execute(Transfer transfer);

    List<Transfer> findAll();

    int getAccountCurrencyCredit (Transfer transfer);
    int getAccountCurrencyDebit (Transfer transfer);

    int getCategoryDebit ();

    int getCategoryCreditCredit ();

}
