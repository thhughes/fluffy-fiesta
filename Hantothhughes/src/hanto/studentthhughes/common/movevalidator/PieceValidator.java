/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.movevalidator;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboard.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounter;

/**
 * @author Troy
 *
 */
public class PieceValidator implements MoveValidator {

	Queue<HantoPieceType> validList;
	
	/**
	 * Empty constructor, not recommended use. No pieces are valid with use of this.
	 */
	public PieceValidator(){
		validList = new LinkedList<HantoPieceType>();
	}
	
	/**
	 * Overloaded COnstructor
	 * @param inputList
	 * 					Queue<HantoPieceType> to be inputted into the allowed pieces
	 */
	public PieceValidator(Queue<HantoPieceType> inputList){
		validList = inputList;
	}
	
	
	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.frontier.Frontier, hanto.common.HantoPiece, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isValidMove(HantoBoard theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to, HantoCoordinate from) {
		boolean result = true;
		
		if(!isValidPieceType(piece)){
			result = false;
		}else if(turnIsPieceMove(from)){
			if(pieceTypeNotOnBoard(theBoard, piece)){
				result = false;
			}
			if(fromPieceIsValidPiece(theBoard, piece, new HantoCoordinateImpl(from))){
				result = false;
			}else if(pieceToMoveIsOpponentsColor(theBoard, piece, new HantoCoordinateImpl(from))){
				result = false;
			}
			
		}
		return result;
	}

	private boolean fromPieceIsValidPiece(HantoBoard theBoard, HantoPiece piece,
			HantoCoordinateImpl from) {
		return !theBoard.isLocationOccupied(from);
	}

	private boolean pieceToMoveIsOpponentsColor(HantoBoard theBoard, HantoPiece piece, HantoCoordinateImpl from) {
		return theBoard.getFromBoard(from).getColor() != piece.getColor();
	}

	private boolean pieceTypeNotOnBoard(HantoBoard theBoard, HantoPiece piece) {
		Map<HantoCoordinate,HantoPiece> playerPieces = theBoard.getPlayerPieces(piece.getColor());
		Collection<HantoPiece> playerPieceTypes = playerPieces.values();
		
		return playerPieceTypes.contains(piece.getType());
	}

	private boolean turnIsPieceMove(HantoCoordinate from) {
		return from != null;
	}

	private boolean isValidPieceType(HantoPiece piece) {
		return validList.contains(piece.getType());
	}

	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Invalid Piece Exception");
		
	}

}
