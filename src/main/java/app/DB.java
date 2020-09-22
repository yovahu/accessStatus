package app;

import java.sql.*;

public class DB {                                                               //Хранит данные подключения к БД PostgreSQL

    //Адрес БД
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/access_system_db";

    //Данные пользователя
    private static final String USERNAME = "postgres";                          //Логин БД
    private static final String PASSWORD = "evgenhalk1999";                     //Пароль БД
    private static final String DRIVER = "org.postgresql.Driver";               //путь к JDBC драйверу

    //Подключение к БД + SQL контейнер
    private static Connection conn = null;                                      //Контейнер для подключения БД
    private static Statement statement = null;                                  //Контейнер для выполнения SQL-запроса

    //Подключение к БД
    public Connection getDBConnection(){
        Connection conn = null;                                                 //Переменная содер-ая сессия подключения к БД
        try {
            Class.forName(DRIVER);                                              //Указываем используемый драйвер
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());                                 //Вывод ошибки, если не удается получить данные о драйвере
        }
        try {
            conn = DriverManager.getConnection(URL,USERNAME, PASSWORD);         //Получаем подключение к БД
            System.out.println("Соединение с "+ URL + " установлено");
            return conn;                                                        //Возвращаем успешное подключение
        } catch (SQLException e) {
            System.out.println(e.getMessage());                                 //Вывод ошибки, если есть проблемы с подключением
            System.out.println("Соединение с "+ URL + "не установлено");
        }
        return conn;                                                            //Возвращаем неудачное подключение
    }
}
