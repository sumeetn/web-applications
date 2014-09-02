package com.smn.puzzle.sudoku.Exception;

// TODO: Auto-generated Javadoc
/**
 * The Class InvalidCharacterException.
 * Only numbers and X(for empty String) are allowed.
1 */
public class InvalidCharacterException extends Exception {
	
	/** The message. */
	private String message;

	/**
	 * Instantiates a new invalid character exception.
	 */
	public InvalidCharacterException() {
		super();
	}

	/**
	 * Instantiates a new invalid character exception.
	 *
	 * @param errorMsg the error msg
	 */
	public InvalidCharacterException(String errorMsg) {
		super(errorMsg);
		message = errorMsg;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		return message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
