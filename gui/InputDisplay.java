package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.ColorScheme;
import utilities.Constants;

/**
 * InputDisplay deals with displaying the correct character / number to display and sets up the
 * layout for softNumberPad and buttonPad.
 * 
 * @author madisynconner
 *
 */
public class InputDisplay extends JPanel implements ActionListener
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private int ptr;
  private int focus;

  private JLabel display;
  private JLabel num;
  private JLabel slash;
  private JLabel denom;
  private SoftNumberPad softnumberpad;
  private boolean fractionPresent;
  
  private String[] parts;

  /**
   * Constructor.
   * 
   * @param display
   */
  public InputDisplay(JLabel whole, JLabel num, JLabel slash, JLabel denom)
  {
    this.display = whole;
    this.num = num;
    this.slash = slash;
    this.denom = denom;
    
    parts = new String[3];
    for (int i = 0; i < 3; i++)
      parts[i] = "";

    fractionPresent = false;
    
    whole.setForeground(ColorScheme.DISPLAY_TEXT);
    num.setForeground(ColorScheme.DISPLAY_TEXT);
    slash.setForeground(ColorScheme.DISPLAY_TEXT);
    denom.setForeground(ColorScheme.DISPLAY_TEXT);

    softnumberpad = new SoftNumberPad(this);
    setLayout();
    updateInputDisplay();
  }

  /*
   * Private method to set up layout
   */
  private void setLayout()
  {
    GridBagLayout gridbag = new GridBagLayout();
    setLayout(gridbag);
    GridBagConstraints c = new GridBagConstraints();
    add(softnumberpad, c);
    setBackground(ColorScheme.BACKGROUND);
  }

  /*
   * Once expression state is displayed, reset fields for next fraction
   */
  public void reset()
  {
    for (int i = 0; i < parts.length; i++)
      parts[i] = "";
    
    fractionPresent = false;
    ptr = 0;
    display.setText(Constants.ENTER_FRACTION);
    num.setText("");
    slash.setText("");
    denom.setText("");

    focus = 0;
    display.setBorder(BorderFactory.createEmptyBorder());
    num.setBorder(BorderFactory.createEmptyBorder());
    denom.setBorder(BorderFactory.createEmptyBorder());

  }

  /*
   * Input getter for expression controller
   */
  public String getInput()
  {
    return "" + parts[0] + (parts[1].isEmpty() ? "" : " " + parts[1] + "/" + parts[2]);
  }

  /*
   * Updates input display
   */
  public void updateInputDisplay()
  {
    if (parts[0].isEmpty() && parts[1].isEmpty() && slash.getText().isEmpty())
    {
      display.setText(Constants.ENTER_FRACTION);
      display.setBorder(BorderFactory.createEmptyBorder());
    } else
    {
      setFocus();
      if (fractionPresent)
      {
        display.setText(parts[0]);
        num.setText(parts[1]);
        denom.setText(parts[2]);
      } else
        display.setText(parts[0]);
    }
  }

  /*
   * Sets focus based on where gui should border around.
   */
  private void setFocus()
  {

    switch (focus) {
    case 0:
      display.setBorder(BorderFactory.createLineBorder(Color.RED));
      num.setBorder(BorderFactory.createEmptyBorder());
      denom.setBorder(BorderFactory.createEmptyBorder());
      break;
    case 1:
      num.setBorder(BorderFactory.createLineBorder(Color.RED));
      display.setBorder(BorderFactory.createEmptyBorder());
      denom.setBorder(BorderFactory.createEmptyBorder());
      break;
    case 2:
      denom.setBorder(BorderFactory.createLineBorder(Color.RED));
      display.setBorder(BorderFactory.createEmptyBorder());
      num.setBorder(BorderFactory.createEmptyBorder());
      break;
    }

  }

  /*
   * Action listeners for SoftNumberPad.
   */
  @Override
  public void actionPerformed(ActionEvent ae)
  {
    String ac;
    ac = ae.getActionCommand();

    if (ac.equals(Constants.CLEAR))
    {
      clear();

    } else if (ac.equals(Constants.BACKSPACE))
    {
      backspace();
      
    } else if (ac.equals(Constants.FORWARDS))
    {
      focusFoward();
    } else if (ac.equals(Constants.SIGN))
    {
      signed();

    } else if (ac.equals("") && slash.getText().equals("")) // fraction button
    {
      makeFraction();
      
    } else
    {
      addToDisplay(ac);
    }

    updateInputDisplay();

  }
  
  
  public void clear() {
    
    reset();
    display.setText(Constants.ENTER_FRACTION);
    
  }
  
  public void addToDisplay(String ac) {
    insertAt(focus, ptr, ac);
  }
  
  /**
   * Pushes Focus foward.
   */
  public void focusFoward() {
    if (focus < 2)
      focus++;
  }
  
  /**
   * Helper method to backspace.
   */
  public void backspace()
  {
    if (!(focus == 0 && parts[0].isEmpty()))
    {
      if (parts[focus].isEmpty())
      {
        if (focus == 1)
        {
          this.slash.setText("");
          fractionPresent = false;
        }
        focus--;
      }
      else
      {
        delete();
      }
    }
  }
  
  public void signed() {
    if (!(parts[0].isEmpty() && parts[1].isEmpty()))
    {
      if (parts[0].charAt(0) == '-')
      {
        parts[0] = parts[0].substring(1, parts[0].length());
      } else
      {
        parts[0] = "-" + parts[0];
      }
    }
  }
  
  public void makeFraction() {
    fractionPresent = true;
    slash.setText("/");
  }
  
  public boolean fractionPresent() {
    return fractionPresent;
    
  }

  /**
   * Helper method to delete input at certain point.
   */
  private void delete()
  {
    parts[focus] = parts[focus].substring(0, parts[focus].length() - 1);
  }

  /*
   * Inserts buttons at specified index
   */
  private void insertAt(int focus, int insert, String s)
  {
    parts[focus] = parts[focus] + s;
  }


}
