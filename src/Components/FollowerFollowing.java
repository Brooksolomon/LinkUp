package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import Database.Database;
import Pages.OtherProfile;

public class FollowerFollowing implements ActionListener {
    Color mycolor = new Color(15, 186, 129);
    Color totalWhite = new Color(255, 255, 255);
    JButton [] userfollowerarray = new JButton[15];
    JButton [] userfollowingarray = new JButton[15];
    String username ;
    JFrame frame;
    public FollowerFollowing()
    {


    }
    public JPanel  createPanel(String username,JFrame f)
    {
        this.username = username ;
        this.frame = f;
        JPanel lister = new JPanel(null);

        JLabel label1, label2;

        label1 = new JLabel("Followers");
        label1.setBounds(300,0,200,30);
        label1.setFont(new Font("Arial", Font.PLAIN , 25));
        lister.add(label1);
        label2 = new JLabel("Following");
        label2.setBounds(800,0,200,30);
        label2.setFont(new Font("Arial", Font.PLAIN , 25));
        lister.add(label2);

        ResultSet follower = new Database().followerlist(username);
        ResultSet following  = new Database().followinglist(username);

        int count = 0;
        int y = 50;
        try {

            while (follower.next()) {
                count+=1;
                String user = follower.getString(1);
                JButton userlabel = new JButton(user);
                userlabel.setForeground(mycolor);
                userlabel.setBackground(totalWhite);
                userlabel.setBounds(300,y,100,40);

                userfollowerarray[count] = userlabel;
                userlabel.addActionListener(this);

                lister.add(userlabel);

                y+=50;
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
        y = 50;
        try {

            while (following.next()) {
                count+=1;
                String user = following.getString(2);
                JButton userlabel = new JButton(user);
                userlabel.setForeground(mycolor);
                userlabel.setBackground(totalWhite);
                userlabel.setBounds(800,y,100,40);

                userfollowingarray[count] = userlabel;
                userlabel.addActionListener(this);

                lister.add(userlabel);

                y+=50;
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
        lister.setBackground(totalWhite);
        return  lister;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int  i = 0  ; i < userfollowerarray.length;i++) {
            if (e.getSource() == userfollowerarray[i])
            {
                frame.dispose();
                new OtherProfile(username,userfollowerarray[i].getText());
            }

        }
        for (int i = 0 ; i < userfollowingarray.length; i++)
        {
            if (e.getSource() == userfollowingarray[i])
            {
                frame.dispose();
                new OtherProfile(username,userfollowingarray[i].getText());
            }
        }
    }
}
