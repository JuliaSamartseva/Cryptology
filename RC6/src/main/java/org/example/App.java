package org.example;

public class App {
  public static void main(String[] args) {
    String text = "Hello";
    String key = "key";
    System.out.println("Text: " + text);
    byte[] enc = RC6.encrypt(text.getBytes(), key.getBytes());
    System.out.println("Encrypted RC6 text: ");
    System.out.println(new String(enc));
    byte[] dec = RC6.decrypt(enc, key.getBytes());
    System.out.println("Decrypted RC6 text: " + new String(dec));
  }
}
