/**
 * 
 */
package hanto.studentThhughes.common.moveValidator;

import java.util.LinkedList;
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
public class TwoSpacesFreeToWalkValidator implements MoveValidator {

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.common.HantoPiece, hanto.studentThhughes.common.moveCounter.MoveCounter, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isValidMove(Board theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to,
			HantoCoordinate from) {
		boolean result = true;
		if(from !=null){
			Queue<HantoCoordinate> fromNeighbors = (new HantoCoordinateImpl(from)).getNeighbors();
			Queue<HantoCoordinate> toNeighbors = (new HantoCoordinateImpl(to)).getNeighbors();
			Queue<HantoCoordinateImpl> overlap = new LinkedList<HantoCoordinateImpl>();
			
			for(HantoCoordinate toN : toNeighbors){
				HantoCoordinateImpl toImpl = new HantoCoordinateImpl(toN);
				
				for(HantoCoordinate frN : fromNeighbors){
					HantoCoordinateImpl frImpl = new HantoCoordinateImpl(frN);
					boolean toAdd = toImpl.equals(frImpl) && !overlap.contains(frImpl);
					if(toAdd){
						overlap.add(frImpl);
					}
				}
			}
			// Iterate over the overlap and find if they have pieces in them
			for(HantoCoordinate oLap : overlap){
				if(!theBoard.isLocationOccupied(oLap)){
					result = true;
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
		throw new HantoException("Movement Exception: Movement should have two free spaces to execute");

	}

}
