package app;

import entities.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserSignIn extends UserReg {
    //Форма входа в систему по созданному логину и паролю
    public String signIn(String login, String password){
        HashMap<Integer, User> users = dbo.getUserDataFromTable();

        //Если карта содержит ключ == введенному логину, и значение по этому ключу из карты == введенному паролю
        for(Map.Entry<Integer, User> item : users.entrySet()){
            if(login.equals(item.getValue().getLogin()) && password.equals(item.getValue().getPassword()))
            {
                user.setId(item.getKey());
                user.setLogin(item.getValue().getLogin());
                user.setPassword(item.getValue().getPassword());
                user.setRegistrationDate(item.getValue().getRegistrationDate());
                return "SUCCESS";
            }
        }
        return "INCORRECT LOGIN OR PASSWORD";
    }

    public void update(int id) throws SQLException {
        String password = genUserPass(user.getLogin());
        dbo.updatePass(id,password);
    }

    public void delete(int id) throws SQLException{
        dbo.deleteRow(id);
    }
}
