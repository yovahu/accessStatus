package app;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        UserReg obj = new UserReg();
        UserSignIn sucUser = new UserSignIn();

        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        switch (a){
            case 1:
                System.out.print("login:");
                String signInLogin = in.next();
                System.out.print("pass:");
                String signInPass = in.next();
                System.out.println(sucUser.signIn(signInLogin,signInPass));
                //System.out.println(sucUser.user.getId());
                System.out.println("1. Сменить пароль");
                System.out.println("2. Удалить аккаунт");
                int c = in.nextInt();
                switch (c){
                    case 1:
                        sucUser.update(sucUser.user.getId());
                        System.out.print("Пароль изменён.");
                        break;
                    case 2:
                        sucUser.delete(sucUser.user.getId());
                        System.out.print("Аккаунт успешно удалён.");
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                System.out.print("login:");
                String signUpLogin = in.next();
                obj.signUp(signUpLogin);
                break;
            case 3:
                GregorianCalendar gcalendar = new GregorianCalendar();
                SimpleDateFormat simpDate;
                simpDate = new SimpleDateFormat("HH:mm:ss");
                System.out.print("Дата: ");
                System.out.print(gcalendar.get(Calendar.MONTH)+1);
                System.out.print("-" + gcalendar.get(Calendar.DATE) + "-");
                System.out.println(gcalendar.get(Calendar.YEAR));
                /*
                System.out.print("Время: ");
                System.out.print(gcalendar.get(Calendar.HOUR) + ":");
                System.out.print(gcalendar.get(Calendar.MINUTE) + ":");
                System.out.println(gcalendar.get(Calendar.SECOND));
                */
                System.out.print(simpDate.format(gcalendar.getTime()));
            default:
                break;
        }
    }
}
