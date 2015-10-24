package com.svc.debate.socket;

import java.io.IOException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 * Created by doyonghoon on 2015. 10. 24..
 */

@WebSocket
public class DebateSocket {
  private Session session;

  @OnWebSocketConnect
  public void connected(Session session) {
    this.session = session;
    System.out.println("connected");
  }

  @OnWebSocketClose
  public void closed(int statusCode, String reason) {
    this.session = null;
    System.out.println("closed");
  }

  @OnWebSocketMessage
  public void message(String message) throws IOException {
    System.out.println("Got: " + message);   // Print message
    session.getRemote().sendString(message); // and send it back
  }
}