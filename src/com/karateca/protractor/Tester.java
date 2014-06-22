package com.karateca.protractor;

import org.intellij.lang.annotations.Language;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Tester {

  private static final String baseUrl = "http://localhost:13000/testSelector?popupInput=";

  public static String testLocator(String locator) {
    StringBuilder sb = new StringBuilder();

    try {
      String url = baseUrl + URLEncoder.encode(locator, "UTF-8");
      URLConnection connection = new URL(url).openConnection();
      BufferedReader in = new BufferedReader(
          new InputStreamReader(connection.getInputStream())
      );
      String inputLine;

      while ((inputLine = in.readLine()) != null) {
        sb.append(inputLine);
        System.out.println(inputLine);
      }
      in.close();
    } catch (Exception e) {
      sb.append("Error testing locator").append(e.getMessage());
    }

    return sb.toString();
  }

  public static String gg(String locator) {
    String json = testLocator(locator);

    // Parse old school:
    // {"results":{"element.all(by.model('yourName')).count()":1}}

    // Remove results:
    @Language("RegExp")
    String pattern = "(\\{\"results\":\\{)(.+)(\\}\\}$)";
    String aa = json.replaceAll(pattern, "$2");

    // "element.all(by.model('yourName')).count()":1
    pattern = "(\")(.+)(\":\"?)(.+)(\")";
    String key = aa.replaceAll(pattern, "$2");
    String value = aa.replaceAll(pattern, "$4");

    System.out.println(key + value);

    return null;
  }
}
