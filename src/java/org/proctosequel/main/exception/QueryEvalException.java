/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
