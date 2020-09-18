package app;

import java.sql.SQLException;
import java.util.Scanner;

//Работа с пользователями (удалить мейн при создании интерфейса кекв)
public class OZapp {

    //Создаём нового пользователя (можно расценивать как форму регистрации)
    public static Users createNewUser(){
        Users user = new Users();                                                           //Инициализация пользователя
        Scanner in = new Scanner(System.in);

        System.out.print("Введите логин: ");
        String login = in.next();                                                           //Придуманный пользователем логин

        user.setLogin(login);                                                               //Инициализация логина пользователя
        user.setPassword(user.genUserPass(user.getLogin()));                                //Инициализация пользователю его сгенерированный пароль по логину
        return user;                                                                        //Возвращение объекта (при использовании интерфейса делит)
    }

    //Форма входа в систему по созданному логину и паролю
    public static void signIn(String enteredLogin, String enteredPassword){

    }

    //Смена логина пользователя
    static void changeLogin(Users user){
        //user.setLogin();
    }

    //Отправка данных пользователя в БД
    public static void sendUsersDataIntoDB(Users user) throws SQLException {
        DB dbObj = new DB();                                                                //Инициализация объекта базы данных
        String userLogin = user.getLogin();                                                 //Получение логина пользователя для занесения в бд
        String userPassword = user.getPassword();                                           //Получение пароля пользователя для занесения в бд

        dbObj.insertIntoTable(userLogin, userPassword);                                     //Непосредственнно добавление в таблицу users (в бд)

    }

    //Можно обойтись без main
    public static void main(String[] args) throws SQLException {
        Users user = createNewUser();                                                       //Создание обьекта пользователя
        sendUsersDataIntoDB(user);                                                          //Отправка данных пользователя в бд
    }
}
