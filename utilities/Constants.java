package utilities;

import java.util.HashMap;
import java.util.Map;
import java.awt.Color;

import math.*;

/**
 * Contains constants that will be used for operations and buttons.
 * 
 * @author rileywhite
 *
 */
public class Constants
{
  public static final String CLEAR = "C";
  public static final String RESET = "R";
  public static final String INVERSE = "Inv";
  public static final String BACKSPACE = "←";
  public static final String FORWARDS = "→";
  public static final String ARROW = ">";
  public static final String FAREY_NUMBER = "F";
  public static final String REDUCED_FORM = "Red";
  public static final String POW = "^";

  public static final String ADD = "+";
  public static final String SUBTRACT = "-";
  public static final String DIVIDE = "÷";
  public static final String MULTIPLY = "x";
  public static final String EQUALS = "=";
  public static final String IPOWER = "^";
  public static final String MEDIANT = "M+";
  public static final String INVESE = "-1";
  public static final String SIGN = "±";

  public static final String ENTER_FRACTION = "  Enter Mixed Fraction";
  
  public static final String ENGLISH = "English";
  public static final String SPANISH = "Español";
  public static final String FRENCH = "Français";

  public static final HashMap<String, Operation> OP_MAP = new HashMap<String, Operation>()
  {
    {
      put(ADD, new Addition());
      put(SUBTRACT, new Subtraction());
      put(DIVIDE, new Division());
      put(MULTIPLY, new Multiplication());
      put(IPOWER, new IntegerPower());
      put(MEDIANT, new Mediant());
      put(INVERSE, new Inverse());
      put(FAREY_NUMBER, new Farey());
    }
  };
}
