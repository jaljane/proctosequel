
package org.proctosequel.query.exception;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class SyntaxError extends RuntimeException{

    public SyntaxError(String message, Throwable cause) {
        super(message, cause);
    }

    public SyntaxError(String message) {
        super(message);
    }
    
    public SyntaxError(String varname, String message) {
        super("varname : " + varname + " : " + message);
    }

    
    public SyntaxError(String varname, String message, Throwable cause) {
        super("varname : " + varname + " : " + message , cause);
    }
    
    
}
