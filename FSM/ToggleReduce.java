package FSM;

import math.Fraction;
import math.Operation;

public class ToggleReduce implements Effect
{

  @Override
  public void applyEffect(ExpressionState es, Fraction f, Operation op)
  {
    es.toggleReduce();
  }

}
