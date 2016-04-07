/**
 * 
 */
package hanto.studentThhughes.common.moveValidator;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.studentThhughes.common.board.Board;
import hanto.studentThhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentThhughes.common.frontier.Frontier;
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
	public boolean isValidMove(Board theBoard, Frontier theFrontier, HantoPiece piece, MoveCounter counter, HantoCoordinate to, HantoCoordinate from) {
		return theFrontier.inFrontier(new HantoCoordinateImpl(to));
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Invalid Location Exception");

	}

}
