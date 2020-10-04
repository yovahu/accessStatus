package appGUI;

import app.UserReg;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class signUpForm extends JFrame{
    private static final int width = 720;
    private static final int height = 480;
    private JTextField tfLogin,tfPassword;
    JButton signUpButton,genPassButton;
    JLabel logLabel, passLabel, ml;
    JMenu menu;
    JMenuItem i1,i2;

    UserReg regUser = new UserReg();

    public signUpForm(){
        createGUI();
    }

    public void createGUI(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JMenuBar mb=new JMenuBar();
        signUpButton = new JButton("Sign Up");
        genPassButton = new JButton("G");
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
        genPassButton.setBounds(440,180,42,29);
        signUpButton.setBounds(280,220,160, 30);
        logLabel.setBounds(240,140, 50,30);
        passLabel.setBounds(214,180, 80,30);
        ml.setBounds(225,270,300,30);

        menu.add(i1);menu.add(i2);mb.add(menu);
        add(ml);add(logLabel);add(passLabel);add(signUpButton);add(tfLogin);add(tfPassword);add(genPassButton);

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

        genPassButton.addActionListener(e -> {
            String login = tfLogin.getText();
            String password = regUser.genUserPass(login);
            tfPassword.setText(password);
        });

        signUpButton.addActionListener(e -> {
            String login = tfLogin.getText();
            String password = tfPassword.getText();
            if(login.length()>5 && password.length()>5){

                boolean a = regUser.signUp(login,password);
                if(a){
                    ml.setText("Your account has been successfully registered");
                    try {
                        Thread.sleep(2000);

                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    new menuForm();
                    dispose();
                }else{
                    ml.setBounds(265,110,300,30);
                    ml.setText("User with this login already exists");
                }
            }else{
                ml.setBounds(275,270,300,30);
                ml.setText("Login or password is too short");
            }
        });
    }

}
