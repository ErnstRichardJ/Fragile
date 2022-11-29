package math;

import java.util.ArrayList;
import java.util.List;

import FSM.ExpressionState;
import gui.FractionGui;
import gui.Step;
import gui.Style;
import utilities.*;

/**
 * Class for calculating the inverse of a Fraction.
 * 
 * @author Richard Ernst
 */
public class Inverse extends UnaryOperation
{

  /**
   * Computes the inverse of frac.
   * 
   * @param frac The fraction
   * @return Result of the operation
   */
  protected Fraction getResult(final Fraction frac)
  {
    return new Fraction(frac.denominator(), frac.improperNumerator());
  }

  @Override
  public List<Step> getSteps(final ExpressionState es, final Style style, final boolean reduced)
  {
    List<Step> out = new ArrayList<>();

    Fraction start = es.getLeftFraction();
    Fraction result = calculate(es);

    FractionGui startFalse = new FractionGui(start, style, false, true);
    FractionGui startTrue = new FractionGui(start, style, true, true);
    FractionGui resultTrue = new FractionGui(result, style, true, true);

    if (start.improperNumerator() > start.denominator())
    {
      out.add(new Step(Languages.getConvertImproper(), startFalse, startTrue));
    }
    
    out.add(
        new Step(Languages.getReplace(), startTrue, resultTrue));

    if (reduced && !result.equals(result.reduce()))
    {
      result = result.reduce();
      out.add(new Step(Languages.getReduce(), resultTrue, new FractionGui(result, style, true, true)));
    }

    if (result.wholes() > 0 && result.mixedNumerator() != 0)
    {
      out.add(new Step(Languages.getConvertMixed(), new FractionGui(result, style, true, true),
          new FractionGui(result, style, false, true)));
    }
    return out;
  }

  @Override
  public String toString()
  {
    return Constants.INVERSE;
  }
}
