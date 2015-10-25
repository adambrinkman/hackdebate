package com.svc.debate.socket;

import com.google.gson.Gson;
import com.svc.debate.model.Post;
import com.svc.debate.service.DatabaseService;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by Adam on 15-10-24.
 */
public class SocketServer {

    public String handleMessage (String message) {
        JSONObject result = new JSONObject();

        Post post = jsonToPost(message);
        post.timeToTimestamp();

        try {
            if (attemptPost(post) == null) {
                result.put("status", 0);
                result.put("message", "Failed to post message to the database");
            } else {
                result.put("status", 1);
                result.put("message", "success!");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    private Post jsonToPost (String json) {
        Post post = new Gson().fromJson(json, Post.class);
        return post;
    }

    private UUID attemptPost (Post post) {
        DatabaseService dbService = new DatabaseService();
        return dbService.insertPost(post.getText(), post.getTimestamp(), post.getUser_id());
    }
}
