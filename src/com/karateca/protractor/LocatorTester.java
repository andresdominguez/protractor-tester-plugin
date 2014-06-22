package com.karateca.protractor;

import com.intellij.openapi.util.Pair;

public class LocatorTester {
  final JsonReader jsonReader;

  public LocatorTester(JsonReader jsonReader) {
    this.jsonReader = jsonReader;
  }

  public Pair<String, String> testLocator(String locator) {
    String json = jsonReader.read(locator);

    // Parse the json string. It looks like this:
    // {"results":{"element.all(by.model('yourName')).count()":1}}
    String resultsPattern = "(\\{\"results\":\\{)(.+)(\\}\\}$)";
    String keyAndValue = json.replaceAll(resultsPattern, "$2");

    // The key / value looks like this. Split at ":
    // "element.all(by.model('yourName')).count()":1
    int i = keyAndValue.indexOf("\":") + 1;
    if (i == 0) {
      return null;
    }

    // Get the contents between quotes.
    String quotesPattern = "\"?(.*?)\"?";
    return new Pair<String, String>(
        keyAndValue.substring(0, i).replaceAll(quotesPattern, "$1"),
        keyAndValue.substring(i + 1).replaceAll(quotesPattern, "$1")
    );
  }
}
