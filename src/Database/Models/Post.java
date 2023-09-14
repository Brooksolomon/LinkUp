package Database.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Post {
    Connection connection;

    public Post(Connection connection) {
        this.connection = connection;
    }

    public void post(String title, String body, String username){
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
    public ResultSet getpost()
    {
        try {
            PreparedStatement newe = connection.prepareStatement("select * from posts left join userdata on posts.user_name = userdata.username order by posts.id desc");
            ResultSet answer = newe.executeQuery();
            return answer;
        }catch (Exception e)
        {
            System.out.println(e);
        }


        return null;
    }
    public void likeunlike(boolean increase, int id, String username)
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
    public ResultSet fetchtopusers()
    {
        try {
            PreparedStatement newe = connection.prepareStatement("select user_name,count(user_name)  as count from posts group by user_name order by count desc OFFSET 0 ROWS FETCH FIRST 5 ROWS ONLY");
            ResultSet answer = newe.executeQuery();
            return answer;
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    public ResultSet getLikedPosts(String username)
    {
        try {
            PreparedStatement newe = connection.prepareStatement("select Distinct posts.id,posts.title,posts.body,posts.likes,posts.user_name,posts.created_At from liked left  join posts on liked.post_id = posts.id where liked.user_name = ? order by id desc");
            newe.setString(1,username);
            ResultSet answer = newe.executeQuery();
            return answer;
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    public ResultSet fetchMyPosts(String username)
    {
        try {
            PreparedStatement newe = connection.prepareStatement("select * from posts where user_name = ? order by id desc");
            newe.setString(1,username);
            ResultSet answer = newe.executeQuery();
            return answer;
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    public ResultSet fetchCurrentPost(int Postid)
    {
        try {
            PreparedStatement newe = connection.prepareStatement("select * from posts where id = ?");
            newe.setInt(1,Postid);
            ResultSet answer = newe.executeQuery();
            return answer;
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    public void createcomment(String body, String username, int postID)
    {
        try {
            PreparedStatement newe = connection.prepareStatement("insert into comments(body,username,post_id) values (?,?,?)");
            newe.setString(1,body);
            newe.setString(2,username);
            newe.setInt(3,postID);
            ResultSet answer = newe.executeQuery();
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public ResultSet fetchCurrentComments(int Postid)
    {
        try {
            PreparedStatement newe = connection.prepareStatement("select * from comments where post_id = ?");
            newe.setInt(1,Postid);
            ResultSet answer = newe.executeQuery();
            return  answer;
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    public ResultSet fetchMyComments(String username)
    {
        try {
            PreparedStatement newe = connection.prepareStatement("select * from comments where username=?");
            newe.setString(1,username);
            ResultSet answer = newe.executeQuery();
            return  answer;
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    public boolean checkifliked(String username, int Postid) {
        ResultSet answer=null;
        try {
            PreparedStatement newe = connection.prepareStatement("select * from liked where user_name=? and post_id = ?");
            newe.setString(1,username);
            newe.setInt(2,Postid);
            answer = newe.executeQuery();
        }catch (Exception e)
        {
            System.out.println(e);
        }
        int count = 0 ;
        try{

            while(answer.next()) {
                count++;
            }

        }catch (Exception e)
        {
            System.out.println(username + " " + Postid);
            return false;
        }
        if (count > 0 ){
            return true;
        }
        else {
            System.out.println(username + " " + Postid);
            return false;
        }


    }
    public  void deletepost(int postid)
    {
        try {
            PreparedStatement newe = connection.prepareStatement("delete from liked where post_id =  ?");
            newe.setInt(1,postid);
            newe.executeUpdate();
        }catch (Exception e)
        {
            System.out.println(e);
        }
        try {
            PreparedStatement newe = connection.prepareStatement("delete from comments where post_id =  ?");
            newe.setInt(1,postid);
            newe.executeUpdate();
        }catch (Exception e)
        {
            System.out.println(e);
        }
        try {
            PreparedStatement newe = connection.prepareStatement("delete from posts where id =  ?");
            newe.setInt(1,postid);
            newe.executeUpdate();
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public void deleteComment(int comid)
    {
        try {
            PreparedStatement newe = connection.prepareStatement("delete from comments where id = ?");
            newe.setInt(1,comid);
            newe.executeUpdate();
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }



}