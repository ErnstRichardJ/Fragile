package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import FSM.ExpressionState;
import utilities.ColorScheme;

public class ExpressionDisplay extends JPanel
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private ExpressionState es;
  private JPanel leftFraction;
  private JPanel operator;
  private JPanel rightFraction;
  private JPanel equal;
  private JPanel result;

  /*
   * Constructor.
   */
  public ExpressionDisplay()
  {
    setBackground(ColorScheme.DISPLAY);
    setOpaque(true);
  }

  /*
   * Sets the expression state from the controller.
   */
  public void setExpression(ExpressionState es)
  {
    this.es = es;
    display();
  }

  /*
   * Sets up display of all JPanels.
   */
  private void display()
  {
    reset();
    operator = new JPanel();
    equal = new JPanel();

    if (es.getLeftFraction() != null)
    {
      leftFraction = new FractionGui(es.getLeftFraction(), es.getStyle(), false, false);

      if (es.getOperator() != null)
      {
        JLabel operatorLabel = new JLabel(es.getOperator().toString());
        operator.add(operatorLabel);
        operator.setBackground(ColorScheme.DISPLAY);
        operatorLabel.setForeground(ColorScheme.DISPLAY_TEXT);
        operator.setOpaque(true);
      }

      if (es.isComplete())
      {
        if (es.getRightFraction() != null)
          rightFraction = new FractionGui(es.getRightFraction(), es.getStyle(), false, false);
        result = new FractionGui(es.getResult(), es.getStyle(), false, false);
      }
      
      JLabel equals = new JLabel("=");
      equal.add(equals);
      equal.setBackground(ColorScheme.DISPLAY);
      equals.setForeground(ColorScheme.DISPLAY_TEXT);
      equal.setOpaque(true);

      setupLayout();
    }
  }

  /**
   * Sets up layout of expression state.
   */
  private void setupLayout()
  {
    removeAll();

    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;

    c = addToDisplay(0, 0, 0, 0, 0);
    add(leftFraction, c);

    c = addToDisplay(1, 15, 0, 0, 0);
    add(operator, c);

    if (es.isComplete())
    {
      if (rightFraction != null)
      {
        c = addToDisplay(2, 0, 0, 0, 0);
        add(rightFraction, c);
      }

      c = addToDisplay(3, 15, 0, 0, 0);
      add(equal, c);

      c = addToDisplay(4, 0, 0, 0, 0);
      add(result, c);
    }

  }

  /*
   * Helper method to add proper gridbag contraints.
   */
  private GridBagConstraints addToDisplay(final int column, final int top, final int left,
      final int bot, final int right)
  {

    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(top, left, bot, right);
    c.gridx = column;
    return c;
  }

  /*
   * Resets the expression state every complete operation.
   */
  private void reset()
  {
    removeAll();
    revalidate();
    repaint();
  }
  
  /**
   * Gets the current expressionState.
   * @return ExpressionState the current expression state.
   */
  public ExpressionState getExpressionState() 
  {
    return this.es;
  }

}
