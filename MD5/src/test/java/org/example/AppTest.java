package org.example;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {
  @Test
  public void shouldAnswerWithTrue() {
    // Two different strings will have different hashes
    String s1 = "String";
    String s2 = "Strint";
    assertNotEquals(MD5.toHexString(MD5.computeMD5(s1.getBytes())), MD5.toHexString(MD5.computeMD5(s2.getBytes())));
  }
}
