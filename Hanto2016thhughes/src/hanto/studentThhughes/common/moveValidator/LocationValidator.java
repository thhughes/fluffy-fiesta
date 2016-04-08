/**
 * 
 */
package hanto.studentThhughes.common.moveValidator;

import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.moveCounter.MoveCounter;

/**
 * @author Troy
 *
 */
public class LocationValidator implements MoveValidator {

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.frontier.Frontier, hanto.common.HantoPiece, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isValidMove(Board theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to, HantoCoordinate from) {
		boolean result = false;
		if(counter.getNumberMoves(HantoPlayerColor.BLUE) == 0 &&
				counter.getNumberMoves(HantoPlayerColor.RED) == 0){
			if((new HantoCoordinateImpl(0,0)).equals(new HantoCoordinateImpl(to))){
				result = true;
			}
			
		}else{
			Queue<HantoCoordinate> neighbors = (new HantoCoordinateImpl(to)).getNeighbors();
			for(HantoCoordinate hc : neighbors){
				if(theBoard.isLocationOccupied(new HantoCoordinateImpl(hc))){
					if(from == null){
						result = true;
						break;
					}else if (!(new HantoCoordinateImpl(hc)).equals(new HantoCoordinateImpl(from))){
						result = true;
						break;
					}
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
		throw new HantoException("Invalid Location Exception");

	}

}
