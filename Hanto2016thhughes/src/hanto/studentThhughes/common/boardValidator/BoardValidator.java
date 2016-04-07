package hanto.studentThhughes.common.boardValidator;

import hanto.common.MoveResult;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.moveCounter.MoveCounter;

public interface BoardValidator {

	/**
	 * This is a validator that checks the result of the board based off the input board and the number
	 * of moves that have been taken. This returns 
	 * @param theBoard
	 * 				Board Implementation that gets checked for valid states
	 * @param counter
	 * 				Move Counter object to access the move data 
	 * @return MoveResult
	 * 					This returns the result of the board based off of the outcome. 
	 */
	public MoveResult getOutcome(Board theBoard, MoveCounter counter);
}
