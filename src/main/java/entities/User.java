package entities;

import databases.DB;

import java.sql.*;
import java.util.*;

//Класс пользователей
public class User {

    //Данные которые имеет зарегистрировавшийся пользователь
    private int id;
    private String login;
    private String password;
    private java.sql.Date registrationDate;

    public DB dbo = new DB();

    public User(){}

    public User(String login, String password, java.sql.Date registrationDate) {
        this.login = login;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    //Уникальный идентификатор
    public void setId(int id) {
        this.id = id;
    }

    //получение ID
    public int getId() {
        return id;
    }

    //Инициализация логина
    public void setLogin(String login) {                //Инициализируем логин пользователю
        this.login = login;
    }

    //Получение пароля
    public String getLogin() {                          //Получаем данные логина пользователя
        return login;
    }

    //Инициализация пароля
    public void setPassword(String password) {          //Инициализируем пароль пользователю
        this.password = password;
    }

    //Получение пароля
    public String getPassword() {                       //Получаем данные пароля пользователя
        return password;
    }

    //Инициализация даты регистрации
    public void setRegistrationDate(java.sql.Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    //Получение даты регистрации
    public java.sql.Date getRegistrationDate() {
        return registrationDate;
    }

    //Форма входа в систему по созданному логину и паролю
    public String signIn(String login, String password){
        HashMap<Integer, User> users = dbo.getUserDataFromTable();

        //Если карта содержит ключ == введенному логину, и значение по этому ключу из карты == введенному паролю
        for(Map.Entry<Integer, User> item : users.entrySet()){
            if(login.equals(item.getValue().getLogin()) && password.equals(item.getValue().getPassword()))
            {
                setId(item.getKey());
                setLogin(item.getValue().getLogin());
                setPassword(item.getValue().getPassword());
                setRegistrationDate(item.getValue().getRegistrationDate());
                return "SUCCESS";
            }
        }
        return "INCORRECT LOGIN OR PASSWORD";
    }

    public void signUp(String login) throws SQLException {
        boolean checker = checkLogins(login);
        if (checker){
            User newUser = new User(login,genUserPass(login),getCurrentTimeStamp());
            dbo.insertIntoTable(newUser.getLogin(),newUser.getPassword(),newUser.getRegistrationDate());
        }else{
            System.out.println("Такой логин уже существует!");
        }

    }

    //Генерация пароля пользователя
    public static String genUserPass(String login) {
        StringBuilder generatedPass = new StringBuilder();                        //В этой переменной собираем пароль
        final Random rand = new Random();                                         //Генерация чисел
        String ruWords = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        String[] symbols = {String.valueOf('!'), String.valueOf('"'), "#", "$", "%", "&", "'", "(", ")", "*"};
        generatedPass.append(rand.nextInt(9)+1);                           //1-й символ пароля
        generatedPass.append(rand.nextInt(9)+1);                           //2-й символ пароля

        int Q = login.length() % 8;                                              //Вычисление количества букв русского алфавита (ниж. рег) будет в пароле
        int Mlp = 9 - Q;                                                         //Вычисление количества символов symbols будет в пароле

        for (int i = 0; i < Q; i++){
            generatedPass.append(ruWords.charAt(rand.nextInt(ruWords.length()))); //Количество русских букв (i < Q)
        }

        for (int i = 0; i < Mlp; i++){
            generatedPass.append(symbols[rand.nextInt(symbols.length)]);          //Количество символов rSymbols (i < Mlp)
        }

        return String.valueOf(generatedPass);                                     //Возвращаем сгенерированный пароль
    }

    //Проверка на существующий логин
    public boolean checkLogins(String inputLogin){

        HashMap<Integer, User> users = dbo.getUserDataFromTable();
        for(Map.Entry<Integer, User> item : users.entrySet()){
            if(inputLogin.equals(item.getValue().getLogin()))
            {
                return false;
            }
        }
        return true;
    }

    //Получение текущей даты
    private java.sql.Date getCurrentTimeStamp() {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = convert(utilDate);
        return sqlDate;
    }

    //Конвертация util.date -> sql.date
    private java.sql.Date convert(java.util.Date uDate) {                           //Получение из util даты формата sql даты
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    /*public List<Character> ConvertStringToCharList(String str){                   //Конвертация строки в список char
        List<Character> chars = str
                .chars()
                .mapToObj(e -> (char)e)
                .collect(Collectors.toList());

        return chars;
    }*/

}