/**
 * 
 */
package hanto.studentthhughes.common.movevalidator;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboard.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounter;

/**
 * @author Troy
 *
 */
public class FirstMoveValidator implements MoveValidator {

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.frontier.Frontier, hanto.common.HantoPiece, hanto.common.HantoCoordinate, hanto.studentThhughes.common.MoveCounter)
	 */
	@Override
	public boolean isValidMove(HantoBoard theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to,
			HantoCoordinate from) {
		boolean result = true;
		if(counter.getNumberMoves(HantoPlayerColor.BLUE)==0 && 
				counter.getNumberMoves(HantoPlayerColor.RED)==0){
			
			result = (new HantoCoordinateImpl(0,0)).equals(new HantoCoordinateImpl(to));
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("First Move Error: First move must be at HantoCoordinate(0,0)");

	}

}