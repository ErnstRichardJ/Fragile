package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * A timer to control the opening of side panels.
 * 
 * @author Riley White
 *
 */
public class SideDisplayTimerTask implements ActionListener
{
  private SideDisplay sd;
  private Timer t;
  private int increment;
  private boolean right;

  /**
   * Creates a TimerTask for the step display animation.
   * 
   * @param sd StepDisplay to update.
   * @param right Variable indicating if the panel is on the right.
   */
  public SideDisplayTimerTask(final SideDisplay sd, final boolean right)
  {
    this.sd = sd;
    this.right = right;
  }

  /**
   * Sets the increment.
   * 
   * @param increment How much to change the width.
   */
  public void setIncrement(final int increment)
  {
    this.increment = increment;
  }

  /**
   * Sets the timer.
   * 
   * @param timer The timer
   */
  public void setTimer(final Timer timer)
  {
    this.t = timer;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    sd.setSize(sd.getWidth() + this.increment, sd.getHeight());
    if (!right)
    {
//      sd.setLocation(sd.getX() - this.increment, sd.getY());
      sd.setLocation(sd.getMainWindow().getX() - sd.getWidth(), sd.getY());
    }
    sd.validate();
    if (sd.getWidth() >= StepDisplay.MAX_WIDTH)
    {
      this.t.stop();
      sd.setSize(StepDisplay.MAX_WIDTH, sd.getHeight());
    }
    if (sd.getWidth() <= 0)
    {
      this.t.stop();
      this.sd.setVisible(false);
      sd.setSize(0, sd.getHeight());
    }
  }

}
