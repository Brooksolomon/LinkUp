package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Pages.*;

public class LeftBar implements ActionListener{
    JLabel logo,label1,label2,label3,label4,label5;
    JButton button1,button2,button3,button4,button5,button6;
    Color mycolor = new Color(15,186,129);
    Color totalwhite  = new Color(255,255,255);
    JFrame framemain;
    String username;
    public JPanel  sidebar(int num,JFrame frame,String u)
    {
        username = u;
        framemain = frame;
        JPanel panel1 = new JPanel(null);
        logo = new JLabel(new ImageIcon(getClass().getResource("Assets/Logo.png")));
        label1 = new JLabel(new ImageIcon(getClass().getResource("Assets/HomeIcon.png")));
        label2 = new JLabel(new ImageIcon(getClass().getResource("Assets/posts.png")));
        label3 = new JLabel(new ImageIcon(getClass().getResource("Assets/comment.png")));
        label4 = new JLabel(new ImageIcon(getClass().getResource("Assets/like.png")));
        label5 = new JLabel(new ImageIcon(getClass().getResource("Assets/user.png")));


        button1 = new JButton("Home");
        button2 = new JButton("Posts");
        button3 = new JButton("Comments");
        button4 = new JButton("Likes");
        button5 = new JButton("Profile");
        button6 = new JButton("Logout");

        button1.setBackground(totalwhite);
        button2.setBackground(totalwhite);
        button3.setBackground(totalwhite);
        button4.setBackground(totalwhite);
        button5.setBackground(totalwhite);
        button6.setBackground(new Color(254,59,41));
        button6.setForeground(totalwhite);

        if (num ==1)
        {
            button1.setBackground(mycolor);
        } else if (num==2) {
            button2.setBackground(mycolor);
        } else if (num==3) {
            button3.setBackground(mycolor);
        } else if (num==4) {
            button4.setBackground(mycolor);
        } else if (num == 5) {
            button5.setBackground(mycolor);
        }

        button1.add(label1);
        button2.add(label2);
        button3.add(label3);
        button4.add(label4);
        button5.add(label5);


        panel1.setBackground(totalwhite);

        panel1.add(logo);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);
        panel1.add(button5);
        panel1.add(button6);



        logo.setBounds(90,20,120,120);
        button1.setBounds(70,190,150,40);
        button2.setBounds(70,250,150,40);
        button3.setBounds(70,310,150,40);
        button4.setBounds(70,370,150,40);
        button5.setBounds(70,430,150,40);
        button6.setBounds(70,900,150,40);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);



        panel1.setPreferredSize(new Dimension(300,200));

        return panel1;
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button1){
            framemain.dispose();
            new HomePage(username);
        }
        else if (e.getSource()==button2)
        {
            framemain.dispose();
            new Posts(username);
        } else if (e.getSource() == button3) {
            framemain.dispose();
            new Comments(username);
        } else if (e.getSource() ==button4) {
            framemain.dispose();
            new Likes(username);
        } else if (e.getSource() == button6) {
            framemain.dispose();
            new Login();
        } else if (e.getSource() == button5) {
            framemain.dispose();
            new Profile(username);
        }
    }
}
