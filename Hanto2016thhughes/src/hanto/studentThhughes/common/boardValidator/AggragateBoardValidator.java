/**
 * 
 */
package hanto.studentThhughes.common.boardValidator;

import java.util.LinkedList;
import java.util.Queue;

import hanto.common.MoveResult;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.moveCounter.MoveCounter;

/**
 * @author Troy
 *
 */
public class AggragateBoardValidator implements BoardValidator {
	
	Queue<BoardValidator> validatorList;
	
	public AggragateBoardValidator(){
		validatorList = new LinkedList<BoardValidator>();
		
	}
	
	/**
	 * This function adds a validator to the aggrigate to be run. 
	 * 
	 * @param toAdd
	 * 				BoardValidator : is used in the aggrigate's getOutcome function
	 */
	public void addValidator(BoardValidator toAdd){
		validatorList.add(toAdd);
	}

	
	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.boardValidator.BoardValidator#getOutcome(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.moveCounter.MoveCounter)
	 */
	@Override
	public MoveResult getOutcome(Board theBoard, MoveCounter counter) {
		Queue<MoveResult> results = new LinkedList<MoveResult>();
		
		for(BoardValidator validator: validatorList){
			results.add(validator.getOutcome(theBoard, counter));
		}
		
		MoveResult result = MoveResult.OK;
		if(results.contains(MoveResult.DRAW) || 
				(results.contains(MoveResult.BLUE_WINS) && results.contains(MoveResult.RED_WINS))){
			result = MoveResult.DRAW;
		}else if(results.contains(MoveResult.BLUE_WINS)){
			result = MoveResult.BLUE_WINS;
		}else if(results.contains(MoveResult.RED_WINS)){
			result = MoveResult.RED_WINS;
		}
		return result;
	}

}
