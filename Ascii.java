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
public class Ascii
{
    private ArrayList<Character> asciiList = new ArrayList<Character>(); //Array of the characters representing the string value to encode
    private String asciiString; //String value to convert
    private ArrayList<String> convertList; //Array of the values to decode
    
    /** Default Constructor **/
    public Ascii()
    {
        asciiString = "";
    }
    
    /** Constructors **/
    public Ascii(String a)
    {
        asciiString = a;
        for(int i = 0; i < asciiString.length(); i++)
        {
            asciiList.add(asciiString.charAt(i));
        }
    }
    public Ascii(ArrayList cl)
    {
        convertList = cl;
    }
    
    /** Accessors (getters) **/
    //They don't actually print anything out, they just give the value
    public ArrayList getAsciiList()
    {
        return asciiList;
    }
    public String getAsciiString()
    {
        return asciiString;
    }
    public ArrayList getConvertList()
    {
        return convertList;
    }
    
    /** Mutators (setters) **/
    //Void means that nothing is being given out or "returned"
    public void setAsciiList(ArrayList<Character> al)
    {
        asciiList = al;
    }
    public void setAsciiString(String as)
    {
        asciiString = as;
    }
    public void setConvertList(ArrayList<String> cl)
    {
        convertList = cl;
    }
    
    /** Encoding Methods **/
    public ArrayList encodeAscii()
    {
        ArrayList<Integer> returnList = new ArrayList<Integer>();
        for(char character : asciiList)
        {
            returnList.add((int)character);
        }
        return returnList;
    }
    public ArrayList baseEncodeAscii(int baseValue) //Encodes with a base value
    {
        ArrayList<Integer> numList = this.encodeAscii();
        ArrayList<String> returnList = new ArrayList<String>();
        Base convert = new Base(baseValue);
        for(int num : numList)
        {
            convert.setDecimal(num);
            returnList.add(convert.encodeNumber());
        }
        return returnList;
    }
    
    /** Decoding Methods **/
    public String decodeAscii()
    {
        String returnStr = "";
        int numValue;
        for(String val : convertList)
        {
            numValue = Integer.parseInt(val);
            returnStr += (char)numValue;
        }
        return returnStr;
    }
    public String baseDecodeAscii(int baseValue) //Decodes with a base value
    {
        String returnStr = "";
        int i = 0;
        Base convert = new Base(baseValue);
        for(String character : convertList)
        {
            convert.setBaseString(character);
            convertList.set(i, convert.decodeNumber());
            i++;
        }
        returnStr += this.decodeAscii();
        return returnStr;
    }
}
