/**
 * 
 */
package hanto.studentthhughes.common.turnactionvalidator.placecheckers;

import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.turnactionvalidator.AbsTurnActionValidator;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;

/**
 * This class is used to validate the move made by the first move of a 
 * HantoGame
 * 
 * @author Troy
 *
 */
public class FirstMoveValidator extends AbsTurnActionValidator implements TurnActionValidator {

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.frontier.Frontier, hanto.common.HantoPiece, hanto.common.HantoCoordinate, hanto.studentThhughes.common.MoveCounter)
	 */


	protected void handlePlaceCheck(HantoBoard theBoard, HantoPiece piece, MoveCounterImpl counter,
			HantoCoordinateImpl to) {
		if(counter.isFirstMoveOfGame()){
			validResult = (new HantoCoordinateImpl(0,0)).equals(to);
		}
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("First Move Error: First move must be at HantoCoordinate(0,0)");

	}

}