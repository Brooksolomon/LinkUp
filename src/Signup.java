import javax.swing.*;
import java.awt.*;

public class Signup extends JFrame {
    JPanel p1;
    JLabel l1,l2,l3,l4,l5,l6;
    JTextField tf1,tf2,tf3,tf4;
    JPasswordField tf5,tf6;
    JButton b1 ,b2,b3;
    Font mf = new Font("Arial", Font.PLAIN, 16);


    Signup(){


        setSize(500,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Sign up");
        p1 = new JPanel(null);
        l1 = new JLabel("First Name:");
        l2 = new JLabel("Last Name:");
        l3 = new JLabel("Username:");
        l4 = new JLabel("Email:");
        l5 = new JLabel("Password:");
        l6 = new JLabel("Re-enter password:");
        l1.setFont(mf);
        l2.setFont(mf);
        l3.setFont(mf);
        l4.setFont(mf);
        l5.setFont(mf);
        l6.setFont(mf);
        l1.setBounds(96,40,100,100);
        l2.setBounds(96,90,100,100);
        l3.setBounds(100,140,100,100);
        l4.setBounds(130,190,100,100);
        l5.setBounds(100,240,100,100);
        l6.setBounds(40,290,150,100);
        p1.add(l1);
        p1.add(l2);
        p1.add(l3);
        p1.add(l4);
        p1.add(l5);
        p1.add(l6);
        add(p1);
        tf1  = new JTextField(20);
        tf2  = new JTextField(20);
        tf3  = new JTextField(20);
        tf4  = new JTextField(20);
        tf5  = new JPasswordField(20);
        tf6  = new JPasswordField(20);

        tf1.setBounds(200,78,200,25);
        tf2.setBounds(200,128,200,25);
        tf3.setBounds(200,178,200,25);
        tf4.setBounds(200,228,200,25);
        tf5.setBounds(200,278,200,25);
        tf6.setBounds(200,328,200,25);

        p1.add(tf1);
        p1.add(tf2);
        p1.add(tf3);
        p1.add(tf4);
        p1.add(tf5);
        p1.add(tf6);

        b1  = new JButton("Cancel");
        b2 = new JButton("Sign up");
        b3 = new JButton("Already have an account");
        b1.setBounds(130,400,100,25);
        b2.setBounds(250,400,100,25);
        b3.setBounds(140,440,200,25);
        b1.setBackground(new Color(254,59,41));
        b2.setBackground(new Color(57,185,255));
        b1.setForeground(Color.white);
        b2.setForeground(Color.white);


        p1.add(b1);
        p1.add(b2);
        p1.add(b3);


        setVisible(true);

    }
    public static void main(String [] args)
    {
        new Signup();
    }
}
