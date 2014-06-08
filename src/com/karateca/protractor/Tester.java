package com.karateca.protractor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Tester {

  private static final String baseUrl = "http://localhost:13000/testSelector?locators=";

  public static void testLocator(String locator) throws Exception {
    String queryString = URLEncoder.encode(
        String.format("{\"popupInput\":\"%s\"}", locator), "UTF-8");
    String url = baseUrl + queryString;
    URLConnection connection = new URL(url).openConnection();
    BufferedReader in = new BufferedReader(
        new InputStreamReader(connection.getInputStream())
    );
    String inputLine;

    while ((inputLine = in.readLine()) != null) {
      System.out.println(inputLine);
    }

    in.close();
  }
}