package com.svc.debate.service;

import com.svc.debate.model.Course;
import com.svc.debate.model.Debate;
import com.svc.debate.model.Post;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsawla on 10/24/2015.
 */
public class DatabaseService {

  private static java.sql.Connection mPostgresDatabaseConnection = null;
  private static DatabaseService singleton = null;

  public static DatabaseService getInstance() {
    if (singleton == null) {
      singleton = new DatabaseService();
      createUserTableIfNotExists();
      createCourseTableIfNotExists();
      createDebateTableIfNotExists();
      createPostTableIfNotExists();
    }
    return singleton;
  }

  public DatabaseService() {
    getConnection();
  }

  public static java.sql.Connection getConnection() {
    if (mPostgresDatabaseConnection == null) {
      try {
        Class.forName("org.postgresql.Driver");
        mPostgresDatabaseConnection = DriverManager.getConnection(System.getenv("DATABASE_URL"));
      } catch (SQLException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
    return mPostgresDatabaseConnection;
  }

  public static ArrayList<String> authenticateValidUser(String email, String password) {
    boolean flag = true;
    System.out.println("authenticateValidUser");
      ArrayList<String> list = new ArrayList<String>();
      list.clear();
      ResultSet rs = null;
      try {
          Class.forName("org.postgresql.Driver");
          Statement stmt = getConnection().createStatement();
          String query = "select user_id, password, role from users where email = '" + email + "' and password = '" + password + "';";
          System.out.println(query);
          rs = stmt.executeQuery(query);

          while (rs.next()) {
            System.out.println(String.valueOf(rs.getInt("user_id")) + String.valueOf(rs.getString("role")));
            list.add(String.valueOf(rs.getInt("user_id")));
            list.add(String.valueOf(rs.getString("role")));
          }
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return list;
  }

  public boolean insertPost(Post p) {
    try {
      PreparedStatement s = getConnection().prepareCall("INSERT INTO post (user_id, time, opinion, side) VALUES (?, ?, ?, ?)");
      s.setInt(1, p.getUserId());
      s.setTimestamp(2, p.getTimestamp());
      s.setString(3, p.getOpiniton());
      s.setString(4, p.getSide().name().toLowerCase());
      int result = s.executeUpdate();
      return result > 0;
    }
    catch(Exception e) {
      System.out.println(e.toString());
    }
    return false;
  }

  public static List<Post> getPost(int debateId) {
    List<Post> posts = new ArrayList<>();
    try {
      PreparedStatement s = getConnection().prepareCall("SELECT * FROM post WHERE debate_id = ?");
      s.setInt(1, debateId);
      ResultSet rs = s.executeQuery();
      if (rs.next()) {
        Post p = new Post();
        p.setTimestamp(rs.getTimestamp("time"));
        p.setOpiniton(rs.getString("opinion"));
        p.setUserId(rs.getInt("user_id"));
        p.setPostId(rs.getInt("post_id"));
        p.setUserName(rs.getString("user_name"));
        posts.add(p);
      }
    }
    catch(Exception e) {
      System.out.println(e.toString());
    }
    return posts;
  }

  public static List<Course> getCourses(final int userId) {
    List<Course> courses = new ArrayList<>();
    try {
      PreparedStatement s = getConnection().prepareCall("SELECT * FROM course WHERE user_id = ?");
      s.setInt(1, userId);
      ResultSet rs = s.executeQuery();
      if (rs.next()) {
        Course c = new Course();
        c.setUserId(rs.getInt("user_id"));
        c.setName(rs.getString("name"));
        courses.add(c);
      }
    } catch(Exception e) {
      System.out.println(e.toString());
    }
    return courses;
  }

  public static List<Debate> getDebates(final int courseId) {
    List<Debate> debates = new ArrayList<>();
    try {
      PreparedStatement s = getConnection().prepareCall("SELECT * FROM debate WHERE course_id = ?");
      s.setInt(1, courseId);
      ResultSet rs = s.executeQuery();
      if (rs.next()) {
        Debate d = new Debate();
        d.setDebateId(rs.getInt("debate_id"));
        d.setCourseId(rs.getInt("course_id"));
        d.setUserId(rs.getInt("user_id"));
        d.setEndTime(rs.getTimestamp("end_time"));
        d.setStartTime(rs.getTimestamp("start_time"));
        debates.add(d);
      }
    } catch(Exception e) {
      System.out.println(e.toString());
    }
    return debates;
  }

  private static void createUserTableIfNotExists() {
    try {
      Statement stmt = getConnection().createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (user_id SERIAL PRIMARY KEY, email TEXT, user_name TEXT, password TEXT, role TEXT)");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void createPostTableIfNotExists() {
    try {
      Statement stmt = getConnection().createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS post (post_id SERIAL PRIMARY KEY, user_id INT, time TIMESTAMP, opinion TEXT, side TEXT, user_name TEXT)");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void createDebateTableIfNotExists() {
    try {
      Statement stmt = getConnection().createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS debate (debate_id SERIAL PRIMARY KEY, course_id INT, user_id INT, start_time TIMESTAMP, end_time TIMESTAMP, topic TEXT)");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void createCourseTableIfNotExists() {
    try {
      Statement stmt = getConnection().createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS course " + "(course_id SERIAL PRIMARY KEY, user_id INT, name TEXT)");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
