package transactionSum;

import java.sql.*;

public class SumFunctions {
    Connection connection;

    public SumFunctions(Connection connection) {
        this.connection = connection;
    }

    public double sumTransaction (int account_id , Timestamp start , Timestamp end){
        double amount =0;
        String sql = "SELECT sum_transaction(?, ?::timestamp ,?::timestamp) AS total_amount;";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,account_id);
            statement.setTimestamp(2,start);
            statement.setTimestamp(3,end);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
               amount =  resultSet.getDouble("total_amount");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
return amount;
    }

    public double sumCredit (int account_id , Timestamp start , Timestamp end){
        double amount = 0;
        String sql = "select sum(amount) from transaction inner join category on transaction.category_id = category.category_id where (transaction.date_time between ? and ?) and category_type = 'credit' and transaction.account_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setTimestamp(1,start);
            statement.setTimestamp(2,end);
            statement.setInt(3,account_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                amount =  resultSet.getDouble("sum");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return amount;
    }

    public double sumDebit (int account_id , Timestamp start , Timestamp end){
        double amount = 0;
        String sql = "select sum(amount) from transaction inner join category on transaction.category_id = category.category_id where (transaction.date_time between ? and ?) and category_type = 'debit' and transaction.account_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setTimestamp(1,start);
            statement.setTimestamp(2,end);
            statement.setInt(3,account_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                amount =  resultSet.getDouble("sum");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return amount;
    }

    public double sumTransactionJava (int account_id , Timestamp start , Timestamp end){
        return sumCredit(account_id , start,end) - sumDebit(account_id ,start,end);

    }
}
