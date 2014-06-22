package com.karateca.protractor;

import com.intellij.openapi.util.Pair;
import junit.framework.TestCase;

public class LocatorTesterTest extends TestCase {

  Pair<String, String> testElementExplorerResponse(final String response) {
    return new LocatorTester(new JsonReader() {
      @Override
      public String read(String locator) {
        return response;
      }
    }).testLocator("");
  }

  public void testCountResultValue() {
    String response = "{\"results\":{\"element(by.model('yourName'))." +
        "getAttribute('value')\":\"\"}}";
    Pair<String, String> pair = testElementExplorerResponse(response);

    assertEquals("element(by.model('yourName')).getAttribute('value')", pair.first);
    assertEquals("", pair.second);
  }
}