package com.svc.debate.socket;

import com.svc.debate.util.WLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 * Created by doyonghoon on 2015. 10. 25..
 */
@WebSocket
public class GroupDiscussionSocket {

  private SocketServer handler;
  private List<Session> sessions;

  public GroupDiscussionSocket() {
    handler = new SocketServer();
    sessions = new ArrayList<>();
  }

  @OnWebSocketConnect
  public void connected(Session session) {
    WLog.i("connected");
    if (!sessions.contains(session)) {
      sessions.add(session);
    }
  }

  @OnWebSocketClose
  public void closed(int statusCode, String reason) {
    WLog.i("statusCode: " + statusCode + ", reason: " + reason);
    WLog.i("closed");
  }

  @OnWebSocketMessage
  public void message(String message) throws IOException {
    WLog.i("Got: " + message);
    for (Session s : sessions) {
      if (s.isOpen()) {
        WLog.i("notified: " + s.getProtocolVersion());
        s.getRemote().sendString(handler.handleMessage(message));
      }
    }
  }
}
