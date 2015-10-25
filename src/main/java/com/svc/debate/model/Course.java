package com.svc.debate.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dsawla on 10/24/2015.
 */
public class Course {

    @SerializedName("course_id") private int courseId;
    @SerializedName("user_id") private int userId;
    @SerializedName("name") private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
