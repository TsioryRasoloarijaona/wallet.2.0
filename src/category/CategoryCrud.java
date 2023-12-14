package category;

import java.io.PipedInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryCrud {
    Connection connection;

    public CategoryCrud(Connection connection) {
        this.connection = connection;
    }

    public List<Category> getAll(String name){
        List<Category> categories = new ArrayList<>();
        String sql = "select * from category where category_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                categories.add(new Category(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name"),
                        resultSet.getString("category_type")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    public Category insert (Category category){
        List<Category> categories = new ArrayList<>(getAll(category.getCategoryName()));

        if (categories.size() > 0 ){
            System.out.println("this category already exist");
        }else {
            String sql = "insert into category (category_name , category_type) values (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, category.getCategoryName());
                statement.setString(2, category.getCategoryType());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return category;

    }
}
