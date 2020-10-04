package app;

import databases.DB;
import entities.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//Регистрация пользователей в системе
public class UserReg {

    public DB dbo = new DB();
    Logging lgng = new Logging();

    //Регистрация в системе
    public Boolean signUp(String login,String pass){
        boolean checker = checkLogins(login);
        if (checker){
            lgng.writeIntoFile("Пользователь " +  login + " зарегистрировался в системе " + lgng.timeOfAction());
            try{
               dbo.insertIntoTable(login, pass, getCurrentTimeStamp());
           }catch (SQLException ex){
               ex.printStackTrace();
           }
            return true;
        }else{
            return false;
        }
    }

    //Генерация пароля пользователя
    public String genUserPass(String login) {
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


}

