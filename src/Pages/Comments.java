package Pages;

import Components.LeftBar;
import Components.RightBar;
import Database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Comments extends JFrame implements ActionListener {
    String username;

    JLabel pagelabel;
    JButton[] likearray = new JButton[15];
    int commentidarray [] = new int[15];
    int[] idarray = new int[15];
    JButton commentbutton;
    JButton [] commentarray = new JButton[15];
    JPanel panel1,panel2,panel3;
    JButton deletebutton;
    JButton deletearray[] = new JButton[15] ;
    Color mycolor = new Color(15,186,129);
    Color totalwhite  = new Color(255,255,255);

    public Comments(String username)
    {
        panel3  = new RightBar().topusers();
        this.username = username;
        panel1 = new LeftBar().sidebar(3,this,username);
        feed();
        setSize(1920,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Home page");


        panel3.setPreferredSize(new Dimension(300,300));



        add(panel1,BorderLayout.WEST);
        add(new JScrollPane(panel2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
        add(panel3,BorderLayout.EAST);

        setVisible(true);
    }

    public void feed() {

        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBackground(totalwhite);
        panel2.add(Box.createRigidArea(new Dimension(0, 50)));
        pagelabel = new JLabel("My comments");
        panel2.add(pagelabel);
        pagelabel.setFont(new Font("Arial", Font.PLAIN, 30));

        ResultSet posts = new Database().fetchMyComments(username);
        System.out.println(posts);
        int y = 0;
        int count = 0;
        try {
            while (posts.next()) {

                int id = posts.getInt(1);
                String content = posts.getString(2);
                String username = posts.getString(3);
                int postid = posts.getInt(4);
                Date postdate = posts.getDate(5);

                JLabel userlabel = new JLabel("@" + username);
                userlabel.setFont(new Font("Arial", Font.PLAIN, 18));

                JLabel datelabel = new JLabel("‧" + postdate.toString());
                datelabel.setForeground(Color.gray);

                JTextArea bodyarea = new JTextArea(10, 10);
                bodyarea.setText(content);
                bodyarea.setEditable(false);

                bodyarea.setLineWrap(true);
                bodyarea.setFont(new Font("Arial", Font.PLAIN, 15));


                JPanel postpanel = new JPanel(null);
                userlabel.setBounds(50, 0, 100, 20);
                datelabel.setBounds(150, 0, 100, 20);
                bodyarea.setBounds(50, 80, 1100, 50);

                commentbutton = new JButton("See post");
                commentbutton.setBounds(50,140,100,35);
                commentbutton.setBackground(totalwhite);
                postpanel.add(commentbutton);
                commentbutton.addActionListener(this);
                commentarray [count] = commentbutton;

                deletebutton = new JButton("Delete comment");
                deletebutton.setBounds(180,140,130,35);
                deletebutton.setBackground(new Color(254,59,41));
                deletebutton.setForeground(totalwhite);
                postpanel.add(deletebutton);
                deletebutton.addActionListener(this);
                deletearray[count] = deletebutton;


                postpanel.add(userlabel);
                postpanel.add(datelabel);
                postpanel.add(bodyarea);
                postpanel.setBounds(400, y, 2500, 600);
                postpanel.setMinimumSize(new Dimension(1250, 200));
                postpanel.setPreferredSize(new Dimension(1250, 200));
                postpanel.setMaximumSize(new Dimension(1250, 200));
                postpanel.setBackground(totalwhite);

                panel2.add(postpanel);
                idarray[count] = postid;
                commentidarray[count] = id;
                y += 2000;
                count += 1;
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("noooo");
        }
        System.out.println(count);
    }
    public void actionPerformed(ActionEvent e) {
        for (int i = 0 ; i < commentarray.length;i++)
        {
            if (e.getSource() == commentarray[i])
            {
                new WriteComment(username,idarray[i]);
            }

        }
        for (int i = 0 ; i < deletearray.length; i++)
        {
            if (e.getSource() == deletearray[i])
            {
                new Database().deleteComment(commentidarray[i]);
                this.dispose();
                new Comments(username);
            }
        }
    }
}
