import java.sql.*;

public class Main {
    public Connection connection;

    void Createuser(String fName, String lName, String userName, String email, String password) {
        connect();
        User newus = new User(connection);
        newus.insert_info(fName, lName, userName, email, password);
    }



    void connect() {
        try {
            //customize string
            String constring = "jdbc:sqlserver://localhost;databaseName=SocialMedia;encrypt=true;trustServerCertificate=true;user=app;Password=1";

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(constring);
            System.out.println("connected");
        } catch (Exception e) {
            System.out.println(e);
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
            PreparedStatement newe = connection.prepareStatement("insert into staff values(?,?,?,?,?,?)");
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
}


