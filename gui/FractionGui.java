package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import math.Fraction;
import utilities.ColorScheme;
import utilities.Constants;

public class FractionGui extends JPanel implements ActionListener
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private Fraction input;
  private boolean improper;
  
  private Integer whole;
  private Integer numerator;
  private Integer denominator;

  private JLabel wholeNumber;
  private JLabel num;
  private JLabel denom;

  /**
   * FractionGUI constructor.
   * 
   * 
   * @param input Fraction to display
   * @param style Style enum (Slash, solidus, bar)
   * @param improper whether or not to display it in improper form.
   */
  public FractionGui(Fraction input, Style style, boolean improper, boolean sideDisplay)
  {
    if (input != null)
    {
      this.input = input;
      this.improper = improper;
      this.whole = input.wholes();
      this.numerator = improper ? input.improperNumerator() : input.mixedNumerator();
      this.denominator = input.denominator();

      String wholeString = this.whole.toString();
      
      if (style.equals(Style.BAR))
      {
        if (improper || (this.whole == 0 && this.numerator != 0))
          wholeString = " ";
        else
          wholeString = this.whole.toString();
      }
      else
      {
        wholeString = input.toString(style.equals(Style.SOLIDUS), improper);
      }
      
      wholeNumber = new JLabel(wholeString);
      wholeNumber.setForeground(ColorScheme.DISPLAY_TEXT);
      
      num = new JLabel(numerator == 0 || !style.equals(Style.BAR) ? "" : numerator + "",
              SwingConstants.CENTER);
      num.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
      num.setFont(new Font("DejaVu Sans", Font.PLAIN, 10));
      num.setForeground(ColorScheme.DISPLAY_TEXT);
      denom = new JLabel(numerator == 0 || !style.equals(Style.BAR) ? "" : denominator + "",
              SwingConstants.CENTER);
      denom.setFont(new Font("DejaVu Sans", Font.PLAIN, 10));
      denom.setForeground(ColorScheme.DISPLAY_TEXT);
      if (sideDisplay) {
        // for fractions on the side display
        // todo set background color for side panels
      } else {
        // for fractions on the dispaly
        setBackground(ColorScheme.DISPLAY); 
      }
      
      JPopupMenu copyMenu = new JPopupMenu();
      JMenuItem copy = new JMenuItem("Copy");
      copy.addActionListener(this);
      copyMenu.add(copy);
      this.setComponentPopupMenu(copyMenu);
     
      setLayout();
      
      
    }

  }

  private void setLayout()
  {
    GridBagLayout gridbag = new GridBagLayout();
    setLayout(gridbag);
    GridBagConstraints c = new GridBagConstraints();
    setOpaque(true);
    
    // soft pad size and placement test
    gridbag.setConstraints(wholeNumber, c);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.CENTER;
    c.gridwidth = 1;
    c.gridy = 1;
    c.insets = new Insets(18, 0, 0, 5);
    add(wholeNumber, c);

    gridbag.setConstraints(num, c);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = 1;
    c.gridx = 2;
    c.gridy = 1;
    c.insets = new Insets(0, 0, 0, 0);
    add(num, c);

    gridbag.setConstraints(denom, c);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = 1;
    c.gridx = 2;
    c.gridy = 2;
    c.insets = new Insets(-10, 0, 0, 0);
    add(denom, c);

  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getActionCommand().equals("Copy"))
    {
      StringSelection fracString = new StringSelection(input.toString(false, improper));
      Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
      clipboard.setContents(fracString, fracString);
    }
  }

}