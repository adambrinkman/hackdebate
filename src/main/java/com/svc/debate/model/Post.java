package com.svc.debate.model;

import com.google.gson.annotations.SerializedName;
import com.svc.debate.util.DataUtil;
import java.sql.Timestamp;

/**
 * Created by dsawla on 10/24/2015.
 *
 *
 * postgresql://doyonghoon:password@localhost/doyonghoon
 */
public class Post {
    @SerializedName("post_id") private int post_id;
    @SerializedName("user_id") private int user_id;
    @SerializedName("timestamp") private Timestamp timestamp;
    @SerializedName("text") private String text;

    public void setTimeToTimestamp(long time) {
        this.timestamp = new Timestamp(time);
    }

    public int getPostId() {
        return post_id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getText() {
        return text;
    }

    public int getUserId() {
        return user_id;
    }

    @Override
    public String toString() {
        return DataUtil.getGson().toJson(this);
    }
}


// post_id, time, text, user_id