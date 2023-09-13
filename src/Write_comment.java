import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Write_comment extends JFrame implements ActionListener {
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

    Write_comment(String username,int Postid)
    {
        this.username = username;
        this.Postid = Postid;
        this.sidebar(0);
        this.feed();
        this.topusers();

        setSize(1920,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Write a commment");

        panel3.setPreferredSize(new Dimension(300,300));



        add(panel1,BorderLayout.WEST);

        add(new JScrollPane(panel2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
        add(panel3,BorderLayout.EAST);

        setVisible(true);
    }
    public void  sidebar(int num)
    {
        panel1 = new JPanel(null);
        logo = new JLabel(new ImageIcon(getClass().getResource("Logo.png")));
        label1 = new JLabel(new ImageIcon(getClass().getResource("homeIcon.png")));
        label2 = new JLabel(new ImageIcon(getClass().getResource("posts.png")));
        label3 = new JLabel(new ImageIcon(getClass().getResource("comment.png")));
        label4 = new JLabel(new ImageIcon(getClass().getResource("like.png")));

        button1 = new JButton("Home");
        button2 = new JButton("posts");
        button3 = new JButton("comments");
        button4 = new JButton("Likes");
        button5 = new JButton("Log out");

        button1.setBackground(totalwhite);
        button2.setBackground(totalwhite);
        button3.setBackground(totalwhite);
        button4.setBackground(totalwhite);
        button5.setBackground(new Color(254,59,41));
        button5.setForeground(totalwhite);

        if (num ==1)
        {
            button1.setBackground(mycolor);
        } else if (num==2) {
            button2.setBackground(mycolor);
        } else if (num==3) {
            button3.setBackground(mycolor);
        } else if (num==4) {
            button4.setBackground(mycolor);
        }

        button1.add(label1);
        button2.add(label2);
        button3.add(label3);
        button4.add(label4);


        panel1.setBackground(totalwhite);
        panel1.add(logo);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);
        panel1.add(button5);



        logo.setBounds(90,20,120,120);
        button1.setBounds(70,190,150,40);
        button2.setBounds(70,250,150,40);
        button3.setBounds(70,310,150,40);
        button4.setBounds(70,370,150,40);
        button5.setBounds(70,600,150,40);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);


        panel1.setPreferredSize(new Dimension(300,200));


    }

    public void topusers()
    {
        panel3 = new JPanel(null);
        JLabel header= new JLabel("Top 5 users");
        header.setFont(new Font("Arial",Font.PLAIN,30));
        header.setBounds(80,50,300,30);
        panel3.setBackground(totalwhite);
        panel3.add(header);

        Main temp = new Main();
        ResultSet answer = temp.fetchtopusers();
        int y = 120;
        int rank=1;
        try {
            while (answer.next()) {
                String user = answer.getString(1);
                int count = answer.getInt(2);
                JLabel userlabel = new JLabel(rank + ",@"+ user);
                userlabel.setFont(new Font("Arial",Font.PLAIN,18));
                userlabel.setBounds(100,y,160,20);

                JLabel countlabel = new JLabel(count + " posts");
                countlabel.setBounds(130,y+20,100,20);
                userlabel.setForeground(mycolor);
                panel3.add(userlabel);
                panel3.add(countlabel);
                y+=60;
                rank+=1;
            }
        }catch (Exception e)
        {
            System.out.println("fetch error");
        }

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

        ResultSet posts = new Main().fetchCurrentPost(Postid);
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
                likebutton.setBounds(200, 140, 100, 35);
                JLabel likelogo = new JLabel(new ImageIcon(getClass().getResource("heart.png")));
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
        posts = new Main().fetchCurrentComments(Postid);

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
        if (e.getSource() == button1) {
            this.dispose();
            new Homepage(username);
        } else if (e.getSource() == button3) {
            this.dispose();
            new comments(username);
        } else if (e.getSource() == button4) {
            this.dispose();
            new likes(username);
        } else if (e.getSource() == button5) {
            this.dispose();
            new Login();
        }else if (e.getSource() ==button2) {
            this.dispose();
            new posts(username);
        } else if (e.getSource() == postButton) {
            new Main().createcomment(commentInput.getText(),username,Postid);
        }
    }


}
