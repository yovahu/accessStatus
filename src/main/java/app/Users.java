package app;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

//Класс пользователей
public class Users {

    //Данные которые имеет зарегистрировавшийся пользователь
    private String login;                                                       //Логин пользователя
    private String password;                                                    //Пароль пользователя
    private boolean status;                                                     //Статус пользователя (чи бан, чи не)

    //Инициализация логина пользователя
    public void setLogin(String login) {                //Инициализируем логин пользователю
        this.login = login;
    }

    //Инициализация пароля пользователя
    public void setPassword(String password) {          //Инициализируем пароль пользователю
        this.password = password;
    }

    //Инициализация статуса пользователя
    public void setStatus(boolean status) {
        this.status = status;
    }

    //Получение пароля пользователя
    public String getLogin() {                          //Получаем данные логина пользователя
        return login;
    }

    //Получение пароля пользователя
    public String getPassword() {                       //Получаем данные пароля пользователя
        return password;
    }

    //Получение статуса пользователя
    public boolean getStatus(){
        return status;
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

    public List<Character> ConvertStringToCharList(String str){                   //Конвертация строки в список char
        List<Character> chars = str
                .chars()
                .mapToObj(e -> (char)e)
                .collect(Collectors.toList());

        return chars;
    }

}