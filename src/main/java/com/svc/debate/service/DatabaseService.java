package com.svc.debate.service;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import org.sql2o.Sql2o;

/*import org.sql2o.*;
import org.sql2o.Connection;*/

/**
 * Created by dsawla on 10/24/2015.
 */
public class DatabaseService {

  private static java.sql.Connection mPostgresDatabaseConnection = null;

  public static Sql2o SQL_INSTANCE = null;
  private static DatabaseService singleton = null;

  public static DatabaseService getInstance() {
    if (singleton == null) {
      singleton = new DatabaseService();
    }
    return singleton;
  }

  public static java.sql.Connection getConnection() {
    if (mPostgresDatabaseConnection == null) {
      try {
        mPostgresDatabaseConnection = DriverManager.getConnection(System.getenv("DATABASE_URL"));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return mPostgresDatabaseConnection;
  }

  public static boolean authenticateValidUser(String username, String password) {
    boolean flag = true;
    System.out.println("authenticateValidUser");

    try {
      Class.forName("org.postgresql.Driver");
      Statement stmt = getConnection().createStatement();
      String query = "select user_id, password from \"Users\" where sid = '" + username + "' and password = '" + password + "';";
      System.out.println(query);
      ResultSet rs = stmt.executeQuery(query);
      flag = rs.next();
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    return flag;
  }

  public DatabaseService() {
    getConnection();
  }

  public boolean insertPost(String text, Timestamp time, String userId) {
    try {
      Class.forName("org.postgresql.Driver");
      Statement stmt = getConnection().createStatement();
      String query = "insert into \"Post\"(post_id, time, text, user_id) VALUES (:post_id, :time, :text, :user_id)";
      System.out.println(query);
      ResultSet rs = stmt.executeQuery(query);
      if(rs.next()) {
        return true;
      }
    }
    catch(Exception e) {
      System.out.println(e.toString());
    }
    return false;
  }
}
