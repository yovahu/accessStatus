package app;

import entities.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//Вход в аккаунт пользователя
public class UserSignIn extends UserReg {

    //Обьект пользователя, который вошёл в систему, для получения всех его данных
    public User usersDataOnSignInTime = new User();
    public HashMap<Integer, User> users = dbo.getUserDataFromTable();

    //Форма входа в систему по созданному логину и паролю
    public boolean signIn(String login, String password) {
            //Если карта содержит ключ == введенному логину, и значение по этому ключу из карты == введенному паролю
        for (Map.Entry<Integer, User> item : users.entrySet()) {
            usersDataOnSignInTime.setId(item.getKey());
            usersDataOnSignInTime.setLogin(item.getValue().getLogin());
            usersDataOnSignInTime.setPassword(item.getValue().getPassword());
            usersDataOnSignInTime.setRegistrationDate(item.getValue().getRegistrationDate());
            if (login.equals(usersDataOnSignInTime.getLogin()) && password.equals(usersDataOnSignInTime.getPassword())) {
                lgng.writeIntoFile("Пользователь " + usersDataOnSignInTime.getLogin() + " вошёл в систему " + lgng.timeOfAction());
                return true;
            }
        }
        return false;
    }

    //Обновление пароля
    public void update(int id, String password) throws SQLException {
        dbo.updatePass(id,password);
        lgng.writeIntoFile("Пользователь " +  usersDataOnSignInTime.getLogin() + " обновил пароль " + lgng.timeOfAction());
    }

    //Удаление аккаунта
    public void delete(int id) throws SQLException{
        dbo.deleteRow(id);
        lgng.writeIntoFile("Пользователь " +  usersDataOnSignInTime.getLogin() + " удалил свой аккаунт " + lgng.timeOfAction());
    }
}
