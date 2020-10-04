package appGUI;

import app.UserReg;
import app.UserSignIn;
import entities.User;

import javax.swing.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class signInForm extends JFrame{
    private static final int width = 720;
    private static final int height = 480;
    private JTextField tfLogin,tfPassword;
    JButton signInButton;
    JLabel logLabel, passLabel, ml;
    JMenu menu;
    JMenuItem i1,i2;
    int counter;
    List<String> arr= new ArrayList<>();
    UserSignIn userSignIn = new UserSignIn();

    public signInForm(){
        counter = 0;
        createGUI();
    }

    public void createGUI(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JMenuBar mb=new JMenuBar();
        signInButton = new JButton("Sign In");
        tfLogin = new JTextField();
        tfPassword = new JTextField();
        logLabel = new JLabel("Login:");
        passLabel = new JLabel("Password:");
        ml = new JLabel();
        menu=new JMenu("Menu");
        i1=new JMenuItem("Main window");
        i2=new JMenuItem("Exit");

        tfLogin.setBounds(280,140,160,30);
        tfPassword.setBounds(280,180,160,30);
        signInButton.setBounds(280,220,160, 30);
        logLabel.setBounds(240,140, 50,30);
        passLabel.setBounds(214,180, 80,30);
        ml.setBounds(225,270,300,30);

        menu.add(i1);menu.add(i2);mb.add(menu);add(signInButton);
        add(ml);add(logLabel);add(passLabel);add(tfLogin);add(tfPassword);

        setJMenuBar(mb);
        setSize(width,height);
        setLayout(null);
        setVisible(true);

        i1.addActionListener(e -> {
            new menuForm();
            dispose();
        });

        i2.addActionListener(e -> {
            System.exit(0);
        });

        signInButton.addActionListener(e -> {
            String login = tfLogin.getText();
            arr.add(login);
            String password = tfPassword.getText();
            boolean c = userSignIn.signIn(login,password);
            try {
                resetUsersBanTime(login);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if(c){
                ml.setText("success");
                int id = userSignIn.usersDataOnSignInTime.getId();
                String lgn = userSignIn.usersDataOnSignInTime.getLogin();
                String pass = userSignIn.usersDataOnSignInTime.getPassword();
                java.sql.Date dat = userSignIn.usersDataOnSignInTime.getRegistrationDate();
                new userForm(id,lgn,pass,dat);
                dispose();
            }else {
                boolean cl = userSignIn.checkLogins(login);
                if(!cl){
                    if(counter==0){
                        counter++;
                    }else if(counter==1){
                        if(arr.get(0).equals(arr.get(1))){
                            counter++;
                        }else {
                            counter = 0;
                        }
                    }else if(counter == 2){
                        if(arr.get(0).equals(arr.get(1))&&arr.get(1).equals(arr.get(2))){
                            try {
                                HashMap<Integer, User> usersMap = userSignIn.users;
                                for (Map.Entry<Integer, User> item : usersMap.entrySet()) {
                                    if(item.getValue().getLogin().equals(login)){
                                        userSignIn.dbo.insertIntoBanTable(item.getValue().getLogin(),timeOfAction());
                                    }
                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                            counter=0;
                        }
                    }

                }else System.out.println("false");

            }
        });
    }

    public void resetUsersBanTime(String login) throws SQLException {
        String nowDat = timeOfAction();
        String banDat = userSignIn.dbo.getBannedUserData(login);
        if(!banDat.equals("")){
            if(Integer.parseInt(String.valueOf(nowDat.charAt(13))) > Integer.parseInt(String.valueOf(banDat.charAt(13)))){
                userSignIn.dbo.deleteBannedUser(login);
            }else if(Integer.parseInt(String.valueOf(nowDat.charAt(14))) > Integer.parseInt(String.valueOf(banDat.charAt(14)))){
                userSignIn.dbo.deleteBannedUser(login);
            }else if(Integer.parseInt(String.valueOf(nowDat.charAt(16))) > Integer.parseInt(String.valueOf(banDat.charAt(16)))){
                userSignIn.dbo.deleteBannedUser(login);
            }
        }else {
            System.out.println("В таблице \"BanTable\" нет, заблокированных пользователей.");
        }
    }

    public String timeOfAction(){
        GregorianCalendar gcalendar = new GregorianCalendar();
        SimpleDateFormat simpDate;
        simpDate = new SimpleDateFormat("HH:mm:ss");
        String blockDate = (gcalendar.get(Calendar.MONTH) + 1) +
                "-" + gcalendar.get(Calendar.DATE) + "-" +
                gcalendar.get(Calendar.YEAR) + " " +
                simpDate.format(gcalendar.getTime());
        return blockDate;
    }

}
