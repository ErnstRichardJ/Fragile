package FSM;

import math.Fraction;
import math.Operation;

/**
 * Describes an effect applied to an expression state.
 * @author Riley White
 *
 */
public interface Effect
{
  /**
   * Apply effect to the given expression state.
   * @param es Expression state to modify.
   * @param f Fraction to use (can be null)
   * @param op Op to use (can be null).
   */
  public void applyEffect(final ExpressionState es, final Fraction f, final Operation op);

}
