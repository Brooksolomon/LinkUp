package Database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import Database.Models.Post;
import Database.Models.User;
import Pages.Posts;

public class Database {

    public Connection connection;

    public void Createuser(String fName, String lName, String userName, String email, String password) {
        connect();
        User newuser = new User(connection);
        newuser.insert_info(fName, lName, userName, email, password);
    }
    public boolean Searchuser(String username, String pass, JLabel l3, JFrame myframe)
    {
        connect();
        User newuser = new User(connection);
        return newuser.get_user(username,pass,l3,myframe);
    }
    public void createpost(String title , String body,String username)
    {
        connect();
        new Post(connection).post(title,body,username);
    }
    public ResultSet fetchposts()
    {
        connect();
        return  new Post(connection).getpost();
    }
    public ResultSet fetchLikedPosts(String username)
    {
        connect();
        return new Post(connection).getLikedPosts(username);
    }
    public ResultSet fetchMyPosts(String username)
    {
        connect();
        return new Post(connection).fetchMyPosts(username);
    }
    public void likeupdatepost(boolean increase,int id,String username)
    {
        connect();
        new Post(connection).likeunlike(increase,id,username);
    }
    public ResultSet fetchtopusers()
    {
        connect();
        return new Post(connection).fetchtopusers();
    }
    public ResultSet fetchCurrentPost(int id)
    {
        connect();
        return new Post(connection).fetchCurrentPost(id);
    }
    public void createcomment(String body , String username, int postid)
    {
        connect();
        new Post(connection).createcomment(body,username,postid);
    }
    public ResultSet fetchCurrentComments(int postid)
    {
        connect();
        return new Post(connection).fetchCurrentComments(postid);
    }
    public ResultSet fetchMyComments(String username)
    {
        connect();
        return new Post(connection).fetchMyComments(username);
    }
    public boolean checkifliked(String username,int postid)
    {
        connect();
        return new Post(connection).checkifliked(username,postid);
    }
    public ResultSet userprofile(String username)
    {
        connect();
        return new User(connection).userprofile(username);
    }
    public void Editrow(String username,String fName, String lName, String userName, String email, String password )
    {
        connect();
        new User(connection).Editrow(username,fName,lName,userName,email,password);
    }
    public void createfollow(String user,String profileuser,Boolean f)
    {
        connect();
        new User(connection).createfollow(user,profileuser,f);
    }
    public boolean checkiffollows(String user,String profileuser)
    {
        connect();
        return new User(connection).checkiffollows(user,profileuser);
    }
    public ResultSet followerlist(String username)
    {
        connect();
        return new User(connection).followerlist(username);
    }
    public ResultSet followinglist(String username)
    {
        connect();
        return new User(connection).followinglist(username);
    }
    public void deletepost(int postid)
    {
        connect();
        new Post(connection).deletepost(postid);
    }
    public void deleteAccount(String username)
    {
        connect();
        new User(connection).deleteAccount(username);
    }
    public void deleteComment(int comid)
    {
        connect();
        new Post(connection).deleteComment(comid);
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
