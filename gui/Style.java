package gui;

/**
 * Enum for different typesetting styles.
 * 
 * @author Jacob Gottschalk
 * @version 2021-11-17
 */
public enum Style
{
  SLASH, SOLIDUS, BAR;

  /**
   * Gets the next style in a toggle fashion.
   * @param style Current style.
   * @return Next style to appear.
   */
  public static Style getNextStyle(final Style style)
  {
    switch (style) 
    {
      case SLASH:
        return SOLIDUS;
      case SOLIDUS:
        return BAR;
      case BAR:
        return SLASH;
      default:
        return SLASH;
    }
  }
}
