package math;

/**
 * Fraction class that supports mixed form.
 * 
 * @author Jacob Gottschalk
 * @version 2021-10-27
 */
public class Fraction
{
  private static final String SPACE = " ";
  private static final String SLASH = "/";
  
  private Integer numerator;
  private Integer denominator;

  /**
   * Constructor for Fraction as an improper fraction.
   * 
   * @param numerator numerator of fraction
   * @param denominator denominator of fraction
   */
  public Fraction(final Integer numerator, final Integer denominator)
  {
    this.numerator = numerator;
    this.denominator = denominator;
    
    if (this.denominator < 0 )
    {
      this.numerator *= -1;
      this.denominator *= -1;
    }
  }

  /**
   * Constructor for Fraction as a Mixed Number. The fractional part can be improper.
   * 
   * @param wholes number of wholes (the large number in a mixed number)
   * @param numerator numerator of fractional part
   * @param denominator denominator of fractional part
   */
  public Fraction(final Integer wholes, final Integer numerator, final Integer denominator)
  {
    this(wholes * denominator + ((wholes < 0 ? -1 : 1) * numerator), denominator);
  }
  
  /**
   * Parses a String in the form of "W n/d" or "n/d".
   * 
   * @param inputString String to parse
   * @return the Object this represents;
   */
  public static Fraction parseString(final String inputString)
  {
    Fraction result = null;
    if (inputString == null || inputString.length() == 0)
      return result;
    
    if (inputString.charAt(0) == ' ')
      return parseString(inputString.substring(1));
      
    
    Integer whole = 0;
    Integer numerator = 0;
    Integer denominator = 0;
    
    int wholeIndex = 0;
    int numeratorIndex = 0;
    int denominatorIndex = 0;
    
    // whole part
    wholeIndex = inputString.indexOf(SPACE);
    if (wholeIndex >= 0)
      whole = parseInteger(inputString.substring(0, wholeIndex));
    
    // numerator part
    numeratorIndex = inputString.indexOf(SLASH);
    if (numeratorIndex > 0)
      numerator = parseInteger(inputString.substring(wholeIndex + 1, numeratorIndex));
    
    // denominator part
    denominatorIndex = inputString.length();
    denominator = parseInteger(inputString.substring(numeratorIndex + 1, denominatorIndex));
    
    // check if only a whole number
    if (wholeIndex < 0 && numeratorIndex < 0)
    {
      whole = parseInteger(inputString);
      numerator = 0;
      denominator = 1;
    }
    
    // return null if failed to parse
    if (numerator == null || denominator == null || whole == null)
      return result;
    
    // return Fraction result
    result = new Fraction(whole * denominator + numerator, denominator);
    return result;
  }
  
  /**
   * Gets the numerator to use if representing this fraction as an improper fraction.
   * 
   * @return the numerator
   */
  public Integer improperNumerator()
  {
    return numerator;
  }
  
  /**
   * Returns the numerator when this fraction is represented in mixed form.
   * 
   * @return the numerator for mixed representation
   */
  public Integer mixedNumerator()
  {
    return Math.abs(numerator % denominator);
  }
  
  /**
   * Returns the denominator.
   * 
   * @return the denominator of the fraction
   */
  public Integer denominator()
  {
    return denominator;
  }
  
  /**
   * Gives the number of wholes when representing this fraction in mixed form, or null if undefined.
   * 
   * @return the number of wholes or null if undefined.
   */
  public Integer wholes()
  {
    if (this.isUndefined())     return null;
    else                        return numerator / denominator;
  }
  
  /**
   * Checks whether this fraction is undefined (negative or undefined).
   * 
   * @return whether or not this fraction is undefined
   */
  public boolean isUndefined()
  {
    return denominator == 0;
  }
  
  /**
   * Gets the value as a decimal.
   * 
   * @return the Fraction as a decimal
   */
  public Double decimal()
  {
    if (this.isUndefined())   return null;
    else                      return (double) numerator / denominator;
  }
 
  /**
   * Gets a Fraction of this Fraction's reduced form.
   * 
   * @return reduced Fraction or itself if undefined
   */
  public Fraction reduce()
  {
    if (isUndefined())
      return this;
    
    Integer gcd = gcd(improperNumerator(), denominator());
    
    return new Fraction(improperNumerator() / gcd, denominator() / gcd);
  }
  
  /**
   * Represents the fraction as a mixed number in a String. NaN if the fraction is undefined. If
   * solidus is set, it will return an html String.
   * 
   * @param solidus whether the string should be in solidus style
   * @param improper whether to give the improper version
   * @return the string representation of the mixed number
   */
  public String toString(final boolean solidus, final boolean improper)
  {
    String result;
    if (this.isUndefined())
      return "NaN";
   
    String denominatorString = "";
    String numeratorString = "";
    
    if (solidus)
    {
      numeratorString = String.format("<sup>%s</sup>",
                                      improper ? improperNumerator() : mixedNumerator());
      denominatorString = String.format("<sub>%s</sub>", denominator());
    }
    else
    {
      numeratorString = improper ? this.improperNumerator().toString() 
                                    : this.mixedNumerator().toString();
      denominatorString = this.denominator().toString();
    }
    
    if (improperNumerator() < 0)
      result = "-";
    else
      result = "";
    
    if ((wholes() == 0 && mixedNumerator() != 0) || improper)
      result += String.format("%s/%s", numeratorString, denominatorString);
    else if (mixedNumerator() == 0)
      result += String.format("%d", Math.abs(this.wholes()));
    else
      result += String.format("%d %s/%s", Math.abs(wholes()), numeratorString, denominatorString);
    
    if (solidus)
      result = "<html>" + result + "</html>";
    
    return result;
  }
  
  /**
   * Normal toString in Slash style.
   * 
   * @return String in slash style
   */
  @Override
  public String toString()
  {
    return this.toString(false, false);
  }
    
  /**
   * Fraction equals another fraction if they have the same irreduced numerator and denominator.
   * 
   * @param o Object to compare to
   * @return if its the same
   */
  @Override
  public boolean equals(final Object o)
  {
    if (o instanceof Fraction)  return ((Fraction) o).hashCode() == this.hashCode();
    else                        return false;
  }
    
  /**
   * Uses toString for the hash.
   * 
   * @return hash of String
   */
  @Override
  public int hashCode()
  {
    return this.toString().hashCode();
  }
  
  /**
   * Same as Integer.valueOf() except returns null instead of throwing an error when the String
   * cannot be parsed.
   * 
   * @param str String to parse
   * @return Integer value of String or null if invalid
   */
  private static Integer parseInteger(final String str)
  {
    try
    {
      return Integer.valueOf(str);
    }
    catch (NumberFormatException nfe)
    {
      return null;
    }
  }
  
  /**
   * Private helper method to find the gcd of 2 numbers.
   * 
   * @param inputa
   * @param inputb
   * @return gcd of a and b
   */
  private static Integer gcd(final Integer inputa, final Integer inputb)
  {
    Integer a = inputa;
    Integer b = inputb;
    Integer tmp = null;
    
    while (b != 0)
    {
      tmp = a % b;
      a = b;
      b = tmp;
    }
    
    return a;
  }
}
