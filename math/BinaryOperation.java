package math;

import FSM.ExpressionState;

public abstract class BinaryOperation implements Operation
{
  public Fraction calculate(final ExpressionState es)
  {
    if (es == null)
    {
      return null;
    }

    return getResult(es.getLeftFraction(), es.getRightFraction());
  }

  /**
   * Computes the result of frac1 (this Operation) frac2.
   * 
   * @param frac1 The left fraction
   * @param frac2 The right fraction
   * @return Result of the operation
   */
  protected abstract Fraction getResult(final Fraction frac1, final Fraction frac2);
}
