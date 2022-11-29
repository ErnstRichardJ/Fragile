package gui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

/**
 * This is the main class that runs the program. 
 * 
 * @author madisynconner
 *
 */
public class Fragile implements Runnable
{

  /**
   * The entry point of the application (which is executed in the main thread of execution).
   *
   * @param args The command-line arguments
   */
  public static void main(final String[] args)
      throws InterruptedException, InvocationTargetException
  {
    SwingUtilities.invokeAndWait(new Fragile());
  }

  /**
   * The code that is executed in the event dispatch thread.
   */
  public void run()
  {
    Window window;
    try
    {
      window = new Window();
      window.setTitle("Calculator");
      window.setVisible(true);
    } catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
//    sd.setVisible(true);
  }

}
