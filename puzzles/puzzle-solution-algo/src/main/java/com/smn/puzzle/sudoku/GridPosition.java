package com.smn.puzzle.sudoku;

// TODO: Auto-generated Javadoc
/**
 * The Class GridPosition.
 * Represent the cell/element position in terms of X & Y (2D) plane
 */
public class GridPosition {
	
	/** The x. */
	private int X=0;
	
	/** The y. */
	private int Y=0;

	

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return X;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		X = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return Y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		Y = y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GridPosition [X=" + X + ", Y=" + Y + "]";
	}
	
	

}
