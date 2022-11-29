package FSM;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;

import FSM.FSM.Event;
import FSM.FSM.State;
import gui.Display;
import gui.ExpressionDisplay;
import gui.HistoryDisplay;
import gui.InputDisplay;
import gui.StepDisplay;
import gui.Style;
import math.BinaryOperation;
import math.Fraction;
import math.Operation;
import math.UnaryOperation;
import utilities.Constants;
import utilities.Languages;

/**
 * Controller Class which implements a finite state machine to control GUI access.
 * 
 * @author Riley White
 *
 */
public class ExpressionController implements ActionListener
{
  private InputDisplay input;
  private ExpressionDisplay display;
  private FSM fsm;
  private StepDisplay sd;
  private HistoryDisplay hd;

  /**
   * Creates a controller with a given display and input field.
   * 
   * @param display The display to update.
   * @param input The input to take input from.
   */
  public ExpressionController(final ExpressionDisplay display, final InputDisplay input,
      final StepDisplay sd, final HistoryDisplay hd)
  {
    this.display = display;
    this.input = input;
    this.sd = sd;
    this.hd = hd;
    fsm = new FSM();
  }
  
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String ac = e.getActionCommand();
    String inputText = input.getInput();
    Event event = getEvent(ac, inputText);
    // Don't reset input if we are in result before state change.
    // So someone can type number, op and number will not be erased
    // on op button press.
    if (fsm.getState() != State.Result)
      input.reset();

    fsm.handleEvent(event, inputText, ac);

    ExpressionState es = fsm.getExpressionState();
    
    if (ac.equals(Languages.getSlash())) {
      es.setStyle(Style.SLASH);
    }
    if (ac.equals(Languages.getSolidus())) {
      es.setStyle(Style.SOLIDUS);
    }
    if (ac.equals(Languages.getBar())) {
      es.setStyle(Style.BAR);
    }
    
    if (ac.equals(Constants.ENGLISH)) {
      Languages.selectedEnglish();
    }
    if (ac.equals(Constants.SPANISH)) {
      Languages.selectedSpanish();
    }
    if (ac.equals(Constants.FRENCH)) {
      Languages.selectedFrench();
    }
    
    // Update display
    this.display.setExpression(es);
    this.sd.setSteps(es);
    this.hd.addExpressionState(es);
  }

  /**
   * Parses event string and input to retrn an Event enum.
   * 
   * @param event The string event.
   * @param input The input from the GUI.
   * @return The correct event.
   */
  public static Event getEvent(final String event, final String input)
  {
    Event evt = Event.None;
    Operation op = Constants.OP_MAP.get(event);

    if (event.equals(Constants.RESET))
    {
      evt = Event.Reset;
    }else if (event.equals(Constants.REDUCED_FORM))
    {
      evt = Event.ToggleReduce;
    } else if (event.equals(Constants.EQUALS))
    {
      if (Fraction.parseString(input) != null)
      {
        evt = Event.FractionEq;
      } else
      {
        evt = Event.Equals;
      }
    } else if (op instanceof UnaryOperation)
    {
      if (input.strip().length() == 0)
        evt = Event.UnaryOp;
      else
        evt = Event.FracUnaryOp;
    } else if (op instanceof BinaryOperation)
    {
      if (input.strip().length() == 0)
        evt = Event.BinaryOp;
      else
        evt = Event.FracBinaryOp;
    }
    return evt;
  }

}
