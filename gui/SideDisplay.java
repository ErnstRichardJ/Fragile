package gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JWindow;
import javax.swing.Timer;

public class SideDisplay extends JWindow
    implements ComponentListener, ActionListener, WindowListener
{
  public static final int MAX_WIDTH = 600;

  private Timer timer;
  private SideDisplayTimerTask tt;
  protected int openIncrement;
  protected int closeIncrement;
  private boolean right;
  protected String openSymb;
  protected String closeSymb;
  private Window window;

  /**
   * Creates a new StepDisplay.
   */
  public SideDisplay(final Window w, final boolean right)
  {
    this.right = right;
    this.window = w;

    Point p = w.getLocation();
    if (right)
    {
      p.setLocation(p.getX() + w.getWidth(), p.getY() + w.getHeight() / 3 - 50);
    } else
    { // Left side
      p.setLocation(p.getX(), p.getY() + w.getHeight() / 3 - 50);
    }
    this.setLocation(p);

    this.setSize(0, (int) (w.getHeight() - this.getY() - 4));
    w.addComponentListener(this);

    this.tt = new SideDisplayTimerTask(this, this.right);
    this.timer = new Timer(10, tt);
    this.tt.setTimer(timer);
    this.setLayout(new GridLayout(0, 1));
  }
  
  public Window getMainWindow() {
    return this.window;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String ac = e.getActionCommand();
    JButton b = (JButton) e.getSource();
    if (!timer.isRunning() && ac.equals(this.openSymb))
    {
      tt.setIncrement(openIncrement);
      this.setVisible(true);
      b.setActionCommand(this.closeSymb);
      b.setText(this.closeSymb);
      timer.start();
    } else if (!timer.isRunning() && ac.equals(this.closeSymb))
    {
      tt.setIncrement(closeIncrement);
      b.setActionCommand(this.openSymb);
      b.setText(this.openSymb);
      timer.start();
    }
  }

  @Override
  public void windowOpened(WindowEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void windowClosing(WindowEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void windowClosed(WindowEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void windowIconified(WindowEvent e)
  {
    this.setVisible(false);

  }

  @Override
  public void windowDeiconified(WindowEvent e)
  {
    this.setVisible(true);

  }

  @Override
  public void windowActivated(WindowEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void windowDeactivated(WindowEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void componentResized(ComponentEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void componentMoved(ComponentEvent e)
  {
    Component c = e.getComponent();
    Point p = c.getLocation();
    if(right) 
      p.setLocation(p.getX() + c.getWidth(), p.getY() + c.getHeight() / 3 - 50);
    else
      p.setLocation(p.getX() - this.getWidth(), p.getY() + c.getHeight() / 3 - 50);
    this.setLocation(p);
  }

  @Override
  public void componentShown(ComponentEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void componentHidden(ComponentEvent e)
  {
    // TODO Auto-generated method stub

  }

}
