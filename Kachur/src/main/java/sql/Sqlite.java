package sql;
import java.sql.*;

public class Sqlite {
    public void conn() {
        Connection connection = null;
        try {
            // Підключення до бази даних SQLite
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");

            // Створення таблиці (приклад)
            Statement statement = connection.createStatement();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
