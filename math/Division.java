package math;

import java.util.ArrayList;
import java.util.List;

import FSM.ExpressionState;
import gui.FractionGui;
import gui.Step;
import gui.Style;
import utilities.*;

/**
 * Class for calculating the result of dividing 2 Fractions.
 * 
 * @author Richard Ernst
 */
public class Division extends BinaryOperation
{

  /**
   * Computes the result of frac1 / frac2.
   * 
   * @param frac1 The left fraction
   * @param frac2 The right fraction
   * @return Result of the operation
   */
  protected Fraction getResult(final Fraction frac1, final Fraction frac2)
  {
    Integer numerator = frac1.improperNumerator() * frac2.denominator();
    Integer denominator = frac1.denominator() * frac2.improperNumerator();
    return new Fraction(numerator, denominator);
  }

  @Override
  public List<Step> getSteps(final ExpressionState es, final Style style, final boolean reduced)
  {
    List<Step> out = new ArrayList<>();

    Fraction left = es.getLeftFraction();
    Fraction right = es.getRightFraction();
    Fraction result = calculate(es);

    FractionGui resultTrue = new FractionGui(result, style, true, true);

    Integer ln = left.improperNumerator();
    Integer ld = left.denominator();
    Integer rn = right.improperNumerator();
    Integer rd = right.denominator();

    if (ln > ld)
    {
      out.add(new Step(Languages.getConvertLeftImproper(), new FractionGui(left, style, false, true),
          new FractionGui(left, style, true, true)));
    }

    if (rn > rd)
    {
      out.add(new Step(Languages.getConvertRightImproper(), new FractionGui(right, style, false, true),
          new FractionGui(right, style, true, true)));
    }

    out.add(new Step(String.format("%s %d * %d = %d", Languages.getMultiplyLeft(), ln, rd,
        result.improperNumerator()), null, null));
    out.add(new Step(
        String.format("%s %d * %d = %d", Languages.getMultiplyLeft(), ld, rn, result.denominator()),
        null, null));
    out.add(new Step(Languages.getResult(), resultTrue, null));

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
    return Constants.DIVIDE;
  }

}
