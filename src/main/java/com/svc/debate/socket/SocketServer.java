package com.svc.debate.socket;

import com.svc.debate.model.Post;
import com.svc.debate.service.DatabaseService;
import com.svc.debate.util.DataUtil;
import com.svc.debate.util.WLog;

/**
 * Created by Adam on 15-10-24.
 */
public class SocketServer {

  private static Post parsePostFromJson(String json) {
    Post p = DataUtil.getGson().fromJson(json, Post.class);
    return p;
  }

  public String handleMessage(String message) {
    Post post = parsePostFromJson(message);
    boolean hasPosted = attemptPost(post);
    return DataUtil.getGson().toJson(post);
  }

  private boolean attemptPost(Post post) {
    return DatabaseService.getInstance().insertPost(post);
  }
}
