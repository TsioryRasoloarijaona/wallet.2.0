package account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountCrud implements AccountOperation{
    Connection connection;

    public AccountCrud(Connection connection) {
        this.connection = connection;
    }
    int currency_id ;
    int account_id ;
    @Override
    public Account insert(Account account)  {
        String sql1 = "select currency_id from currency where name ilike ? ";
        String sql2 = "insert into account(account_name , currency_id , account_type) values (?,?,?)";
        String sql4 = "select account_id from account order by account_id desc limit 1";
        String sql3 = "insert into balance (account_id , amount) values (?,?)";


        try(PreparedStatement statement = connection.prepareStatement(sql1)) {
            statement.setString(1,account.getCurrency().getName());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                currency_id = resultSet.getInt("currency_id");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (PreparedStatement statement = connection.prepareStatement(sql2)){
            statement.setString(1,account.getAccount_name());
            statement.setInt(2,currency_id);
            statement.setString(3,account.getAccount_type());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (PreparedStatement statement = connection.prepareStatement(sql4)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                account_id = resultSet.getInt("account_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (PreparedStatement statement = connection.prepareStatement(sql3)){
            statement.setInt(1,account_id);
            statement.setDouble(2,account.getIntialAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  account;
    }
}
