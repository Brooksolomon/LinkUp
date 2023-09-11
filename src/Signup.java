import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends JFrame implements ActionListener {
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
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);


        p1.add(b1);
        p1.add(b2);
        p1.add(b3);


        setVisible(true);

    }
    public static void main(String [] args)
    {
        new Signup();
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == b1)
        {
            tf1.setText("");
            tf2.setText("");
            tf3.setText("");
            tf4.setText("");
            tf5.setText("");
            tf6.setText("");
        }
        else if (e.getSource() == b2)
        {
            String fName  = tf1.getText();
            String lName =tf2.getText();
            String userName = tf3.getText();
            String email = tf4.getText();
            String password = tf5.getText();
            String repeatPassword = tf6.getText();

            Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
            Matcher mat = pattern.matcher(email);

            if (fName.equals("") || lName.equals("") || userName.equals("") || email.equals("") ||  password.equals("") || repeatPassword.equals(""))
            {
                System.out.println("None of the fields can be empty");
            } else if (tf5.getText().length() <8) {
                System.out.println("Password has to be a length of 8 minimum");
            } else if (!tf5.getText().equals(tf6.getText())) {
                System.out.println("Passwords don't match");
            } else if (!mat.matches()) {
                System.out.println("not a valid email address");
            }
            else {
                Main run = new Main();
                run.Createuser(fName, lName, userName, email, password);
            }
        }
    }

}
