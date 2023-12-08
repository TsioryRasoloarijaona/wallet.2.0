package currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyCrud implements CurrencyOperations{
    Connection connection;

    public CurrencyCrud(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Currency insert(Currency currency) {
        String sql = "insert into currency (name , code ) values (?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,currency.getName());
            statement.setString(2,currency.getCode());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return currency;
    }

    @Override
    public List<Currency> findAll() {
        List<Currency> currencies = new ArrayList<>();
        String sql = "select * from currency";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                currencies.add(new Currency(
                        resultSet.getInt("currency_id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return currencies;
    }

    @Override
    public List<Currency> findByName(String name) {
        List<Currency> currencies = new ArrayList<>();
        String sql = "select * from currency where name ilike ? or code ilike ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,name);
            statement.setString(2,name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                currencies.add(new Currency(
                        resultSet.getInt("currency_id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return currencies;
    }
}
