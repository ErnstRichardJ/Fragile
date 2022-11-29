package math;

import java.util.List;

import FSM.ExpressionState;

import gui.Step;
import gui.Style;

public interface Operation
{
  /**
   * Calculates the result of performing this Operation on es.
   * 
   * @param es ExpressionState to perform the operation on
   * @return null when es is null, otherwise the result
   */
  public Fraction calculate(final ExpressionState es);

  /**
   * Generate a list of steps for performing this Operation on es.
   * 
   * @param es ExpressionState to generate steps for
   * @param style Typsetting style used for displaying Fractions
   * @param reduced True if result should be in reduced form, false for irreduced
   * @return List of Strings containing the steps
   */
  public List<Step> getSteps(final ExpressionState es, final Style style, final boolean reduced);
}
