package FSM;

import math.Fraction;
import math.Operation;

/**
 * Combines the effects of multiple effects.
 * 
 * @author rileywhite
 *
 */
public class CompositeEffect implements Effect
{

  Effect effects[];

  /**
   * Combines the effects of the given Effect classes.
   * 
   * @param effects The effects to combine.
   */
  public CompositeEffect(final Effect... effects)
  {
    this.effects = effects;

  }

  @Override
  public void applyEffect(final ExpressionState es, final Fraction f, final Operation op)
  {
    for (Effect effect : effects)
    {
      effect.applyEffect(es, f, op);
    }

  }

}
