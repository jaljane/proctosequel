package org.proctosequel.main.exception;

/**
 *
 * @author Jamel Aljane <aljane.jamel@gmail.com>
 */
public class QueryEvalException  extends RuntimeException {
    
    public QueryEvalException(String message, Throwable cause) {
        super(message, cause);
    }    
    public QueryEvalException(String message) {
        super(message);
    }    
}
