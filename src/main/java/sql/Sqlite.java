package sql;

import java.sql.*;


public class Sqlite {
    public void conn() {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db")){
            // Підключення до бази даних SQLite


            // Створення таблиці (приклад)
            Statement statement = connection.createStatement();
            //statement.executeUpdate("CREATE TABLE IF NOT EXISTS guilds( id INTEGER PRIMARY KEY AUTOINCREMENT, guild_name TEXT NOT NULL, guild_id TEXT NOT NULL ) ");
            //statement.executeUpdate("CREATE TABLE IF NOT EXISTS guild_users( id INTEGER PRIMARY KEY AUTOINCREMENT, user_name TEXT NOT NULL, user_id TEXT NOT NULL, guild_name TEXT NOT NULL, guild_id TEXT NOT NULL  ) ");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS commands_count( id INTEGER PRIMARY KEY, commandscount INTEGER DEFAULT 0 ) ");


            connection.setAutoCommit(false);
            connection.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int countcom() {
        int count = 0;


        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT commandscount FROM commands_count");
            if (resultSet.next()) {
                count = resultSet.getInt("commandscount");
            }

            connection.setAutoCommit(false);
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void commands_count() {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db")) {

            Statement statement = connection.createStatement();

            statement.executeUpdate("UPDATE commands_count SET commandscount = commandscount + 1");
            connection.setAutoCommit(false);


            statement.close();
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gld_users(String name) {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db")) {
//            connection = DriverManager.getConnection("jdbc:sqlite:test.db");

            PreparedStatement stat = connection.prepareStatement("CREATE TABLE IF NOT EXISTS \"" + name +  "\" ( id INTEGER PRIMARY KEY AUTOINCREMENT, user_name TEXT NOT NULL, user_id TEXT NOT NULL, bio TEXT DEFAULT NULL ) ");
            stat.executeUpdate();

            stat.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public boolean biocheck(String name, long userid) {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            PreparedStatement statement = connection.prepareStatement("SELECT bio FROM \"" + name +  "\" WHERE user_id = ?");
            statement.setLong(1, userid);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultSet.getString("bio");
                connection.setAutoCommit(false);
                resultSet.close();
                statement.close();
                connection.close();

                return true;
            }
            else{
                return false;
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getbio(String name, long userid) {
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db")){
            PreparedStatement statement = connection.prepareStatement("SELECT bio FROM \"" + name +  "\" WHERE user_id = ?");
            statement.setLong(1, userid);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String biotxt = resultSet.getString("bio");
                if (biotxt != null) {

                    connection.setAutoCommit(false);
                    resultSet.close();
                    statement.close();
                    connection.close();

                    return biotxt;
                }
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void checkusers(String name, long userid, String username){
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db")) {
//            Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            //Statement statement = connection.createStatement();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"" + name +  "\" WHERE user_id = ?");
            statement.setLong(1, userid);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() != true) {
                 PreparedStatement statement1 = connection.prepareStatement("INSERT INTO \"" + name +  "\" ( user_name, user_id ) VALUES (?, ?)");
                 statement1.setString(1, username);
                 statement1.setLong(2, userid);

                 statement1.executeUpdate(); // This line was missing

                 //connection.commit();
                 statement1.close();
            }


            connection.setAutoCommit(false);
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insrtusers(String name, long userid, String username){
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db")) {
//            Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO \"" + name +  "\"( user_name, user_id ) VALUES (?, ?)");
            statement.setString(1, username);
            statement.setLong(2, userid);

            //connection.commit();

            connection.setAutoCommit(false);
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();


        }
    }

}
