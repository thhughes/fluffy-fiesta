/**
 * 
 */
package hanto.studentThhughes.common.boardValidator;

import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.moveCounter.MoveCounter;

/**
 * @author Troy
 *
 */
public class TerminalMoveCheck implements BoardValidator {
	int terminalNumber;
	public TerminalMoveCheck(int maxNumberMoves){
		terminalNumber = maxNumberMoves;
	}
	
	
	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.boardValidator.BoardValidator#getOutcome(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.MoveCounter)
	 */
	@Override
	public MoveResult getOutcome(Board theBoard, MoveCounter counter) {
		MoveResult result = MoveResult.OK;
		if(counter.getNumberMoves(HantoPlayerColor.RED) == terminalNumber &&
				counter.getNumberMoves(HantoPlayerColor.BLUE) == terminalNumber){
			result = MoveResult.DRAW;
		}
		return result;
	}

}
