package com.svc.debate.model;

import org.apache.commons.lang3.math.NumberUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dsawla on 10/24/2015.
 */
public class Post {
    private int post_id;
    private String time;
    private Timestamp timestamp;
    private String text;
    private String user_id;

    private long timeToLong() {
        if (NumberUtils.isNumber(time)) {
            return Long.parseLong(time);
        }

        return -1;
    }

    public void timeToTimestamp () {
        long longTime = timeToLong();
        this.timestamp = new Timestamp(longTime);
    }

    public int getPost_id() {
        return post_id;
    }

    public String getTime() {
        return time;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getText() {
        return text;
    }

    public String getUser_id() {
        return user_id;
    }
}


// post_id, time, text, user_id