package org.example;

import java.math.BigInteger;
import java.util.Random;

public class FermatTest {
  public static boolean test(BigInteger number, int iterations) {
    // corner cases
    if (number.equals(BigInteger.ONE) || number.equals(BigInteger.valueOf(4))) {
      return false;
    } else if (number.equals(BigInteger.TWO) || number.equals(BigInteger.valueOf(3))) {
      return true;
    }

    for (int i = 0; i < iterations; i++) {
      // pick random number in [2 ... n-2]
      BigInteger a = getRandomNumber(number);

      // Fermat's little theorem - a^(n - 1) mod n
      if (!a.modPow(number.subtract(BigInteger.ONE), number).equals(BigInteger.ONE))
        return false;
    }

    return true;

  }

  // random number in [2 ... n-2]
  private static BigInteger getRandomNumber(BigInteger n) {
    BigInteger k = n.subtract(BigInteger.ONE);

    BigInteger randomNumber;
    while (true) {
      randomNumber = new BigInteger(k.bitLength(), new Random());

      if (BigInteger.ONE.compareTo(randomNumber) <= 0 && randomNumber.compareTo(n) < 0)
        return randomNumber;
    }
  }

}
