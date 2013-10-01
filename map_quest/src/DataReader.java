// Class DataReader
//
// Author: Joe Turner
// Date:   30 March 1997
// Modified: 11 April 1997
// Modified: 27 October 1997 - removed date
// Modified: 3 November 1997 - added line
// Modified: 13 January 1998 - changed method names to be consistent with
//                                  java.io classes
//                             added double
// Modified: 14 September 1998 - fixed blank line problem
//                               added char input for System.in to improve
//                                   Dos interactive characteristics
//                                   (removed inheritance from BufferedReader)
// Modified 22 September 1998 - fixed error with leading zeros in decimal
//                              part of double
// Modified 29 August 1999 - Changed name from InputDataReader to DataReader.
//
// This class provides token-oriented stream input.  Input data can be obtained
// one token at a time, where a token is one of the following:
//
//     word - a sequence of non-whitespace characters
//     integer - an int value (converted from input text)
//               input data format:  a sequence of digits, optionally preceded
//                                   by a minus sign
//     double - a double value (converted from input text)
//               input data format: an optional minus sign, followed by a
//                                  sequence od digits, optionally followed by
//                                  a period and an optional sequence of digits
//                   examples:  123   -12.  12.345
//     line - the (remainder of the) current line
//
// Tokens are separated by whitespace characters (blanks, tabs, end-of-lines).
// Note that this is more restrictive than is often allowed for stream input.
// For example, reading an int when the next unread characters on the stream
// are
//       123cat
// will throw a DataException, rather than returning int value 123 as is 
// sometimes done in other stream input facilities.
//
// (Input operations are also inherited from class BufferedReader.)
//
// Constructors:
//
//    DataReader() - use standard input
//
//    DataReader(String filename) - use the file named "filename"
//    
// Public methods:
//    Note: EOFException is thrown when end-of-file occurs in an attempt to
//                       get another token.
//          IOException is thrown if an I/O error occurs on reading the input
//                       file.
//          DataException is thrown if an error occurs in converting characters
//                       to the requested data type (e.g., int)
//
//    String readWord() - return the next word token
//      throws EOFException, IOException
//
//    int readInt() - return the next int token
//      throws EOFException, IOException, DataException
//
//    double readDouble() - return the next floating-point token
//      throws EOFException, IOException, DataException
//
//    String readLine() - return the (remainder of the) current line
//      throws EOFException, IOException
//
//    void close() - close the file
//      throws IOException
//

// package cucs; Okay, I admit, removing this is a hack -GMK

import java.io.*;
import java.util.StringTokenizer;

public class DataReader {

   protected String inputLine;       // the current line of input
   protected StringTokenizer tokens; // the tokenizer for the input line
   protected boolean file = false;   // indicates whether external file is used
   protected Reader reader;          // the input reader
   protected boolean eof = false;    // eof indicator

   public DataReader() { // Constructor for standard input
      reader = new InputStreamReader(System.in);
   }
 
   public DataReader(String filename)
              throws FileNotFoundException
           // constructor for external file
   {
      reader = new BufferedReader(new FileReader(filename));
      file = true;
   }

   protected void getNextLine() throws EOFException, IOException {
      // Get the next input line.
      StringBuffer inbuf = new StringBuffer(100);
      int nextCharCode;
      char nextChar;

      if (eof) throw new EOFException(); 

      do {
         nextCharCode = reader.read();

         if (nextCharCode<0)  {  // eof
           if (file) close();
           if (inbuf.length()==0) throw new EOFException();
           eof = true;
           nextChar = '\n';   // Insert eol
         }
         else {
            nextChar = (char) nextCharCode;
         }
         inbuf.append(nextChar);
      } while (nextChar != '\n' && nextChar != '\r');

      if (nextChar == '\r' && reader.ready()) {
         nextCharCode = reader.read();    // Consume the '\n'.
      }

      inbuf.setLength(inbuf.length()-1);  // Truncate the eol character.
      inputLine = inbuf.toString();
      tokens = new StringTokenizer(inputLine);

   }

   protected void checkSetup()
            throws EOFException, IOException
   { // Make sure that the next token is available.

      while (inputLine==null || !tokens.hasMoreTokens()) { 
        getNextLine();
      }
   }

   public String readWord()
            throws EOFException, IOException
   { // Return the next sequence of non-whitespace characters.

      checkSetup();

      return tokens.nextToken();
   }

   public String readLine()
            throws EOFException, IOException
   { // Return the remainder of the current line.

      if (inputLine==null) getNextLine();
      inputLine = null;       // Consume the current line.
      try {
         return tokens.nextToken("\r\n");
      } catch (Exception e) { return ""; }
   }

   public int readInt() throws EOFException, IOException, DataException {
     // Return the next token as an integer.

      checkSetup();

      String intToken = tokens.nextToken();
      
      try {
        return Integer.parseInt(intToken);
      }
      catch (Exception e) {
        throw new DataException(intToken);
      }
   }

   public double readDouble() throws EOFException, IOException, DataException {
     // Return the next token as a double.

      checkSetup();

      String doubleToken = tokens.nextToken();
      int pointIndex = doubleToken.indexOf('.');
      boolean negative = doubleToken.charAt(0)=='-';

      try {
         if (pointIndex<0)  // (then there is no decimal point)
           return (double)Integer.parseInt(doubleToken);
         else {
            double result;
            // Start with the integer part.
               if (pointIndex==0 || negative && pointIndex==1)
                  result = 0;  // (empty integer part)
               else result =
                  (double)Integer.parseInt(doubleToken.substring(0,pointIndex));

            // Add in the decimal part (if any).
               if (pointIndex < doubleToken.length()-1) {
                  double decimalPartValue = 0;

                  for (int i=doubleToken.length()-1; i>pointIndex; i--) {
                     // Calculate digit-by-digit value, right-to-left.
                     decimalPartValue = decimalPartValue/10 +
                                  ((double)(doubleToken.charAt(i)-'0'))/10;
                  }
                  if (negative) result -= decimalPartValue;
                  else  result += decimalPartValue;
               }
            return result;
         }
      } catch (Exception e) {
           throw new DataException(doubleToken);
        }
   }


   public void close() throws IOException {
      if (file) reader.close();
   }

   protected void finalize() throws Throwable {
      if (file) {
         reader.close();
         super.finalize();
      }
   }


} // End class DataReader
