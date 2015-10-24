package com.svc.debate.service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.svc.debate.model.Debate;

/**
 * Created by doyonghoon on 2015. 10. 24..
 */
public class DebateService {
  public static void start() throws InterruptedException {
    Configuration config = new Configuration();
    config.setHostname("localhost");
    config.setPort(9092);

    final SocketIOServer server = new SocketIOServer(config);
    server.addEventListener("chatevent", Debate.class, new DataListener<Debate>() {
      @Override
      public void onData(SocketIOClient socketIOClient, Debate data, AckRequest ackRequest) throws Exception {
        server.getBroadcastOperations().sendEvent("chatevent", data);
      }
    });
    server.start();
//    Thread.sleep(Integer.MAX_VALUE);
//    server.stop();
  }
}
