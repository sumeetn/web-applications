package com.smn.puzzle.sudoku.Exception;

/**
 * The Class InvalidDataException.
 * Number of elements should be perfect square.
 */
public class InvalidDataException extends Exception {

	/** The message. */
	private String message;

	/**
	 * Instantiates a new invalid data exception.
	 */
	public InvalidDataException() {
		super();
	}

	/**
	 * Instantiates a new invalid data exception.
	 *
	 * @param errorMsg the error msg
	 */
	public InvalidDataException(String errorMsg) {
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
