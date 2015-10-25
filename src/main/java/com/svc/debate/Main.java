package com.svc.debate;

import com.svc.debate.service.DatabaseService;
import com.svc.debate.service.MainService;
import com.svc.debate.socket.DebateSocket;
import com.svc.debate.util.WLog;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.get;
import static spark.Spark.post;
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
      return freeMarkerEngine.render(new ModelAndView(createCommonMap(), "assets/home.ftl"));
    });

    get("/debate", (req, res) -> {
      res.status(200);
      res.type("text/html");
      return freeMarkerEngine.render(new ModelAndView(createCommonMap(), "assets/debate.ftl"));
    });

    post("/login", (req, res) -> {
      res.status(200);
      res.type("text/html");
      boolean flag = DatabaseService.isAuthenticatedUser(req.queryMap("cs_login_sid").value(), req.queryMap("cs_login_password").value());
      System.out.println("flag: " + flag);
      if (!flag)
        return freeMarkerEngine.render(new ModelAndView(createCommonMap(), "assets/home.ftl"));
      else
        return freeMarkerEngine.render(new ModelAndView(createCommonMap(), "assets/debate.ftl"));
    });
  }

  private static Map<String, Object> createCommonMap() {
    Map<String, Object> m = new HashMap<>();
    m.put("PORT", System.getenv("PORT"));
    return m;
  }
}
