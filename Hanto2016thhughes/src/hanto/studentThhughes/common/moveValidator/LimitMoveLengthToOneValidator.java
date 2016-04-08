/**
 * 
 */
package hanto.studentThhughes.common.moveValidator;

import java.util.Queue;

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
public class LimitMoveLengthToOneValidator implements MoveValidator {

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.common.HantoPiece, hanto.studentThhughes.common.moveCounter.MoveCounter, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isValidMove(Board theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to,
			HantoCoordinate from) {
		boolean result = true;
		if(from != null){
			Queue<HantoCoordinate> neighbors = (new HantoCoordinateImpl(to)).getNeighbors();
			for(HantoCoordinate hc : neighbors){
				if((new HantoCoordinateImpl(hc)).equals(new HantoCoordinateImpl(from))){
					result = true;
					System.out.println("I made it");
					break;
				}
				result = false;
			}
			
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Distance Moved Error: Can only move one spot");

	}

}
