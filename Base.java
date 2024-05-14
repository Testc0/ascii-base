import java.util.*;
/** Program Manual 
 * This program is able to both decode/encode a number with a given base value
 * and decode/encode a string into ASCII, with or without a base conversion.
 * To start the program, right click on the block titled "conversionTester"
 * and select main(String args[]), a pop-up should appear after you've clicked
 * it. Select "OK" on the pop-up and the program should run as usual.
 * 
 * Troubleshooting:
 * - If the program displays "Error: Invalid Selection", it means that you've
 * entered in a choice that does not correspond to any options it has given
 * you.
 * - If the program displays "Error: Invalid Base Value", it means that the
 * base value you've entered is too small for the number you're trying to
 * decode.
 * - If the encoding process does not seem to function properly, remember that
 * you must enter a DECIMAL number, do not enter a number in any other base or
 * else it might not give the correct encoded value.
**/
public class Base
{
    /** Instance Variables **/
    private int decimal; //Decimal number to encode
    private int baseValue; //Base value
    private String baseString; //String representing the value to decode
    
    /** Default Constructor **/
    public Base()
    {
        decimal = 0;
        baseValue = 0;
        baseString = null;
    }
    
    /** Constructors **/
    public Base(int d, int bv)
    {
        decimal = d;
        baseValue = bv;
    }
    public Base(String s, int bv)
    {
        baseString = s;
        baseValue = bv;
    }
    public Base(int bv)
    {
        baseValue = bv;
    }
    
    /** Accessors (getters) **/
    //They don't actually print anything out, they just give the value
    public int getDecimal()
    {
        return decimal;
    }
    public int getBaseValue()
    {
        return baseValue;
    }
    public String getBaseString()
    {
        return baseString;
    }
    
    /** Mutators (setters) **/
    //Void means that nothing is being given out or "returned"
    public void setDecimal(int d)
    {
        decimal = d;
    }
    public void setBaseValue(int bv)
    {
        baseValue = bv;
    }
    public void setBaseString(String s)
    {
        baseString = s;
    }
    
    /** Encoding Method **/
    public String encodeNumber()
    {
        String returnNum = "";
        for(int i = decimal; i > 0; i /= baseValue) 
        //For loop that starts with i = decimal, divides i by baseValue every time, and stops when i is smaller than 0.
        {
            if(i % baseValue <= 9) //If i is within the decimal range
            {
                returnNum = i % baseValue
                            + returnNum.substring(0);
            }
            else
            {
                returnNum = (char)(i % baseValue + 55) //(char) converts the integer value to a character using ASCII
                            + returnNum.substring(0);
            }
        }
        return returnNum;
    }
    
    /** Decoding Method **/
    public String decodeNumber()
    {
        String returnNum = "";
        int intDigit, returnInt = 0, digitNum = baseString.length() - 1;
        for(int i = 0; i < baseString.length(); i++)
        //For loop that starts with i = 0, adds one to i every time, and stops when i is greater than the length of baseString.
        {
            if(baseString.charAt(i) <= '9') 
            //If the ASCII value of the character at position of i in baseString is smaller than the ASCII value of '9'
            {
                intDigit = baseString.charAt(i) - '0'; //char values are automatically converted to int values when added to an int variable.
            }
            else
            {
                intDigit = baseString.charAt(i) - '7';
            }
            returnInt += intDigit * (int)Math.pow(baseValue, digitNum); //Math.pow is a method for raising a number to a certain power. Math.pow(x, y) = x^y
            digitNum--; //This represents the current digit. Since String values go from left to right this value gets smaller every time.
        }
        return returnNum + returnInt;
    }
    
    /** isValid Method 
        tests whether or not all the values in a created object are valid **/
    public boolean isValid()
    {
        int intDigit;
        if(baseValue < 0) //If the base value is negative
        {
            return false;
        }
        if(baseString == null) //If the number to decode is non-existent
        {
            return false;
        }
        for(int i = 0; i < baseString.length(); i++)
        {
            intDigit = baseString.charAt(i) - '0';
            if(intDigit >= baseValue) //If any individual digit of the decoding number has a regular value bigger than the base value
            {
                return false;
            }
        }
        return true; //If none of the above statements are true, then the object is valid.
    }
}
