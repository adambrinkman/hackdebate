package com.svc.debate;

import com.svc.debate.model.Users;
import com.svc.debate.service.DatabaseService;
import com.svc.debate.service.MainService;
import com.svc.debate.socket.DebateSocket;
import com.svc.debate.util.WLog;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
      if (!hasCookie(req)) {
        return freeMarkerEngine.render(new ModelAndView(createCommonMap(), "assets/home.ftl"));
      } else {
        res.redirect("/debate", 301);
        res.status(200);
        return null;
      }
    });

    get("/debate", (req, res) -> {
      res.status(200);
      res.type("text/html");
      Map<String, Object> m = createCommonMap();
      if (hasCookie(req)) {
        Users u =DatabaseService.getUser(NumberUtils.toInt(req.cookie("userId")));
        WLog.i("userName: " + u.getUserName());
        m.put("userId", req.cookie("userId"));
        m.put("userName", u.getUserName());
        m.put("userRole", u.getRole().name().toLowerCase());
      }
      return freeMarkerEngine.render(new ModelAndView(m, "assets/debate.ftl"));
    });

    post("/login", (req, res) -> {
      res.status(200);
      res.type("text/html");
      ArrayList<String> list = new ArrayList<>();
      list = DatabaseService.authenticateValidUser(req.queryMap("login_email").value(), req.queryMap("login_password").value());
      System.out.println("list: "+ !list.isEmpty());

      if (list.isEmpty())
        return freeMarkerEngine.render(new ModelAndView(null, "assets/home.ftl"));
      else {
        res.cookie("userId", list.get(0));
        res.cookie("role", list.get(1));
        System.out.println("Cookie: " + list.get(0) +", " + list.get(1));
        if(list.get(1).equalsIgnoreCase("Professor"))
          return freeMarkerEngine.render(new ModelAndView(createCommonMap(), "assets/Professor.ftl"));
        else
          return freeMarkerEngine.render(new ModelAndView(createCommonMap(), "assets/debate.ftl"));
      }
    });
  }

  private static Map<String, Object> createCommonMap() {
    Map<String, Object> m = new HashMap<>();
    m.put("PORT", System.getenv("PORT"));
    return m;
  }

  private static boolean hasCookie(spark.Request req) {
    WLog.i("cookie: " + req.cookie("userId"));
    return !StringUtils.isEmpty(req.cookie("userId"));
  }
}
