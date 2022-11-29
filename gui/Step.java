package gui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class for displaying a intermediate step of a calculation.
 * 
 * @author Richard Ernst
 */
public class Step extends JPanel
{
  String step;
  FractionGui f1;
  FractionGui f2;

  /**
   * Creates new Step Object.
   * 
   * @param step Text for step
   * @param f1 First FractionGui - null if no FractionGui is needed
   * @param f2 Second FractionGui - null if no FractionGui is needed
   */
  public Step(final String step, final FractionGui f1, final FractionGui f2)
  {
    this.step = step;
    this.f1 = f1;
    this.f2 = f2;
    setLayout();
  }

  /**
   * Adds each element, conditionally adds FractionGuis based on whether they are null or not.
   */
  private void setLayout()
  {
    FlowLayout flow = new FlowLayout();

    setLayout(flow);

    JLabel text = new JLabel(step);

    add(text);

    if (f1 != null)
    {
      add(f1);
    } else
    {
      return;
    }
    if (f2 != null)
    {
      JLabel arrow = new JLabel(" -> ");
      add(arrow);
      add(f2);
    }
  }
}
