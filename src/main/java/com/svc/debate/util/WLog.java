package com.svc.debate.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by doyonghoon on 15. 1. 24..
 */

public class WLog {

  public final static DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");

  static int lineNumber;
  static String className;
  static String methodName;

  public static boolean isDebuggable() {
    return true;
  }

  private static void log(String tag, String msg) {
    System.out.println(getMessage(tag, msg));
  }

  public static String getMessage(String tag, String msg) {
    return dateFormat.format(Calendar.getInstance().getTime()) + " " + tag + ": " + msg;
  }

  private static String createLog(String log) {
    return "[" + methodName + "():" + lineNumber + "] " + log;
  }

  private static void getMethodNames(StackTraceElement[] sElements) {
    className = sElements[1].getFileName().replaceAll(".java", "");
    methodName = sElements[1].getMethodName();
    lineNumber = sElements[1].getLineNumber();
  }

  public static void i(String message) {
    if (!isDebuggable()) return;

    getMethodNames(new Throwable().getStackTrace());

    log(className, createLog(message));
  }
}
