import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class follower_following {
    Color mycolor = new Color(15, 186, 129);
    Color totalwhite = new Color(255, 255, 255);
    follower_following()
    {


    }
    JPanel  createpanel(String username)
    {
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

        ResultSet follower = new Main().followerlist(username);
        ResultSet following  = new Main().followinglist(username);

        int count = 0;
        int y = 100;
        try {

            while (follower.next()) {
                count+=1;
                String user = follower.getString(1);
                JButton userlabel = new JButton(user);
                userlabel.setForeground(mycolor);
                userlabel.setBackground(totalwhite);
                userlabel.setBounds(300,y,100,40);



                lister.add(userlabel);

                y+=100;
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }

        return  lister;
    }

}
