package transfer;

import CurrencyValue.CurrencyValueOperatons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransferCrud implements TransferOprations {
    Connection connection;

    public TransferCrud(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int getAccountCurrencyCredit(Transfer transfer) {
        int accountCurrency = 0;
        String sql1 = "select currency_id from account where account_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql1)) {
            statement.setInt(1, transfer.getCredit_account());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accountCurrency = resultSet.getInt("currency_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountCurrency;
    }

    @Override
    public int getAccountCurrencyDebit(Transfer transfer) {
        int accountCurrency = 0;
        String sql1 = "select name from currency inner join account on currency.currency_id = account.currency_id where account_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql1)) {
            statement.setInt(1, transfer.getCredit_account());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accountCurrency = resultSet.getInt("currency_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountCurrency;

    }

    @Override
    public String getAccountType(Transfer transfer) {
        String account_type = null;

        String sql1 = "select account_type from account where account_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql1)) {
            statement.setInt(1, transfer.getDebit_account());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                account_type = resultSet.getString("account_type");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account_type;
    }

    @Override
    public Transfer insertTransfert(Transfer transfer) {
        String sql = "insert into transfer_history (debit_account , credit_account , amount) values (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transfer.getDebit_account());
            statement.setInt(2, transfer.getCredit_account());
            statement.setDouble(3, transfer.getAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transfer;
    }

    @Override
    public double getAmountDebit(Transfer transfer) {
        double amount = 0;
        String sql2 = "select amount from balance where account_id = ? order by date_time desc limit 1";
        try (PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setInt(1, transfer.getDebit_account());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getDouble("amount");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return amount;

    }

    @Override
    public double getAmountCredit(Transfer transfer) {
        double amount = 0;
        String sql2 = "select amount from balance where account_id = ? order by date_time desc limit 1";
        try (PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setInt(1, transfer.getCredit_account());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getDouble("amount");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return amount;
    }

    @Override
    public Transfer insertBalance(Transfer transfer) {
        String sql1 = "insert into balance (account_id , amount) values (?,?),(?,?)";
        if (Objects.equals(getAccountCurrencyDebit(transfer), getAccountCurrencyCredit(transfer))) {
            double newAmountDebitAccount = getAmountDebit(transfer) - transfer.getAmount();
            double newAmountCreditAccount = getAmountCredit(transfer) + transfer.getAmount();

            try (PreparedStatement statement = connection.prepareStatement(sql1)) {
                statement.setInt(1, transfer.getDebit_account());
                statement.setDouble(2, newAmountDebitAccount);
                statement.setInt(3, transfer.getCredit_account());
                statement.setDouble(4, newAmountCreditAccount);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            double newAmountDebitAccount = getAmountDebit(transfer) - transfer.getAmount();
            CurrencyValueOperatons currencyValueOperatons = null;
            double credit = currencyValueOperatons.ConvertByAvarage(transfer.getDebit_account(), transfer.getCredit_account(), transfer.getAmount());

            try (PreparedStatement statement = connection.prepareStatement(sql1)) {
                statement.setInt(1, transfer.getDebit_account());
                statement.setDouble(2, newAmountDebitAccount);
                statement.setInt(3, transfer.getCredit_account());
                statement.setDouble(4, credit);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
            return null;

    }

        @Override
        public Transfer insertTransaction (Transfer transfer){

            String sql3 = "insert into transaction (account_id,label,  amount, transaction_type) values (?,?,?,?),(?,?,?,?)";
            if (Objects.equals(getAccountCurrencyDebit(transfer), getAccountCurrencyCredit(transfer))) {
                try (PreparedStatement statement = connection.prepareStatement(sql3)) {
                    statement.setInt(1, transfer.getDebit_account());
                    statement.setString(2, "transfer");
                    statement.setDouble(3, transfer.getAmount());
                    statement.setString(4, "debit");

                    statement.setInt(5, transfer.getCredit_account());
                    statement.setString(6, "transfer");
                    statement.setDouble(7, transfer.getAmount());
                    statement.setString(8, "credit");
                    statement.executeUpdate();


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            } else {
                CurrencyValueOperatons currencyValueOperatons = null;
                double credit = currencyValueOperatons.ConvertByAvarage(transfer.getDebit_account(), transfer.getCredit_account(), transfer.getAmount());
                try (PreparedStatement statement = connection.prepareStatement(sql3)) {
                    statement.setInt(1, transfer.getDebit_account());
                    statement.setString(2, "transfer");
                    statement.setDouble(3, transfer.getAmount());
                    statement.setString(4, "debit");

                    statement.setInt(5, transfer.getCredit_account());
                    statement.setString(6, "transfer");
                    statement.setDouble(7, credit);
                    statement.setString(8, "credit");
                    statement.executeUpdate();


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            return transfer;
        }
        @Override
        public Transfer execute (Transfer transfer){
            if (transfer.getDebit_account() == transfer.getCredit_account()) {
                System.out.println("failed");
            } else {
                if (Objects.equals(getAccountType(transfer), "cash")) {

                    if (getAmountDebit(transfer) >= transfer.getAmount()) {
                        insertTransfert(transfer);
                        insertBalance(transfer);
                        insertTransaction(transfer);
                    } else {
                        System.out.println("failed");
                    }
                } else {
                    insertTransfert(transfer);
                    insertBalance(transfer);
                    insertTransaction(transfer);
                }
            }
            return transfer;
        }
        ;
        @Override
        public List<Transfer> findAll () {
            List<Transfer> transfers = new ArrayList<>();
            String sql = "select * from transfer_history";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    transfers.add(new Transfer(
                            resultSet.getInt("debit_account"),
                            resultSet.getInt("credit_account"),
                            resultSet.getDouble("amount"),
                            resultSet.getTimestamp("date_time")
                    ));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return transfers;
        }
        ;


    }

