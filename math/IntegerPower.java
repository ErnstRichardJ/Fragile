package math;

import java.util.ArrayList;
import java.util.List;

import FSM.ExpressionState;
import gui.FractionGui;
import gui.Step;
import gui.Style;
import utilities.Constants;
import utilities.Languages;

/**
 * Class for calculating the result of raising a Fraction to an Integer power.
 * 
 * @author Richard Ernst
 */
public class IntegerPower extends BinaryOperation
{

  /**
   * Computes the result of frac1 ^ n.
   * 
   * @param frac1 The fraction
   * @param frac2 The power
   * @return Result of the operation
   */
  protected Fraction getResult(final Fraction frac1, final Fraction frac2)
      throws IllegalArgumentException
  {

    // x^0 = 1
    if (frac2.isUndefined() || frac2.improperNumerator() == 0)
    {
      return new Fraction(1, 1);
    }
    if (frac2.denominator() != 1)
    {
      throw new IllegalArgumentException("2nd operator should be an Integer");
    }

    Integer numerator = (int) Math.pow(frac1.improperNumerator(), frac2.wholes());
    Integer denominator = (int) Math.pow(frac1.denominator(), frac2.wholes());

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
    Integer p = right.wholes();

    if (ln > ld)
    {
      out.add(new Step(Languages.getConvertLeftImproper(), new FractionGui(left, style, false, true),
          new FractionGui(left, style, true, true)));
    }

    String suffix;
    switch (p) 
    {
      case 1:
        suffix = "st";
        break;
      case 2:
        suffix = "nd";
        break;
      case 3:
        suffix = "rd";
        break;
      default:
        suffix = "th";
    }

    out.add(new Step(String.format("%s %d%s %s %d ^ %d = %d", Languages.getPowerNum(), p, suffix,
        Languages.getPower(), ln, p, result.improperNumerator()), null, null));

    out.add(new Step(String.format("%s %d%s %s %d ^ %d = %d", Languages.getPowerDenom(), p, suffix,
        Languages.getPower(), ld, p, result.denominator()), null, null));
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
    return Constants.IPOWER;
  }
}

