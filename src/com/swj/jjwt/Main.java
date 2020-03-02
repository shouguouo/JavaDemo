package com.swj.jjwt;

import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明:
 *
 * @author wink~
 */
public class Main {
  public static void main(String[] args) {
    String secret = "shouweijian";
    JWT jwt = new JWT(secret);

    Map<String, Object> payload = new HashMap<>();
    payload.put("name", "swj");
    payload.put("age", "12");

    Date date = new Date(System.currentTimeMillis() + 60 * 1000);

    String token = jwt.sign(payload, date);
    System.out.println(token);

    Claims claims = jwt.verify(token);
    System.out.println(claims.toString());
  }
}
