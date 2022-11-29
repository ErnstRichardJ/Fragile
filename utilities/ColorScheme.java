package utilities;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ColorScheme
{

//  public static Color BACKGROUND = new Color(238, 238, 238);
//  public static Color DISPLAY = new Color(218, 228, 239);
//  public static Color DISPLAY_TEXT = Color.BLACK;
//
//  public static Color BUTTON_COLOR = Color.WHITE;
//
//  public static Color NUMBERPAD_TEXT = Color.BLACK;
//  public static Color BUTTONPAD_TEXT = new Color(51, 153, 255);
//  public static Color INPUT_EDITOR_TEXT = new Color(202, 145, 75);
  
    public static Color BACKGROUND;
    public static Color DISPLAY;
    public static Color DISPLAY_TEXT;
    
    public static Color BUTTON_COLOR;
    
    public static Color NUMBERPAD_TEXT;
    public static Color BUTTONPAD_TEXT;
    public static Color INPUT_EDITOR_TEXT;

  private InputStream is;

  public void setColors() throws IOException
  {

    BufferedReader reader = createBufferedReader("ColorScheme.txt");
    BACKGROUND = readHexCode(reader.readLine());
    DISPLAY = readHexCode(reader.readLine());
    DISPLAY_TEXT = readHexCode(reader.readLine());
    BUTTON_COLOR = readHexCode(reader.readLine());
    NUMBERPAD_TEXT = readHexCode(reader.readLine());
    BUTTONPAD_TEXT = readHexCode(reader.readLine());
    INPUT_EDITOR_TEXT = readHexCode(reader.readLine());

  }

  /**
   * Helper method to make factory creator for BufferedReader.
   * 
   * @param name
   * @return BufferedReader to use when reading file
   */
  private BufferedReader createBufferedReader(final String name)
  {
    is = getClass().getResourceAsStream(name);
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));

    return reader;
  }

  /**
   * Helper method to read lines into hex code.
   * @param line to read into Hex Code
   * @return Color object
   */
  private static Color readHexCode(String line)
  {

    // 0, 0, 0
    String values = line.substring(line.indexOf('(') + 1, line.length() - 1);

    // Set values for Color object
    int[] rgb = new int[3];

    // Cuts string to get RGB values
    for (int i = 0; i < 3; i++)
    {
      // Check to see if user put in integer correctly, if not it'll automatically
      // set to 0
      try
      {
        // If on last value, should only be an integer left
        if (i == 2)
          rgb[i] = Integer.parseInt(values);
        else
          rgb[i] = Integer.parseInt(values.substring(0, values.indexOf(',')));
        
      } catch (NumberFormatException nfe) {
          rgb[i] = 0;
      }
      
      // Truncate string to read each value
      if (values.length() > 1)
          values = values.substring(values.indexOf(' ') + 1, values.length());
      
    }
    
    // Returns new color to convert color object throughout program
    return new Color(rgb[0], rgb[1], rgb[2]);
  }
}
