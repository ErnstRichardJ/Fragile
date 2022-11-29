package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import FSM.ExpressionController;
import utilities.ColorScheme;
import utilities.Constants;

/**
 * This class handles the display portion of the mixed fraction calculator.
 * 
 * 
 * @author madisynconner
 *
 */
public class Display extends JPanel
{

  private static final long serialVersionUID = 1L;
  private GridBagLayout gridbag = new GridBagLayout();

  // private JTextField input;
  // private String contents;
  private ButtonPad buttonPad;

  private InputDisplay id;
  private ExpressionDisplay ed;
  private ExpressionController ec;
  private KeyboardInput key;

  private JLabel whole;
  private JLabel numerator;
  private JLabel slash;
  private JLabel denominator;

  private JPanel text;
  private JPanel output;

  private JMenu typeMen;
  private JMenu langMen;

  /**
   * Constructor displays input from the user.
   * 
   * @param input
   */
  public Display(final StepDisplay sd, final HistoryDisplay hd, final JMenu typsettingMenu,
      final JMenu languageMenu)
  {
    // setBorder(BorderFactory.createEtchedBorder());

    typeMen = typsettingMenu;
    langMen = languageMenu;
    
    whole = new JLabel("", SwingConstants.LEFT);
    numerator = new JLabel("", SwingConstants.LEFT);
    slash = new JLabel("", SwingConstants.LEFT);
    denominator = new JLabel("", SwingConstants.LEFT);

    text = new JPanel();

    id = new InputDisplay(whole, numerator, slash, denominator);
    key = new KeyboardInput(id);
    
    ed = new ExpressionDisplay();
    ec = (new ExpressionController(ed, id, sd, hd));
    buttonPad = new ButtonPad(ec, id);

    output = new JPanel();
    output.setLayout(new GridLayout(2, 1));
    output.add(ed);
    output.add(text);

    setTextLayout();
    setLayout();
    setMenuActionListeners();

  }

  public ExpressionDisplay getED()
  {
    return ed;
  }

  public InputDisplay getID()
  {
    return id;
  }

  private void setMenuActionListeners()
  {
    typeMen.addActionListener(ec);
    for (int i=0; i<typeMen.getItemCount(); ++i) {
      typeMen.getItem(i).addActionListener(ec);
    }
    for (int i=0; i<langMen.getItemCount(); ++i) {
      langMen.getItem(i).addActionListener(ec);
    }
  }

  private void setTextLayout()
  {
    text.setBackground(ColorScheme.DISPLAY);
    text.setOpaque(true);
    text.setLayout(gridbag);
    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.WEST;
    // c.weightx = 0.9;
    c.gridy = 0;
    c.insets = new Insets(0, 0, 0, 4);
    c.gridx = 0;
    text.add(whole, c);
    c.insets = new Insets(0, 0, 0, 0);
    c.gridx++;
    text.add(numerator, c);
    c.gridx++;
    text.add(slash, c);
    c.gridx++;
    text.add(denominator, c);

  }

  private void setLayout()
  {
    // GridBagLayout
    setLayout(gridbag);
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    output.setBackground(new Color(218, 228, 239));
    output.setOpaque(true);

    // Fragile logo
    Image icon = new ImageIcon(this.getClass().getResource("Logo.png")).getImage();
    Image newimg = icon.getScaledInstance(110, 40, java.awt.Image.SCALE_SMOOTH);
    JLabel logo = new JLabel();
    logo.setIcon(new ImageIcon(newimg));
    c.anchor = GridBagConstraints.NORTH;
    c.insets = new Insets(0, 15, 0, 0);
    add(logo, c);

    // display size and placement
    c.anchor = GridBagConstraints.NORTH;
    c.gridy = 1;
    c.gridwidth = 6;
    c.ipadx = 0;
    c.ipady = 60;
    c.insets = new Insets(20, 10, 0, 10);
    output.setBorder(BorderFactory.createLoweredBevelBorder());
    add(output, c);

    c.gridwidth = 2;
    c.gridx = 0;
    c.ipadx = 0;
    c.gridy = 3;
    c.insets = new Insets(25, 10, 0, 10);
    add(id, c);

    c.gridwidth = 1;
    c.gridx = 3;
    c.gridy = 3;
    c.insets = new Insets(25, 3, 0, 10);
    add(buttonPad, c);

    setBackground(ColorScheme.BACKGROUND);
  }

}
