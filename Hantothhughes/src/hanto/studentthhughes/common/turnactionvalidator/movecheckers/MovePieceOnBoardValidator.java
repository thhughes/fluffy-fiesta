/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.turnactionvalidator.movecheckers;


import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.turnactionvalidator.AbsTurnActionValidator;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;

/**
 * This class is used to check that a piece being moved is the correct color/type/location
 * that the player wishes to move. Therefore, it protects against player A moving player B's
 * peices
 * 
 * @author Troy
 *
 */
public class MovePieceOnBoardValidator extends AbsTurnActionValidator implements TurnActionValidator {


	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("The Piece You're Trying To Move Doesn't exist");

	}
	
	protected void handleMoveCheck(HantoBoard theBoard, HantoPiece piece, MoveCounterImpl counter,
			HantoCoordinateImpl to, HantoCoordinateImpl from) {
		
		if(!theBoard.isLocationOccupied(from) || 
				theBoard.getFromBoard(from).getType() != piece.getType() || 
				theBoard.getFromBoard(from).getColor() != piece.getColor()){
			validResult = false;
		}
	}

	
}
