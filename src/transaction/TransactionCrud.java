package transaction;

import account.Account;
import balance.Balance;
import category.Category;
import currency.Currency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionCrud implements TransactionOperation{
    Connection connection;

    public TransactionCrud(Connection connection) {
        this.connection = connection;
    }

    @Override
    public double getAmount(Transaction transaction){
        double amount = 0;
        String sql2 = "select amount from balance where account_id = ? order by date_time desc limit 1";
        try(PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setInt(1,transaction.getAccount_id());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                amount = resultSet.getDouble("amount");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return amount;
    }

    public int getCategory (Transaction transaction){
        String sql = "select category_id from category where category_name = ?";
        int id = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, transaction.getCategory().getCategoryName());
            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                id = resultSet.getInt("category_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }
    @Override
    public void inserttransaction (Transaction transaction){

        String sql3 = "insert into transaction (account_id,category_id,  amount) values (?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql3)) {
            statement.setInt(1,transaction.getAccount_id());
            statement.setInt(2, getCategory(transaction));
            statement.setDouble(3,transaction.getAmount());

            statement.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    };
    @Override
    public void insertBalance (Transaction transaction){
        double amount = 0;
        String sql = "select amount from balance where account_id = ? order by date_time desc limit 1";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,transaction.getAccount_id());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
               amount = resultSet.getDouble("amount");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        double newAmount;

        if (transaction.getCategory().getCategoryType() == "credit") {
             newAmount = amount + transaction.getAmount();
        }else {
            newAmount = amount - transaction.getAmount();
        }

        String sql1 =  "insert into balance (account_id , amount) values (?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql1)){
            statement.setInt(1,transaction.getAccount_id());
            statement.setDouble(2,newAmount);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    };
    @Override
    public  String getAccountType (Transaction transaction){
        String account_type= null;

        String sql1 = "select account_type from account where account_id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql1)) {
            statement.setInt(1,transaction.getAccount_id());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                account_type = resultSet.getString("account_type");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account_type;
    }

    @Override
    public Account getAccount(Transaction transaction){


        int account_id = transaction.getAccount_id();
        String account_name = null;
        String curreny_name = null;
        double amount = 0;
        Timestamp dateTimeBalance = null;
        List<Transaction> transactions = new ArrayList<>();

        String sql1 = "select account_id, account_name , name from account inner join currency on account.currency_id = currency.currency_id where account_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql1)){
            statement.setInt(1,account_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                account_name = resultSet.getString("account_name");
                curreny_name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Currency currency = new Currency(curreny_name);
        String sql2 = "select amount,date_time from balance where account_id = ? order by date_time desc limit 1";
        try (PreparedStatement statement = connection.prepareStatement(sql2)){
            statement.setInt(1,account_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
               amount = resultSet.getDouble("amount");
               dateTimeBalance = resultSet.getTimestamp("date_time");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Balance balance = new Balance(amount,dateTimeBalance);

        String sql3 = "select  amount ,date_time , category_name , category_type from transaction inner join category on transaction.category_id = category.category_id where account_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql3)){
            statement.setInt(1,account_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){

                transactions.add(new Transaction(
                        resultSet.getDouble("amount"),
                        resultSet.getTimestamp("date_time"),
                        new Category(resultSet.getString("category_name"),
                              resultSet.getString("category_type")  )

                ));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Account account = new Account(account_id ,account_name, currency ,balance , transactions);
        return account;

    }

   @Override
    public Account transaction(Transaction transaction) {
        String accountType = getAccountType(transaction);
        double amount = getAmount(transaction);

        if (Objects.equals(accountType, "cash")){
            if (transaction.getCategory().getCategoryType() == "debit"){
                if (amount >= transaction.getAmount()){
                    inserttransaction(transaction);
                    insertBalance(transaction);
                }else {
                    System.out.println("not enough");
                }
            }else {
                inserttransaction(transaction);
                insertBalance(transaction);
            }
        }else {
            inserttransaction(transaction);
            insertBalance(transaction);
        }


        return getAccount(transaction);


    }


}
