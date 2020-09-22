package app;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

//Работа с пользователями (удалить мейн при создании интерфейса кекв)
public class Main {

    //Форма входа в систему по созданному логину и паролю
    public static void signIn(){
        Scanner in = new Scanner(System.in);

        System.out.print("Введите логин: ");
        String login = in.next();
        System.out.print("Введите пароль: ");
        String password = in.next();

        HashMap<String, String> userData = getUserDataFromTable();
        for (String lgn : userData.keySet()){
            for (String pass : userData.values()){
                if ((lgn.equals(login)) & (pass.equals(password))) {
                    System.out.println("Успех!");
                }
            }
        }
    }

    //Добавление значения в таблицу users
    public static void insertIntoTable(String log, String pass) throws SQLException {
        DB dbobj = new DB();
        Connection conn = null;
        PreparedStatement statement = null;
        String ins =  "INSERT INTO users(login, password) VALUES (?,?)";         //SQL-запрос

        try {
            conn = dbobj.getDBConnection();
            statement = conn.prepareStatement(ins);                             //С помощью PreparedStatement подготавливаем SQL-запрсос к выполнению
            statement.setString(1,log);                             //1-й устанавливаемый в БД параметр SQL-запроса - логин пользователя
            statement.setString(2,pass);                            //2-й устанавливаемый в БД параметр SQL-запроса - пароль пользователя

            statement.executeUpdate();                                          //Выполнение SQL-запроса (может возвращать количество добавленных строк)
            System.out.println("Данные успешно добавлены в \"таблицу\".");
        }catch (SQLException e){
            System.out.println(e.getMessage());                                 //Вывод ошибки, если SQL-запрос не был исполнен
        }finally {
            if(statement != null) {                                             //Если контейнер SQL-запроса не пуст...
                statement.close();                                              //...закрыть его
            }if(conn != null){                                                  //Если есть подключение к БД...
                conn.close();                                                   //...закрыть его
            }
        }
    }

    //Получение логинов с таблицы
    public static HashMap<String, String> getUserDataFromTable(){
        DB dbobj = new DB();
        Connection conn = null;
        Statement statement = null;
        String getLogins =  "SELECT login,password from users";                          //Выбираем данные с БД

        HashMap<String, String> map = new HashMap<>();

        try{
            conn = dbobj.getDBConnection();                                     //Получаем наше подключение
            statement = conn.createStatement();                                 //Подготавливаем SQL-запрсос к выполнению

            ResultSet rs = statement.executeQuery(getLogins);                   //Выполняем запрос

            while(rs.next()){
                map.put(rs.getString("login"),rs.getString("password"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return map;                                                       //Возвращаем все логины
    }

    //Проверка на существующий логин
    public static boolean checkLogins(String inputLogin){

        HashMap<String, String> map = getUserDataFromTable();
        for (String login : map.keySet()){
            if (inputLogin.equals(login)) {
                return false;
            }
        }
        return true;
    }

    //Создаём нового пользователя (можно расценивать как форму регистрации)
    public static void createNewUser() throws SQLException {
        Users user = new Users();                                                           //Инициализация пользователя
        Scanner in = new Scanner(System.in);

        System.out.print("Введите логин: ");
        String login = in.next();                                                           //Придуманный пользователем логин

        boolean checker = checkLogins(login);
        if (checker){
            user.setLogin(login);                                                               //Инициализация логина пользователя
            user.setPassword(user.genUserPass(user.getLogin()));                                //Инициализация пользователю его сгенерированный пароль по логину
            insertIntoTable(user.getLogin(),user.getPassword());                                //Добавление его данных в БД
        }else{
            System.out.println("Такой логин уже существует!");
        }

    }

    //Можно обойтись без main
    public static void main(String[] args) throws SQLException {
        //createNewUser();                                                                    //Создание нового пользователя и добавление его в БД
        signIn();
    }
}
