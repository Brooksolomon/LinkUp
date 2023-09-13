import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class likes extends JFrame implements ActionListener {
    String username;
    JButton likebutton;
    JPanel panel1,panel2,panel3;

    JLabel logo,label1,label2,label3,label4;
    JLabel pagelabel;
    JButton[] likearray = new JButton[15];
    int[] idarray = new int[15];
    JButton commentbutton;
    JButton [] commentarray = new JButton[15];
    JButton button1,button2,button3,button4,button5;
    Color mycolor = new Color(15,186,129);
    Color totalwhite  = new Color(255,255,255);
    likes(String username)
    {
        this.username = username;
        topusers();
        feed();


        sidebar(4);

        setSize(1920,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Home page");


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
        JScrollPane newscroller = new JScrollPane(panel2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        newscroller.getVerticalScrollBar().setBlockIncrement(10);
        add(newscroller, BorderLayout.CENTER);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBackground(totalwhite);
        panel2.add(Box.createRigidArea(new Dimension(0, 50)));
        pagelabel = new JLabel("Liked posts");
        panel2.add(pagelabel);
        pagelabel.setFont(new Font("Arial", Font.PLAIN, 30));
        System.out.println(username);
        ResultSet posts = new Main().fetchLikedPosts(username);
        int y = 0;
        int count = 0;
        try {
            while (posts.next()) {

                int id = posts.getInt(1);
                String title = posts.getString(2);
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

                commentbutton = new JButton("");
                commentbutton.setBounds(600,140,100,35);
                JLabel commentlogo = new JLabel(new ImageIcon(getClass().getResource("comment.png")));
                commentbutton.setBackground(totalwhite);
                commentbutton.add(commentlogo);
                postpanel.add(commentbutton);
                commentbutton.addActionListener(this);
                commentarray [count] = commentbutton;


                postpanel.add(userlabel);
                postpanel.add(datelabel);
                postpanel.add(titlelabel);
                postpanel.add(bodyarea);
                postpanel.add(likebutton);
                postpanel.setBounds(400, y, 2500, 600);
                postpanel.setMinimumSize(new Dimension(1250, 200));
                postpanel.setPreferredSize(new Dimension(1250, 200));
                postpanel.setMaximumSize(new Dimension(1250, 200));
                postpanel.setBackground(totalwhite);

                panel2.add(postpanel);
//                panel2.add(Box.createRigidArea(new Dimension(0,20)));
                likebutton.addActionListener(this);
                likearray[count] = likebutton;
                idarray[count] = id;
                y += 2000;
                count += 1;
                if (new Main().checkifliked(this.username,id))
                {
                    likebutton.setBackground(mycolor);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("noooo");
        }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button1)
        {
            this.dispose();
            new Homepage(username);
        } else if (e.getSource() == button3) {
            this.dispose();
            new comments(username);
        } else if (e.getSource() ==button2) {
            this.dispose();
            new posts(username);
        } else if (e.getSource() == button5) {
            this.dispose();
            new Login();
        }
        for(int i= 0; i < likearray.length;i++)
        {
            if (likearray[i] == e.getSource())
            {
                if(likearray[i].getBackground() == totalwhite){
                    likearray[i].setBackground(mycolor);
                    Main ne = new Main();
                    ne.likeupdatepost(true,idarray[i],username);
                    likearray[i].setText(Integer.toString(Integer.valueOf(likearray[i].getText()) + 1));
                }else {
                    likearray[i].setBackground(totalwhite);
                    Main ne = new Main();
                    ne.likeupdatepost(false,idarray[i],username);
                    likearray[i].setText(Integer.toString(Integer.valueOf(likearray[i].getText()) - 1));
                }

            }
        }
        for(int i = 0 ; i < commentarray.length;i++)
        {
            if(commentarray[i] == e.getSource())
            {
                this.dispose();
                new Write_comment(username,idarray[i]);
            }
        }
    }
}
