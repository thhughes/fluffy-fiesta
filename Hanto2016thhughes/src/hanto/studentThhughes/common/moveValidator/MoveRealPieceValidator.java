/**
 * 
 */
package hanto.studentThhughes.common.moveValidator;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.moveCounter.MoveCounter;

/**
 * @author Troy
 *
 */
public class MoveRealPieceValidator implements MoveValidator {

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.common.HantoPiece, hanto.studentThhughes.common.moveCounter.MoveCounter, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isValidMove(Board theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to,
			HantoCoordinate from) {
		boolean result = true;
		
		if(from != null){
			result = false;
			if(theBoard.isLocationOccupied(new HantoCoordinateImpl(from))){
				result = true;
				if(theBoard.getFromBoard(new HantoCoordinateImpl(from)).getType() != piece.getType()){
					result = false;
				}
			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("The Piece You're Trying To Move Doesn't exist");

	}

}