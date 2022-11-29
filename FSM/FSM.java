package FSM;

import utilities.Constants;

import java.awt.Taskbar.State;

import math.Fraction;

/**
 * The finite state machine for our gui. 
 * @author Riley White
 *
 */
public class FSM
{

  private ExpressionState es;
  private State state;

  // Row order: Initial, Unary, Binary, Result, None
  // Map current state and event to next state
  private State stateTable[][] = {
      // FracUnaryOp FracBinaryOp FracEq UnaryOp BinaryOp Equals ToggleStyle Clear
      {State.Unary, State.Binary, State.None, State.None, State.None, State.None, State.Initial, State.Initial, State.Initial},
      {State.None, State.None, State.None, State.None, State.None, State.Result, State.Unary, State.Initial, State.Unary},
      {State.None, State.None, State.Result, State.None, State.None, State.None, State.Binary, State.Initial, State.Binary},
      {State.Unary, State.Binary, State.None, State.Unary, State.Binary, State.None, State.Result, State.Initial, State.Result},
      {State.None, State.None, State.None, State.None, State.None, State.None, State.None, State.Initial, State.None}};

  private Effect addFrac = new AddFrac();
  private Effect addOp = new AddOp();
  private Effect addFracOp = new CompositeEffect(addFrac, addOp);
  private Effect calculate = new Calculate();
  private Effect clear = new Reset();
  private Effect addFracEq = new CompositeEffect(addFrac, calculate);
  private Effect toggleReduce = new ToggleReduce();

  // Map current state and event to effect
  private Effect effectTable[][] = {
      // FracUnaryOp FracBinaryOp FracEq UnaryOp BinaryOp Equals ToggleStyle Clear
      {addFracOp, addFracOp, null, null, null, null, toggleReduce, clear, null}, // Initial
      {null, null, null, null, null, calculate, toggleReduce, clear, null}, // Unary
      {null, null, addFracEq, null, null, null, toggleReduce, clear, null }, // Binary
      {addOp, addOp, null, addOp, addOp, null, toggleReduce, clear, null }, // Result
      {null, null, null, null, null, null, toggleReduce, clear, null } // None
  };
  
  /**
   * Creates a few fsm.
   */
  public FSM() 
  {
    this.state = State.Initial;
    this.es = new ExpressionState();
  }
  
  /**
   * Handles the event with the given input and opstr.
   * @param event The event that happened.
   * @param inputText The current input text, may be empty. 
   * @param opstr The current op string, may be invalid. 
   * @return If event was valid.
   */
  public boolean handleEvent(final Event event, final String inputText, final String opstr) 
  {

    State nextState = stateTable[state.getState()][event.getEvent()];

    if (nextState.equals(State.None))
      return false;

    Effect effect = effectTable[state.getState()][event.getEvent()];
    if (effect != null) {
      effect.applyEffect(es, Fraction.parseString(inputText), Constants.OP_MAP.get(opstr));
    }
    this.state = nextState;
    return true;
    
  }
  
  /**
   * Gets the state.
   * @return The state.
   */
  public State getState() 
  {
    return this.state;
  }
  
  /**
   * Gets the ExpressionState.
   * @return The expressionState
   */
  public ExpressionState getExpressionState() 
  {
    return this.es;
  }

  /**
   * Describes possible States.
   * @author Riley White
   *
   */
  public enum State
  {
    Initial(0), Unary(1), Binary(2), Result(3), None(4);

    int state;

    State(final int state)
    {
      this.state = state;
    }

    int getState()
    {
      return this.state;
    }
  }

  /**
   * Describes Events that can happen with our FSM.
   * @author Riley White
   *
   */
  public enum Event
  {
    FracUnaryOp(0), 
    FracBinaryOp(1), 
    FractionEq(2), 
    UnaryOp(3), 
    BinaryOp(4), 
    Equals(5), 
    ToggleReduce(6),
    Reset(7),
    None(8);

    int event;

    Event(final int event)
    {
      this.event = event;
    }

    int getEvent()
    {
      return this.event;
    }

  }

}
