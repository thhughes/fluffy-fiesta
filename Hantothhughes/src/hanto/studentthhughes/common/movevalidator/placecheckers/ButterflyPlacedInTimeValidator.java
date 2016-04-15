/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.movevalidator.placecheckers;



import java.util.Collection;


import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboard.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.movevalidator.AbsMoveValidator;
import hanto.studentthhughes.common.movevalidator.MoveValidator;

/**
 * This class is a validator that will check to see if there are enough butterflies for 
 * a given player by a given turn. This check should only run when there is a place occuring, and will
 * return true when there is a move. 
 *  
 * @author Troy
 *
 */
public class ButterflyPlacedInTimeValidator extends AbsMoveValidator implements MoveValidator{
	Integer numberOfTurns;
	
	/**
	 * constructor 
	 * @param numTurns
	 * 					int : max number of turns before you need a butterfly
	 */
	public ButterflyPlacedInTimeValidator(int numTurns){
		numberOfTurns = new Integer(numTurns-1);
	}
	
	
	@Override
	protected void handlePlaceCheck(HantoBoard theBoard, HantoPiece piece, MoveCounterImpl counter,
			HantoCoordinateImpl to) {
		boolean playerHasButterfly = hasPlayerPlacedButterfly(theBoard);
		
		if(counter.getNumberMoves(playerColor) == numberOfTurns){
			if(!playerHasButterfly && piece.getType() != HantoPieceType.BUTTERFLY){
				validResult = false;
			}
		}
		
	}
	

	/**
	 * Function to check if a player has placed a butterfly
	 * 
	 * @param theBoard
	 * @return
	 */
	private boolean hasPlayerPlacedButterfly(HantoBoard theBoard) {
		
		Collection<HantoPiece> playerPieces = theBoard.getPlayerPieces(playerColor).values();
		boolean playerHasButterfly = false;
		
		for (HantoPiece hp : playerPieces){
			if(hp.getType() == HantoPieceType.BUTTERFLY){
				playerHasButterfly = true;
			}
		}
		return playerHasButterfly;
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
