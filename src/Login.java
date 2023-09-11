import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    JPanel p1;
    JLabel l1,l2;
    JTextField tf1;
    JPasswordField tf2;
    JButton b1,b2,b3;
    Font mf = new Font("Arial", Font.PLAIN, 16);
    Login(){

        setSize(450,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login");

        p1 = new JPanel(null);
        add(p1);

        l1 = new JLabel("Username:");
        l2 = new JLabel("Password:");
        l1.setFont(mf);
        l2.setFont(mf);
        l1.setBounds(60,40,80,40);
        l2.setBounds(60,90,80,40);

        p1.add(l1);
        p1.add(l2);

        tf1 = new JTextField(20);
        tf2  = new JPasswordField(20);

        tf1.setBounds(150,50,200,25);
        tf2.setBounds(150,100,200,25);

        p1.add(tf1);
        p1.add(tf2);

        b1 = new JButton("Cancel");
        b2 = new JButton("Login");
        b3 = new JButton("Create account");

        b1.setBounds(100,150,100,25);
        b2.setBounds(220,150,100,25);
        b3.setBounds(100,200,220,25);

        b1.setBackground(new Color(254,59,41));
        b2.setBackground(new Color(57,185,255));
        b1.setForeground(Color.white);
        b2.setForeground(Color.white);
        p1.add(b1);
        p1.add(b2);
        p1.add(b3);
        setVisible(true);
    }
    public static  void main(String[] args) {
         new Login();
    }
}