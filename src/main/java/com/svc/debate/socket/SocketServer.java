package com.svc.debate.socket;

import com.svc.debate.model.Post;
import com.svc.debate.service.DatabaseService;
import com.svc.debate.util.DataUtil;
import com.svc.debate.util.WLog;
import java.util.UUID;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Adam on 15-10-24.
 */
public class SocketServer {

    private static long convertTimeFormat(String m) {
        if (NumberUtils.isNumber(m)) {
            return Long.parseLong(m);
        }
        return 0;
    }

    public String handleMessage (String message) {
        JSONObject result = new JSONObject();

        Post post = createPostFromJson(message);
        post.setTimeToTimestamp(convertTimeFormat(message));
        try {
            if (attemptPost(post) == null) {
                result.put("status", 500);
                result.put("message", "Failed to post message to the database");
            } else {
                result.put("status", 200);
                result.put("message", "success!");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    private static Post createPostFromJson(String json) {
        WLog.i("json: " + json);
        return DataUtil.getGson().fromJson(json, Post.class);
    }

    private UUID attemptPost (Post post) {
        DatabaseService dbService = new DatabaseService();
        return dbService.insertPost(post.getText(), post.getTimestamp(), post.getUserId());
    }
}
