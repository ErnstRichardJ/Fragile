package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import FSM.ExpressionState;

import java.util.concurrent.ThreadLocalRandom;

import math.*;
import utilities.Constants;

public class OperationsTest
{
  Fraction zeroes = new Fraction(0, 0);

  ExpressionState es;

  Operation add = new Addition();
  Operation subtract = new Subtraction();
  Operation multiply = new Multiplication();
  Operation divide = new Division();
  Operation power = new IntegerPower();
  Operation mediant = new Mediant();
  Operation inverse = new Inverse();
  Operation farey = new Farey(true);

  @Test
  void testAdd()
  {
    for (int i = 0; i < 1000; i++)
    {
      Fraction f1 = randFrac();
      Fraction f2 = randFrac();

      es = new ExpressionState();

      es.addFractionIfPossible(f1);
      es.addFractionIfPossible(f2);

      Fraction result = add.calculate(es);

      assertEquals(toDecimal(f1) + toDecimal(f2), toDecimal(result), 0.000001);
    }
  }

  @Test
  void testSubtract()
  {
    for (int i = 0; i < 1000; i++)
    {
      Fraction f1 = randFrac();
      Fraction f2 = randFrac();

      es = new ExpressionState();

      es.addFractionIfPossible(f1);
      es.addFractionIfPossible(f2);

      Fraction result = subtract.calculate(es);

      assertEquals(toDecimal(f1) - toDecimal(f2), toDecimal(result), 0.000001);
    }
  }

  @Test
  void testMultiply()
  {
    for (int i = 0; i < 1000; i++)
    {
      Fraction f1 = randFrac();
      Fraction f2 = randFrac();

      es = new ExpressionState();

      es.addFractionIfPossible(f1);
      es.addFractionIfPossible(f2);

      Fraction result = multiply.calculate(es);

      assertEquals(toDecimal(f1) * toDecimal(f2), toDecimal(result), 0.000001);
    }
  }

  @Test
  void testDivide()
  {
    for (int i = 0; i < 1000; i++)
    {

      Fraction f1 = randFrac();
      Fraction f2 = randFrac();

      es = new ExpressionState();

      es.addFractionIfPossible(f1);
      es.addFractionIfPossible(f2);

      Fraction result = divide.calculate(es);

      assertEquals(toDecimal(f1) / toDecimal(f2), toDecimal(result), 0.000001);
    }
  }

  @Test
  void testPower()
  {
    for (int i = 0; i < 1000; i++)
    {
      Fraction f1 = randFrac();
      Fraction f2 = new Fraction(ThreadLocalRandom.current().nextInt(1, 5), 1);

      es = new ExpressionState();

      es.addFractionIfPossible(f1);
      es.addFractionIfPossible(f2);

      Fraction result = power.calculate(es);

      assertEquals(Math.pow(toDecimal(f1), f2.wholes()), toDecimal(result), 0.000001);
    }
  }

  @Test
  void testPowerEdgeCases()
  {
    es = new ExpressionState();

    es.addFractionIfPossible(zeroes);
    es.addFractionIfPossible(new Fraction(1, 2));

    assertThrows(IllegalArgumentException.class, () -> {
      power.calculate(es);
    });

    es = new ExpressionState();

    es.addFractionIfPossible(new Fraction(1, 2));
    es.addFractionIfPossible(new Fraction(0, 1));

    assertEquals(new Fraction(1, 1), power.calculate(es));
  }

  @Test
  void testMediant()
  {
    for (int i = 0; i < 1000; i++)
    {

      Fraction f1 = randFrac();
      Fraction f2 = randFrac();

      es = new ExpressionState();

      es.addFractionIfPossible(f1);
      es.addFractionIfPossible(f2);

      Fraction result = mediant.calculate(es);

      assertEquals(f1.improperNumerator() + f2.improperNumerator(), result.improperNumerator());
      assertEquals(f1.denominator() + f2.denominator(), result.denominator());
    }
  }

  @Test
  void testInverse()
  {
    for (int i = 0; i < 1000; i++)
    {

      Fraction f1 = randFrac();

      es = new ExpressionState();

      es.addFractionIfPossible(f1);

      Fraction result = inverse.calculate(es);

      assertEquals(toDecimal(f1), 1 / toDecimal(result), 0.000001);
    }
  }
  
  @Test
  void testFarey()
  {
    es = new ExpressionState();
    es.addFractionIfPossible(new Fraction(0,1));
    es.addFractionIfPossible(new Fraction(1,3));
    Fraction result = farey.calculate(es);
    assertEquals(toDecimal(new Fraction(1,2)), toDecimal(result), 0.000001);
    
    es = new ExpressionState();
    es.addFractionIfPossible(new Fraction(-1,1));
    es.addFractionIfPossible(new Fraction(1,3));
    assertNull(farey.calculate(es));
    
    es = new ExpressionState();
    es.addFractionIfPossible(new Fraction(1,3));
    es.addFractionIfPossible(new Fraction(-1,1));
    assertNull(farey.calculate(es));
    
    es = new ExpressionState();
    es.addFractionIfPossible(new Fraction(4,3));
    es.addFractionIfPossible(new Fraction(0,1));
    assertNull(farey.calculate(es));
    
    es = new ExpressionState();
    es.addFractionIfPossible(new Fraction(0,1));
    es.addFractionIfPossible(new Fraction(4,3));
    assertNull(farey.calculate(es));
    
    es = new ExpressionState();
    es.addFractionIfPossible(new Fraction(0,1));
    es.addFractionIfPossible(new Fraction(2,3));
    assertNull(farey.calculate(es));
  }

  @Test
  void testZeroes()
  {
    es = new ExpressionState();

    es.addFractionIfPossible(zeroes);

    assertEquals(zeroes, inverse.calculate(es));

    es.addFractionIfPossible(zeroes);

    assertEquals(zeroes, add.calculate(es));
    assertEquals(zeroes, subtract.calculate(es));
    assertEquals(zeroes, multiply.calculate(es));
    assertEquals(zeroes, divide.calculate(es));
    assertEquals(zeroes, mediant.calculate(es));

    assertEquals(new Fraction(1, 1), power.calculate(es));
  }

  private static Double toDecimal(Fraction f)
  {
    return (double) f.improperNumerator() / f.denominator();
  }

  private static Fraction randFrac()
  {
    Integer n = ThreadLocalRandom.current().nextInt(-100, 100);
    Integer d = ThreadLocalRandom.current().nextInt(-100, 100);

    if (d == 0)
    {
      d = 1;
    }

    if (n == 0)
    {
      n = 1;
    }

    return new Fraction(n, d);
  }
}
