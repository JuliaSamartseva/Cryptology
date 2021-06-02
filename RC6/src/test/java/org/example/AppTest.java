package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.UUID;

public class AppTest {
  @Test
  public void testSampleText() {
    String text = "Hello world";
    String key = "Random key";
    byte[] enc = RC6.encrypt(text.getBytes(), key.getBytes());
    assertEquals(new String(RC6.decrypt(enc, key.getBytes())), text);
  }

  @Test
  public void testRandomText() {
    int n = 1000;
    for (int i = 0; i < n; i++) {
      String text = generateString();
      String key = generateString();
      byte[] enc = RC6.encrypt(text.getBytes(), key.getBytes());
      assertEquals(new String(RC6.decrypt(enc, key.getBytes())), text);
    }
  }

  private static String generateString() {
    String uuid = UUID.randomUUID().toString();
    return "uuid = " + uuid;
  }
}
