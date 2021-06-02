package org.example;

import java.math.BigInteger;

public class ExtendedEuclideanAlgorithm {
  public static BigInteger[] gcdExtended(BigInteger a, BigInteger b) {
    BigInteger[] ans = new BigInteger[3];

    if (b.intValue() == 0) {
      // Base case
      ans[0] = a;
      ans[1] = BigInteger.valueOf(1);
      ans[2] = BigInteger.valueOf(0);
    } else {
      BigInteger q = a.divide(b);
      ans = gcdExtended(b, a.remainder(b));

      BigInteger temp = ans[1].subtract(ans[2].multiply(q));
      ans[1] = ans[2];
      ans[2] = temp;
    }
    return ans;
  }

  public static void main(String[] args) {
    // 17x mod 43 = 1
    BigInteger a = BigInteger.valueOf(17);
    BigInteger b = BigInteger.valueOf(43);

    BigInteger[] res = gcdExtended(a, b);
    System.out.println("gcd (" + a + ", " + b + ") = " + res[0]);
    System.out.println("x = " + res[1]);
    System.out.println("y = " + res[2]);
  }
}
