/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.turnactionvalidator;


import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;

/**
 * This is a turn action validator that only validates moves on a specific piece. 
 * Upon instantiation a specific type is given to the class along with a validator, 
 * whenever a turn occurs with a  <em> move </em> action this action validator
 * will attempt to validate the move action.
 * @author Troy
 *
 */
public class PieceSpecificTurnActionValidator extends AbsTurnActionValidator implements TurnActionValidator {

	HantoPieceType type;
	TurnActionValidator pieceMoveValidator;
	
	/**
	 * Constructor
	 * @param toCheck
	 * 			HantoPieceType to check
	 * @param toValidate
	 * 			TurnActionValidator to evaluate when it's the peices turn
	 * 
	 */
	public PieceSpecificTurnActionValidator(HantoPieceType toCheck, TurnActionValidator toValidate){
		type = toCheck;
		pieceMoveValidator = toValidate;
	}
	
	/*
	 * (non-Javadoc)
	 * @see hanto.studentthhughes.common.turnactionvalidator.AbsTurnActionValidator#handleMoveCheck(hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard, hanto.common.HantoPiece, hanto.studentthhughes.common.movecounter.MoveCounterImpl, hanto.studentthhughes.common.coordinate.HantoCoordinateImpl, hanto.studentthhughes.common.coordinate.HantoCoordinateImpl)
	 */
	@Override
	protected void handleMoveCheck(HantoBoard theBoard, HantoPiece piece, MoveCounterImpl counter,
			HantoCoordinateImpl to, HantoCoordinateImpl from) {
		if(piece.getType() == type){
			validResult = pieceMoveValidator.isValidMove(theBoard, piece, counter, to, from);
		}
	}

	/* (non-Javadoc)
	 * @see hanto.studentthhughes.common.movevalidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		pieceMoveValidator.invalidError();

	}

}
