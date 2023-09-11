import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Main {
    public Connection connection;

    void Createuser(String fName, String lName, String userName, String email, String password) {
        connect();
        User newuser = new User(connection);
        newuser.insert_info(fName, lName, userName, email, password);
    }
    boolean Searchuser(String username,String pass, JLabel l3)
    {
        connect();
        User newuser = new User(connection);
         return newuser.get_user(username,pass,l3);
    }



    void connect() {
        try {
            //customize string
            String constring = "jdbc:sqlserver://localhost;databaseName=SocialMedia;encrypt=true;trustServerCertificate=true;user=app;Password=1";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(constring);
            System.out.println("connected");
        } catch (Exception e) {
            System.out.println("failed");
            throw new RuntimeException(e);
        }
    }

}







class User {
    Connection connection;

    User(Connection connection) {
        this.connection = connection;
    }

    void insert_info(String fName, String lName, String userName, String email, String password) {
        try {
            PreparedStatement newe = connection.prepareStatement("insert into userdata values(?,?,?,?,?)");
            newe.setString(1, fName);
            newe.setString(2, lName);
            newe.setString(3, userName);
            newe.setString(4, email);
            newe.setString(5, password);
            System.out.println("added");
            newe.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    boolean get_user(String username,String pass , JLabel l3)
    {
        try
        {
            PreparedStatement newe = connection.prepareStatement("select password from userdata where username = ? ");
            newe.setString(1,username);
            ResultSet answer = newe.executeQuery();
            int count  =0;
            String password=null;
            while (answer.next()) {
                password = answer.getString("password");
                count++;
            }
            if (count == 0){
                l3.setText("user not found ");
                return false;
            }
            else if (!pass.equals(password))
            {
                l3.setText("password incorrect ");
            }
            else {
                l3.setForeground(Color.green);
                l3.setText("login successful ");
            }

        } catch (SQLException e) {
            System.out.println("user doesnt exist");
            throw new RuntimeException(e);


        }
        return true ;
    }
}


