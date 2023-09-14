import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class rightbar {
    Color mycolor = new Color(15, 186, 129);
    Color totalwhite = new Color(255, 255, 255);

    public JPanel topusers() {
        JPanel panel3 = new JPanel(null);
        JLabel header = new JLabel("Top 5 users");
        header.setFont(new Font("Arial", Font.PLAIN, 30));
        header.setBounds(80, 50, 300, 30);
        panel3.setBackground(totalwhite);
        panel3.add(header);

        Main temp = new Main();
        ResultSet answer = temp.fetchtopusers();
        int y = 120;
        int rank = 1;
        try {
            while (answer.next()) {
                String user = answer.getString(1);
                int count = answer.getInt(2);
                JLabel userlabel = new JLabel(rank + ",@" + user);
                userlabel.setFont(new Font("Arial", Font.PLAIN, 18));
                userlabel.setBounds(100, y, 160, 20);

                JLabel countlabel = new JLabel(count + " posts");
                countlabel.setBounds(130, y + 20, 100, 20);
                userlabel.setForeground(mycolor);
                panel3.add(userlabel);
                panel3.add(countlabel);
                y += 60;
                rank += 1;
            }
        } catch (Exception e) {
            System.out.println("fetch error");
        }
        return panel3;
    }
}
