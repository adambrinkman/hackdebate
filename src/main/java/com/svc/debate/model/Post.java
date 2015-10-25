package com.svc.debate.model;

import com.google.gson.annotations.SerializedName;
import java.sql.Timestamp;

/**
 * Created by dsawla on 10/24/2015.
 */
public class Post {
    @SerializedName("post_id") private int post_id;
    @SerializedName("time") private long time;
    @SerializedName("timestamp") private Timestamp timestamp;
    @SerializedName("text") private String text;
    @SerializedName("user_id") private String user_id;

//    private long timeToLong(long t) {
//        if (NumberUtils.isNumber(t)) {
//            return Long.parseLong(t);
//        }
//        return -1;
//    }

    public void setTimeToTimestamp(long time) {
        this.timestamp = new Timestamp(time);
    }

    public int getPostId() {
        return post_id;
    }

    public long getTime() {
        return time;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getText() {
        return text;
    }

    public String getUserId() {
        return user_id;
    }
}


// post_id, time, text, user_id