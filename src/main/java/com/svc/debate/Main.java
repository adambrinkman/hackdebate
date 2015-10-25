package com.svc.debate;

import com.svc.debate.model.Users;
import com.svc.debate.service.Authenticate;
import com.svc.debate.service.DatabaseService;
import com.svc.debate.service.MainService;
import com.svc.debate.socket.DebateSocket;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

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

    get("/debate", (req, res) -> {
      res.status(200);
      res.type("text/html");
      return freeMarkerEngine.render(new ModelAndView(null, "assets/debate.ftl"));
    });

    post("/login", (req, res) -> {
      res.status(200);
      res.type("text/html");
      DatabaseService db = new DatabaseService();

      boolean flag = db.authenticateValidUser(req.queryMap("cs_login_sid").value(), req.queryMap("cs_login_password").value());
      System.out.println("flag: "+ flag);

      if (!flag)
        return freeMarkerEngine.render(new ModelAndView(null, "assets/home.ftl"));
      else
        return freeMarkerEngine.render(new ModelAndView(null, "assets/debate.ftl"));
    });

    /*get("/login", (req, res) -> {
      res.status(200);
      res.type("text/html");
      Authenticate authenticate = new Authenticate();
      DatabaseService db = new DatabaseService();
      boolean flag = db.authenticateUser(req.params("username"), req.params("password"));
      if (!flag)
        return freeMarkerEngine.render(new ModelAndView(null, "assets/home.ftl"));
      else
        return freeMarkerEngine.render(new ModelAndView(null, "assets/debate.ftl"));
    });*/
  }
}
