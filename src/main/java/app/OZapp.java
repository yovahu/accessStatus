package app;

import java.sql.SQLException;
import java.util.Scanner;

public class OZapp {

    public static Users createNewUser(){
        Users user = new Users();
        Scanner in = new Scanner(System.in);

        System.out.print("Введите логин: ");
        String login = in.next();

        user.setLogin(login);
        user.setPassword(user.genUserPass(user.getLogin()));
        return user;
    }

    public static void sendUsersDataIntoDB(Users user) throws SQLException {
        DB dbObj = new DB();
        String userLogin = user.getLogin();
        String userPassword = user.getPassword();

        dbObj.insertIntoTable(userLogin, userPassword);

    }

    public static void main(String[] args) throws SQLException {
        Users user = createNewUser();
        sendUsersDataIntoDB(user);
    }
}
