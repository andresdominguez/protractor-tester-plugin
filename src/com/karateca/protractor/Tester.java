package com.karateca.protractor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Tester {
  public static void testLocator(String locator) throws Exception {
    URL yahoo = new URL("http://www.yahoo.com/");
    URLConnection yc = yahoo.openConnection();
    BufferedReader in = new BufferedReader(
        new InputStreamReader(
            yc.getInputStream())
    );
    String inputLine;

    while ((inputLine = in.readLine()) != null)
      System.out.println(inputLine);
    in.close();
  }
}
