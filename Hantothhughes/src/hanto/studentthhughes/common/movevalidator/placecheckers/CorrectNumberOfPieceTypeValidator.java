/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.movevalidator.placecheckers;

import java.util.Collection;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounter;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.movevalidator.AbsMoveValidator;
import hanto.studentthhughes.common.movevalidator.MoveValidator;

/**
 * This class is used to ensure that there is a correct number of a specific piece 
 * and that there are not more than allowed. This occurs at time of place and will
 * return true if it is a move 
 * @author Troy
 *
 */
public class CorrectNumberOfPieceTypeValidator extends AbsMoveValidator implements MoveValidator {

	HantoPieceType pieceType;
	int maxNumberOfPiece;
	
	/**
	 * Constructor
	 * 
	 * @param hpt
	 * 				HantoPieceType 
	 * @param maxNum
	 * 				int representing the number of moves that piece can take
	 */
	public CorrectNumberOfPieceTypeValidator(HantoPieceType hpt, int maxNum){
		pieceType = hpt;
		maxNumberOfPiece = maxNum;
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see hanto.studentthhughes.common.movevalidator.AbsMoveValidator#isValidMove(hanto.studentthhughes.common.hantoboard.HantoBoard, hanto.common.HantoPiece, hanto.studentthhughes.common.movecounter.MoveCounter, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	public boolean isValidMove(HantoBoard theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to,
			HantoCoordinate from) {
		if(piece.getType() == pieceType){
			validResult = super.isValidMove(theBoard, piece, counter, to, from);
		}
		return validResult;
	}
	
	@Override
	public void handlePlaceCheck(HantoBoard theBoard, HantoPiece piece, MoveCounterImpl counter,
			HantoCoordinateImpl to){
		
		int numberOfPieces = countPieceTypesOnBoard(theBoard);
		
		validResult = (numberOfPieces+1 <= maxNumberOfPiece && piece.getType() == pieceType);
	}


	
	/**
	 * Counts the number of pieces of a specific type on the board
	 * @param theBoard
	 * 					HantoBoard 
	 * @return
	 * 			int : number of 
	 */
	private int countPieceTypesOnBoard(HantoBoard theBoard) {
		Collection<HantoPiece> playerPieces = theBoard.getPlayerPieces(playerColor).values();
		int numberOfPieces = 0;
		
		for(HantoPiece hp : playerPieces){
			if(hp.getType() == pieceType){
				numberOfPieces++;
			}
		}
		return numberOfPieces;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Butterfly Check Error: Cannot add two butterflies");

	}


}
