
package org.proctosequel.parsing.exception;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class SemanticsError extends RuntimeException {

    public SemanticsError(String message, Throwable cause) {
        super(message, cause);
    }    
    public SemanticsError(String message) {
        super(message);
    }
}
