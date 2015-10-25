package com.svc.debate.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by dsawla on 10/24/2015.
 */

@Data
public class Users {
  @SerializedName("user_id")
  private int id;
  @SerializedName("email")
  private String email;
  @SerializedName("user_name")
  private String userName;
  @SerializedName("password")
  private String password;
  @SerializedName("role")
  private TypeUser role;

  public TypeUser getRole() {
    return role;
  }

  public void setRole(TypeUser role) {
    this.role = role;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
