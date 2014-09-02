package com.smn.puzzle.sudoku;


/**
 * The Class BackTrackAlgorithm. Implements the backtrack algorithm for solving
 * sudoku. Algorithm: Sequentially starting from 1, check whether it is safe to
 * assign. i.e. check that the number is not present in current row, current
 * column and current sub-box. Then , assign the number, and recursively check
 * whether this assignment leads to a solution or not. If the assignment doesn’t
 * lead to a solution, then get next number for current empty cell.
 */
public class BackTrackAlgorithm extends SudokuSolver {

	/** The result. */
	private String result = null;

	/** The is solved. */
	private boolean isSolved = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smn.puzzle.sudoku.SudokuSolver#solve()
	 */
	@Override
	public boolean solve() {
		if (solveRecursively())
			isSolved = true;

		return isSolved;
	}

	/**
	 * Solve recursively.
	 * 
	 * @return true, if successful
	 */
	private boolean solveRecursively() {
		GridPosition currentPosition = new GridPosition();
		if (!findUnassigned(currentPosition))
			return true;

		int gridSize = getGridSize();
		int[][] grid = getGrid();
		int num;
		for (num = 1; num <= gridSize; num++) {
			if (isSafe(currentPosition.getY(), currentPosition.getX(), num)) {
				grid[currentPosition.getY()][currentPosition.getX()] = num;
				if (solveRecursively())
					return true;
				// Unassign after backtracking
				grid[currentPosition.getY()][currentPosition.getX()] = 0;
			}
		}
		return false;
	}

	/**
	 * Find unassigned.
	 * 
	 * @param currentPosition
	 *            the current position
	 * @return true, if successful
	 */
	public boolean findUnassigned(GridPosition currentPosition) {
		int rowCount = getGridSize();
		int columnCount = getGridSize();
		int[][] grid = getGrid();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (grid[i][j] == 0) {
					currentPosition.setY(i);
					currentPosition.setX(j);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if given number is safe in given row,column and box.
	 * 
	 * @param rowIndex
	 *            the row index
	 * @param colIndex
	 *            the col index
	 * @param num
	 *            the num
	 * @return true, if is safe
	 */
	public boolean isSafe(int rowIndex, int colIndex, int num) {

		if (!usedInRow(rowIndex, num)
				&& !usedInCol(colIndex, num)
				&& !usedInBox(rowIndex - rowIndex % getRegionX(), colIndex
						- colIndex % getRegionY(), num)) {
			return true;
		}
		return false;
	}

	/**
	 * Used in row.
	 * 
	 * @param rowIndex
	 *            the row index
	 * @param num
	 *            the num
	 * @return true, if successful
	 */
	public boolean usedInRow(int rowIndex, int num) {
		int[][] grid = getGrid();
		int rowCount = getGridSize();

		for (int y = 0; y < rowCount; y++) {
			if (grid[rowIndex][y] == num)
				return true;
		}
		return false;
	}

	/**
	 * Used in col.
	 * 
	 * @param colIndex
	 *            the col index
	 * @param num
	 *            the num
	 * @return true, if successful
	 */
	public boolean usedInCol(int colIndex, int num) {

		int[][] grid = getGrid();
		int columnCount = getGridSize();
		for (int x = 0; x < columnCount; x++) {
			if (grid[x][colIndex] == num)
				return true;
		}
		return false;
	}

	/**
	 * Used in box.
	 * 
	 * @param rowIndex
	 *            the row index
	 * @param colIndex
	 *            the col index
	 * @param num
	 *            the num
	 * @return true, if successful
	 */
	public boolean usedInBox(int rowIndex, int colIndex, int num) {

		int[][] grid = getGrid();
		int boxRegionRowCount = getRegionX();
		int boxRegionColumnCount = getRegionY();
		for (int x = 0; x < boxRegionColumnCount; x++) {
			for (int y = 0; y < boxRegionRowCount; y++) {
				if (grid[rowIndex + x][colIndex + y] == num)
					return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smn.puzzle.sudoku.SudokuSolver#getResult()
	 */
	@Override
	public String getResult() {
		return isSolved ? convertGridToString() : Sudoku.ERROR;

	}
}
