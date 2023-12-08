package balance;

import account.Account;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BalanceCrud implements BalanceOperation{
    Connection connection;

    public BalanceCrud(Connection connection) {
        this.connection = connection;
    }

    @Override
    public double getAmount(Timestamp dataTime , Account account) {
        String sql = "select amount from balance where date_time <= ? and account_id = ? order by date_time desc limit 1";
        double amount = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setTimestamp(1,dataTime);
            statement.setInt(2,account.getAccount_id());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                amount = resultSet.getDouble("amount");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return amount;
    }
@Override
   public double getAmountNow( Account account){
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDateTime = now.format(formatter);

    String sql = "select amount from balance where date_time <= ? and account_id = ? order by date_time desc limit 1";
    double amount = 0;
    try (PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setTimestamp(1, Timestamp.valueOf(formattedDateTime));
        statement.setInt(2,account.getAccount_id());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            amount = resultSet.getDouble("amount");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return amount;
 }
@Override
    public List<Balance> getAmountBetween(Account account , Timestamp start , Timestamp end){
    List<Balance> balances = new ArrayList<>();
    String sql = "select amount, date_time from balance where (date_time >= ? and date_time <= ?) and account_id = ? order by date_time desc limit 1";

    try (PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setTimestamp(1, start);
        statement.setTimestamp(2, end);
        statement.setInt(3,account.getAccount_id());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
           balances.add(new Balance(
                   resultSet.getDouble("amount"),
                   resultSet.getTimestamp("date_time")
           ));
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return balances;
    }


}





