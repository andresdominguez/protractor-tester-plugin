package com.karateca.protractor;

import com.intellij.openapi.util.Pair;

public class LocatorTester {
  final JsonReader jsonReader;
  private static final String resultsPattern = "(\\{\"results\":\\{)(.+)(\\}\\}$)";
  private static final String keyValuePattern = "(\")(.+)(\":\"?)(.+)(\")";

  public LocatorTester(JsonReader jsonReader) {
    this.jsonReader = jsonReader;
  }

  public Pair<String, String> testLocator(String locator) {
    String json = jsonReader.read(locator);

    // Parse the json string. It looks like this:
    // {"results":{"element.all(by.model('yourName')).count()":1}}
    String keyAndValue = json.replaceAll(resultsPattern, "$2");

    // The key / value looks like this:
    // "element.all(by.model('yourName')).count()":1
    return new Pair<String, String>(
        keyAndValue.replaceAll(keyValuePattern, "$2"),
        keyAndValue.replaceAll(keyValuePattern, "$4")
    );
  }
}
