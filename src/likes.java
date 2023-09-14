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
        panel3  = new rightbar().topusers();
        feed();


        panel1 = new letbar().sidebar(4,this,username);

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
                likebutton.setBounds(50, 140, 100, 35);
                JLabel likelogo = new JLabel(new ImageIcon(getClass().getResource("heart.png")));
                likebutton.setBackground(totalwhite);
                likebutton.add(likelogo);

                commentbutton = new JButton("");
                commentbutton.setBounds(175,140,100,35);
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
