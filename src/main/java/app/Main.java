package app;

import databases.DB;
import entities.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        DB dbo = new DB();
        User user1 = new User();
        Scanner in = new Scanner(System.in);
        System.out.println("Select 1 or 2:");
        System.out.println("1. Sign Up");
        System.out.println("2. Sign In");
        int selected = in.nextInt();
        switch (selected){
            case 1:
                System.out.print("Введите логин: ");
                String login = in.next();
                user1.signUp(login);
                break;
            case 2:
                int counter = 0;
                while (true){
                    System.out.print("Введите логин: ");
                    login = in.next();
                    System.out.print("Введите пароль: ");
                    String password = in.next();
                    if (counter!=2) {
                        String val = user1.signIn(login, password);
                        if (val.equals("SUCCESS")) {
                            System.out.println("Вы успешно вошли в систему");
                            System.out.println(user1.getId());
                            System.out.println(user1.getLogin());
                            System.out.println(user1.getPassword());
                            System.out.println(user1.getRegistrationDate());
                            break;
                        }else{
                            System.out.println("Вы ввели неверный логин или пароль");
                            counter++;
                        }
                    }else {
                        System.out.println("Вы превысили число допустимых попыток: 3\nЧтобы снова попробовать войти в систему необходимо подождать: 5 секунд");
                        try {
                            Thread.sleep(5000);
                            counter = 0;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            default:
                break;
        }
    }
}
