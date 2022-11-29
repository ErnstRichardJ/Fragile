package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import math.Fraction;

class FractionTest
{

  @Test
  void normalMixedTest()
  {
    Fraction f = new Fraction(5, 3, 4);
    
    assertEquals(4, f.denominator(), "Fraction did not have the correct denominator");
    assertEquals(23, f.improperNumerator(), "Fraction did not have the correct numerator");
    assertEquals(3, f.mixedNumerator(), "Fraction did not have correct numerator in mixed form");
    assertEquals(5, f.wholes(), "Fraction did not have correct number of wholes");
    
    assertEquals("5 3/4", f.toString(), "Fraction toString was wrong");
    
    f = new Fraction(3, 4);
    assertEquals("3/4", f.toString(), "Fraction with no wholes String was wrong");
    assertEquals(0.75, f.decimal(), "Decimal was wrong value");
    
    f = new Fraction(27, 1);
    assertEquals("27", f.toString(), "Whole number without fractional component string was wrong.");
    assertFalse(f.isUndefined());
    
    f = new Fraction(0, 1);
    assertEquals("0", f.toString(), "Whole with zero fractional component string was wrong.");
    
    f = new Fraction(3, 0, 2);
    assertEquals("3", f.toString());
  }

  @Test
  void improperTest()
  {
    Fraction f = new Fraction(25, 4);
    
    assertEquals(6, f.wholes(), "Improper fraction had incorrect number of wholes");
  }
  
  @Test
  void undefinedTest()
  {
    Fraction f = new Fraction(3, 0);
    
    assertTrue(f.isUndefined(), "3/0 was said to be defined");
    
    f = new Fraction(2, 0);
    assertTrue(f.isUndefined());
    assertNull(f.wholes());
    assertNull(f.decimal());
    assertEquals("NaN", f.toString());
  }
  
  @Test
  void constructNegativesTest()
  {
    Fraction f = new Fraction(-4, -2);
    assertEquals(4, f.improperNumerator());
    assertEquals(2, f.denominator());
    
    f = new Fraction(-4, 2);
    assertEquals(-4, f.improperNumerator());
    
    f = new Fraction(4, -2);
    assertEquals(-4, f.improperNumerator());
    assertEquals("-2", f.toString());
    
    f = new Fraction(-1, 1, 2);
    assertEquals("-1 1/2", f.toString());
  }
  
  @Test
  void parseStringTest()
  {
    assertEquals(23, Fraction.parseString("5 3/4").improperNumerator());
    assertEquals(27, Fraction.parseString("27/5").improperNumerator());
    assertEquals(4, Fraction.parseString("4").improperNumerator());
    assertEquals(4, Fraction.parseString("4").wholes());
    assertEquals(1, Fraction.parseString("4").denominator());
    
    assertEquals(3, Fraction.parseString("  3/4").improperNumerator());
    assertEquals(4, Fraction.parseString("  3/4").denominator());

  }
  
  @Test
  void parseStringBadTest()
  {
    assertNull(Fraction.parseString("WHO AM I?!"));
    assertNull(Fraction.parseString(""));
    assertNull(Fraction.parseString(null));
    
    assertNull(Fraction.parseString("w a/b"));
    assertNull(Fraction.parseString("WRONG 3/4"));
    assertNull(Fraction.parseString("FOUR"));
  }
  
  @Test
  void equalsTest()
  {
    Fraction f1 = new Fraction(7, 2, 5);
    Fraction f2 = new Fraction(37, 5);
    
    assertTrue(f1.equals(f2));
    assertFalse(f1.equals("7 2/5"));
    assertFalse(f1.equals(new Fraction(8, 2, 5)));
  }
}
