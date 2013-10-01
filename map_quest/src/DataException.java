// Class DataException
//
// Author: Joe Turner
// Date:   30 March 1997
//
// A DataException is thrown when an error occurs in an attempt to convert from
// text to an internal form such as int (for example, converting input text to
// internal data).
//
// Constructor:
//
//    DataException(String data) - data is the text that could not be converted
//
// Public data field:
//
//    String data - the data that could not be converted
//
// Public methods:
//    
//    (none)
//

// package cucs; Okay, I admit, removing this is a hac -GMK

public class DataException extends Exception {

   public String data;  // string that doesn't convert properly

   public DataException(String data) {  // Constructor
      this.data = data;
   }
}
   
