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
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS guilds( id INTEGER PRIMARY KEY AUTOINCREMENT, guild_name TEXT NOT NULL, guild_id TEXT NOT NULL ) ");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS guild_users( id INTEGER PRIMARY KEY AUTOINCREMENT, user_name TEXT NOT NULL, user_id TEXT NOT NULL, guild_name TEXT NOT NULL, guild_id TEXT NOT NULL  ) ");



            connection.setAutoCommit(false);
            connection.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gld_users(String name){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");


            PreparedStatement stat = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `" + name + "` ( id INTEGER PRIMARY KEY AUTOINCREMENT, user_name TEXT NOT NULL, user_id TEXT NOT NULL ) ");
            stat.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void delgld_users(String name){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");


            PreparedStatement stat = connection.prepareStatement("DROP TABLE IF EXISTS `" + name + "`");

            stat.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insert_guild(String name, String idd){
        try {

            Connection connection = null;
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");


            //тут тіпа формат строк
            PreparedStatement statement = connection.prepareStatement("INSERT INTO guilds (guild_name, guild_id) VALUES ( ? , ? )");

            statement.setString(1,name);
            statement.setString(2,idd);
            statement.executeUpdate();


            connection.setAutoCommit(false);
            connection.commit();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void delete_guild(String name, String idd){
        try {
            Connection connection = null;
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");


            //тут тіпа формат строк
            PreparedStatement statement = connection.prepareStatement("DELETE FROM guilds WHERE guild_id = ?");

            statement.setString(1,idd);
//            statement.setString(2,idd);
            statement.executeUpdate();


            connection.setAutoCommit(false);
            connection.commit();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

}
