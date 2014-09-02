package com.smn.puzzles.webservices.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.smn.puzzle.sudoku.BackTrackAlgorithm;
import com.smn.puzzle.sudoku.SudokuSolver;
import com.smn.puzzle.sudoku.Exception.InsufficientDataException;
import com.smn.puzzle.sudoku.Exception.InvalidCharacterException;
import com.smn.puzzle.sudoku.Exception.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * The Class SudokuController. Handles the rest request and invokes the
 * corresponding algorithm.
 */
@Controller
public class SudokuController {

	/** The Constant logger. */
	private static final Logger logger = Logger
			.getLogger(SudokuController.class);

	/** The json view. */
	@Autowired
	private View jsonView;

	/** The Constant SOLUTION_FIELD. */
	private static final String SOLUTION_FIELD = "solution";

	/** The Constant ERROR_FIELD. */
	private static final String ERROR_FIELD = "error";

	/**
	 * Solve problem.
	 * 
	 * @param problem
	 *            the problem
	 * @return the model and view
	 */
	private ModelAndView solveProblem(String problem) {
		String sMessage;
		SudokuSolver solver = new BackTrackAlgorithm();
		try {
			logger.debug("problem statement: " + problem);
			solver.initializeGrid(problem);
			solver.solve();
			sMessage = solver.getResult();
			logger.debug("result: " + sMessage);

		} catch (InvalidDataException e) {
			sMessage = "Error - InvalidDataException";
			return createErrorResponse(e.getMessage());
		} catch (InvalidCharacterException e) {
			sMessage = "Error - InvalidCharacterException";
			return createErrorResponse(e.getMessage());
		} catch (InsufficientDataException e) {
			sMessage = "Error - InsufficientDataException";
			return createErrorResponse(e.getMessage());
		}

		return new ModelAndView(jsonView, SOLUTION_FIELD, sMessage);
	}

	/**
	 * Solve sudoku problem.
	 * 
	 * @param query
	 *            the query
	 * @return the model and view
	 */
	@RequestMapping(value = "/rest/sudoku", method = RequestMethod.GET)
	public ModelAndView solveSudokuProblem(
			@RequestParam(value = "query", required = false) String query) {
		logger.debug("param value: " + query);
		return solveProblem(query);
	}

	/**
	 * Checks if is empty.
	 * 
	 * @param input
	 *            the input
	 * @return true, if is empty
	 */
	public boolean isEmpty(String input) {
		return (null == input) || input.trim().length() == 0;
	}

	/**
	 * Creates the error response.
	 * 
	 * @param sMessage
	 *            the s message
	 * @return the model and view
	 */
	private ModelAndView createErrorResponse(String sMessage) {
		return new ModelAndView(jsonView, ERROR_FIELD, sMessage);
	}

}
