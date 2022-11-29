package FSM;

import java.util.List;

import gui.Step;
import gui.Style;
import math.Fraction;
import math.Operation;

/**
 * Class to keep track of the Expression values.
 * 
 * @author Riley White
 */
public class ExpressionState
{
  private Fraction left;
  private Fraction right;
  private Operation operator;
  private Fraction result;
  private boolean isComplete;
  private Style style;
  private boolean reduce;

  /**
   * Default Expression state.
   */
  public ExpressionState()
  {
    style = Style.SLASH;
  }

  /**
   * Deep copy constructor.
   * 
   * @param es ExpressionState to make deep copy with.
   */
  public ExpressionState(ExpressionState es)
  {
    this.left = es.getLeftFraction();
    this.right = es.getRightFraction();
    this.operator = es.getOperator();
    this.result = es.getResult();
    this.isComplete = es.isComplete();
    this.style = es.getStyle();
    this.reduce = es.getReduce();
  }

  /**
   * Adds a fraction to the expression state if possible.
   * 
   * @param f The fraction to add.
   * @return If the operation was successful.
   */
  public boolean addFractionIfPossible(final Fraction f)
  {
    if (left == null)
    {
      left = f;
      return true;
    }
    if (right == null)
    {
      right = f;
      return true;
    }
    return false;
  }

  /**
   * Adds an operator if operator is not already defined.
   * 
   * @param op The operator to add.
   * @return If the operator was added.
   */
  public boolean addOperatorIfPossible(final Operation op)
  {
    if (this.operator == null)
    {
      // TODO: Make operator an Operation
      this.operator = op;
      return true;
    }
    return false;
  }

  /**
   * Gets the left fraction of the expression.
   * 
   * @return The left fraction
   */
  public Fraction getLeftFraction()
  {
    return left;
  }

  /**
   * Gets the right fraction of the expression.
   * 
   * @return The right fraction
   */
  public Fraction getRightFraction()
  {
    return right;
  }

  /**
   * Gets the operator of the expression.
   * 
   * @return The operator
   */
  public Operation getOperator()
  {
    return operator;
  }

  /**
   * Gets current reduce state.
   * 
   * @return Reduce state.
   */
  public boolean getReduce()
  {
    return reduce;
  }

  /**
   * Toggles reduce state.
   */
  public void toggleReduce()
  {
    this.reduce = !reduce;
  }

  /**
   * Gets the steps with the given style and reduce state.
   * 
   * @return List of steps.
   */
  public List<Step> getSteps()
  {
    if (!this.isComplete)
    {
      return null;
    }
    return this.operator.getSteps(this, this.style, reduce);
  }

  /**
   * Toggles style to the next in the sequence.
   */
  public void setStyle(final Style style)
  {
    this.style = style;
  }

  /**
   * Gets the style.
   * 
   * @return The style.
   */
  public Style getStyle()
  {
    return style;
  }

  /**
   * Checks if the expression is complete which is defined by the left, right, and operator values
   * not being null.
   * 
   * @return If expresion is complete.
   */
  public boolean isComplete()
  {
    return this.isComplete;
  }

  /**
   * Marks this expression state as complete.
   */
  public void setComplete()
  {
    this.isComplete = true;
  }

  /**
   * Calculates result if possible.
   * 
   */
  public void calculate()
  {

    this.result = this.operator.calculate(this);
    if (result == null)
    {
      return;
    }
    this.result = result.isUndefined() ? new Fraction(0, 1) : result;
    setComplete();
  }

  /**
   * Gets the result of the expression, if valid. If the expression is not complete then it will
   * return null. Can be called multiple times.
   * 
   * @return The result of the operation or null if invalid.
   */
  public Fraction getResult()
  {
    if (result == null)
    {
      return null;
    }
    return reduce ? this.result.reduce() : this.result;
  }

  /**
   * Resets the Expression state so that the result is the new left fraction.
   */
  public void reset()
  {
    left = this.result;
    right = null;
    operator = null;
    result = null;
    this.isComplete = false;
  }

  /**
   * Sets expression values to null.
   */
  public void clear()
  {
    left = null;
    right = null;
    operator = null;
    result = null;
    this.isComplete = false;
  }

}
