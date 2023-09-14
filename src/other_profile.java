import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLOutput;

public class other_profile extends JFrame implements ActionListener {


    String username;
    String profileuser;
    JPanel panel1, panel2, panel3;
    JButton  followbutton;
    JLabel pagelabel;
    Color mycolor = new Color(15, 186, 129);
    Color totalwhite = new Color(255, 255, 255);
    String firstName,lastName,userName ,email,password ;
    JTextField fnField,lnField,userField,EmailField;

    other_profile(String username,String profileuser) {
        this.username = username;
        this.profileuser = profileuser;
        panel1 = new letbar().sidebar(0, this, username);
        this.feed();
        panel3 = new rightbar().topusers();


        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Profile");

        panel3.setPreferredSize(new Dimension(300, 300));


        add(panel1, BorderLayout.WEST);
        add(new JScrollPane(panel2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
        add(panel3, BorderLayout.EAST);

        setVisible(true);
    }

    public void feed() {
        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBackground(totalwhite);
        pagelabel = new JLabel("user profile");
        panel2.add(Box.createRigidArea(new Dimension(5, 30)));
        panel2.add(pagelabel);
        pagelabel.setFont(new Font("Arial", Font.PLAIN, 30));


        JLabel label1, label2, label3, label4, label6;
        label1 = new JLabel("First name");
        label2 = new JLabel("Last name ");
        label3 = new JLabel("Username");
        label4 = new JLabel("Email");

        Font myfont  = new Font("Arial", Font.PLAIN, 20);
        System.out.println(profileuser);
        ResultSet data = new Main().userprofile(profileuser);
        System.out.println("your data:" + data);
        label1.setFont(myfont);
        label2.setFont(myfont);
        label3.setFont(myfont);
        label4.setFont(myfont);
        try {
            while (data.next()) {

                int id = data.getInt(1);
                firstName = data.getString(2);
                lastName = data.getString(3);
                userName = data.getString(4);
                email = data.getString(5);
                password = data.getString(6);


                System.out.println("firstname" + firstName);
                JPanel profilePanel = new JPanel(null);

                fnField = new JTextField(firstName);
                lnField = new JTextField(lastName);
                userField = new JTextField(userName);
                EmailField = new JTextField(email);


                label1.setBounds(40,160,200,40);
                label2.setBounds(700,160,200,40);
                label3.setBounds(40,260,200,40);
                label4.setBounds(700,260,200,40);

                fnField.setBounds(140,160,200,40);
                lnField.setBounds(800,160,200,40);
                userField.setBounds(140,260,200,40);
                EmailField.setBounds(800,260,200,40);

                fnField.setEditable(false);
                lnField.setEditable(false);
                userField.setEditable(false);
                EmailField.setEditable(false);



                followbutton = new JButton("Follow");
                followbutton.setBackground(mycolor);
                followbutton.setForeground(totalwhite);
                followbutton.setBounds(900,60,100,40);

                followbutton.addActionListener(this);
                if (new Main().checkiffollows(username,profileuser))
                {
                    System.out.println("DO i follow :" + new Main().checkiffollows(username,profileuser));
                    followbutton.setBackground(new Color(254,59,41));
                    followbutton.setText("unfollow");
                }

                System.out.println("oiggy");
                profilePanel.add(label1);
                profilePanel.add(label2);
                profilePanel.add(label3);
                profilePanel.add(label4);

                profilePanel.add(followbutton);


                profilePanel.add(fnField);
                profilePanel.add(lnField);
                profilePanel.add(userField);
                profilePanel.add(EmailField);

                profilePanel.setBackground(totalwhite);
                panel2.add(profilePanel);


            }
        } catch (Exception e) {
            System.out.println("new error1");
        }

    }













    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == followbutton)
        {
            if(followbutton.getBackground() == mycolor)
            {
                followbutton.setBackground(new Color(254,59,41));
                followbutton.setText("unfollow");
                new Main().createfollow(username,profileuser,true);
            }
            else{
                followbutton.setBackground(mycolor);
                followbutton.setText("follow");
                new Main().createfollow(username,profileuser,false);
            }

        }
    }
}
