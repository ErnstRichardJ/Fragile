package gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

import utilities.Constants;

public class KeyboardInput extends AbstractAction
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private InputDisplay id;

  KeyboardInput(InputDisplay id)
  {
    this.id = id;
    setKeyBindings();
  }

  /**
   * Creates key bindings based on buttons
   */
  private void setKeyBindings()
  {

    for (int i = 0; i <= 9; i++)
    {
      if (i == 8) 
        addEventKey(KeyEvent.VK_8);
       else
        addNumberKey(i);
    }
    
//    // handling regular chars
//    addStringKey(Constants.CLEAR);
    
    
    // handling key events from keyboard
    addEventKey(KeyEvent.VK_BACK_SPACE);
    addEventKey(KeyEvent.VK_SLASH);
    addEventKey(KeyEvent.VK_PERIOD);

  }

//  /**
//   * Handles non-number actionlisteners.
//   * @author joselynetran
//   */
//  private class handleNonNumbers extends AbstractAction
//  {
//    private static final long serialVersionUID = 1L;
//
//    String set;
//    handleNonNumbers(String s)1
//    {
//      this.set = s;
//    }
//    @Override
//    public void actionPerformed(ActionEvent e)
//    {
//      System.out.print(set);
//      if (set.equals(Constants.CLEAR))
//        id.clear();
//      
//      id.updateInputDisplay();
//    }
//  }
  
  /**
   * Handles key events actionlisteners.
   * @author joselynetran
   */
  private class handleKeyEvents extends AbstractAction
  {
    private static final long serialVersionUID = 1L;

    int ke;
    handleKeyEvents(int ke)
    {
      this.ke = ke;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
      if (ke == KeyEvent.VK_BACK_SPACE)
        id.backspace();
      if (ke == KeyEvent.VK_PERIOD)
        id.focusFoward();
      if (ke == KeyEvent.VK_8)
        id.addToDisplay(8 + "");
      
      id.updateInputDisplay();
    }
  }
  
  /**
   * Handles entering digits
   * @author joselynetran
   */
  private class addNumbers extends AbstractAction
  {
    private static final long serialVersionUID = 1L;
    int digit;

    addNumbers(int n)
    {
      this.digit = n;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
      if (digit == 104)
        id.addToDisplay(8 + "");
      else
        id.addToDisplay(digit + "");
      id.updateInputDisplay();

    }
  }
  
//  /**
//   * Adds key strokes for normal strings.
//   * @param s what key is pressed
//   */
//  private void addStringKey(String s) {
//    id.getInputMap().put(KeyStroke.getKeyStroke(s), s);
//    id.getActionMap().put(s, new handleNonNumbers(s));
//  }
  
  /**
   * Adds key strokes for key events.
   * @param ke what key event is pressed
   */
  private void addEventKey(int ke) {
    id.getInputMap().put(KeyStroke.getKeyStroke(ke, 0),
        ke);
    id.getActionMap().put(ke, new handleKeyEvents(ke));
  }
  
  /**
   * Adds numbers for number keys
   * @param n what number keys are pressed
   */
  private void addNumberKey(int n) {
    id.getInputMap().put(KeyStroke.getKeyStroke(n + ""), n);
    id.getActionMap().put(n, new addNumbers(n));
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    // TODO Auto-generated method stub
    
  }

}
