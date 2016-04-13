/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentThhughes.common.movevalidator;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentThhughes.common.hantoboard.HantoBoard;
import hanto.studentThhughes.common.movecounter.MoveCounter;

/**
 * @author Troy
 *
 */
public class PlayingTooManyPieceValidator implements MoveValidator {

	int numberOfPieces;
	/**
	 * Insert the maximum number of pieces that a player can play.
	 * @param numPieces
	 */
	public PlayingTooManyPieceValidator(int numPieces){
		numberOfPieces = numPieces;
	}
	
	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.common.HantoPiece, hanto.studentThhughes.common.moveCounter.MoveCounter, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isValidMove(HantoBoard theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to,
			HantoCoordinate from) {
		boolean result = true;
		if(from == null){
			HantoPlayerColor turnColor = piece.getColor();
			Collection<HantoPiece> playerPieces = theBoard.getPlayerPieces(turnColor).values();
			result = !(playerPieces.size() >= numberOfPieces);
		}
		
		
		return result;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("You have played too many pieces, you may now only move pieces");

	}

}
