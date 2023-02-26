package project;

import java.awt.*;
import javax.swing.*;

import java.awt.Container;
import java.awt.event.*;

import java.sql.*;

public class homePage extends JFrame {
    ImageIcon img;
    JLabel background;
    JLabel mess;
    JPanel panel;
    JLabel login;
    Container container;
    JLabel username;
    JTextField userfield;
    JLabel Password;
    JTextField passfield;
    JButton submit;
    JButton clear;
    JButton Signup;
    String url = "jdbc:mysql://localhost:3306/login";
    String user = "root";
    String password = "toor";
    Connection con;
    ResultSet rs, rs1;
    Statement st, st1;

    homePage() {
        // try {
        // Connection con = (Connection) DriverManager
        // .getConnection("jdbc:mysql://localhost:4306/?user=root&password=");
        // con.close();
        // } catch (Exception e) {
        // System.out.println(e);
        // }

        container = getContentPane();
        container.setLayout(null);

        img = new ImageIcon(getClass().getResource("image2.jpg"));
        background = new JLabel(img);
        background.setBounds(0, 0, 1200, 700);
        add(background);

        mess = new JLabel("Banking System");
        mess.setForeground(Color.RED);
        mess.setFont(new Font("", Font.BOLD, 30));
        mess.setBounds(500, 10, 600, 40);
        background.add(mess);

        panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0, 50));
        panel.setLocation(400, 150);
        panel.setSize(400, 400);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.BLACK));
        background.add(panel);

        login = new JLabel("Login System");
        login.setFont(new Font("", Font.BOLD, 20));
        login.setForeground(Color.RED);
        login.setBounds(80, 0, 600, 50);
        panel.add(login);

        username = new JLabel("Email :");
        username.setForeground(Color.RED);
        username.setFont(new Font("", Font.BOLD, 20));
        username.setBounds(100, 50, 200, 30);
        panel.add(username);

        userfield = new JTextField();
        userfield.setBounds(100, 80, 200, 30);
        panel.add(userfield);

        Password = new JLabel("Password :");
        Password.setForeground(Color.RED);
        Password.setFont(new Font("", Font.BOLD, 20));
        Password.setBounds(100, 110, 200, 30);
        panel.add(Password);

        passfield = new JTextField();
        passfield.setBounds(100, 140, 200, 30);
        panel.add(passfield);

        submit = new JButton();
        submit.setText("Submit");
        submit.setForeground(Color.RED);
        submit.setBounds(100, 200, 200, 30);
        panel.add(submit);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s1 = userfield.getText();
                String s = passfield.getText();
                if (s1.equals("") || s.equals("")) {
                    JOptionPane.showMessageDialog(panel, "Enter Email or password!");
                }
                // new Bankprocess();
                try {
                    Connection con = (Connection) DriverManager
                            .getConnection("jdbc:mysql://localhost:3306/login", "root", "ranjan5044");
                    String query = "Select pass from bank where email=?";
                    PreparedStatement x = (PreparedStatement) con.prepareStatement(query);
                    x.setString(1, s1);
                    ResultSet rs = x.executeQuery();
                    while (rs.next()) {
                        String Name = rs.getString("pass");
                        if (s.compareTo(Name) == 0) {
                            new Bankprocess();
                        } else {
                            JOptionPane.showMessageDialog(panel, "Wrong Email or password!");
                        }
                    }
                    con.close();
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        });

        clear = new JButton();
        clear.setForeground(Color.RED);
        clear.setText("Clear");
        clear.setBounds(100, 250, 200, 30);
        panel.add(clear);
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userfield.setText("");
                passfield.setText("");
            }
        });

        Signup = new JButton();
        Signup.setForeground(Color.RED);
        Signup.setText("Register");
        Signup.setBounds(100, 300, 200, 30);
        panel.add(Signup);
        Signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Register();
            }
        });

    }

    public static void main(String[] args) {

        homePage frame = new homePage();
        frame.setTitle("Login page");
        frame.setVisible(true);
        frame.setSize(1200, 700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setBackground(Color.LIGHT_GRAY);

    }

}
