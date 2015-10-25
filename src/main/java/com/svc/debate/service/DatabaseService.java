package com.svc.debate.service;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by dsawla on 10/24/2015.
 */
public class DatabaseService {

  private static java.sql.Connection mPostgresDatabaseConnection = null;
  private static DatabaseService singleton = null;

  public static DatabaseService getInstance() {
    if (singleton == null) {
      singleton = new DatabaseService();
      createCourseTableIfNotExists();
      createDebateTableIfNotExists();
      createPostTableIfNotExists();
      createUserTableIfNotExists();
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
          String query = "select user_id, password, role from \"Users\" where email = '" + email + "' and password = '" + password + "';";
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

  public boolean insertPost(String text, Timestamp time, int userId) {
    try {
      PreparedStatement s = getConnection().prepareCall("INSERT INTO post (user_id, time, opinion) VALUES (?, ?, ?)");
      s.setInt(1, userId);
      s.setTimestamp(2, time);
      s.setString(3, text);
      int result = s.executeUpdate();
      return result > 0;
    }
    catch(Exception e) {
      System.out.println(e.toString());
    }
    return false;
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
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS post (post_id SERIAL PRIMARY KEY, user_id INT, time TIMESTAMP, opinion TEXT)");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void createDebateTableIfNotExists() {
    try {
      Statement stmt = getConnection().createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS debate (debate_id SERIAL PRIMARY KEY, course_id INT, user_id INT, start_time TIMESTAMP, end_time TIMESTAMP, side TEXT)");
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
