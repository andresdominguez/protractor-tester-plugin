package com.karateca.protractor;

import com.intellij.openapi.util.Pair;

public class LocatorTester {
  final JsonReader jsonReader;

  public LocatorTester(JsonReader jsonReader) {
    this.jsonReader = jsonReader;
  }

  public Pair<String, String> testLocator(String locator) {
    String json = jsonReader.read(locator);

    String pattern = "(\\{\"results\":\\{)(.+)(\\}\\}$)";
    String aa = json.replaceAll(pattern, "$2");

    // "element.all(by.model('yourName')).count()":1
    pattern = "(\")(.+)(\":\"?)(.+)(\")";
    String key = aa.replaceAll(pattern, "$2");
    String value = aa.replaceAll(pattern, "$4");

    return new Pair<String, String>(key, value);
  }
}
