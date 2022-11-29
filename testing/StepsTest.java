package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;

import math.*;
import gui.Style;
import utilities.Constants;
import FSM.ExpressionState;

public class StepsTest
{
  ExpressionState es;
  Operation op;

  @Test
  void testAdd()
  {
    op = new Addition();
    assertEquals(op.toString(), Constants.ADD);
    for (int i = 0; i < 100; i++)
    {
      es = new ExpressionState();

      Fraction l = randFrac();
      Fraction r = randFrac();

      es.addFractionIfPossible(l);
      es.addOperatorIfPossible(op);
      es.addFractionIfPossible(r);

      int n = op.getSteps(es, Style.BAR, true).size();
      int expected = 5;

      if (l.improperNumerator() > l.denominator())
      {
        expected++;
      }

      if (r.improperNumerator() > r.denominator())
      {
        expected++;
      }

      es.calculate();

      Fraction result = es.getResult();

      if (!result.equals(result.reduce()))
      {
        expected++;
      }

      if (result.wholes() > 0 && result.mixedNumerator() != 0)
      {
        expected++;
      }

      assertEquals(expected, n);
    }
  }
  
  @Test
  void testSub()
  {
    op = new Subtraction();
    assertEquals(op.toString(), Constants.SUBTRACT);
    for (int i = 0; i < 100; i++)
    {
      es = new ExpressionState();

      Fraction l = randFrac();
      Fraction r = randFrac();

      es.addFractionIfPossible(l);
      es.addOperatorIfPossible(op);
      es.addFractionIfPossible(r);

      int n = op.getSteps(es, Style.BAR, true).size();
      int expected = 5;

      if (l.improperNumerator() > l.denominator())
      {
        expected++;
      }

      if (r.improperNumerator() > r.denominator())
      {
        expected++;
      }

      es.calculate();

      Fraction result = es.getResult();

      if (!result.equals(result.reduce()))
      {
        expected++;
      }

      if (result.wholes() > 0 && result.mixedNumerator() != 0)
      {
        expected++;
      }

      assertEquals(expected, n);
    }
  }
  
  @Test
  void testMult()
  {
    op = new Multiplication();
    assertEquals(op.toString(), Constants.MULTIPLY);
    for (int i = 0; i < 100; i++)
    {
      es = new ExpressionState();

      Fraction l = randFrac();
      Fraction r = randFrac();

      es.addFractionIfPossible(l);
      es.addOperatorIfPossible(op);
      es.addFractionIfPossible(r);

      int n = op.getSteps(es, Style.BAR, true).size();
      int expected = 3;

      if (l.improperNumerator() > l.denominator())
      {
        expected++;
      }

      if (r.improperNumerator() > r.denominator())
      {
        expected++;
      }

      es.calculate();

      Fraction result = es.getResult();

      if (!result.equals(result.reduce()))
      {
        expected++;
      }

      if (result.wholes() > 0 && result.mixedNumerator() != 0)
      {
        expected++;
      }

      assertEquals(expected, n);
    }
  }
  
  @Test
  void testDiv()
  {
    op = new Division();
    assertEquals(op.toString(), Constants.DIVIDE);
    for (int i = 0; i < 100; i++)
    {
      es = new ExpressionState();

      Fraction l = randFrac();
      Fraction r = randFrac();

      es.addFractionIfPossible(l);
      es.addOperatorIfPossible(op);
      es.addFractionIfPossible(r);

      int n = op.getSteps(es, Style.BAR, true).size();
      int expected = 3;

      if (l.improperNumerator() > l.denominator())
      {
        expected++;
      }

      if (r.improperNumerator() > r.denominator())
      {
        expected++;
      }

      es.calculate();

      Fraction result = es.getResult();

      if (!result.equals(result.reduce()))
      {
        expected++;
      }

      if (result.wholes() > 0 && result.mixedNumerator() != 0)
      {
        expected++;
      }

      assertEquals(expected, n);
    }
  }
  
  @Test
  void testMediant()
  {
    op = new Mediant();
    assertEquals(op.toString(), Constants.MEDIANT);
    for (int i = 0; i < 100; i++)
    {
      es = new ExpressionState();

      Fraction l = randFrac();
      Fraction r = randFrac();

      es.addFractionIfPossible(l);
      es.addOperatorIfPossible(op);
      es.addFractionIfPossible(r);

      int n = op.getSteps(es, Style.BAR, true).size();
      int expected = 3;

      if (l.improperNumerator() > l.denominator())
      {
        expected++;
      }

      if (r.improperNumerator() > r.denominator())
      {
        expected++;
      }

      es.calculate();

      Fraction result = es.getResult();

      if (!result.equals(result.reduce()))
      {
        expected++;
      }

      if (result.wholes() > 0 && result.mixedNumerator() != 0)
      {
        expected++;
      }

      assertEquals(expected, n);
    }
  }
  
  @Test
  void testInverse()
  {
    op = new Inverse();
    assertEquals(op.toString(), Constants.INVERSE);
    for (int i = 0; i < 100; i++)
    {
      es = new ExpressionState();

      Fraction l = randFrac();

      es.addFractionIfPossible(l);
      es.addOperatorIfPossible(op);

      int n = op.getSteps(es, Style.BAR, true).size();
      int expected = 1;

      if (l.improperNumerator() > l.denominator())
      {
        expected++;
      }

      es.calculate();

      Fraction result = es.getResult();

      if (!result.equals(result.reduce()))
      {
        expected++;
      }

      if (result.wholes() > 0 && result.mixedNumerator() != 0)
      {
        expected++;
      }

      assertEquals(expected, n);
    }
  }
  
  @Test
  void testPow()
  {
    op = new IntegerPower();
    assertEquals(op.toString(), Constants.POW);
    for (int i = 0; i < 100; i++)
    {
      es = new ExpressionState();

      Fraction l = randFrac();
      Fraction r = new Fraction(ThreadLocalRandom.current().nextInt(1, 5), 1);

      es.addFractionIfPossible(l);
      es.addOperatorIfPossible(op);
      es.addFractionIfPossible(r);

      int n = op.getSteps(es, Style.BAR, true).size();
      int expected = 3;

      if (l.improperNumerator() > l.denominator())
      {
        expected++;
      }

      es.calculate();

      Fraction result = es.getResult();

      if (!result.equals(result.reduce()))
      {
        expected++;
      }

      if (result.wholes() > 0 && result.mixedNumerator() != 0)
      {
        expected++;
      }

      assertEquals(expected, n);
    }
  }
  
  @Test
  void testFarey()
  {
    op = new Farey();
    es = new ExpressionState();
    
    assertEquals(op.toString(), Constants.FAREY_NUMBER);
    
    es.addFractionIfPossible(new Fraction(0,1));
    es.addFractionIfPossible(new Fraction(1,2));
    
    assertEquals(8, op.getSteps(es, Style.BAR, true).size());
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
