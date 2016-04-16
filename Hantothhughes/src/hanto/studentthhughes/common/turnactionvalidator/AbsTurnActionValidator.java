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
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounter;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;

/**
 *This is an abstract TurnActionValidator. This is to allow the turn validators
 *to be templated out based off if the turn is a 'place' action or a 
 *'move' action.
 * @author Troy
 *
 */
public abstract class AbsTurnActionValidator implements TurnActionValidator {

	protected boolean validResult = true;
	protected MoveCounterImpl counterImpl;
	protected HantoPlayerColor playerColor;
	
	
	/* (non-Javadoc)
	 * @see hanto.studentthhughes.common.movevalidator.MoveValidator#isValidMove(hanto.studentthhughes.common.hantoboard.HantoBoard, hanto.common.HantoPiece, hanto.studentthhughes.common.movecounter.MoveCounter, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isValidMove(HantoBoard theBoard, 
			HantoPiece piece, MoveCounter counter, HantoCoordinate to, HantoCoordinate from) {
		playerColor = piece.getColor();
		validResult = true;
		
		if(isMoveAPlace(from)){
			handlePlaceCheck(theBoard, piece,
					new MoveCounterImpl(counter), new HantoCoordinateImpl(to));
		}else{
			handleMoveCheck(theBoard, piece,
					new MoveCounterImpl(counter),
					new HantoCoordinateImpl(to), 
					new HantoCoordinateImpl(from));
		}
		return validResult;
	}

	
	/**
	 * Template method for validator. Override if this validator handles places
	 * @param theBoard
	 * 				HantoBoard recording game state
	 * @param piece
	 * 				HantoPiece 
	 * @param counter
	 * 				MoveCounterImpl : implementation of the counter class
	 * @param to
	 * 			HantoCoordinateImpl : the starting location of the place
	 * @param from
	 * 			HantoCoordinateImpl : the from location in a move
	 */
	protected void handleMoveCheck(HantoBoard theBoard, HantoPiece piece, MoveCounterImpl counter,
			HantoCoordinateImpl to, HantoCoordinateImpl from) {
		validResult = true;
	}

	/**
	 * Template method for validator. Override if this validator handles places
	 * @param theBoard
	 * 				HantoBoard recording game state
	 * @param piece
	 * 				HantoPiece 
	 * @param counter
	 * 				MoveCounterImpl : implementation of the counter class
	 * @param to
	 * 			HantoCoordinateImpl : the starting location of the place
	 */
	protected void handlePlaceCheck(HantoBoard theBoard, HantoPiece piece, MoveCounterImpl counter,
			HantoCoordinateImpl to) {
		validResult = true;
	}

	/* (non-Javadoc)
	 * @see hanto.studentthhughes.common.movevalidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Move Validator Error");

	}
	
	/**
	 * This function will return true if the turn type is a place
	 * @param from
	 * 				HantoCoordinate of the location that the 
	 * @return
	 * 			Boolean : true if the piece is null, thus showing that this is a place move
	 */
	public boolean isMoveAPlace(HantoCoordinate from)
	{
		return from == null;
	}
	
	
	
	

}
