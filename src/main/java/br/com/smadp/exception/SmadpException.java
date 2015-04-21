package br.com.smadp.exception;

/**
 *
 * @author kurt
 */
public class SmadpException extends Exception {

	public SmadpException(String message) {
		super(message);
	}

	public SmadpException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
