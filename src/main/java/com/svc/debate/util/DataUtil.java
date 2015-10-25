package com.svc.debate.util;

import com.google.gson.Gson;
import com.svc.debate.serializer.TimestampSerializer;
import java.sql.Timestamp;

/**
 * Created by doyonghoon on 2015. 10. 24..
 */
public class DataUtil {

  private static Gson gson;

  public static Gson getGson() {
    if (gson == null) {
      gson = new com.google.gson.GsonBuilder().serializeNulls()
          .registerTypeAdapter(Timestamp.class, new TimestampSerializer())
          .create();
    }
    return gson;
  }
}
