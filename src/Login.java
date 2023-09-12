import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    JPanel panel1;
    JLabel label1,label2,label3;
    JTextField textfield1;
    JPasswordField textfield2;
    JButton button1,button2,button3;
    Font myFont = new Font("Arial", Font.PLAIN, 16);
    Login(){

        setSize(450,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login");

        panel1 = new JPanel(null);
        add(panel1);
       label1 = new JLabel("Username:");
       label2 = new JLabel("Password:");
       label1.setFont(myFont);
       label2.setFont(myFont);
       label1.setBounds(60,40,80,40);
       label2.setBounds(60,90,80,40);
       label3 = new JLabel("");
       label3.setFont(myFont);
       label3.setBounds(150,10,200,40);
       label3.setForeground(Color.red);


        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);

        textfield1 = new JTextField(20);
        textfield2  = new JPasswordField(20);

        textfield1.setBounds(150,50,200,25);
        textfield2.setBounds(150,100,200,25);


        panel1.add(textfield1);
        panel1.add(textfield2);

        button1 = new JButton("Cancel");
        button2 = new JButton("Login");
        button3 = new JButton("Create account");

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);


        button1.setBounds(100,150,100,25);
        button2.setBounds(220,150,100,25);
        button3.setBounds(100,200,220,25);

        button1.setBackground(new Color(254,59,41));
        button2.setBackground(new Color(57,185,255));
        button1.setForeground(Color.white);
        button2.setForeground(Color.white);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);





        setVisible(true);
    }
    public static  void main(String[] args) {
         new Login();

    }
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == button1)
        {
            textfield1.setText("");
            textfield2.setText("");
        } else if (e.getSource() == button2) {
            Main run = new Main();
            run.Searchuser(textfield1.getText(),textfield2.getText(),label3,this);
        } else if (e.getSource() == button3) {
            Signup sp = new Signup();
            this.dispose();
        }
    }
}
