package app;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {

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
                String status = sucUser.signIn(signInLogin,signInPass);
                if(status.equals("SUCCESS")){
                    System.out.println("1. Сменить пароль");
                    System.out.println("2. Удалить аккаунт");
                    int c = in.nextInt();
                    switch (c){
                        case 1:
                            sucUser.update(sucUser.usersDataOnSignInTime.getId());
                            System.out.print("Пароль изменён.");
                            break;
                        case 2:
                            sucUser.delete(sucUser.usersDataOnSignInTime.getId());
                            System.out.print("Аккаунт успешно удалён.");
                            break;
                        default:
                            break;
                    }
                }
                break;
            case 2:
                System.out.print("login:");
                String signUpLogin = in.next();
                obj.signUp(signUpLogin);
                //System.out.print(obj.usersDataOnRegistrationTime.getLogin());
                break;
            case 3:
                //obj.lgng.zipLogs();
                obj.lgng.unZipLogs();
            default:
                break;
        }
    }
}
