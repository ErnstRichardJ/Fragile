package gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.Timer;

import FSM.ExpressionState;
import math.Fraction;

/**
 * Creates a display for the Steps.
 * 
 * @author Riley White
 *
 */
public class StepDisplay extends SideDisplay
{

  private Timer timer;
  private SideDisplayTimerTask tt;

  /**
   * Creates stepdisplay.
   * @param w Window to build off from. 
   */
  public StepDisplay(final Window w)
  {
    super(w, true);
    this.openIncrement = 5;
    this.closeIncrement = -5;
    this.openSymb = ">";
    this.closeSymb = "<";
    
    // TODO Auto-generated constructor stub
  }

  /**
   * Sets the new list of steps to be the given list.
   * 
   * @param es The new steps to make the GUI display.
   */
  public void setSteps(final ExpressionState es)
  {
    this.getContentPane().removeAll();
    this.revalidate();
    this.repaint();
    if (es.getSteps() == null)
    {
      return;
    }
    for (Step s : es.getSteps())
    {
      add(s);
    }
    this.revalidate();
  }

}
