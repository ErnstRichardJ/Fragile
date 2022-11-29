package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import FSM.ExpressionState;
import gui.Style;
import math.Fraction;
import utilities.Constants;

class ExpressionStateTest
{
  ExpressionState es = new ExpressionState();

  @Test
  void testReset()
  {
    Fraction left = new Fraction(1, 2);
    Fraction right = new Fraction(1, 2);
    String op = Constants.MULTIPLY;

    es.clear();
    es.addFractionIfPossible(left);
    es.addOperatorIfPossible(Constants.OP_MAP.get(Constants.MULTIPLY));
    es.addFractionIfPossible(right);
    es.calculate();

    Fraction result = es.getResult();
    assertNotNull(result);
    
    es.reset();

    assertEquals(result.improperNumerator(), es.getLeftFraction().improperNumerator());
    assertEquals(result.denominator(), es.getLeftFraction().denominator());
    assertEquals(null, es.getRightFraction());
    assertEquals(null, es.getOperator());
    assertEquals(null, es.getResult());

  }
  @Test
  void testClear()
  {
    Fraction left = new Fraction(1, 2);
    Fraction right = new Fraction(1, 2);
    String op = Constants.MULTIPLY;
//    assertTrue(es.update(left));
//    assertTrue(es.update(op));
//    assertTrue(es.update(right));
    Fraction result = es.getResult();
    
    es.clear();

    assertEquals(null, es.getLeftFraction());
    assertEquals(null, es.getRightFraction());
    assertEquals(null, es.getOperator());
    assertEquals(null, es.getResult());

  }

  @Test
  void testMultipleGetResult()
  {
    Fraction left = new Fraction(1, 2);
    Fraction right = new Fraction(1, 2);
    String op = Constants.MULTIPLY;
//    assertTrue(es.update(left));
//    assertTrue(es.update(op));
//    assertTrue(es.update(right));
    Fraction result = es.getResult();
    assertTrue(result == es.getResult()); // Check if same address
    
  }
  @Test
  void testUpdate()
  {
    Fraction left = new Fraction(1, 2);
    Fraction right = new Fraction(1, 2);
    es.clear();
    es.addFractionIfPossible(left);
    es.addOperatorIfPossible(Constants.OP_MAP.get(Constants.MULTIPLY));
    es.addFractionIfPossible(right);
    es.calculate();
    Fraction result = es.getResult();
    
    assertTrue(es.isComplete());
    es.setComplete();

    assertEquals(1, result.improperNumerator());
    assertEquals(4, result.denominator());
    
    assertEquals(left.improperNumerator(), es.getLeftFraction().improperNumerator());
    assertEquals(left.denominator(), es.getLeftFraction().denominator());

    assertEquals(right.improperNumerator(), es.getRightFraction().improperNumerator());
    assertEquals(right.denominator(), es.getRightFraction().denominator());

    assertEquals(Constants.OP_MAP.get(Constants.MULTIPLY), es.getOperator());
  }

  @Test
  void testFailedCalulation()
  {
    Fraction left = new Fraction(1, 1);
    Fraction right = new Fraction(1, 1);

    es.clear();
    es.addFractionIfPossible(left);
    es.addOperatorIfPossible(new math.Farey(true));
    es.addFractionIfPossible(right);
    es.calculate();

    Fraction result = es.getResult();
    assertEquals(null, result);
    es.clear();

    left = new Fraction(1, 1);
    right = new Fraction(0, 1);
    String op = Constants.DIVIDE;
    es.addFractionIfPossible(left);
    es.addOperatorIfPossible(Constants.OP_MAP.get(op));
    es.addFractionIfPossible(right);
    es.calculate();

    result = es.getResult();
    assertEquals(new Fraction(0, 1), result);
  }

  @Test
  void testStyle()
  {
    es.setStyle(Style.BAR);
    assertEquals(Style.BAR, es.getStyle());
    es.setStyle(Style.SOLIDUS);
    assertEquals(Style.SOLIDUS, es.getStyle());
    es.setStyle(Style.SLASH);
    assertEquals(Style.SLASH, es.getStyle());

  }

  @Test
  void testSteps() {
    Fraction left = new Fraction(1, 2);
    Fraction right = new Fraction(1, 2);
    es.clear();
    es.addFractionIfPossible(left);
    es.addOperatorIfPossible(Constants.OP_MAP.get(Constants.MULTIPLY));
    es.addFractionIfPossible(right);
    es.calculate();
    
    assertTrue(es.getSteps() != null);
    
    es.clear();

    assertTrue(es.getSteps() == null);
  }

  @Test
  void testAddNull() {
    es.clear();
    Fraction left = new Fraction(1, 2);
    Fraction right = new Fraction(1, 2);
    assertTrue(es.addFractionIfPossible(left));
    assertTrue(es.addFractionIfPossible(right));
    assertFalse(es.addFractionIfPossible(left));

    assertTrue(es.addOperatorIfPossible(Constants.OP_MAP.get(Constants.MULTIPLY)));
    assertFalse(es.addOperatorIfPossible(Constants.OP_MAP.get(Constants.MULTIPLY)));

  }

  @Test
  void testReduce() {

    Fraction left = new Fraction(1, 2);
    Fraction right = new Fraction(1, 2);
    es.toggleReduce();
    assertEquals(true, es.getReduce());

    es.clear();
    es.addFractionIfPossible(left);
    es.addOperatorIfPossible(Constants.OP_MAP.get(Constants.MULTIPLY));
    es.addFractionIfPossible(right);
    es.calculate();
    
    assertEquals(new Fraction(1, 4), es.getResult());
    
    es.toggleReduce();
  }

  @Test
  void testDeepCopy() {
    Fraction left = new Fraction(1, 2);
    Fraction right = new Fraction(1, 2);

    es.clear();
    es.addFractionIfPossible(left);
    es.addOperatorIfPossible(Constants.OP_MAP.get(Constants.MULTIPLY));
    es.addFractionIfPossible(right);
    es.calculate();
    
    ExpressionState newes = new ExpressionState(es);
    assertEquals(es.getResult(), newes.getResult());
  }

}
