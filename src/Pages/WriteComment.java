package Pages;

import Components.LeftBar;
import Components.RightBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import Database.Database;
public class WriteComment extends JFrame implements ActionListener {
    String username;
    JButton likebutton;
    JButton[] likearray = new JButton[15];
    JButton [] commentarray = new JButton[15];
    int[] idarray = new int[15];
    JPanel panel1,panel2,panel3;
    JPanel writepost;
    JLabel logo,label1,label2,label3,label4;
    JLabel pagelabel;
    JTextField postTitle,commentInput;
    JTextArea postBody;
    JButton postButton;
    JButton button1,button2,button3,button4,button5;
    Color mycolor = new Color(15,186,129);
    Color totalwhite  = new Color(255,255,255);
    int Postid;

    WriteComment(String username, int Postid)
    {
        this.username = username;
        this.Postid = Postid;
        panel1 = new LeftBar().sidebar(0,this,username);
        this.feed();
        panel3  = new RightBar().topusers();

        setSize(1920,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Write a commment");

        panel3.setPreferredSize(new Dimension(300,300));



        add(panel1,BorderLayout.WEST);

        add(new JScrollPane(panel2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
        add(panel3,BorderLayout.EAST);

        setVisible(true);
    }



    public void feed() {

        panel2 = new JPanel();
        ;
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBackground(totalwhite);
        panel2.add(Box.createRigidArea(new Dimension(0, 50)));
        pagelabel = new JLabel("Comments");
        panel2.add(pagelabel);
        pagelabel.setFont(new Font("Arial", Font.PLAIN, 30));

        ResultSet posts = new Database().fetchCurrentPost(Postid);
        try {
            while (posts.next()) {
                int id = posts.getInt(1);
                String title = posts.getString(2);
                System.out.println(title);
                String content = posts.getString(3);
                int likes = posts.getInt(4);
                String username = posts.getString(5);
                Date postdate = posts.getDate(6);

                JLabel userlabel = new JLabel("@" + username);
                userlabel.setFont(new Font("Arial", Font.PLAIN, 18));

                JLabel datelabel = new JLabel("‧" + postdate.toString());
                datelabel.setForeground(Color.gray);

                JLabel titlelabel = new JLabel(title);
                titlelabel.setFont(new Font("Arial", Font.PLAIN, 20));
                titlelabel.setForeground(mycolor);

                JTextArea bodyarea = new JTextArea(10, 10);
                bodyarea.setText(content);
                bodyarea.setEditable(false);

                bodyarea.setLineWrap(true);
                bodyarea.setFont(new Font("Arial", Font.PLAIN, 15));


                likebutton = new JButton(Integer.toString(likes));

                JPanel postpanel = new JPanel(null);
                userlabel.setBounds(50, 0, 100, 20);
                datelabel.setBounds(150, 0, 100, 20);
                titlelabel.setBounds(50, 40, 500, 20);
                bodyarea.setBounds(50, 80, 1100, 50);
                likebutton.setBounds(50, 140, 100, 35);
                JLabel likelogo = new JLabel(new ImageIcon(getClass().getResource("Assets/heart.png")));
                likebutton.setBackground(totalwhite);
                likebutton.add(likelogo);

                commentInput = new JTextField("Write comment here......", 300);
                commentInput.setBounds(50, 200, 800, 50);
                commentInput.setFont(new Font("Arial", Font.PLAIN, 13));
                postpanel.add(commentInput);

                postButton = new JButton("Post");
                postButton.setBounds(850, 200, 100, 50);
                postButton.setBackground(mycolor);
                postpanel.add(postButton);
                postButton.addActionListener(this);


                postpanel.add(userlabel);
                postpanel.add(datelabel);
                postpanel.add(titlelabel);
                postpanel.add(bodyarea);
                postpanel.add(likebutton);
                postpanel.setBounds(400, 20, 2500, 600);
                postpanel.setMinimumSize(new Dimension(1250, 300));
                postpanel.setPreferredSize(new Dimension(1250, 300));
                postpanel.setMaximumSize(new Dimension(1250, 300));
                postpanel.setBackground(totalwhite);

                panel2.add(postpanel);
                likebutton.addActionListener(this);

            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("noooo");
        }
        posts = new Database().fetchCurrentComments(Postid);

        System.out.println(posts);
        int y = 0;
        int count = 0;
        try {
            while (posts.next()) {

                int id = posts.getInt(1);
                String content = posts.getString(2);
                String username = posts.getString(3);
                Date postdate = posts.getDate(5);

                JLabel userlabel = new JLabel("@" + username);
                userlabel.setFont(new Font("Arial", Font.PLAIN, 18));

                JLabel datelabel = new JLabel("‧" + postdate.toString());
                datelabel.setForeground(Color.gray);


                JTextArea bodyarea = new JTextArea(1, 5);
                bodyarea.setText(content);
                bodyarea.setEditable(false);

                bodyarea.setLineWrap(true);
                bodyarea.setFont(new Font("Arial", Font.PLAIN, 15));



                JPanel postpanel = new JPanel(null);
                userlabel.setBounds(50, 0, 100, 20);
                datelabel.setBounds(150, 0, 100, 20);
                bodyarea.setBounds(50, 40, 1100, 20);

                postpanel.add(userlabel);
                postpanel.add(datelabel);
                postpanel.add(bodyarea);
                postpanel.setBounds(200, y, 2500, 100);
                postpanel.setMinimumSize(new Dimension(1250, 100));
                postpanel.setPreferredSize(new Dimension(1250, 100));
                postpanel.setMaximumSize(new Dimension(1250, 100));
                postpanel.setBackground(totalwhite);

                panel2.add(postpanel);
                y += 200;
                count += 1;
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("noooo");
        }
    }

    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == postButton) {
            new Database().createcomment(commentInput.getText(),username,Postid);
            this.dispose();
            new WriteComment(username,Postid);
        } else if (e.getSource() == likebutton) {
            if(likebutton.getBackground() == totalwhite){
                likebutton.setBackground(mycolor);
                new Database().likeupdatepost(true,Postid,username);
                likebutton.setText(Integer.toString(Integer.valueOf(likebutton.getText()) + 1));
            }else {
                likebutton.setBackground(totalwhite);
                new Database().likeupdatepost(false,Postid,username);
                likebutton.setText(Integer.toString(Integer.valueOf(likebutton.getText()) - 1));
            }
        }

    }


}
