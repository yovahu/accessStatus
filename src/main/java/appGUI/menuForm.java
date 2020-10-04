package appGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuForm extends JFrame {
    private static final int width = 720;
    private static final int height = 480;

    private final JButton b1,b2;

    public menuForm(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Main");
        b1 = new JButton("Sign Up");
        b2 = new JButton("Sign In");

        b1.setBounds(260,170,160, 40);
        b2.setBounds(260,220,160, 40);

        add(b1);
        add(b2);

        ActionListener actionListener = new bActionListener();

        b1.addActionListener(actionListener);
        b2.addActionListener(actionListener);

        setSize(width,height);
        setLayout(null);
        setVisible(true);
    }

    public class bActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == b1){
                new signUpForm();
                dispose();
            }else if(e.getSource() == b2){
                new signInForm();
                dispose();
            }
        }
    }

    public static void main(String[] args) {
        new menuForm();
    }
}
