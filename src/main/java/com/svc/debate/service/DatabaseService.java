package com.svc.debate.service;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.svc.debate.model.Post;
import org.sql2o.*;
import java.util.UUID;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.*;
/**
 * Created by dsawla on 10/24/2015.
 */
public class DatabaseService {

    public static Sql2o returnString() {
        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:3306/myDB", "myUsername", "topSecretPassword");
        return sql2o;
    }

    public boolean authenticateUser(String username, String password) {
        String connectionString = "";
        String query="select username, password from login where username = '" + username + "' and password = '" + password + "';";

        DatabaseService db = new DatabaseService();
        return db.get(query);
    }

    /*creates a new post*/
    public UUID insertPost(String title, String text) {
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
    }

    /*returns a list of posts for a particular post_id*/
    public List<Post> getAllPostsOn(UUID post) {
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
    }

    public boolean post(String query) {
        return true;
    }

    public boolean get(String query) {
        return true;
    }
}
