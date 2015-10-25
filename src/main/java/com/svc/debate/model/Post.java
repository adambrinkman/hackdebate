package com.svc.debate.model;

import com.google.gson.annotations.SerializedName;
import com.svc.debate.util.DataUtil;
import java.sql.Timestamp;

/**
 * Created by dsawla on 10/24/2015.
 */
public class Post {

    @SerializedName("post_id") private int postId;
    @SerializedName("user_id") private int userId;
    @SerializedName("user_name") private String userName;
    @SerializedName("timestamp") private Timestamp timestamp;
    @SerializedName("opinion") private String opiniton;
    @SerializedName("side") private Side side;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public String getOpiniton() {
        return opiniton;
    }

    public void setOpiniton(String opiniton) {
        this.opiniton = opiniton;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return DataUtil.getGson().toJson(this);
    }
}


// post_id, time, text, user_id