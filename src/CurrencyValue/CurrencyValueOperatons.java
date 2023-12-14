package CurrencyValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyValueOperatons {
    private Connection connection;

    public CurrencyValueOperatons(Connection connection) {
        this.connection = connection;
    }

    public double ConvertByAvarage (int id_source , int id_destination ,double amount){
        double value = 0;
        String sql = "select avg (amount) from currency_value where (currency_source_id = ? and currency_destination = ? ) and date (date_effect) = current_date";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id_source);
            statement.setInt(2,id_destination);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                value = resultSet.getDouble("avg");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return amount * value;

    }

    public double ConvertByMin (int id_source , int id_destination ,double amount){
        double value = 0;
        String sql = "select min (amount) from currency_value where (currency_source_id = ? and currency_destination = ? ) and date (date_effect) = current_date";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id_source);
            statement.setInt(2,id_destination);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                value = resultSet.getDouble("min");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return amount * value;

    }

    public double ConvertByMax (int id_source , int id_destination ,double amount){
        double value = 0;
        String sql = "select max (amount) from currency_value where (currency_source_id = ? and currency_destination = ? ) and date (date_effect) = current_date";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id_source);
            statement.setInt(2,id_destination);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                value = resultSet.getDouble("max");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return amount * value;

    }

    public double ConvertByMedian (int id_source , int id_destination ,double amount){
        double value = 0;
        String sql = "SELECT percentile_cont(0.5) WITHIN GROUP (ORDER BY amount) AS median_value FROM currency_value where (currency_source_id = ? and currency_destination = ? ) and date (date_effect) = current_date";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id_source);
            statement.setInt(2,id_destination);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                value = resultSet.getDouble("median_value");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return amount * value;

    }

    public void insert (CurrencyValue currencyValue) {
        String sql = "insert into currency_value (currency_source_id ,currency_destination,amount ) values (?,?,?)";
       try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
           preparedStatement.setInt(1,currencyValue.getCurrencySourceId());
           preparedStatement.setInt(2,currencyValue.getCurrencyDestinationId());
           preparedStatement.setDouble(3,currencyValue.getAmount());
           preparedStatement.executeUpdate();
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

    }

}
