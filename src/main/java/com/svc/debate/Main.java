package com.svc.debate;

import com.google.gson.Gson;
import com.svc.debate.model.Users;
import com.svc.debate.service.CheggService;
import com.svc.debate.service.DatabaseService;
import com.svc.debate.service.MainService;
import com.svc.debate.socket.DebateSocket;
import com.svc.debate.socket.GroupDiscussionSocket;
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
    DatabaseService.getInstance();
    webSocket("/debatechat", DebateSocket.class);
//    webSocket("/gd", GroupDiscussionSocket.class);
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

      get("/professor", (req, res) -> {
          res.status(200);
          res.type("text/html");
          if (!hasCookie(req)) {
              return freeMarkerEngine.render(new ModelAndView(createCommonMap(), "assets/home.ftl"));
          } else {
              res.redirect("/professor", 301);
              res.status(200);
              return null;
          }
      });

    get("/debate", (req, res) -> {
      res.status(200);
      res.type("text/html");
      return routeToDebate(freeMarkerEngine, req);
    });

    get("/gd", (req, res) -> {
      res.status(200);
      res.type("text/html");
      return routeToGroupDiscussion(freeMarkerEngine, req);
    });

    post("/searchBooks", (req, res) -> {
      res.status(200);
      res.type("text/html");
      return routeToDebate(freeMarkerEngine, req);
    });

    post("/insertTopic", (req, res) -> {
      res.status(200);
      res.type("text/html");
      System.out.println("Books: " + req.queryMap("books").value());
      System.out.println("Cookie: " + req.cookie("userId"));
      int user_id = Integer.parseInt(req.cookie("userId"));
      // Timestamp begintime = new Timestamp(String.valueOf(req.queryMap("begindate").value()));
      //Timestamp begintime =  Timestamp.from (req.queryMap("begindate").value());
      // boolean flag = DatabaseService.insertTopic(req.queryMap("topic").value(), req.queryMap("begindate").value(), req.queryMap("enddate").value(), user_id);
      return freeMarkerEngine.render(new ModelAndView(null, "assets/Professor.ftl"));
    });


      get("/books", (req, res) -> {
          String bookGuess = req.queryMap("guess").value();
          CheggService cService = new CheggService();
          Gson gson = new Gson();

          return gson.toJson(cService.findBook(bookGuess));
      });

    post("/login", (req, res) -> {
      res.status(200);
      res.type("text/html");
      ArrayList<String> list = new ArrayList<>();
      list = DatabaseService.authenticateValidUser(req.queryMap("login_email").value(), req.queryMap("login_password").value());
      System.out.println("list: " + !list.isEmpty());

      if (list.isEmpty()) {
        return freeMarkerEngine.render(new ModelAndView(null, "assets/home.ftl"));
      } else {
        res.cookie("userId", list.get(0));
        res.cookie("role", list.get(1));
        System.out.println("Cookie: " + list.get(0) +", " + list.get(1));
        if(list.get(1).equalsIgnoreCase("Professor")) {
          return freeMarkerEngine.render(new ModelAndView(createCommonMap(), "assets/Professor.ftl"));
        } else {
          res.redirect("/debate", 301);
//          return routeToDebate(freeMarkerEngine, req);
          return null;
        }
      }
    });
  }

  private static String routeToDebate(FreeMarkerEngine freeMarkerEngine, spark.Request req) {
    Map<String, Object> m = createCommonMap();
    if (hasCookie(req)) {
      Users u = DatabaseService.getUser(NumberUtils.toInt(req.cookie("userId")));
      m.put("userId", req.cookie("userId"));
      m.put("userName", !StringUtils.isEmpty(u.getUserName()) ? u.getUserName() : "");
      m.put("userRole", u.getRole() != null ? u.getRole().name().toLowerCase() : "");
      return freeMarkerEngine.render(new ModelAndView(m, "assets/debate.ftl"));
    } else {
      return freeMarkerEngine.render(new ModelAndView(null, "assets/home.ftl"));
    }
  }

  private static String routeToGroupDiscussion(FreeMarkerEngine freeMarkerEngine, spark.Request req) {
    Map<String, Object> m = createCommonMap();
    if (hasCookie(req)) {
      Users u = DatabaseService.getUser(NumberUtils.toInt(req.cookie("userId")));
      m.put("userId", req.cookie("userId"));
      m.put("userName", !StringUtils.isEmpty(u.getUserName()) ? u.getUserName() : "");
      m.put("userRole", u.getRole() != null ? u.getRole().name().toLowerCase() : "");
      return freeMarkerEngine.render(new ModelAndView(m, "assets/gd.ftl"));
    } else {
      return freeMarkerEngine.render(new ModelAndView(null, "assets/home.ftl"));
    }
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
