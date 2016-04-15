/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.turnactionvalidator;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounter;

/**
 * @author Troy
 *
 */
public class PieceSpecificMoveValidator implements TurnActionValidator {

	HantoPieceType type;
	TurnActionValidator pieceMoveValidator;
	
	public PieceSpecificMoveValidator(HantoPieceType toCheck, TurnActionValidator toValidate){
		type = toCheck;
		pieceMoveValidator = toValidate;
	}
	
	/* (non-Javadoc)
	 * @see hanto.studentthhughes.common.movevalidator.MoveValidator#isValidMove(hanto.studentthhughes.common.hantoboard.HantoBoard, hanto.common.HantoPiece, hanto.studentthhughes.common.movecounter.MoveCounter, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isValidMove(HantoBoard theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to,
			HantoCoordinate from) {
		boolean result = true;
		if(piece.getType() == type){
			result = pieceMoveValidator.isValidMove(theBoard, piece, counter, to, from);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see hanto.studentthhughes.common.movevalidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		pieceMoveValidator.invalidError();

	}

}
