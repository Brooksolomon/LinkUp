import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.sql.ResultSet;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Homepage extends JFrame implements ActionListener {
    String username;
    JButton likebutton;
    JButton  likearray[] = new JButton[15];
    int idarray[] = new int[15];
    JPanel panel1,panel2,panel3;
    JLabel logo,label1,label2,label3,label4;
    JLabel pagelabel;
    JTextField postTitle;
    JTextArea postBody;
    JButton postButton;
    JButton button1,button2,button3,button4,button5;
    Color mycolor = new Color(15,186,129);
    Color totalwhite  = new Color(255,255,255);
    Homepage(String username){
        this.username = username;
        this.sidebar(1);
        this.feed();

        panel3 = new JPanel(null);


        panel3.setBackground(Color.blue);

        setSize(1920,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Home page");


        panel3.setPreferredSize(new Dimension(300,300));



        add(panel1,BorderLayout.WEST);
        add(panel2,BorderLayout.CENTER);
        add(panel3,BorderLayout.EAST);

        setVisible(true);
    }
    public static void main(String [] args)
    {
        new Homepage("brooksolo");
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
    public void feed()
    {
        panel2 = new JPanel(null);
        panel2.setBackground(totalwhite);
        pagelabel = new JLabel("Home");
        panel2.add(pagelabel);
        pagelabel.setBounds(30,20,100,40);
        pagelabel.setFont(new Font("Arial", Font.PLAIN, 30));

        Border blackline = BorderFactory.createLineBorder(Color.black);
        panel2.setBorder(blackline);

        postTitle = new JTextField("Post title",300);
        postTitle.setBounds(30,80,300,50);
        panel2.add(postTitle);



        postBody = new JTextArea(10,500);
        postBody.setText("Post body here");
        postBody.setBounds(30,150,1200,100);
        postBody.setBorder(blackline);
        panel2.add(postBody);
        postBody.setLineWrap(true);

        postButton = new JButton("Post");
        postButton.setBounds(1130,270,100,50);
        postButton.setBackground(mycolor);
        panel2.add(postButton);
        postButton.addActionListener(this);

        ResultSet posts = new Main().fetchposts();
        System.out.println(posts);
        int y = 350;
        int count = 0;
        try {
            while (posts.next()) {
                int id = posts.getInt(1);
                String title = posts.getString(2);
                String content = posts.getString(3);
                int likes= posts.getInt(4);
                String username = posts.getString(5);
                Date postdate = posts.getDate(6);

                JLabel userlabel = new JLabel(username );
                userlabel.setFont(new Font("Arial", Font.PLAIN, 18));

                JLabel datelabel = new JLabel("‧"+postdate.toString());
                datelabel.setForeground(Color.gray);

                JLabel titlelabel = new JLabel(title);
                titlelabel.setFont(new Font("Arial", Font.PLAIN, 20));
                titlelabel.setForeground(mycolor);

                JTextArea bodyarea = new JTextArea(10,10);
                bodyarea.setText(content);
                bodyarea.setEditable(false);

                bodyarea.setLineWrap(true);



                likebutton= new JButton(Integer.toString(likes));

                userlabel.setBounds(50,y,100,20);
                datelabel.setBounds(150,y,100,20);
                titlelabel.setBounds(50,(y+40),500,20);
                bodyarea.setBounds(50,y+80,1100,50);
                likebutton.setBounds(200,y+140,100,35);
                JLabel likelogo = new JLabel(new ImageIcon(getClass().getResource("heart.png")));
                likebutton.setBackground(totalwhite);
                likebutton.add(likelogo);
                panel2.add(userlabel);
                panel2.add(datelabel);
                panel2.add(titlelabel);
                panel2.add(bodyarea);
                panel2.add(likebutton);
                likebutton.addActionListener(this);
                likearray[count] = likebutton;
                idarray[count] = id;
                y+=200;
                count+=1;
            }
        }catch (Exception e)
        {
            System.out.println(e);
            System.out.println("noooo");
        }




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button2)
        {
            this.dispose();
            new posts(username);
        } else if (e.getSource() == button3) {
            this.dispose();
            new comments(username);
        } else if (e.getSource() ==button4) {
            this.dispose();
            new likes(username);
        } else if (e.getSource() == button5) {
            this.dispose();
            new Login();
        } else if (e.getSource() == postButton) {
            if(!postBody.equals("") && !postTitle.equals("")) {
                System.out.println("in");
                Main newm = new Main();
                newm.createpost(postTitle.getText(), postBody.getText(),username);
                pagelabel.setText("Home");
            } else {
                pagelabel.setText("fill out both fields");
            }
        }
        for(int i= 0; i < likearray.length;i++)
        {
            if (likearray[i] == e.getSource())
            {
                if(likearray[i].getBackground() == totalwhite){
                    likearray[i].setBackground(mycolor);
                    Main ne = new Main();
                    ne.likeupdatepost(true,idarray[i]);
                    likearray[i].setText(Integer.toString(Integer.valueOf(likearray[i].getText()) + 1));
                }else {
                    likearray[i].setBackground(totalwhite);
                    Main ne = new Main();
                    ne.likeupdatepost(false,idarray[i]);
                    likearray[i].setText(Integer.toString(Integer.valueOf(likearray[i].getText()) - 1));
                }

            }
        }
    }

}
