package com.svc.debate.socket;

import com.google.gson.JsonObject;
import com.svc.debate.model.Post;
import com.svc.debate.service.DatabaseService;
import com.svc.debate.util.DataUtil;
import com.svc.debate.util.WLog;
import java.util.UUID;

/**
 * Created by Adam on 15-10-24.
 */
public class SocketServer {

    private static Post parsePostFromJson(String json) {
        Post p = DataUtil.getGson().fromJson(json, Post.class);
        return p;
    }

    public String handleMessage (String message) {
        JsonObject result = new JsonObject();
        Post post = parsePostFromJson(message);
        boolean hasPosted = attemptPost(post);
            if (hasPosted) {
              result.addProperty("status", 500);
              result.addProperty("message", "Failed to post message to the database");
            } else {
              result.addProperty("status", 200);
              result.addProperty("message", "success");
            }
        return result.toString();
    }

    private boolean attemptPost(Post post) {
        WLog.i("post: " + post);
        return DatabaseService.getInstance().insertPost(post.getText(), post.getTimestamp(), post.getUserId());
    }


}
