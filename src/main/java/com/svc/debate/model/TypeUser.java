package com.svc.debate.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by doyonghoon on 2015. 10. 25..
 */
public enum TypeUser {
  @SerializedName("professor") PROFESSOR,
  @SerializedName("student") STUDENT
}
