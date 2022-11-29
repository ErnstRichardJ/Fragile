package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import utilities.*;

/**
 * This class handles the layout for the pop up window.
 * 
 * @author madisynconner
 *
 */
public class Window extends JFrame implements ActionListener
{

  private static final long serialVersionUID = 1L;

  private JButton rightStepButton;
  private JButton leftStepButton;
  
  private JMenuBar menuBar;
  private JMenu menu;
  private JMenu typesettingMenu;
  private JMenu languageMenu;
  private JMenuItem item;
  
  private StepDisplay sd;
  private HistoryDisplay hd;
  
  private Display display;

  private JFrame popup;
  JPanel textArea;

  /**
   * Constructor.
   * 
   * @throws IOException
   */
  public Window() throws IOException
  {
    super();
    Languages.selectedEnglish();
    setColors();
    
    
    setPreferredSize(new Dimension(650, 700));

    this.rightStepButton = new JButton(">");
    this.rightStepButton.setFocusable(false);
    this.leftStepButton = new JButton("<");
    this.leftStepButton.setFocusable(false);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setResizable(false);
    sd = new StepDisplay(this);
    this.addWindowListener(sd);
    this.rightStepButton.addActionListener(sd);
    hd = new HistoryDisplay(this);
    this.leftStepButton.addActionListener(hd);
    this.addWindowListener(hd);
    
    menuBar = new JMenuBar();
    addMenuBar();
    display = new Display(sd, hd, typesettingMenu, languageMenu);
    
    setupLayout();

  }

  /**
   * Gets the left step open button.
   * 
   * @return The step button.
   */
  public JButton getLeftStepButton()
  {
    return this.rightStepButton;
  }

  /**
   * Gets the right step open button.
   * 
   * @return The step button.
   */
  public JButton getRightStepButton()
  {
    return this.rightStepButton;
  }

  /**
   * Setup layout for the window.
   */
  private void setupLayout()
  {
    Container contentPane;
    // GridBagLayout
    GridBagLayout gridbag = new GridBagLayout();
    contentPane = getContentPane();
    contentPane.setLayout(gridbag);
    GridBagConstraints c = new GridBagConstraints();
    // input display

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.CENTER;
    c.gridx = 1;
    c.insets = new Insets(80, 0, 0, 0);
    contentPane.add(this.leftStepButton, c);

    c.gridx = 2;
    c.ipady = 30;
    c.insets = new Insets(0, 0, 0, 0);
    display.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    contentPane.add(display, c);

    // input display
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.CENTER;
    c.gridx = 3;
    c.ipady = 0;
    c.insets = new Insets(80, 0, 0, 0);

    contentPane.add(this.rightStepButton, c);
    setBackground(ColorScheme.BACKGROUND);
    // add(new FractionGui(new Fraction(234, 24, 202), Style.BAR, false));
  }

  public void addMenuBar()
  {
    // Add the menu
    setJMenuBar(menuBar);
    popup = new JFrame(Languages.getAbout());
    popup.setPreferredSize(new Dimension(300, 100));
    popup.pack();

    
    // file
    menu = new JMenu(Languages.getFile());
    menuBar.add(menu);
    item = new JMenuItem(Languages.getPrint());
    menu.add(item);
    item.addActionListener(hd);

    // type setting
    typesettingMenu = new JMenu(Languages.getTypesetting());
    menuBar.add(typesettingMenu);
    item = new JMenuItem(Languages.getSlash());
    typesettingMenu.add(item);
    item = new JMenuItem(Languages.getSolidus());
    typesettingMenu.add(item);
    item = new JMenuItem(Languages.getBar());
    typesettingMenu.add(item);

    // help
    menu = new JMenu(Languages.getHelp());
    menuBar.add(menu);
    item = new JMenuItem(Languages.getAbout());
    menu.add(item);

    // about text
    languageMenu = new JMenu(Languages.getLanguage());
    JMenuItem item2 = new JMenuItem(Constants.ENGLISH);
    languageMenu.add(item2);
    item2 = new JMenuItem(Constants.SPANISH);
    languageMenu.add(item2);
    item2 = new JMenuItem(Constants.FRENCH);
    languageMenu.add(item2);
    menu.add(languageMenu);
    
    menu.addActionListener(this);
    languageMenu.addActionListener(this);

    textArea = new JPanel();
    JLabel label = new JLabel("<html><p>"
        + Languages.getAboutText().replaceAll("\n", "<br/>").replaceAll(" ", "&nbsp;") + "</p></html>",
        SwingConstants.LEFT);

    item.addActionListener(this);
    popup.add(label);

    // subMenu for language
    item = new JMenuItem(Languages.getHelpPage());
    menu.add(item);

  }
  

  public void setColors() throws IOException
  {
    ColorScheme cs = new ColorScheme();
    cs.setColors();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals("About"))
    {
      popup.setVisible(true);
    }
    return;

  }

}