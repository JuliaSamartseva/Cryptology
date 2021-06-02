package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

public class AppTest {
  /**
   * Take power by modulo
   */
  @Test
  public void takeToPower() {
    int testNumber = 100;
    BigInteger upperLimit = BigInteger.valueOf(1000000000);
    for (int i = 0; i < testNumber; i++) {
      BigInteger first = generateRandomNumber(upperLimit);
      BigInteger second = generateRandomNumber(upperLimit);
      BigInteger modulo = generateRandomNumber(upperLimit);
      BigInteger expression = Power.takeToPower(first, second, modulo);
      assertEquals(expression, first.modPow(second, modulo));
    }
  }

  private BigInteger generateRandomNumber(BigInteger upperLimit) {
    Random random = new Random();
    BigInteger randomNumber;
    do {
      randomNumber = new BigInteger(upperLimit.bitLength(), random);
    } while (randomNumber.compareTo(upperLimit) >= 0);
    return randomNumber;
  }

  /**
   * Karatsuba multiplication
   */
  @Test
  public void karatsubaMultiplicationCornerCases() {
    BigInteger upperLimit = BigInteger.valueOf(1000000000);
    BigInteger num = generateRandomNumber(upperLimit);
    assertEquals(Karatsuba.multiply(num, BigInteger.ONE), num);
    assertEquals(Karatsuba.multiply(BigInteger.ZERO, num), BigInteger.ZERO);
  }

  @Test
  public void karatsubaMultiplication() {
    int testNumber = 100;
    for (int i = 0; i < testNumber; i++) {
      BigInteger upperLimit = BigInteger.valueOf(1000000000);
      BigInteger first = generateRandomNumber(upperLimit);
      BigInteger second = generateRandomNumber(upperLimit);
      assertEquals(Karatsuba.multiply(first, second),
              first.multiply(second));
    }
  }

  /**
   * Extended Euclidean Algorithm
   */
  @Test
  public void extendedEuclideanAlgorithm() {
    BigInteger[] gcd1 = ExtendedEuclideanAlgorithm.gcdExtended(new BigInteger(String.valueOf(17)), new BigInteger(String.valueOf(43)));
    assertEquals(gcd1[0], BigInteger.ONE);
    assertEquals(gcd1[1],  new BigInteger("-5"));
    assertEquals(gcd1[2],  new BigInteger("2"));

    BigInteger[] gcd2 = ExtendedEuclideanAlgorithm.gcdExtended(new BigInteger(String.valueOf(100)), new BigInteger(String.valueOf(100)));
    assertEquals(gcd2[0], new BigInteger("100"));
    assertEquals(gcd2[1], BigInteger.ZERO);

    BigInteger[] gcd3 = ExtendedEuclideanAlgorithm.gcdExtended(new BigInteger(String.valueOf(51)), new BigInteger(String.valueOf(17)));
    assertEquals(gcd3[0], new BigInteger("17"));
    assertEquals(gcd3[1], BigInteger.ZERO);

    BigInteger[] gcd4 = ExtendedEuclideanAlgorithm.gcdExtended(new BigInteger(String.valueOf(5)), new BigInteger(String.valueOf(17)));
    assertEquals(gcd4[0], new BigInteger("1"));
    assertEquals(gcd4[1], new BigInteger("7"));
  }

  /**
   * Fermat prime test
   */
  @Test
  public void fermatTestPrime() {
    int k = 50;
    assertTrue(FermatTest.test(BigInteger.valueOf(239), k));
    assertTrue(FermatTest.test(BigInteger.valueOf(7919), k));
    assertTrue(FermatTest.test(BigInteger.valueOf(6827), k));
    assertTrue(FermatTest.test(BigInteger.valueOf(6449), k));
  }

  /**
   * Miller-Rabin prime test
   */
  @Test
  public void millerRabinTestPrime() {
    int k = 50;
    assertTrue(MillerRabin.test(BigInteger.valueOf(239), k));
    assertTrue(MillerRabin.test(BigInteger.valueOf(7919), k));
    assertTrue(MillerRabin.test(BigInteger.valueOf(6827), k));
    assertTrue(MillerRabin.test(BigInteger.valueOf(6449), k));
  }

  /**
   * Montgomery
   */
  @Test
  public void multiplyMontgomery() {
    int testNumber = 100;
    BigInteger upperLimit = BigInteger.valueOf(1000000000);
    for (int i = 0; i < testNumber; i++) {
      BigInteger first = generateRandomNumber(upperLimit);
      BigInteger second = generateRandomNumber(upperLimit);
      BigInteger modulo = generateRandomNumber(upperLimit);
      if (!modulo.testBit(0)) continue;
      Montgomery red = new Montgomery(modulo);
      BigInteger result = red.multiply(red.convertToMontgomery(first), red.convertToMontgomery(second));
      BigInteger expected = first.multiply(second).mod(modulo);
      assertEquals(expected, red.convertFromMontgomery(result));
    }
  }

  @Test
  public void powMontgomery() {
    int testNumber = 100;
    BigInteger upperLimit = BigInteger.valueOf(1000000000);
    for (int i = 0; i < testNumber; i++) {
      BigInteger first = generateRandomNumber(upperLimit);
      BigInteger second = generateRandomNumber(upperLimit);
      BigInteger modulo = generateRandomNumber(upperLimit);

      if (!modulo.testBit(0)) continue;
      Montgomery red = new Montgomery(modulo);
      BigInteger result = red.pow(red.convertToMontgomery(first), second);
      BigInteger expected = first.modPow(second, modulo);
      assertEquals(expected, red.convertFromMontgomery(result));
    }
  }

}
