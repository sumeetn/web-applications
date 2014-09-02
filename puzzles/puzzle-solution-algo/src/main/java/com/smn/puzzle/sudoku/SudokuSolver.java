package com.smn.puzzle.sudoku;

import org.apache.log4j.Logger;

import com.smn.puzzle.sudoku.Exception.InsufficientDataException;
import com.smn.puzzle.sudoku.Exception.InvalidCharacterException;
import com.smn.puzzle.sudoku.Exception.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * The Class SudokuSolver. Abstract class, creates platform for sudoku solving
 * algorithm.
 */
public abstract class SudokuSolver {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(SudokuSolver.class);

	/** The region x, represents row count for sub-grid. */
	private int regionX = 0;

	/** The region y. represents column count for sub-grid.*/
	private int regionY = 0;

	/** The grid size. */
	private int gridSize = 0;

	/** The grid. */
	private int[][] grid;

	/**
	 * Solve.
	 * 
	 * @return true, if successful
	 */
	public abstract boolean solve();

	/**
	 * Gets the result.
	 * 
	 * @return the result
	 */
	public abstract String getResult();

	/**
	 * Sets the grid metrics.
	 * 
	 * @param gridSize
	 *            the grid size
	 * @param rows
	 *            the rows
	 * @param columns
	 *            the columns
	 */
	public void setGridMetrics(int gridSize, int rows, int columns) {
		grid = new int[gridSize][gridSize];
		this.gridSize = gridSize;
		this.regionX = rows;
		this.regionY = columns;

	}

	/**
	 * Initialize grid. Takes a sudoku problem statement and calculates the
	 * magnitude/order of problem and initializes accordingly.
	 * 
	 * @param problem
	 *            the problem
	 * @throws InvalidDataException
	 *             the invalid data exception
	 * @throws InvalidCharacterException
	 *             the invalid character exception
	 * @throws InsufficientDataException
	 *             the insufficient data exception.
	 */
	public void initializeGrid(String problem) throws InvalidDataException,
			InvalidCharacterException, InsufficientDataException {
		logger.debug("initialize grid with " + problem);
		String[] elements = problem.split(",");
		int elementCount = elements.length;
		logger.debug("element count : " + elementCount);
		if (elementCount < 4)
			throw new InsufficientDataException(
					"Insufficient data: Need atleast 16 elements for sudoku problem, (standard size is 9x9 i.e 81 elements) found "
							+ elementCount);
		double order = Math.sqrt(elementCount);
		if (order % 1 == 0) {
			gridSize = (int) order;
			logger.debug("grid size found to be  " + gridSize);

			int rows = (int) Math.sqrt(gridSize);
			int columns = (int) Math.sqrt(gridSize);
			logger.info("Initialize grid of size  " + rows + "X" + columns);
			initializeGrid(elements, gridSize, rows, columns);
		} else {
			throw new InvalidDataException("invalid data size " + elementCount
					+ ", total number count should be perfect square, eg. 81");
		}

	}

	/**
	 * Initialize grid. Converts comma separated number string to rows x column
	 * matrix and populates the number at respective position.
	 * 
	 * @param elements
	 *            the elements
	 * @param gridSize
	 *            the grid size
	 * @param rows
	 *            the rows
	 * @param columns
	 *            the columns
	 * @throws InvalidCharacterException
	 *             the invalid character exception
	 */
	private void initializeGrid(String[] elements, int gridSize, int rows,
			int columns) throws InvalidCharacterException {
		setGridMetrics(gridSize, rows, columns);
		populateGrid(elements);
	}

	/**
	 * Initialize grid. Creates custom grid, The regular size is 9x9 puzzle.
	 * Commonly used Sudoku sizes include 2x3 regions rather than the normal 3x3
	 * so there are six numbers to place in the squares rather than nine and 4x4
	 * where there are sixteen numbers.
	 * 
	 * @param problem
	 *            the problem
	 * @param gridSize
	 *            the grid size
	 * @param rows
	 *            the rows
	 * @param columns
	 *            the columns
	 * @throws InvalidCharacterException
	 *             the invalid character exception
	 */
	public void initializeGrid(String problem, int gridSize, int rows,
			int columns) throws InvalidCharacterException {
		String[] elements = problem.split(",");
		initializeGrid(elements, gridSize, rows, columns);

	}

	/**
	 * Populate grid.
	 * 
	 * @param elements
	 *            the elements
	 * @throws InvalidCharacterException
	 *             the invalid character exception
	 */
	private void populateGrid(String[] elements)
			throws InvalidCharacterException {
		int elementIndex = 0;
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				try {
					grid[i][j] = Integer.parseInt(elements[elementIndex]);

				} catch (NumberFormatException e) {
					if (elements[elementIndex].equalsIgnoreCase(Sudoku.EMPTY))
						grid[i][j] = 0;
					else
						throw new InvalidCharacterException(
								"Invalid caharcter '"
										+ elements[elementIndex]
										+ " ', found only number and x for empty permitted");
				}
				elementIndex++;

			}

		}

	}

	/**
	 * Convert grid to string.
	 * returns grid in comma separated number string.
	 * @return the string
	 */
	public String convertGridToString() {
		StringBuilder gridString = new StringBuilder();
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				gridString.append(grid[i][j] + ",");

			}
		}
		return gridString.toString().substring(0,
				gridString.toString().length() - 1);

	}

	/**
	 * Prints the grid.
	 */
	public void printGrid() {

		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println("");
		}
	}

	/**
	 * Gets the region x.
	 * 
	 * @return the region x
	 */
	public int getRegionX() {
		return regionX;
	}

	/**
	 * Sets the region x.
	 * 
	 * @param regionX
	 *            the new region x
	 */
	public void setRegionX(int regionX) {
		this.regionX = regionX;
	}

	/**
	 * Gets the region y.
	 * 
	 * @return the region y
	 */
	public int getRegionY() {
		return regionY;
	}

	/**
	 * Sets the region y.
	 * 
	 * @param regionY
	 *            the new region y
	 */
	public void setRegionY(int regionY) {
		this.regionY = regionY;
	}

	/**
	 * Gets the grid size.
	 * 
	 * @return the grid size
	 */
	public int getGridSize() {
		return gridSize;
	}

	/**
	 * Sets the grid size.
	 * 
	 * @param gridSize
	 *            the new grid size
	 */
	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}

	/**
	 * Gets the grid.
	 * 
	 * @return the grid
	 */
	public int[][] getGrid() {
		return grid;
	}

	/**
	 * Sets the grid.
	 * 
	 * @param grid
	 *            the new grid
	 */
	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

}
