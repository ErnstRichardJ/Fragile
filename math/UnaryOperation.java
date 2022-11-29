package math;

import FSM.ExpressionState;

public abstract class UnaryOperation implements Operation
{ 
  public Fraction calculate(final ExpressionState es)
  {
    if (es == null)
    {
      return null;
    }

    return getResult(es.getLeftFraction());
  }

  /**
   * Computes the result of this Operation on frac.
   * 
   * @param frac The fraction
   * @return The result
   */
  protected abstract Fraction getResult(final Fraction frac);
}