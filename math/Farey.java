package math;

import java.util.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import FSM.ExpressionState;
import gui.*;
import utilities.Constants;
import utilities.Languages;

/**
 * Class for calculating the next number in a farey sequence given 2 Fractions.
 * 
 * @author Richard Ernst
 */
public class Farey extends BinaryOperation
{

  private final JDialog dialog;

  private boolean testing;

  /**
   * Creates new Farey operation.
   */
  public Farey()
  {
    dialog = new JDialog();
    dialog.setAlwaysOnTop(true);
    testing = false;
  }
  
  /**
   * Creates new Farey operation for testing purposes, no error pop-ups will appear.
   * 
   * @param testing
   */
  public Farey(final boolean testing)
  {
    this();
    this.testing = true;
  }

  /**
   * Computes the next farey number.
   * 
   * @param frac1 The left fraction
   * @param frac2 The right fraction
   * @return Result of the operation
   */
  public Fraction getResult(final Fraction frac1, final Fraction frac2)
  {
    Integer ln = frac1.improperNumerator();
    Integer rn = frac2.improperNumerator();
    Integer ld = frac1.denominator();
    Integer rd = frac2.denominator();

    // Checks if Fractions are in the bounds of a Farey sequence [0, 1)
    if (frac1.wholes() < 0 || frac2.wholes() < 0 || frac1.wholes() >= 1 || frac2.wholes() >= 1)
    {
      if (!testing)
      {
        JOptionPane.showMessageDialog(dialog, Languages.getFareyErrorOOB(), Languages.getFareyErrorBar(),
            JOptionPane.INFORMATION_MESSAGE);
      }

      return null;
    }

    // Checks if Fractions are farey neighbours
    if (ld * rn - ln * rd != 1)
    {
      if (!testing)
      {
        JOptionPane.showMessageDialog(dialog, Languages.getFareyErrorNeighbours(),
            Languages.getFareyErrorBar(), JOptionPane.INFORMATION_MESSAGE);
      }
      return null;
    }

    Integer n = Math.max(ld, rd);
    Integer numerator = Math.floorDiv(ld + n, rd) * rn - ln;
    Integer denominator = Math.floorDiv(ld + n, rd) * rd - ld;
    return new Fraction(numerator, denominator);
  }

  @Override
  public List<Step> getSteps(final ExpressionState es, final Style style, final boolean reduced)
  {
    List<Step> out = new ArrayList<>();

    Fraction left = es.getLeftFraction();
    Fraction right = es.getRightFraction();
    Fraction result = calculate(es);

    Integer ln = left.improperNumerator();
    Integer rn = right.improperNumerator();
    Integer ld = left.denominator();
    Integer rd = right.denominator();

    Integer n = Math.max(ld, rd);

    out.add(new Step(Languages.getCheckIfFarey(), null, null));
    out.add(
        new Step(String.format("%s %d + %d = %d", Languages.getAddMax(), ld, n, ld + n), null, null));
    out.add(new Step(String.format("%s, %d / %d = %d", Languages.getDivideDenom(), ld + n, rd,
        Math.floorDiv(ld + n, rd)), null, null));
    out.add(new Step(String.format("%s %d * %d = %d", Languages.getMultResultRight(),
        Math.floorDiv(ld + n, rd), rn, Math.floorDiv(ld + n, rd) * rn), null, null));
    out.add(new Step(String.format("%s %d - %d = %d", Languages.getSubtractNum(),
        Math.floorDiv(ld + n, rd) * rn, ln, result.improperNumerator()), null, null));
    out.add(new Step(String.format("%s %d * %d = %d", Languages.getMult3rdStep(),
        Math.floorDiv(ld + n, rd), rd, Math.floorDiv(ld + n, rd) * rd), null, null));
    out.add(new Step(String.format("%s %d - %d = %d", Languages.getSubtractDenom(),
        Math.floorDiv(ld + n, rd) * rd, ld, result.denominator()), null, null));
    out.add(new Step(Languages.getFareyResult(), new FractionGui(result, style, false, true), null));

    return out;
  }
  
  @Override
  public String toString()
  {
    return Constants.FAREY_NUMBER;
  }
}
