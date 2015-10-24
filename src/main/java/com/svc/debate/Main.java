package com.svc.debate;

import com.svc.debate.service.MainService;
import com.svc.debate.socket.DebateSocket;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.get;
import static spark.Spark.webSocket;

/**
 * Created by doyonghoon on 2015. 10. 24..
 */
public class Main {
  public static void main(String[] args) {
    webSocket("/debatechat", DebateSocket.class);

    Spark.staticFileLocation("/assets");
    FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
    Configuration freeMarkerConfiguration = new Configuration();
    freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(MainService.class, "/"));
    freeMarkerEngine.setConfiguration(freeMarkerConfiguration);

    Spark.port(Integer.parseInt(System.getenv("PORT")));
    get("/", (req, res) -> {
      res.status(200);
      res.type("text/html");
      return freeMarkerEngine.render(new ModelAndView(null, "assets/home.ftl"));
    });
  }
}
