package com.svc.debate.service;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.svc.debate.model.Post;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import org.sql2o.Sql2o;
/**
 * Created by dsawla on 10/24/2015.
 */
public class DatabaseService {

    public static Sql2o SQL_INSTANCE = null;

    public static Sql2o getSqlInstance() {
        URI dbUri = null;
        try {
            dbUri = new URI(System.getenv("DATABASE_URL"));
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
//        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:3306/myDB", "myUsername", "topSecretPassword");
            SQL_INSTANCE = new Sql2o(dbUrl, username, password);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return SQL_INSTANCE;
    }

    public boolean authenticateUser(String username, String password) {
        String query="select username, password from login where username = '" + username + "' and password = '" + password + "';";

        DatabaseService db = new DatabaseService();
        return db.get(query);
    }

    /*creates a new post*/
    public UUID insertPost(String text, Timestamp time, String userId) {
        Sql2o sql2o = getSqlInstance();
        Post post = new Post();
        ObjectIdGenerators.UUIDGenerator uuidGenerator = new ObjectIdGenerators.UUIDGenerator();
        UUID postId = uuidGenerator.generateId(post);

        try (org.sql2o.Connection conn = sql2o.beginTransaction()) {
            conn.createQuery("insert into Post(post_id, time, text, user_id) VALUES (:post_id, :time, :text, :user_id)")
                    .addParameter("post_id", postId)
                    .addParameter("time", time)
                    .addParameter("text", text)
                    .addParameter("user_id", userId)
                    .executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        //TODO: change return?
        return postId;
    }

    /*returns a list of posts for a particular post_id*/
    public List<Post> getAllFavPostsOn(UUID post) {
        Sql2o sql2o = getSqlInstance();
        try (org.sql2o.Connection conn = sql2o.open()) {
            return conn.createQuery("select * from comments where post_id=:post_id")
                    .addParameter("post_id", post)
                    .executeAndFetch(Post.class);
        }
    }

    private List<String> getCategoriesFor(UUID post_uuid) {
        Sql2o sql2o = getSqlInstance();
        try (org.sql2o.Connection conn = sql2o.open()) {
            return conn.createQuery("select category from posts_categories where post_uuid=:post_uuid")
                    .addParameter("post_uuid", post_uuid)
                    .executeAndFetch(String.class);
        }
    }

    public

    public boolean post(String query) {
        return true;
    }

    public boolean get(String query) {
        return true;
    }
}
