package entities;

//Класс пользователей
public class User {

    //Данные которые имеет зарегистрировавшийся пользователь
    private int id;
    private String login;
    private String password;
    private java.sql.Date registrationDate;

    public User(){}

    public User(String login, String password, java.sql.Date registrationDate) {
        this.login = login;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    //Уникальный идентификатор
    public void setId(int id) {
        this.id = id;
    }

    //получение ID
    public int getId() {
        return id;
    }

    //Инициализация логина
    public void setLogin(String login) {
        this.login = login;
    }

    //Получение пароля
    public String getLogin() {
        return login;
    }

    //Инициализация пароля
    public void setPassword(String password) {
        this.password = password;
    }

    //Получение пароля
    public String getPassword() {
        return password;
    }

    //Инициализация даты регистрации
    public void setRegistrationDate(java.sql.Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    //Получение даты регистрации
    public java.sql.Date getRegistrationDate() {
        return registrationDate;
    }

    /*public List<Character> ConvertStringToCharList(String str){                   //Конвертация строки в список char
        List<Character> chars = str
                .chars()
                .mapToObj(e -> (char)e)
                .collect(Collectors.toList());

        return chars;
    }*/

}