package com.svc.debate.service;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.svc.debate.model.Post;
import com.svc.debate.model.Users;
/*import org.sql2o.*;
import org.sql2o.Connection;*/

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;
import java.util.List;
import javax.sql.*;
/**
 * Created by dsawla on 10/24/2015.
 */
public class DatabaseService {

    private static java.sql.Connection mPostgresDatabaseConnection = null;

/*    public static Sql2o returnString() {
        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/postgres", "postgres", "123");
        return sql2o;
        postgresql://postgres:123@localhost/postgres
    }*/

    public static java.sql.Connection getConnection() {
        if (mPostgresDatabaseConnection == null) {
            try {
                URI dbUri = new URI(System.getenv("DATABASE_URL"));
                System.out.println(dbUri.toString());
                /*String username = dbUri.getUserInfo().split(":")[0];
                String password = dbUri.getUserInfo().split(":")[1];*/
                //String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

                //System.out.println(username + ";" + password);
                String url = "jdbc:postgresql://localhost/postgres?user=postgres&password=123";
                mPostgresDatabaseConnection = DriverManager.getConnection(url);
//                mPostgresDatabaseConnection = DriverManager.getConnection(dbUri.toString(), "localhost", "123");

            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Conn String: " + mPostgresDatabaseConnection);
        return mPostgresDatabaseConnection;
    }

    public static boolean authenticateValidUser(String username, String password) {
        boolean flag = true;
        System.out.println("authenticateValidUser");

        try {
            Class.forName("org.postgresql.Driver");
            Statement stmt = getConnection().createStatement();
            String query="select user_id, password from \"Users\" where sid = '" + username + "' and password = '" + password + "';";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);

            if(rs.next()== true)
                flag = true;
            else
                flag = false;
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return flag;
    }

/*    public List<Users> authenticateUser(String username, String password) {
        String query="select user_id, password from Users where sid = '" + username + "' and password = '" + password + "';";
        System.out.println(query);

        Sql2o sql2o = returnString();
        System.out.println("sql2o: " + sql2o);
        System.out.println(username + " " + password);
        String sid=username;

        try (Connection conn = sql2o.open()) {
            System.out.println("opened " + conn);
        return conn.createQuery("select user_id, password from Users where sid=:sid")
            .addParameter("sid", sid)
            .executeAndFetch(Users.class);
        }
    }*/

    /*creates a new post*/
/*    public UUID insertPost(String title, String text) {
        Sql2o sql2o = returnString();
        Post post = new Post();
        ObjectIdGenerators.UUIDGenerator uuidGenerator = new ObjectIdGenerators.UUIDGenerator();
        UUID postUuid = uuidGenerator.generateId(post);

        System.out.println("postUuid: " + postUuid);

        try (org.sql2o.Connection conn = sql2o.beginTransaction()) {
            conn.createQuery("insert into Post(post_uuid, title, content, publishing_date) VALUES (:post_uuid, :title, :content, :date)")
                    .addParameter("post_uuid", postUuid)
                    .addParameter("title", title)
                    .addParameter("content", text)
                    .addParameter("date", new Date())
                    .executeUpdate();
            conn.commit();
            return postUuid;
        }
    }*/

    /*returns a list of posts for a particular post_id*/
/*    public List<Post> getAllPostsOn(UUID post) {
        Sql2o sql2o = returnString();
        try (org.sql2o.Connection conn = sql2o.open()) {
            return conn.createQuery("select * from comments where post_uuid=:post_uuid")
                    .addParameter("post_uuid", post)
                    .executeAndFetch(Post.class);
        }
    }

    private List<String> getCategoriesFor(UUID post_uuid) {
        Sql2o sql2o = returnString();
        try (org.sql2o.Connection conn = sql2o.open()) {
            return conn.createQuery("select category from posts_categories where post_uuid=:post_uuid")
                    .addParameter("post_uuid", post_uuid)
                    .executeAndFetch(String.class);
        }
    }*/

    public boolean post(String query) {
        return true;
    }

    public boolean get(String query) {
        return true;
    }
}
