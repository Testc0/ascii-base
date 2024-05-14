/*
 * Ken Wang
 * Date: 5/14/2023
 * Heritage High School
 * Final Project: Base/ASCII Conversion Program
 */
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
import java.io.*;
import java.util.*;
import java.text.*;
public class conversionTester
{
    public static void main(String args[])
    {
        /** Final Variables **/
        final String CHOICE_DECODE = "D";
        final String CHOICE_ENCODE = "E";
        final String CHOICE_YES = "Y";
        final String CHOICE_NO = "N";
        final String CHOICE_ASCII = "A";
        final String CHOICE_BASE_CONVERT = "B";
        
        /** Scanner Class (input) **/
        Scanner input = new Scanner(System.in);
        
        /** Important Variables **/
        int num, baseVal;
        String userChoice, str;
        ArrayList<String> decodeList = new ArrayList<String>();
        boolean cont = true, exit = false, encounteredError, selection;
        
        /** Main Loop **/
        do
        {
            selection = true; //Determines whether or not the user is currently in the process of doing a selection.
            encounteredError = false; //Determines whether or not the user has made an invalid selection.
            System.out.println("Choose the type of conversion you want:");
            System.out.println("Base Conversion (B)");
            System.out.println("Ascii Conversion (A)");
            userChoice = input.next().toUpperCase(); //Stores the inputs made by the user while choosing options. toUpperCase() just converts it all to uppercase.
            if(userChoice.equals(CHOICE_BASE_CONVERT)) //If the user chooses base conversion
            {
                System.out.println("Choose a mode for your conversion:");
                System.out.println("Encode (E)");
                System.out.println("Decode (D)");
                userChoice = input.next();
                if(userChoice.equals(CHOICE_DECODE)) //If the user chooses to decode
                {
                    System.out.print("Enter a number to decode: ");
                    str = input.next(); //Stores the number to decode
                    System.out.print("Enter the base value of the number: ");
                    baseVal = input.nextInt(); //Stores the base value of the number
                    Base decodedNum = new Base(str, baseVal); //Creates new Base object using Base Class with parameters str and baseVal.
                    //Check the code for the Base Class if you want to know more about how it works.
                    if(decodedNum.isValid()) //Determines whether or not the base value is consistent with the number.
                    {
                        System.out.println(str + " in base" + baseVal + " is "
                                        + decodedNum.decodeNumber());
                        //If yes, then it prints the decoded value.
                    }
                    else
                    {
                        System.out.println("Error: Invalid Base Value");
                        //If no, it returns an error message.
                    }
                }
                else if(userChoice.equals(CHOICE_ENCODE)) //If the user chooses to encode
                {
                System.out.print("Enter a number to encode: ");
                num = input.nextInt(); //Stores the number to encode
                System.out.print("Enter the base value you want to convert it to: ");
                baseVal = input.nextInt(); //Stores the base value of the number
                Base encodedNum = new Base(num, baseVal); //Creates a new base object with parameters num and baseVal
                System.out.println(num + " in base" + baseVal + " is "
                                    + encodedNum.encodeNumber());
                }
                else //If neither options are chosen, then the user has made an invalid selection and an error message is printed.
                {
                    System.out.println("Error: Invalid Selection");
                    encounteredError = true;
                }
                while(selection && !encounteredError) //Only occurs if the user has not made an invalid selection.
                {
                    System.out.print("Would you like to do another conversion? (Y/N) ");
                    userChoice = input.next();
                    if(userChoice.equals(CHOICE_NO)) //If user chooses no, then the program terminates.
                    {
                        cont = false;
                        selection = false;
                    }
                    else if(userChoice.equals(CHOICE_YES)) //If user chooses yes, then the program continues, but this loop is terminated.
                    {
                        selection = false;
                    }
                    else //If user chooses neither, then an error message is printed and the loop continues.
                    {
                        System.out.println("Error: Invalid Selection");
                    }
                }
            }
            else if(userChoice.equals(CHOICE_ASCII)) //If user chooses ASCII conversion
            {
                System.out.println("Choose a mode for your conversion:");
                System.out.println("Encode (E)");
                System.out.println("Decode (D)");
                userChoice = input.next().toUpperCase();
                if(userChoice.equals(CHOICE_DECODE))
                {
                    System.out.println("Enter the list of values you want to decode");
                    System.out.println("(make sure to enter each value on a separate line and type 'end' when you're finished): ");
                    while(!exit) //Loop is only terminated when the user types "end".
                    {
                        String next = input.next(); //Stores every value entered by the user during this loop
                        if(next.toLowerCase().equals("end"))
                        {
                            exit = true;
                        }
                        else
                        {
                            decodeList.add(next); //An ArrayList of the values to decode. next is only added to the list if it is not "end".
                        }
                    }
                    Ascii decodedString = new Ascii(decodeList); //Creates new Ascii object using Ascii Class with the parameter decodeList.
                    //Check the code for Ascii Class if you want to know more about how it works
                    System.out.println("Enter the base value of the values");
                    System.out.print("(If the values are already in decimal format, then just enter 10): ");
                    baseVal = input.nextInt();
                    System.out.println("Your ASCII string converts to \""
                                        + decodedString.baseDecodeAscii(baseVal)
                                        + "\"");
                }
                else if(userChoice.equals(CHOICE_ENCODE))
                {
                    System.out.print("Enter the string you would want to encode: ");
                    str = input.next(); //Stores the string to encode
                    Ascii encodedString = new Ascii(str); //Creates a new Ascii object with the parameter str
                    System.out.println("Enter the base value you would want to convert it to");
                    System.out.print("(If you just want it in decimal format, then enter 10): ");
                    baseVal = input.nextInt();
                    System.out.println(str + " in ASCII converts to:");
                    System.out.println(encodedString.baseEncodeAscii(baseVal));
                }
                else
                {
                    System.out.println("Error: Invalid Selection");
                    encounteredError = true;
                }
                while(selection && !encounteredError)
                {
                    System.out.print("Would you like to convert another string? (Y/N) ");
                    userChoice = input.next().toUpperCase();
                    if(userChoice.equals(CHOICE_NO))
                    {
                        cont = false;
                        selection = false;
                    }
                    else if(userChoice.equals(CHOICE_YES))
                    {
                        selection = false;
                    }
                    else
                    {
                        System.out.println("Error: Invalid Selection");
                    }
                }
            }
            else //If the user selects neither Base nor ASCII conversion, then an error message is printed.
            {
                System.out.println("Error: Invalid Selection");
            }
        }while(cont); //This program will only terminate if the user selects N when being asked to continue.
    }
}
