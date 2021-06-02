package org.example;

import java.math.BigInteger;

public class Montgomery {
  private final BigInteger modulo;

  private final int reducerBits;
  private final BigInteger reciprocal;
  private final BigInteger mask;
  private final BigInteger factor;
  private final BigInteger convertedOne;

  public Montgomery(BigInteger modulo) {
    this.modulo = modulo;

    reducerBits = (modulo.bitLength() / 8 + 1) * 8;
    BigInteger reducer = BigInteger.ONE.shiftLeft(reducerBits);
    mask = reducer.subtract(BigInteger.ONE);

    reciprocal = reducer.modInverse(modulo);
    factor = reducer.multiply(reciprocal).subtract(BigInteger.ONE).divide(modulo);
    convertedOne = reducer.mod(modulo);
  }

  // transform to montgomery form
  public BigInteger convertToMontgomery(BigInteger x) {
    return x.shiftLeft(reducerBits).mod(modulo);
  }

  // transform from montgomery form
  public BigInteger convertFromMontgomery(BigInteger x) {
    return x.multiply(reciprocal).mod(modulo);
  }

  // x = ar mod n
  // y = br mod n
  // c = abr mod n
  // x * y = abr^2 = cr mod n
  public BigInteger multiply(BigInteger x, BigInteger y) {
    BigInteger product = x.multiply(y);
    BigInteger temp = product.and(mask).multiply(factor).and(mask);
    BigInteger reduced = product.add(temp.multiply(modulo)).shiftRight(reducerBits);
    return reduced.compareTo(modulo) < 0 ? reduced : reduced.subtract(modulo);
  }

  public BigInteger pow(BigInteger x, BigInteger y) {
    BigInteger z = convertedOne;
    for (int i = 0, len = y.bitLength(); i < len; i++) {
      if (y.testBit(i)) {
        z = multiply(z, x);
      }
      x = multiply(x, x);
    }
    return z;
  }

}
