package com.svc.debate.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.sql.Timestamp;

/**
 * Created by doyonghoon on 2015. 10. 24..
 */
public class TimestampSerializer implements JsonDeserializer<Timestamp> {
  @Override
  public Timestamp deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    if (jsonElement != null) {
      long utc = Long.parseLong(jsonElement.getAsString());
      return new Timestamp(utc);
    }
    return null;
  }
}
