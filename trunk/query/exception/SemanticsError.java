/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.proctosequel.query.exception;

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
