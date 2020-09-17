package app;

import java.sql.*;

public class DB {
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/access_system_db"; //Адрес БД
    //Данные пользователя
    private static final String USERNAME = "postgres";              //Логин
    private static final String PASSWORD = "evgenhalk1999";              //Пароль
    private static final String DRIVER = "org.postgresql.Driver";   //путь к JDBC драйверу

    private static Connection conn = null;
    private static Statement statement = null;

    private static Connection getDBConnection(){            //Подключение к БД
        Connection conn = null;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            conn = DriverManager.getConnection(URL,USERNAME, PASSWORD);
            System.out.println("Соединение с "+ URL + " установлено");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Соединение с "+ URL + "не установлено");
        }
        return conn;
    }

    public void insertIntoTable(String log, String pass) throws SQLException {             //Добавляем значение в созданную таблицу

        String ins =  "INSERT INTO users(login, password) VALUES (?,?)";

        try {
            conn = getDBConnection();
            PreparedStatement statement = conn.prepareStatement(ins);
            statement.setString(1,log);
            statement.setString(2,pass);
            //Start SQL query
            statement.executeUpdate();
            System.out.println("Данные успешно добавлены в \"таблицу\".");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            if(statement != null) {
                statement.close();
            }if(conn != null){
                conn.close();
            }
        }
    }
}
