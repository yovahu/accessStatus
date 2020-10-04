package appGUI;

import app.UserSignIn;

import javax.swing.*;
import java.sql.SQLException;

public class userForm extends JFrame {
    private static final int width = 720;
    private static final int height = 480;
    private JTextField newPass;
    JLabel passLabell, ml;
    JButton changePass,genPassButton,deleteAccButton;
    JMenu menu;
    JMenuItem i1, i2;
    private final int id;
    private final String lgn, pass;
    private final java.sql.Date dat;
    UserSignIn userSignIn = new UserSignIn();

    userForm(int id, String lgn, String pass, java.sql.Date dat) {
        this.id = id;
        this.lgn = lgn;
        this.pass = pass;
        this.dat = dat;
        createGUI();
    }

    public void createGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JMenuBar mb = new JMenuBar();
        changePass = new JButton("Change password");
        genPassButton = new JButton("G");
        deleteAccButton = new JButton("Delete Acc");
        newPass = new JTextField();
        passLabell = new JLabel("Password:");
        ml = new JLabel();
        menu = new JMenu("Menu");
        i1 = new JMenuItem("Main window");
        i2 = new JMenuItem("Exit");

        deleteAccButton.setBounds(280, 220, 160, 30);
        genPassButton.setBounds(440,180,42,29);
        changePass.setBounds(280, 140, 160, 30);
        newPass.setBounds(280, 180, 160, 30);
        passLabell.setBounds(200, 180, 160, 30);
        ml.setBounds(225, 270, 300, 30);

        menu.add(i1);add(genPassButton);menu.add(i2);mb.add(menu);
        add(changePass);add(ml);add(passLabell);add(newPass);add(deleteAccButton);

        setJMenuBar(mb);
        setSize(width, height);
        setLayout(null);
        setVisible(true);

        deleteAccButton.addActionListener(e -> {
            try {
                userSignIn.delete(id);
                ml.setText("Аккаунт успешно удалён.");
                Thread.sleep(2000);
                new menuForm();
                dispose();
            } catch (SQLException | InterruptedException ex) {
                ex.printStackTrace();
            }

        });

        genPassButton.addActionListener(e -> {
            String password = userSignIn.genUserPass(lgn);
            newPass.setText(password);
        });

        i1.addActionListener(e -> {
            new menuForm();
            dispose();
        });

        i2.addActionListener(e -> {
            System.exit(0);
        });

        changePass.addActionListener(e -> {
            try {
                userSignIn.update(id,newPass.getText());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            ml.setText("Пароль успешно изменён");
        });
    }
}
