package gui;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import javax.swing.*;

import FSM.ExpressionState;
import utilities.ColorScheme;

/**
 * Creates a display for the Steps.
 * 
 * @author Riley White
 *
 */
public class HistoryDisplay extends SideDisplay implements ActionListener, Printable
{

  private Timer timer;
  private SideDisplayTimerTask tt;
  private List<ExpressionDisplay> print;
  private JList<ExpressionDisplay> history;
  private DefaultListModel<ExpressionDisplay> listModel;
  private JScrollPane sp;

  /**
   * Create history display.
   * @param w The window.
   */
  public HistoryDisplay(final Window w)
  {
    super(w, false);
    this.openIncrement = 5;
    this.closeIncrement = -5;
    this.openSymb = "<";
    this.closeSymb = ">";
    print = new ArrayList<ExpressionDisplay>();
    listModel = new DefaultListModel<ExpressionDisplay>();
    history = new JList<ExpressionDisplay>(listModel);
    history.setCellRenderer(new ExpressionCellRenderer());
    sp = new JScrollPane();
    sp.setViewportView(history);
    history.setLayoutOrientation(JList.VERTICAL);
    add(sp);
    sp.setVisible(true);
    history.setBackground(ColorScheme.BACKGROUND);

//    this.add(history);
//    sb = JScrollBar(JScrollBar.VERTICAL, int value, int extent, int min, int max)  ;
    // TODO Auto-generated constructor stub
  }


  /**
   * Sets the new list of steps to be the given list.
   * 
   * @param es The new steps to make the GUI display.
   */
  public void addExpressionState(final ExpressionState es)
  {
    if(!es.isComplete()) 
    {
      return;
    }
    ExpressionDisplay ed = new ExpressionDisplay();
    ed.setExpression(es);
    print.add(ed);
    this.listModel.addElement(ed);
    sp.repaint();
    sp.revalidate();
  }

  class ExpressionCellRenderer implements ListCellRenderer<Object> {
    public ExpressionCellRenderer() {
//        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value,
        int index, boolean isSelected, boolean cellHasFocus)
    {
      return listModel.elementAt(index);
    }
  }
  
  /**
   * If this method is called by the print button, then printing occurs. 
   * Otherwise the HistoryDisplay will extend/retract.
   * 
   * @param e ActionEvent
   */
  public void actionPerformed(final ActionEvent e) 
  {
    if (e.getActionCommand().equals("Print")) {
      PrinterJob job = PrinterJob.getPrinterJob();
      job.setPrintable(this);
      if (job.printDialog()) 
      {
        try
        {
          job.print();
        } catch (PrinterException pe)
        {
        }
      }
    } else 
    {
      super.actionPerformed(e); // extend/retract side panel
    }
  }
  
  @Override
  public int print(final Graphics g, final PageFormat pf, final int page)
  {
   
    if (page > 0) {
      return NO_SUCH_PAGE;
    }
    
    Graphics2D g2d = (Graphics2D)g;
    g2d.translate(pf.getImageableX(), pf.getImageableY());
    
    JFrame jf = createJFrame();
    jf.printAll(g);
    jf.dispose();
    return PAGE_EXISTS;
  }
  
  /**
   * Creates a JFrame used to print the History.
   * 
   * @return JFrame to print
   */
  private JFrame createJFrame() 
  {
    JFrame frame = new JFrame();
    // creates an invisible "visible" jframe for printing
    frame.setSize(612,792); // size of standard letter paper 8.5 x 11 in.
    frame.setUndecorated(true);
    frame.setVisible(true);
    frame.setState(Frame.ICONIFIED);
    
    frame.getContentPane().setBackground(ColorScheme.DISPLAY);
    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    
    for (ExpressionDisplay ed : print) 
    {
      frame.add(ed);
    }
    
    return frame;
  }

}

