import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends JFrame implements ActionListener {
    JPanel panel1;
    JLabel label1,label2,label3,label4,label5,label6,label7;
    JTextField textfield1,textfield2,textfield3,textfield4;
    JPasswordField textfield5,textfield6;
    JButton button1 ,button2,button3;
    Font myfont = new Font("Arial", Font.PLAIN, 16);


    Signup(){


        setSize(500,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Sign up");
        panel1 = new JPanel(null);
        label1 = new JLabel("First Name:");
        label2 = new JLabel("Last Name:");
        label3 = new JLabel("Username:");
        label4 = new JLabel("Email:");
        label5 = new JLabel("Password:");
        label6 = new JLabel("Re-enter password:");
        label7  = new JLabel("");
        label1.setFont(myfont);
        label2.setFont(myfont);
        label3.setFont(myfont);
        label4.setFont(myfont);
        label5.setFont(myfont);
        label6.setFont(myfont);
        label7.setFont(myfont);
        label1.setBounds(96,40,100,100);
        label2.setBounds(96,90,100,100);
        label3.setBounds(100,140,100,100);
        label4.setBounds(130,190,100,100);
        label5.setBounds(100,240,100,100);
        label6.setBounds(40,290,150,100);
        label7.setBounds(150,5,300,100);
        label7.setForeground(Color.red);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(label4);
        panel1.add(label5);
        panel1.add(label6);
        panel1.add(label7);
        add(panel1);
        textfield1  = new JTextField(20);
        textfield2  = new JTextField(20);
        textfield3  = new JTextField(20);
        textfield4  = new JTextField(20);
        textfield5  = new JPasswordField(20);
        textfield6  = new JPasswordField(20);

        textfield1.setBounds(200,78,200,25);
        textfield2.setBounds(200,128,200,25);
        textfield3.setBounds(200,178,200,25);
        textfield4.setBounds(200,228,200,25);
        textfield5.setBounds(200,278,200,25);
        textfield6.setBounds(200,328,200,25);

        panel1.add(textfield1);
        panel1.add(textfield2);
        panel1.add(textfield3);
        panel1.add(textfield4);
        panel1.add(textfield5);
        panel1.add(textfield6);

        button1  = new JButton("Cancel");
        button2 = new JButton("Sign up");
        button3 = new JButton("Already have an account");
        button1.setBounds(130,400,100,25);
        button2.setBounds(250,400,100,25);
        button3.setBounds(140,440,200,25);
        button1.setBackground(new Color(254,59,41));
        button2.setBackground(new Color(57,185,255));
        button1.setForeground(Color.white);
        button2.setForeground(Color.white);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);


        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);


        setVisible(true);

    }
    public static void main(String [] args)
    {
        new Signup();
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == button1)
        {
            textfield1.setText("");
            textfield2.setText("");
            textfield3.setText("");
            textfield4.setText("");
            textfield5.setText("");
            textfield6.setText("");
        }
        else if (e.getSource() == button2)
        {
            String fName  = textfield1.getText();
            String lName =textfield2.getText();
            String userName = textfield3.getText();
            String email = textfield4.getText();
            String password = textfield5.getText();
            String repeatPassword = textfield6.getText();

            Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
            Matcher mat = pattern.matcher(email);

            if (fName.equals("") || lName.equals("") || userName.equals("") || email.equals("") ||  password.equals("") || repeatPassword.equals(""))
            {
                label7.setText("None of the fields can be empty");
            } else if (textfield5.getText().length() <8) {
                label7.setText("Password has to be a length of 8 minimum");
                System.out.println();
            } else if (!textfield5.getText().equals(textfield6.getText())) {
                label7.setText("Passwords don't match");
            } else if (!mat.matches()) {
                label7.setText("not a valid email address");
            } else if (new Main().Searchuser(textfield3.getText(),textfield5.getText(),new JLabel(),this)) {
                label7.setText("Username Taken");
            } else {
                Main run = new Main();
                run.Createuser(fName, lName, userName, email, password);
                label7.setForeground(Color.green);
                label7.setText("Account Created");
                this.dispose();
                new Homepage();
            }

        }
        else if (e.getSource() == button3)
        {
            Login lg =  new Login();
            this.dispose();
        }
    }

}
