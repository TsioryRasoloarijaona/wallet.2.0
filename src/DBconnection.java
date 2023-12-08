import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static Connection connection;

    private DBconnection() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/wallet",
                    "postgres",
                    "Sqlohyvqdiko");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null){
            new DBconnection();
        }
        return connection;
    }
}
