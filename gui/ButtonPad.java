package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

import java.awt.Color;

import utilities.ColorScheme;
import utilities.Constants;

/**
 * Helper class that creates buttons.
 * 
 * @author madisyn conner
 * @version 10/29/21
 * 
 */
public class ButtonPad extends JPanel
{
  KeyStroke stroke = null;
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private static final Font BUTTON_FONT = new Font("DejaVu Sans", Font.BOLD, 15);

  private ActionListener listener;
  private ActionListener inputListen;

  /**
   * Default constructor.
   * 
   * @param listener
   */
  public ButtonPad(final ActionListener listener, final ActionListener inputListen)
  {
    super();
    this.listener = listener;
    this.inputListen = inputListen;
    setupLayout();

  }

  /**
   * Creates a new button.
   * 
   * @param text
   */
  private void addButton(final String text, final GridBagConstraints c)
  {
    JButton button = null;
    button = new JButton(text);

    setButtonListener(button, stroke);

    if (text.equals(Constants.ADD))
    {
      setAddKeyBinding(button);
    }

    if (text.equals(Constants.SUBTRACT))
    {
      setSubtractKeyBinding(button);
    }

    if (text.equals(Constants.MULTIPLY))
    {
      setMultiplyKeyBinding(button);
    }

    if (text.equals(Constants.DIVIDE))
    {
      setDivideKeyBinding(button);
    }

    if (text.equals(Constants.EQUALS))
    {
      setEqualsKeyBinding(button);
    }

    button.setFont(BUTTON_FONT);

    button.setForeground(ColorScheme.BUTTONPAD_TEXT);
    button.setBackground(ColorScheme.BUTTON_COLOR);
    if (text.equals("→"))
      button.addActionListener(inputListen);
    else
    {
      button.addActionListener(listener);
    }

    add(button, c);
    button.setFocusable(false);

  }

  /**
   * Sets up layout of the buttons and adds buttons to the display window.
   */
  private void setupLayout()
  {

    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(3, 3, 3, 3);
    c.ipadx = 10;
    c.ipady = 11;
    addButton(Constants.ADD, c);
    addButton(Constants.RESET, c);
    c.gridy = 1;
    addButton(Constants.SUBTRACT, c);
    addButton(Constants.INVERSE, c);
    c.gridy++;
    addButton(Constants.MULTIPLY, c);
    addButton("→", c);
    // add(new JLabel(), c);
    c.gridy++;
    addButton(Constants.DIVIDE, c);
    addButton(Constants.MEDIANT, c);
    c.gridy++;
    addButton(Constants.EQUALS, c);
    addButton(Constants.FAREY_NUMBER, c);
    c.gridy++;
    addButton(Constants.REDUCED_FORM, c);
    addButton(Constants.POW, c);
    setBackground(ColorScheme.BACKGROUND);

  }
  
  public Action getAction(JButton b) {
    
    return new AbstractAction() {

      @Override
      public void actionPerformed(ActionEvent e)
      {
        b.doClick();
      }
      
    };
  }

  AbstractAction aa = new AbstractAction()
  {
    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(ActionEvent ae)
    {
      System.out.println("Here");
    }

  };

  private void setButtonListener(JButton btn, KeyStroke ks)
  {

    btn.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, btn.getText());
    btn.getActionMap().put(btn.getText(), aa);

  }

  public void setAddKeyBinding(JButton add)
  {

    add.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.SHIFT_DOWN_MASK), "add");


    Action action = getAction(add);
    add.getActionMap().put("add", action);

  }

  public void setSubtractKeyBinding(JButton subtract)
  {
    subtract.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), "subtract");

    Action action = getAction(subtract);
    subtract.getActionMap().put("subtract", action);

  }

  public void setMultiplyKeyBinding(JButton multiply)
  {
    multiply.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.SHIFT_DOWN_MASK), Constants.MULTIPLY);

    Action action = getAction(multiply);
    multiply.getActionMap().put( Constants.MULTIPLY, action);

  }

  public void setDivideKeyBinding(JButton divide)
  {
    divide.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke('/'), "divide");

    Action action = getAction(divide);
    divide.getActionMap().put("divide", action);

  }

  public void setEqualsKeyBinding(JButton equals)
  {
    equals.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), Constants.EQUALS);

    Action action = getAction(equals);
    equals.getActionMap().put(Constants.EQUALS, action);

  }

}
