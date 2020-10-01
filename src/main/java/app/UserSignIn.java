package app;

import entities.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//Вход в аккаунт пользователя
public class UserSignIn extends UserReg {

    //Обьект пользователя, который вошёл в систему, для получения всех его данных
    User usersDataOnSignInTime = new User();

    //Форма входа в систему по созданному логину и паролю
    public String signIn(String login, String password){
        HashMap<Integer, User> users = dbo.getUserDataFromTable();

        //Если карта содержит ключ == введенному логину, и значение по этому ключу из карты == введенному паролю
        for(Map.Entry<Integer, User> item : users.entrySet()){
            if(login.equals(item.getValue().getLogin()) && password.equals(item.getValue().getPassword()))
            {
                usersDataOnSignInTime.setId(item.getKey());
                usersDataOnSignInTime.setLogin(item.getValue().getLogin());
                usersDataOnSignInTime.setPassword(item.getValue().getPassword());
                usersDataOnSignInTime.setRegistrationDate(item.getValue().getRegistrationDate());
                lgng.writeIntoFile("Пользователь " +  usersDataOnSignInTime.getLogin() + " вошёл в систему " + lgng.timeOfAction());
                return "SUCCESS";
            }
        }
        return "INCORRECT LOGIN OR PASSWORD";
    }

    //Обновление пароля
    public void update(int id) throws SQLException {
        String password = genUserPass(usersDataOnSignInTime.getLogin());
        dbo.updatePass(id,password);
        lgng.writeIntoFile("Пользователь " +  usersDataOnSignInTime.getLogin() + " обновил пароль " + lgng.timeOfAction());
    }

    //Удаление аккаунта
    public void delete(int id) throws SQLException{
        dbo.deleteRow(id);
        lgng.writeIntoFile("Пользователь " +  usersDataOnSignInTime.getLogin() + " удалил свой аккаунт " + lgng.timeOfAction());
    }
}
