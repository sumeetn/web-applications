package com.smn.puzzle.sudoku.Exception;

// TODO: Auto-generated Javadoc
/**
 * The Class InsufficientDataException. Number of elements in problem statement
 * should be atleast 16, so that a simple sudoku problem can be formulated.
 */
public class InsufficientDataException extends Exception {

	/** The message. */
	private String message;

	/**
	 * Instantiates a new insufficient data exception.
	 */
	public InsufficientDataException() {
		super();
	}

	/**
	 * Instantiates a new insufficient data exception.
	 * 
	 * @param errorMsg
	 *            the error msg
	 */
	public InsufficientDataException(String errorMsg) {
		super(errorMsg);
		message = errorMsg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
