/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.movevalidator.dualchecks;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboard.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.movevalidator.AbsMoveValidator;
import hanto.studentthhughes.common.movevalidator.MoveValidator;

/**
 * This is a GeneralPieceValidator that runs piece placement and movement validation 
 * that is consistent across all hanto games. 
 * 
 * @author Troy
 *
 */
public class GeneralPieceValidator extends AbsMoveValidator implements MoveValidator {

	Queue<HantoPieceType> validList;
	
	/**
	 * Empty constructor, not recommended use. No pieces are valid with use of this.
	 */
	public GeneralPieceValidator(){
		validList = new LinkedList<HantoPieceType>();
	}
	
	/**
	 * Overloaded Constructor
	 * @param inputList
	 * 					Queue<HantoPieceType> to be inputted into the allowed pieces
	 */
	public GeneralPieceValidator(Queue<HantoPieceType> inputList){
		validList = inputList;
	}
	
	
	@Override
	protected void handlePlaceCheck(HantoBoard theBoard, HantoPiece piece, MoveCounterImpl counter,
			HantoCoordinateImpl to) {
		validResult = isValidPieceType(piece);
		
		if(counter.bothPlayersHaveGoneMoreThanOnce()){
			Queue<HantoPlayerColor> touchingPlayers = getOccupiedNeighborColors(theBoard, to);
			
			for(HantoPlayerColor hpc : touchingPlayers){
				if(hpc != piece.getColor()){
					validResult = false;
					break;
				}
			}
		}
	}
	
	@Override
	protected void handleMoveCheck(HantoBoard theBoard, HantoPiece piece, MoveCounterImpl counter,
			HantoCoordinateImpl to, HantoCoordinateImpl from) {
		
		if(pieceTypeNotOnBoard(theBoard, piece) ||
				!theBoard.isLocationOccupied(from) ||
				pieceToMoveIsOpponentsColor(theBoard, piece, new HantoCoordinateImpl(from))){
			validResult = false;
		}

	}
	
	/**
	 * Get's a Queue of colors that are of the hanto pieces that are surrounding 
	 * the toCheck piece. 
	 * 
	 * @param theBoard
	 * 				HantoBoard
	 * @param toCheck
	 * 				HantoCoordinateImpl
	 * @return
	 * 		Queue<HantoPlayerColor> of the neighbors of the toCheck piece
	 */
	private Queue<HantoPlayerColor> getOccupiedNeighborColors(HantoBoard theBoard, HantoCoordinateImpl toCheck) {
		Queue<HantoPlayerColor> touchingPlayers = new LinkedList<HantoPlayerColor>();
		
		for(HantoCoordinate hc : toCheck.getNeighbors()){
			if(theBoard.isLocationOccupied(hc)){
				touchingPlayers.add(theBoard.getFromBoard(hc).getColor());
			}
		}
		return touchingPlayers;
	}
	
	
	/**
	 * Checks if the piece that wants to be moved is the opponents piece
	 * 
	 * @param theBoard
	 * 				HantoBoard
	 * @param piece
	 * 				HantoPiece
	 * @param toCheck
	 * 				HantoCoordinateImpl
	 * @return
	 * 			Boolean : true if the piece is the opponents piece.
	 */
	private boolean pieceToMoveIsOpponentsColor(HantoBoard theBoard, HantoPiece piece, HantoCoordinateImpl toCheck) {
		return theBoard.getFromBoard(toCheck).getColor() != piece.getColor();
	}

	/**
	 * Checks if the type of piece that wants to be moved is not
	 * on the board
	 * @param theBoard
	 * 				HantoBoard
	 * @param piece
	 * 				HantoPiece
	 * @return
	 * 			Boolean : true if the piece is on the board
	 */
	private boolean pieceTypeNotOnBoard(HantoBoard theBoard, HantoPiece piece) {
		
		Map<HantoCoordinate,HantoPiece> playerPieces = theBoard.getPlayerPieces(piece.getColor());
		Collection<HantoPiece> playerPieceTypes = playerPieces.values();
		
		return playerPieceTypes.contains(piece.getType());
	}

	/**
	 * Checks is the piece type that's going to be placed is a valid piece.
	 * 
	 * @param piece
	 * 				HantoPiece
	 * @return
	 * 			Boolean : true if the piece type is valid
	 */
	private boolean isValidPieceType(HantoPiece piece) {
		return validList.contains(piece.getType());
	}

	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Invalid Piece Exception");
		
	}

}
