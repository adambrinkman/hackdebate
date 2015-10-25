package com.svc.debate.model;

import com.google.gson.annotations.SerializedName;
import java.sql.Timestamp;

/**
 * Created by doyonghoon on 2015. 10. 24..
 */
public class Debate {

  @SerializedName("debate_id") private int debateId;
  @SerializedName("course_id") private int courseId;
  @SerializedName("user_id") private int userId;
  @SerializedName("topic") private String topic;
  @SerializedName("start_time") private Timestamp startTime;
  @SerializedName("end_time") private Timestamp endTime;

  public Timestamp getEndTime() {
    return endTime;
  }

  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  public Timestamp getStartTime() {
    return startTime;
  }

  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
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

  public int getDebateId() {
    return debateId;
  }

  public void setDebateId(int debateId) {
    this.debateId = debateId;
  }

}
