/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.movevalidator;

import static hanto.common.HantoPieceType.BUTTERFLY;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.hantoboard.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounter;

/**
 * This class is a move validator to make sure that the butterfly is placed 
 * in the proper amount of time
 * @author Troy
 *
 */
public class ButterflyPlacedInTimeValidator implements MoveValidator{
	Integer numberOfTurns;
	
	/**
	 * constructor 
	 * @param numTurns
	 * 					int : max number of turns before you need a butterfly
	 */
	public ButterflyPlacedInTimeValidator(int numTurns){
		numberOfTurns = new Integer(numTurns);
	}
	
	
	@Override
	public boolean isValidMove(HantoBoard theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to,
			HantoCoordinate from) {
		HantoPlayerColor moveColor = piece.getColor();
		Collection<HantoPiece> playerPieces = theBoard.getPlayerPieces(moveColor).values();
		boolean playerHasButterfly = false;
		for (HantoPiece hp : playerPieces){
			if(hp.getType() == HantoPieceType.BUTTERFLY){
				playerHasButterfly = true;
			}
		}
		
		boolean result = true;
		if(counter.getNumberMoves(piece.getColor()) == numberOfTurns){
			if(!playerHasButterfly && piece.getType() != HantoPieceType.BUTTERFLY){
				result = false;
			}
		}
		
		return result;
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */

	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Butterfly Placing Error: Must place butterfly by "+numberOfTurns.toString()+" Turns");
		
	}

}