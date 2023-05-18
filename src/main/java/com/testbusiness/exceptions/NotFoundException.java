/**
 * 
 */
package com.testbusiness.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Exception Class
 * 
 * @author Juan Camilo Pedraza
 *
 */
public class NotFoundException extends HttpClientErrorException{

	private static final long serialVersionUID = 1L;
    
	
    public NotFoundException(HttpStatus statusCode, String statusText) {
    	super(statusCode, "dummy service failed "+statusText);
    }
}
