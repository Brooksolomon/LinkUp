package Database.Models;

import Pages.HomePage;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    Connection connection;

    public User(Connection connection) {
        this.connection = connection;
    }

    public void insert_info(String fName, String lName, String userName, String email, String password) {
        try {
            PreparedStatement newe = connection.prepareStatement("insert into userdata(First_Name,Last_Name,username,email,password) values(?,?,?,?,?)");
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
    public boolean get_user(String username, String pass , JLabel l3, JFrame myframe)
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
                new HomePage(username);

            }

        } catch (SQLException e) {
            System.out.println("user doesnt exist");
            throw new RuntimeException(e);
        }
        return true ;
    }
    public ResultSet userprofile(String username)
    {
        ResultSet answer = null;
        try {
            PreparedStatement newe = connection.prepareStatement("select * from userdata where username=? ");
            newe.setString(1,username);
            answer = newe.executeQuery();
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return answer;
    }
    public void Editrow(String username,String fName, String lName, String userName, String email, String password)
    {
        ResultSet answer = null;
        try {
            System.out.println(fName);
            PreparedStatement newe = connection.prepareStatement("update userdata set First_Name = ? , Last_Name = ? , username = ? , email = ?,password = ? where username = ?");
            newe.setString(1, fName);
            newe.setString(2, lName);
            newe.setString(3, userName);
            newe.setString(4, email);
            newe.setString(5, password);
            newe.setString(6, username);
            newe.executeUpdate();
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public void createfollow(String user,String profileuser,Boolean f)
    {
        if (f) {
            try {
                PreparedStatement newe = connection.prepareStatement("insert into follows values(?,?)");
                newe.setString(1, user);
                newe.setString(2, profileuser);

                System.out.println("added");
                newe.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                PreparedStatement newe = connection.prepareStatement("delete from follows where  follower = ?  and followed = ?");
                newe.setString(1, user);
                newe.setString(2, profileuser);

                System.out.println("added");
                newe.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public boolean checkiffollows(String user,String profileuser)
    {
        ResultSet answer = null;
        try{
            PreparedStatement newe = connection.prepareStatement("select * from follows where follower= ?  and followed = ?");
            newe.setString(1, user);
            newe.setString(2, profileuser);

            System.out.println("added");
            answer = newe.executeQuery();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int count = 0 ;
        try{

            while(answer.next()) {
                count++;
            }

        }catch (Exception e)
        {
            return false;
        }
        if (count > 0 ){
            return true;
        }
        else {
            return false;
        }

    }
    public ResultSet followerlist(String username){

        ResultSet answer = null;
        try{
            PreparedStatement newe = connection.prepareStatement("select * from follows where followed = ?");
            newe.setString(1, username);

            System.out.println("added");
            answer = newe.executeQuery();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answer;
    }
    public ResultSet followinglist(String username){

        ResultSet answer = null;
        try{
            PreparedStatement newe = connection.prepareStatement("select * from follows where follower = ? ");
            newe.setString(1, username);

            System.out.println("added");
            answer = newe.executeQuery();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answer;
    }
    public void deleteAccount(String username)
    {
        try{
            PreparedStatement newe = connection.prepareStatement("delete from follows where follower=? or followed = ?");
            newe.setString(1, username);
            newe.setString(2, username);
            System.out.println("Deleted all the users follow");
            newe.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            PreparedStatement newe = connection.prepareStatement("delete from liked where user_name  = ? ");
            newe.setString(1, username);

            System.out.println("deleted all users likes");
            newe.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            PreparedStatement newe = connection.prepareStatement("delete from comments where username = ? ");
            newe.setString(1, username);

            System.out.println("deleted all of users comments");
            newe.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            PreparedStatement newe = connection.prepareStatement("delete from posts where user_name  = ? ");
            newe.setString(1, username);

            System.out.println("deleted all users posts");
            newe.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            PreparedStatement newe = connection.prepareStatement("delete from userdata where username = ? ");
            newe.setString(1, username);

            System.out.println("deleted user");
            newe.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}