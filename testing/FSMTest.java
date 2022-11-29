package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import FSM.FSM;
import FSM.FSM.Event;
import FSM.FSM.State;
import math.Fraction;
import utilities.Constants;

class FSMTest
{


  @Test
  void testValidStateChangesBeginUnaryWEffects()
  {
    FSM fsm = new FSM();
    assertEquals(State.Initial, fsm.getState());

    assertTrue(fsm.handleEvent(Event.FracUnaryOp, "1/2", Constants.INVERSE));
    assertEquals(State.Unary, fsm.getState());

    assertTrue(fsm.handleEvent(Event.Equals, null, null));
    assertEquals(State.Result, fsm.getState());
    assertEquals(new Fraction(2, 1), fsm.getExpressionState().getResult());

    assertTrue(fsm.handleEvent(Event.BinaryOp, null, Constants.ADD));
    assertEquals(State.Binary, fsm.getState());

    assertTrue(fsm.handleEvent(Event.FractionEq, "1/2", null));
    assertEquals(State.Result, fsm.getState());
    assertEquals(new Fraction(5, 2), fsm.getExpressionState().getResult());

    assertTrue(fsm.handleEvent(Event.UnaryOp, null, Constants.INVERSE));
    assertEquals(State.Unary, fsm.getState());

    assertTrue(fsm.handleEvent(Event.Equals, null, null));
    assertEquals(State.Result, fsm.getState());
    assertEquals(new Fraction(2, 5), fsm.getExpressionState().getResult());

    assertTrue(fsm.handleEvent(Event.ToggleReduce, null, null));
    assertEquals(State.Result, fsm.getState());
    assertEquals(new Fraction(2, 5), fsm.getExpressionState().getResult());

    assertTrue(fsm.handleEvent(Event.Reset, null, null));
    assertEquals(State.Initial, fsm.getState());
    assertEquals(null, fsm.getExpressionState().getResult());

  }

  @Test
  void testValidStateChangesBeginClear() {
    FSM fsm = new FSM();
    assertEquals(State.Initial, fsm.getState());

    assertTrue(fsm.handleEvent(Event.Reset, null, null));
    assertEquals(State.Initial, fsm.getState());
    
  }

  @Test
  void testInvalidStateChanges()
  {
    FSM fsm = new FSM();
    assertEquals(State.Initial, fsm.getState());

    assertFalse(fsm.handleEvent(Event.Equals, null, null));
    assertEquals(State.Initial, fsm.getState());

    assertFalse(fsm.handleEvent(Event.FractionEq, null, null));
    assertEquals(State.Initial, fsm.getState());

  }

  @Test
  void testValidStateChangesWEffect()
  {
    FSM fsm = new FSM();
    assertEquals(State.Initial, fsm.getState());

    assertTrue(fsm.handleEvent(Event.FracBinaryOp, "1", Constants.ADD));
    assertEquals(State.Binary, fsm.getState());

    assertTrue(fsm.handleEvent(Event.FractionEq, "1", null));
    assertEquals(State.Result, fsm.getState());
    assertEquals(new Fraction(2, 0, 1), fsm.getExpressionState().getResult());

    assertTrue(fsm.handleEvent(Event.BinaryOp, null, Constants.MULTIPLY));
    assertEquals(State.Binary, fsm.getState());

    assertTrue(fsm.handleEvent(Event.FractionEq, "1", null));
    assertEquals(State.Result, fsm.getState());
    assertEquals(new Fraction(2, 0, 1), fsm.getExpressionState().getResult());

    assertTrue(fsm.handleEvent(Event.UnaryOp, null, Constants.INVERSE));
    assertEquals(State.Unary, fsm.getState());

    assertTrue(fsm.handleEvent(Event.Equals, null, null));
    assertEquals(State.Result, fsm.getState());
    assertEquals(new Fraction(1, 2), fsm.getExpressionState().getResult());

    assertTrue(fsm.handleEvent(Event.Reset, null, null));
    assertEquals(State.Initial, fsm.getState());
    assertEquals(null, fsm.getExpressionState().getResult());

  }

}
