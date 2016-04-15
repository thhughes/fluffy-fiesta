/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.turnactionvalidator.moveandplacechecks;

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
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.turnactionvalidator.AbsTurnActionValidator;
import hanto.studentthhughes.common.turnactionvalidator.TurnActionValidator;

/**
 * This is a GeneralPieceValidator that runs piece placement and movement validation 
 * that is consistent across all hanto games. 
 * 
 * @author Troy
 *
 */
public class GeneralActionValidator extends AbsTurnActionValidator implements TurnActionValidator {

	Queue<HantoPieceType> validList;
	
	
	/**
	 * Overloaded Constructor
	 * @param inputList
	 * 					Queue<HantoPieceType> to be inputted into the allowed pieces
	 */
	public GeneralActionValidator(Queue<HantoPieceType> inputList){
		validList = inputList;
	}
	
	
	@Override
	protected void handlePlaceCheck(HantoBoard theBoard, HantoPiece piece, MoveCounterImpl counter,
			HantoCoordinateImpl to) {
		validResult =  validResult && isValidPieceType(piece);
		validResult =  validResult && pieceIsOnlyTouchingFriendlyPieces(theBoard,counter,piece.getColor(),to);
		
	}
	

	@Override
	protected void handleMoveCheck(HantoBoard theBoard, HantoPiece piece, MoveCounterImpl counter,
			HantoCoordinateImpl to, HantoCoordinateImpl from) {
		
		validResult = validResult && (theBoard.isLocationOccupied(from));
		validResult = validResult && !(theBoard.isLocationOccupied(to));
		validResult = validResult && (isSameTypeAndColorOfPieceOnBoard(theBoard,piece,from));
		validResult = validResult && checkIfMoveIsToContiguousPeice(theBoard, to, from);
		
	
	}
	
	/**
	 * Checks if the piece in the 'from' location of a move is the piece that the player is trying 
	 * to move. This means ensures that the from location is occupied and that the piece in the 
	 * from location is both the same type and color as the piece to be placed.
	 * @param theBoard
	 * 				HantoBoard to check on
	 * @param piece
	 * 				HantoPiece that is going to be placed
	 * @param from
	 * 				HantoPieceImpl : the location to check
	 * @return
	 * 			Boolean : True if the piece being placed is the same type and color as the piece on the board
	 */
	private boolean isSameTypeAndColorOfPieceOnBoard(HantoBoard theBoard, HantoPiece piece, HantoCoordinateImpl from) {
		return theBoard.getFromBoard(from).getType() == piece.getType() && 
				theBoard.getFromBoard(from).getColor() == piece.getColor();
	}
	
	
	/**
	 * This function checks to ensure that the piece to be placed is only touching friendly pieces. 
	 * a friendly piece is one that has the same color as itself. 
	 * @param theBoard
	 * 				HantoBoard to check 
	 * @param counter
	 * 				HantoCounterImpl
	 * @param color
	 * 				HantoPlayerColor of the piece that is being placed
	 * @param to
	 * 			HantoCoordinateImpl representing where the piece will be placed
	 * @return
	 * 			Boolean : true if the piece is only touching friendly pieces.
	 */
	private boolean pieceIsOnlyTouchingFriendlyPieces(HantoBoard theBoard, MoveCounterImpl counter,
			HantoPlayerColor color, HantoCoordinateImpl to) {
		boolean result = true;
		
		if(counter.bothPlayersHaveGoneMoreThanOnce()){
			Queue<HantoPlayerColor> touchingPlayers = getOccupiedNeighborColors(theBoard, to);
			
			for(HantoPlayerColor hpc : touchingPlayers){
				if(hpc != color){
					result = false;
					break;
				}
			}
		}
		return result;
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

	/**
	 * Checks if a point is apart of a contiguous system board.
	 * 
	 * @param theBoard
	 * 				HantoBoard
	 * @param toCheck
	 * 				HantoBoardImpl
	 * @param oldLocation
	 * 				HantoBoardImpl
	 * @return
	 * 			Boolean : true if the toCheck coordinate is apart of the 
	 * 					  contiguous board in theBoard.
	 */
	private boolean checkIfMoveIsToContiguousPeice(HantoBoard theBoard, HantoCoordinateImpl toCheck,
			HantoCoordinateImpl oldLocation) {
		
		boolean result = false;
		Queue<HantoCoordinate> neighbors = toCheck.getNeighbors();
		
		for(HantoCoordinate hc : neighbors){
			if(theBoard.isLocationOccupied(new HantoCoordinateImpl(hc))){
				if (!areHantoCoordinatesEqual(oldLocation, hc)){
					result = true;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Function compares two hanto coordinates
	 * @param coorda 
	 * 				HantoCoordinateImpl
	 * @param coordb
	 * 				HantoCoordinateImpl
	 * @return
	 * 			boolean : true if they are equal
	 */
	private boolean areHantoCoordinatesEqual(HantoCoordinate coorda, HantoCoordinate coordb) {
		return (new HantoCoordinateImpl(coorda)).equals(new HantoCoordinateImpl(coordb));
	}
	
	
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Invalid Piece Exception");
		
	}

}
