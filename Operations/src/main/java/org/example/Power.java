package org.example;

import java.math.BigInteger;

public class Power {
  public static BigInteger takeToPower(BigInteger a, BigInteger b, BigInteger mod) { //a^b
    if (b.equals(BigInteger.ZERO))
      return BigInteger.ONE;
    if (b.equals(BigInteger.ONE))
      return a.mod(mod);

    if (b.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
      return a.multiply(takeToPower(a, b.subtract(BigInteger.ONE), mod)).mod(mod);
    }

    return takeToPower(a, b.divide(BigInteger.TWO), mod).pow(2).mod(mod);
  }
}
