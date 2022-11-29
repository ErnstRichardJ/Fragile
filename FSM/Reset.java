package FSM;

import math.Fraction;
import math.Operation;

/**
 * Clears the expression state to empty.
 * @author Riley White
 *
 */
public class Reset implements Effect
{

  @Override
  public void applyEffect(final ExpressionState es, final Fraction f, final Operation op)
  {
    es.clear();
  }

}
