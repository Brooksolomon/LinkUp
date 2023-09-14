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
    JButton[] likearray = new JButton[15];
    JButton [] userarray =  new JButton[15];
    JButton commentbutton;
    JButton [] commentarray = new JButton[15];
    int[] idarray = new int[15];
    JPanel panel1,panel2,panel3;
    JPanel writepost;

    JLabel pagelabel;
    JTextField postTitle;
    JTextArea postBody;
    JButton postButton;

    Color mycolor = new Color(15,186,129);
    Color totalwhite  = new Color(255,255,255);
    Homepage(String username){
        this.username = username;
        panel1 = new letbar().sidebar(1,this,username);
        this.feed();
        panel3  = new rightbar().topusers();





        setSize(1920,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Home page");


        panel3.setPreferredSize(new Dimension(300,300));



        add(panel1,BorderLayout.WEST);

        add(new JScrollPane(panel2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
        add(panel3,BorderLayout.EAST);

        setVisible(true);
    }
    public static void main(String [] args)
    {
        new Homepage("brooksolo");
    }

    public void feed()
    {
        panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        writepost = new JPanel(null);
        panel2.setBackground(totalwhite);
        writepost.setBackground(totalwhite);
        pagelabel = new JLabel("Home");
        panel2.add(Box.createRigidArea(new Dimension(0,50)));
        panel2.add(pagelabel);
        pagelabel.setFont(new Font("Arial", Font.PLAIN, 30));
        panel2.add(writepost);
        panel2.setMinimumSize(new Dimension(1000,100));
        panel2.setMaximumSize(new Dimension(1000,1000));
        writepost.setMinimumSize(new Dimension(1250,300));
        writepost.setPreferredSize(new Dimension(1250,300));
        writepost.setMaximumSize((new Dimension(1250,300)));




//
        postTitle = new JTextField("Post title",300);
        postTitle.setBounds(30,10,300,50);
        postTitle.setFont(new Font("Arial", Font.PLAIN, 18));
        writepost.add(postTitle);
//
//
//
        postBody = new JTextArea(10,500);
        postBody.setText("Post body here");
        postBody.setBounds(30,80,1200,100);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        postBody.setBorder(blackline);
        writepost.add(postBody);
//
        postBody.setLineWrap(true);
        postBody.setFont(new Font("Arial", Font.PLAIN, 16));
//
        postButton = new JButton("Post");
        postButton.setBounds(1130,190,100,50);
        postButton.setBackground(mycolor);
        writepost.add(postButton);
        postButton.addActionListener(this);


        Font myfont  = new Font("Arial", Font.PLAIN, 25);
        ResultSet posts = new Main().fetchposts();
        int y = 0;
        int count = 0;
        try {
            while (posts.next()) {
                int id = posts.getInt(1);
                String title = posts.getString(2);
                String content = posts.getString(3);
                int likes= posts.getInt(4);
                String username = posts.getString(5);
                Date postdate = posts.getDate(6);

                JButton userlabel = new JButton("@"+username);
                userarray[count] = userlabel;
                userlabel.setFont(new Font("Arial", Font.PLAIN, 16));
                userlabel.setForeground(mycolor);
                userlabel.setBackground(totalwhite);
                userlabel.setBorder(null);
                userlabel.addActionListener(this);

                JLabel datelabel = new JLabel("‧"+postdate.toString());
                datelabel.setForeground(Color.gray);

                JLabel titlelabel = new JLabel(title);
                titlelabel.setFont(new Font("Arial", Font.PLAIN, 20));
                titlelabel.setForeground(mycolor);

                JTextArea bodyarea = new JTextArea(10,10);
                bodyarea.setText(content);
                bodyarea.setEditable(false);

                bodyarea.setLineWrap(true);
                bodyarea.setFont(new Font("Arial", Font.PLAIN, 15));



                likebutton= new JButton(Integer.toString(likes));
                commentbutton = new JButton("");

                JPanel postpanel = new JPanel(null);
                userlabel.setBounds(50,0,100,20);
                datelabel.setBounds(150,0,100,20);
                titlelabel.setBounds(50,40,500,20);
                bodyarea.setBounds(50,80,1100,50);
                likebutton.setBounds(50,140,100,35);
                commentbutton.setBounds(175,140,100,35);

                JLabel likelogo = new JLabel(new ImageIcon(getClass().getResource("heart.png")));
                JLabel commentlogo = new JLabel(new ImageIcon(getClass().getResource("comment.png")));

                likebutton.setBackground(totalwhite);
                likebutton.add(likelogo);

                commentbutton.setBackground(totalwhite);
                commentbutton.add(commentlogo);

                postpanel.add(userlabel);
                postpanel.add(datelabel);
                postpanel.add(titlelabel);
                postpanel.add(bodyarea);
                postpanel.add(likebutton);
                postpanel.add(commentbutton);
                postpanel.setBounds(400,y,2500,600);
                postpanel.setMinimumSize(new Dimension(1250,200));
                postpanel.setPreferredSize(new Dimension(1250,200));
                postpanel.setMaximumSize(new Dimension(1250,200));
                postpanel.setBackground(totalwhite);
                panel2.add(postpanel);
                likebutton.addActionListener(this);
                commentbutton.addActionListener(this);
                likearray[count] = likebutton;
                idarray[count] = id;
                commentarray [count] = commentbutton;
                y+=2000;
                count+=1;
                if (new Main().checkifliked(this.username,id))
                {
                    likebutton.setBackground(mycolor);
                }
            }

        }catch (Exception e)
        {
            System.out.println(e);
            System.out.println("noooo");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == postButton) {
            if(!postBody.equals("") && !postTitle.equals("")) {
                System.out.println("in");
                Main newm = new Main();
                newm.createpost(postTitle.getText(), postBody.getText(),username);
                this.dispose();
                new Homepage(username);
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
        for (int i  = 0; i < userarray.length;i++)
        {
            if (userarray[i] == e.getSource())
            {
                String profileuser = userarray[i].getText();
                profileuser = profileuser.substring(1,profileuser.length());
                if(!username.equals(profileuser)) {
                    this.dispose();
                    new other_profile(username, profileuser);
                }else{
                    this.dispose();
                    new profile(username);

                }
            }
        }
    }

}
