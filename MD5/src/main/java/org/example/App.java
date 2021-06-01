package org.example;

public class App {
  public static void main(String[] args) {
    String s = "String";
    System.out.println("0x" + MD5.toHexString(MD5.computeMD5(s.getBytes())));
  }
}
