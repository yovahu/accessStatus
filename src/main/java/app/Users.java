package app;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Users {

    private String login;                               //Логин пользователя
    private String password;                            //Пароль пользователя
    private boolean status;                             //Статус пользователя (чи бан, чи не)

    public void setLogin(String login) {                //Инициализируем логин пользователю
        this.login = login;
    }

    public void setPassword(String password) {          //Инициализируем пароль пользователю
        this.password = password;
    }

    public String getLogin() {                          //Получаем данные логина пользователя
        return login;
    }

    public String getPassword() {                       //Получаем данные пароля пользователя
        return password;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return status;
    }

    public String genUserPass(String login) {                //Генерация пароля пользователя
        StringBuilder Qs, Mlps;
        final Random rand = new Random();
        String ruWords = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        String[] symbols = {String.valueOf('!'), String.valueOf('"'), "#", "$", "%", "&", "'", "(", ")", "*"};
        int b1 = rand.nextInt(9)+1;
        int b2 = rand.nextInt(9)+1;

        int Q = login.length() % 8;
        int Mlp = 9 - Q;

        Qs = new StringBuilder();
        Mlps = new StringBuilder();


        for (int i = 0; i < Q; i++){
            Qs.append(ruWords.charAt(rand.nextInt(ruWords.length())));
        }

        for (int i = 0; i < Mlp; i++){
            Mlps.append(symbols[rand.nextInt(symbols.length)]);
        }

        return String.valueOf(b1) + b2 + Qs + Mlps;
    }

    public List<Character> ConvertStringToCharList(String str){          //Конвертим строку в список char
        List<Character> chars = str
                .chars()
                .mapToObj(e -> (char)e)
                .collect(Collectors.toList());

        return chars;
    }

}