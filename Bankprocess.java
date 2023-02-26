package project;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.sql.*;

public class Bankprocess extends JFrame {
    Container cont;
    ImageIcon img;
    JLabel back;
    JPanel panel;
    JPanel panel2;
    JLabel title;
    JLabel id;
    JTextField idfield;
    JLabel pin;
    JTextField pinfiled;
    JButton submit;
    JLabel printAccl;
    JLabel printnamel;
    JLabel printBalancel;
    JTextField printAc;
    JTextField printname;
    JTextField printBalance;
    JLabel amount;
    JTextField amountf;
    JButton withraw;
    JButton Deposit;
    double intial = 0;
    double famount = 0;
    String name = "";

    Bankprocess() {
        setTitle("BankProcess");
        setVisible(true);
        setSize(1200, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setBackground(Color.LIGHT_GRAY);

        cont = getContentPane();
        cont.setLayout(null);

        img = new ImageIcon(getClass().getResource("image2.jpg"));
        back = new JLabel(img);
        back.setBounds(0, 0, 1200, 700);
        add(back);

        panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0, 50));
        panel.setLocation(100, 100);
        panel.setSize(200, 200);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.BLACK));
        back.add(panel);

        id = new JLabel("Account No : ");
        id.setForeground(Color.RED);
        id.setFont(new Font("", Font.BOLD, 15));
        id.setBounds(10, 50, 150, 20);
        panel.add(id);

        idfield = new JTextField();
        idfield.setBounds(10, 70, 150, 30);
        panel.add(idfield);

        pin = new JLabel("Enter pin : ");
        pin.setForeground(Color.RED);
        pin.setFont(new Font("", Font.BOLD, 15));
        pin.setBounds(10, 105, 100, 20);
        panel.add(pin);

        pinfiled = new JTextField();
        pinfiled.setBounds(10, 125, 150, 30);
        panel.add(pinfiled);

        panel2 = new JPanel();
        panel2.setBackground(new Color(0, 0, 0, 50));
        panel2.setLocation(600, 100);
        panel2.setSize(400, 400);
        panel2.setLayout(null);
        panel2.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.BLACK));
        back.add(panel2);

        printnamel = new JLabel("Name :");
        printnamel.setForeground(Color.RED);
        printnamel.setFont(new Font("", Font.BOLD, 15));
        printnamel.setBounds(50, 100, 100, 20);
        panel2.add(printnamel);

        printAccl = new JLabel("Account No :");
        printAccl.setForeground(Color.RED);
        printAccl.setFont(new Font("", Font.BOLD, 15));
        printAccl.setBounds(50, 50, 100, 20);
        panel2.add(printAccl);

        printBalancel = new JLabel("Balance :");
        printBalancel.setForeground(Color.RED);
        printBalancel.setFont(new Font("", Font.BOLD, 15));
        printBalancel.setBounds(200, 50, 100, 20);
        panel2.add(printBalancel);

        amount = new JLabel("Enter Amount :");
        amount.setForeground(Color.RED);
        amount.setFont(new Font("", Font.BOLD, 15));
        amount.setBounds(140, 200, 100, 20);
        panel2.add(amount);

        submit = new JButton("Submit");
        submit.setBounds(50, 170, 100, 30);
        panel.add(submit);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long s1 = Long.parseLong(idfield.getText());
                int s = Integer.parseInt(pinfiled.getText());
                try {
                    Connection con = (Connection) DriverManager
                            .getConnection("jdbc:mysql://localhost:3306/login", "root", "ranjan5044");
                    String query = "Select pin from bank where Adhar=?";
                    PreparedStatement x = (PreparedStatement) con.prepareStatement(query);
                    x.setLong(1, s1);
                    ResultSet rs = x.executeQuery();
                    while (rs.next()) {
                        int num = rs.getInt("pin");
                        if (s == num) {
                            printAc = new JTextField(s1 + "");
                            printAc.setBounds(50, 70, 145, 30);
                            panel2.add(printAc);

                            printname = new JTextField(name);
                            printname.setBounds(50, 120, 150, 30);
                            panel2.add(printname);

                            printBalance = new JTextField(famount + "");
                            printBalance.setBounds(200, 70, 150, 30);
                            panel2.add(printBalance);

                            amountf = new JTextField();
                            amountf.setBounds(130, 220, 150, 30);
                            panel2.add(amountf);

                            withraw = new JButton("Withdraw");
                            withraw.setBounds(100, 270, 100, 30);
                            panel2.add(withraw);
                            withraw.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    double amount = Double.parseDouble(amountf.getText());
                                    try {
                                        Connection con = (Connection) DriverManager
                                                .getConnection("jdbc:mysql://localhost:3306/login", "root",
                                                        "ranjan5044");
                                        String query = "Update bank set Amount= ? where Adhar=?";
                                        PreparedStatement st = (PreparedStatement) con.prepareStatement(query);
                                        famount = intial - amount;
                                        st.setDouble(1, famount);
                                        st.setLong(2, s1);
                                        int x = st.executeUpdate();
                                        if (x == 1) {
                                            JOptionPane.showMessageDialog(panel, "Balance is Deposit.");
                                        }
                                        con.close();
                                    } catch (Exception exception) {
                                        System.out.println(e);
                                    }

                                }
                            });

                            Deposit = new JButton("Deposit");
                            Deposit.setBounds(205, 270, 100, 30);
                            panel2.add(Deposit);
                            Deposit.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {

                                    double amount = Double.parseDouble(amountf.getText());

                                    try {
                                        Connection con = (Connection) DriverManager
                                                .getConnection("jdbc:mysql://localhost:3306/login", "root",
                                                        "ranjan5044");
                                        String query = "Select Amount , fname ,lname from bank where Adhar =?";
                                        PreparedStatement st = (PreparedStatement) con.prepareStatement(query);
                                        st.setDouble(1, s1);
                                        ResultSet result = st.executeQuery();
                                        double i = result.getDouble("Amount");
                                        String fname = result.getString("fname");
                                        String lname = result.getString("lname");
                                        name = fname + " " + lname;
                                        con.close();
                                        Connection conn = (Connection) DriverManager
                                                .getConnection("jdbc:mysql://localhost:3306/login", "root",
                                                        "ranjan5044");
                                        String query2 = "Update bank set Amount= ? where Adhar=?";
                                        PreparedStatement st2 = (PreparedStatement) conn.prepareStatement(query);
                                        double f = i + amount;
                                        st2.setDouble(1, f);
                                        st2.setLong(2, s1);
                                        int x = st2.executeUpdate();
                                        if (x == 1) {
                                            JOptionPane.showMessageDialog(panel, "Balance is Deposit.");
                                        }
                                        con.close();
                                    } catch (Exception exception) {
                                        System.out.println(e);
                                    }
                                }
                            });
                        } else {
                            JOptionPane.showMessageDialog(panel, "Wrong Account Number or Pin number");
                        }
                    }
                    con.close();
                } catch (Exception exception) {
                    System.out.println(exception);
                }

            }
        });

    }
}
