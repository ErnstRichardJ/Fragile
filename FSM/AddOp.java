package FSM;

import math.Fraction;
import math.Operation;

/**
 * Adds operator to expression state. 
 * @author Riley White
 *
 */
public class AddOp implements Effect
{

  @Override
  public void applyEffect(final ExpressionState es, final Fraction f, final Operation op)
  {
    // This if could be a separate effect, but would incrase complexity too much currently.
    if(es.isComplete()) 
    {
      es.reset();
    }
    es.addOperatorIfPossible(op);
    
  }

}
