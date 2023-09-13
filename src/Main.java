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
    boolean Searchuser(String username,String pass, JLabel l3,JFrame myframe)
    {
        connect();
        User newuser = new User(connection);
         return newuser.get_user(username,pass,l3,myframe);
    }
    void createpost(String title , String body,String username)
    {
        connect();
        post_managment newp = new post_managment(connection);
        newp.post(title,body,username);
    }
    ResultSet fetchposts()
    {
        connect();
        post_managment newp = new post_managment(connection);
        return  newp.getpost();
    }
    ResultSet fetchLikedPosts(String username)
    {
        connect();
        post_managment ne = new post_managment(connection);
        return ne.getLikedPosts(username);
    }
    ResultSet fetchMyPosts(String username)
    {
        connect();
        post_managment ne = new post_managment(connection);
        return ne.fetchMyPosts(username);
    }
    void likeupdatepost(boolean increase,int id,String username)
    {
        connect();
        post_managment newp = new post_managment(connection);
        newp.likeunlike(increase,id,username);
    }
    ResultSet fetchtopusers()
    {
        connect();
        post_managment newp = new post_managment(connection);
        return newp.fetchtopusers();
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
    boolean get_user(String username,String pass , JLabel l3,JFrame myframe)
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
                myframe.dispose();
                new Homepage(username);

            }

        } catch (SQLException e) {
            System.out.println("user doesnt exist");
            throw new RuntimeException(e);
        }
        return true ;
    }
}
class post_managment
{
    Connection connection;

    post_managment(Connection connection) {
        this.connection = connection;
    }

    void post(String title, String body,String username){
        try{
            PreparedStatement newe = connection.prepareStatement("insert into posts(title,body,likes,user_name) values(?,?,?,?)");
            newe.setString(1, title);
            newe.setString(2, body);
            newe.setInt(3, 0);
            newe.setString(4, username);
            System.out.println("added");
            newe.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    ResultSet getpost()
    {
        try {
            PreparedStatement newe = connection.prepareStatement("select * from posts order by id desc ");
            ResultSet answer = newe.executeQuery();
            return answer;
        }catch (Exception e)
        {
            System.out.println("fuck");
        }


        return null;
    }
    void likeunlike(boolean increase,int id,String username)
    {
        if (increase)
        {
            try{
                PreparedStatement newe = connection.prepareStatement("update posts set likes+=1 where id = ?");
                newe.setInt(1, id);
                System.out.println("added");
                newe.executeUpdate();

                newe = connection.prepareStatement("insert into liked (user_name,post_id) values(?,?)");
                newe.setString(1, username);
                newe.setInt(2,id);
                System.out.println("added");
                newe.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            try{
                PreparedStatement newe = connection.prepareStatement("update posts set likes-=1 where id = ?");
                newe.setInt(1, id);
                System.out.println("added");
                newe.executeUpdate();

                newe = connection.prepareStatement("Delete from liked where user_name=? and post_id = ?");
                newe.setString(1, username);
                newe.setInt(2,id);
                System.out.println("removed");
                newe.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    ResultSet fetchtopusers()
    {
        try {
            PreparedStatement newe = connection.prepareStatement("select user_name,count(user_name)  as count from posts group by user_name order by count desc OFFSET 0 ROWS FETCH FIRST 5 ROWS ONLY");
            ResultSet answer = newe.executeQuery();
            return answer;
        }catch (Exception e)
        {
            System.out.println("fuck");
        }
        return null;
    }
    ResultSet getLikedPosts(String username)
    {
        try {
            PreparedStatement newe = connection.prepareStatement("select posts.id,posts.title,posts.body,posts.likes,posts.user_name,posts.created_At from liked left  join posts on liked.post_id = posts.id where liked.user_name = ? order by id desc");
            newe.setString(1,username);
            ResultSet answer = newe.executeQuery();
            return answer;
        }catch (Exception e)
        {
            System.out.println("fuck");
        }
        return null;
    }
    ResultSet fetchMyPosts(String username)
    {
        try {
            PreparedStatement newe = connection.prepareStatement("select * from posts where user_name = ? order by id desc");
            newe.setString(1,username);
            ResultSet answer = newe.executeQuery();
            return answer;
        }catch (Exception e)
        {
            System.out.println("fuck");
        }
        return null;
    }
}



