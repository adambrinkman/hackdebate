package com.svc.debate.socket;

import com.svc.debate.util.WLog;
import java.io.IOException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.JSONObject;

/**
 * Created by doyonghoon on 2015. 10. 24..
 */

@WebSocket
public class DebateSocket {
  private Session session;

  @OnWebSocketConnect
  public void connected(Session session) {
    this.session = session;
    WLog.i("connected");
  }

  @OnWebSocketClose
  public void closed(int statusCode, String reason) {
    this.session = null;
    WLog.i("closed");
  }

  @OnWebSocketMessage
  public void message(String message) throws IOException {
    WLog.i("Got: " + message);   // Print message
    session.getRemote().sendString(message); // and send it back
    SocketServer socketServer = new SocketServer();
    String result = socketServer.handleMessage(message);
    session.getRemote().sendString(result);
  }
}
