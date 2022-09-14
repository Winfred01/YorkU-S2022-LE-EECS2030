package eecs2030.lab1;

/**
 * An exception class used in Lab 1 of EECS2030 Summer 2019-20.
 *
 */
public class BadSIDException extends RuntimeException {

	/**
	 * Used for serializing exceptions.
	 */
	private static final long serialVersionUID = -1208991100879180584L;

	/**
	 * Initializes an exception with no detail message.
	 */
	public BadSIDException() {
		super();
	}
	
	/**
	 * Initializes an exception with the specified detail message.
	 * 
	 * @param message the detail message
	 */
	public BadSIDException(String message) {
		super(message);
	}
	
	/**
	 * Initializes an exception with the specified detail message
	 * and cause.
	 * 
	 * <p>
	 * Note that the detailed message associated with cause is not automatically 
	 * incorporated in this exception's detail message.
	 * 
	 * @param message the detail message
	 * @param cause the cause
	 */
	public BadSIDException(String message, Throwable cause) {
		super(message, cause);
	}

}
