package org.example;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabin {
  public static boolean test(BigInteger number, int iterations) {
    // corner cases
    if (number.equals(BigInteger.ONE) || number.equals(BigInteger.valueOf(4))) {
      return false;
    } else if (number.equals(BigInteger.TWO) || number.equals(BigInteger.valueOf(3))) {
      return true;
    }

    // n = 2^d * r + 1
    BigInteger d = number.subtract(BigInteger.ONE);
    while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
      d = d.divide(BigInteger.TWO);
    }

    for (int i = 0; i < iterations; i++) {
      if (!testMiller(d, number)) {
        return false;
      }
    }

    return true;
  }

  private static boolean testMiller(BigInteger d, BigInteger number) {
    BigInteger a = getRandomNumber(number);
    // calculate a^d % n
    BigInteger x = a.modPow(d, number);
    if (x.equals(BigInteger.ONE) || x.equals(number.subtract(BigInteger.ONE))) {
      return true;
    }

    while (!d.equals(number.subtract(BigInteger.ONE))) {
      x = x.modPow(BigInteger.TWO, number);
      d = d.multiply(BigInteger.TWO);
      if (x.equals(BigInteger.ONE)) {
        return false;
      }
      if (x.equals(number.subtract(BigInteger.ONE))) {
        return true;
      }
    }

    return false;
  }

  // random number in [2 ... n-2]
  private static BigInteger getRandomNumber(BigInteger n) {
    n = n.subtract(BigInteger.ONE);
    BigInteger randomNumber;
    do {
      randomNumber = new BigInteger(n.bitLength(), new Random());
    } while (randomNumber.compareTo(n) >= 0 && randomNumber.equals(BigInteger.ONE));
    return randomNumber;
  }
}
