package FSM;

import math.Fraction;
import math.Operation;

/**
 * Adds fraction to expression state. 
 * @author Riley White
 *
 */
public class AddFrac implements Effect
{

  @Override
  public void applyEffect(final ExpressionState es, final Fraction f, final Operation op)
  {
    es.addFractionIfPossible(f);
  }

}
