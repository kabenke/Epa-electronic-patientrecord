package exceptions;
/**
* This class can throw an Exception to indicate that the requested operation is not supported
*/
public class UnsupportedOperationException extends RuntimeException{
    /**
    *   Constructor for the UnsupportedOperationException
    */
    public UnsupportedOperationException(String message){
        super(message);
    }

}

