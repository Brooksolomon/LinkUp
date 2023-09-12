import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class posts extends JFrame implements ActionListener {
    String username;
    JPanel panel1,panel2,panel3;

    JLabel logo,label1,label2,label3,label4;
    JButton button1,button2,button3,button4,button5;
    Color mycolor = new Color(15,186,129);
    Color totalwhite  = new Color(255,255,255);
    posts(String username)
    {
        topusers();
        this.username=username;
        this.sidebar(2);
        panel2 = new JPanel(null);

        panel2.setBackground(Color.yellow);

        setSize(1920,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Home page");


        panel3.setPreferredSize(new Dimension(300,300));



        add(panel1,BorderLayout.WEST);
        add(panel2,BorderLayout.CENTER);
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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button1)
        {
            this.dispose();
            new Homepage(username);
        } else if (e.getSource() == button3) {
            this.dispose();
            new comments(username);
        } else if (e.getSource() ==button4) {
            this.dispose();
            new likes(username);
        } else if (e.getSource() == button5) {
            this.dispose();
            new Login();
        }
    }
}
