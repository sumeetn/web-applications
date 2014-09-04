package com.smn.puzzle.sudoku;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.smn.puzzle.sudoku.Exception.InsufficientDataException;
import com.smn.puzzle.sudoku.Exception.InvalidCharacterException;
import com.smn.puzzle.sudoku.Exception.InvalidDataException;

public class SudokuTest {

	private static final Logger logger = Logger.getLogger(SudokuTest.class);

	@Test
	public void test() {
		String input = "3,x,6,5,x,8,4,x,x,5,2,x,x,x,x,x,x,x,x,8,7,x,x,x,x,3,1,x,x,3,x,1,x,x,8,x,9,x,x,8,6,3,x,x,5,x,5,x,x,9,x,6,x,x,1,3,x,x,x,x,2,5,x,x,x,x,x,x,x,x,7,4,x,x,5,2,x,6,3,x,x";
		SudokuSolver solver = new BackTrackAlgorithm();
		try {
			solver.initializeGrid(input);
			if (solver.solve()) {
				assertTrue(true);
			} else {
				fail("cannot be completed");
			}

		} catch (InvalidDataException e) {
			fail("InvalidDataException " + e.getMessage());
		} catch (InvalidCharacterException e) {
			fail("InvalidCharacterException " + e.getMessage());
		} catch (InsufficientDataException e) {
			fail("InsufficientDataException " + e.getMessage());
		}

	}

	@Test
	public void testIncompleteData() {
		String problem = "x,x,x,2,6,x,7,x,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x";
		logger.debug("elements size " + problem.split(",").length);
		SudokuSolver solver = new BackTrackAlgorithm();
		try {
			solver.initializeGrid(problem);
			solver.printGrid();
			if (solver.solve()) {
				assertTrue(false);
			} else {
				fail("cannot be completed");
			}
		} catch (InvalidDataException e) {
			assertTrue(true);
		} catch (InvalidCharacterException e) {
			fail("InvalidCharacterException " + e.getMessage());
		} catch (InsufficientDataException e) {
			fail("InsufficientDataException " + e.getMessage());
		}
	}

	@Test
	public void testCannotBeSolved() {
		String problem = "x,x,9,x,2,8,7,x,x,8,x,6,x,x,4,x,x,5,x,x,3,x,x,x,x,x,4,6,x,x,x,x,x,x,x,x,x,2,x,7,1,3,4,5,x,x,x,x,x,x,x,x,x,2,3,x,x,x,x,x,5,x,x,9,x,x,4,x,x,8,x,7,x,x,1,2,5,x,3,x,x";
		logger.debug("elements size " + problem.split(",").length);
		SudokuSolver solver = new BackTrackAlgorithm();
		try {
			solver.initializeGrid(problem);
			if (solver.solve()) {
				assertTrue(false);
			} else {
				assertTrue(true);
			}
		} catch (InvalidDataException e) {
			fail("InvalidDataException " + e.getMessage());
		} catch (InvalidCharacterException e) {
			fail("InvalidCharacterException " + e.getMessage());
		} catch (InsufficientDataException e) {
			fail("InsufficientDataException " + e.getMessage());
		}
	}

}
