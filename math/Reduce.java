package math;

import java.util.ArrayList;
import java.util.List;

import FSM.ExpressionState;
import gui.Step;
import gui.Style;

/**
 * Reduces a fraction.
 * 
 * @author Jacob Gottschalk
 * @version 2021-11-12
 */
public class Reduce extends UnaryOperation
{

  /**
   * Required getResult method.
   * 
   * @param frac
   * @return fraction in reduced form
   */
  @Override
  protected Fraction getResult(final Fraction frac)
  {
    if (frac == null || frac.isUndefined())
      return frac;
    
    Integer gcd = gcd(frac.improperNumerator(), frac.denominator());
    
    return new Fraction(frac.improperNumerator() / gcd, frac.denominator() / gcd);
  }
  
  /**
   * private helper method to find the gcd of 2 numbers.
   * 
   * @param inputa
   * @param inputb
   * @return gcd of a and b
   */
  private static Integer gcd(final Integer inputa, final Integer inputb)
  {
    Integer a = inputa;
    Integer b = inputb;
    Integer tmp = null;
    
    while (b != 0)
    {
      tmp = a % b;
      a = b;
      b = tmp;
    }
    
    return a;
  }

  @Override
  public List<Step> getSteps(final ExpressionState es, final Style style, final boolean reduced)
  {
    List<Step> output = new ArrayList<>();
    
    return null;
  }

}
