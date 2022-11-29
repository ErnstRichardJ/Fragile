package FSM;

import math.Fraction;
import math.Operation;

/**
 * Calculates result.
 * @author Riley White
 *
 */
public class Calculate implements Effect
{

  @Override
  public void applyEffect(final ExpressionState es, final Fraction f, final Operation op)
  {
    es.calculate();
  }

}
