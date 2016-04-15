/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.movevalidator.movecheckers;

import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboardandboardtools.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounterImpl;
import hanto.studentthhughes.common.movevalidator.AbsMoveValidator;
import hanto.studentthhughes.common.movevalidator.MoveValidator;

/**
 * This class is used to make sure that the piece is moved to a contiguous location. 
 * 
 * @author Troy
 *
 */
public class LocationValidator extends AbsMoveValidator implements MoveValidator {

	HantoCoordinateImpl boardOrigin = new HantoCoordinateImpl(0,0);
	

	
	protected void handleMoveCheck(HantoBoard theBoard, HantoPiece piece, MoveCounterImpl counter,
			HantoCoordinateImpl to, HantoCoordinateImpl from) {
		
		validResult = checkIfMoveIsToContiguousPeice(theBoard, to, from) &&
				isLocationUnoccupied(theBoard, to);
	}
	

	/**
	 * Checks if a location is unoccupied on the board
	 * 
	 * @param theBoard
	 * 				HantoBoard
	 * @param toCheck
	 * 				HantoCoordinateImpl to check
	 * @return
	 * 		Boolean : true if the location is empty
	 */
	private boolean isLocationUnoccupied(HantoBoard theBoard, HantoCoordinateImpl toCheck) {
		return !theBoard.isLocationOccupied(toCheck);
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


	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Invalid Location Exception");

	}

}
