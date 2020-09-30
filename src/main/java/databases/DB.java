package databases;

import entities.User;

import java.sql.*;
import java.util.HashMap;

public class DB {                                                               //Хранит данные подключения к БД PostgreSQL

    //Адрес БД
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/access_system_db";

    //Данные пользователя
    private static final String USERNAME = "postgres";                          //Логин БД
    private static final String PASSWORD = "evgenhalk1999";                     //Пароль БД
    private static final String DRIVER = "org.postgresql.Driver";               //путь к JDBC драйверу

    //Подключение к БД + SQL контейнер
    private static final Connection conn = null;                                //Контейнер для подключения БД
    private static final Statement statement = null;                            //Контейнер для выполнения SQL-запроса

    //Подключение к БД
    public Connection getDBConnection(){
        Connection conn;                                                        //Переменная содер-ая сессия подключения к БД
        try {
            Class.forName(DRIVER);                                              //Указываем используемый драйвер
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());                                 //Вывод ошибки, если не удается получить данные о драйвере
        }
        try {
            System.out.println("Подключаемся к БД...");
            conn = DriverManager.getConnection(URL,USERNAME, PASSWORD);         //Получаем подключение к БД
            System.out.println("Соединение с \""+ URL + "\" установлено");
            return conn;                                                        //Возвращаем успешное подключение
        } catch (SQLException e) {
            System.out.println(e.getMessage());                                 //Вывод ошибки, если есть проблемы с подключением
            System.out.println("Соединение с \""+ URL + "\" не установлено");
        }
        return null;                                                            //Возвращаем неудачное подключение
    }

    //Добавление значения в таблицу users
    public void insertIntoTable(String log, String pass, java.sql.Date regDate) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        String ins =  "INSERT INTO users(login, password, registration_date) VALUES (?,?,?)";

        try {
            conn = getDBConnection();
            statement = conn.prepareStatement(ins);                              //С помощью PreparedStatement подготавливаем SQL-запрсос к выполнению
            statement.setString(1,log);                             //логин пользователя
            statement.setString(2,pass);                            //пароль пользователя
            statement.setDate(3,regDate);                           //дата регистрации
            statement.executeUpdate();                                          //Выполнение SQL-запроса (может возвращать количество добавленных строк)
            System.out.println("Данные успешно добавлены в \"таблицу\".");
        }catch (SQLException e){
            System.out.println(e.getMessage());                                 //Вывод ошибки, если SQL-запрос не был исполнен
        }finally {
            if(statement != null) {
                statement.close();
            }if(conn != null){
                conn.close();
            }
        }
    }

    public void deleteRow(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        String updateUserPass =  "DELETE FROM users WHERE id = ?";

        try {
            conn = getDBConnection();
            statement = conn.prepareStatement(updateUserPass);
            statement.setInt(1,id);
            statement.executeUpdate();
            statement.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            if(statement != null) {
                statement.close();
            }if(conn != null){
                conn.close();
            }
        }
    }

    //обновление пароля пользователя по id
    public void updatePass(int id, String pass) throws SQLException {
        Connection conn = null;
        PreparedStatement statement = null;
        String updateUserPass =  "UPDATE users SET password = ? WHERE id = ?";

        try {
            conn = getDBConnection();
            statement = conn.prepareStatement(updateUserPass);
            statement.setString(1,pass);
            statement.setInt(2,id);

            statement.executeUpdate();
            statement.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            if(statement != null) {
                statement.close();
            }if(conn != null){
                conn.close();
            }
        }
    }

    //Получение логинов с таблицы
    public HashMap<Integer, User> getUserDataFromTable(){
        Connection conn;
        Statement statement;
        String getLogins =  "SELECT * from users";

        HashMap<Integer, User> users = new HashMap<>();

        try{
            conn = getDBConnection();
            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(getLogins);

            while(rs.next()){
                users.put(rs.getInt("id"),new User(rs.getString("login"),rs.getString("password"),rs.getDate("registration_date")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }
}
