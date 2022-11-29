package gui;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

import utilities.ColorScheme;
import utilities.Constants;

/**
 * Helper class that creates buttons.
 * 
 * @author madisyn conner
 * @version 10/29/21
 * 
 */
public class SoftNumberPad extends JPanel
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private static final Font BUTTON_FONT = new Font("DejaVu Sans", Font.PLAIN, 15);

  private ActionListener listener;

  /**
   * Default constructor.
   * 
   * @param listener
   */
  public SoftNumberPad(final ActionListener listener)
  {
    super();
    this.listener = listener;
    setupLayout();

  }

  /**
   * Creates a new button.
   * 
   * @param text
   */
  private void addButton(final String text, final GridBagConstraints c)
  {
    JButton button;
    button = new JButton(text);
    button.setFont(BUTTON_FONT);

    button.setForeground(ColorScheme.NUMBERPAD_TEXT);
    button.setBackground(ColorScheme.BUTTON_COLOR);
    if (text.equals("←") || text.equals(Constants.CLEAR) || text.equals("±"))
    {
      button.setForeground(ColorScheme.INPUT_EDITOR_TEXT);
      button.setFont(new Font("DejaVu Sans", Font.BOLD, 15));
    }
    button.addActionListener(listener);
    button.setFocusable(false);
    add(button, c);

  }

  /**
   * Sets up layout of the buttons and adds buttons to the display window.
   */
  private void setupLayout()
  {
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipadx = 10;
    c.ipady = 20;
    c.insets = new Insets(3, 3, 3, 3);
    addButton("±", c);
    addButton(Constants.CLEAR, c);
    addButton("←", c);

    c.gridy = 1;
    for (int i = 1; i <= 9; i++)
    {
      if (i == 4 || i == 7)
      {
        c.gridy++;
      }
      addButton(String.format("%d", i), c);
    }
    
    c.gridy++;
    c.gridwidth = 2; // 2 columns wide
    addButton("0", c);
    c.fill = 0;
    c.gridwidth = 1; // 2 columns wide
    Image icon = new ImageIcon(this.getClass().getResource("/bar.png")).getImage();
    Image newimg = icon.getScaledInstance( 30, 18,  java.awt.Image.SCALE_SMOOTH ) ; 
    JButton fraction = new JButton();
    fraction.setIcon(new ImageIcon(newimg));
    fraction.addActionListener(listener);
    fraction.setFocusable(false);
    fraction.setBackground(ColorScheme.BUTTON_COLOR);
    add(fraction, c);
    setBackground(ColorScheme.BACKGROUND);

  }

}
