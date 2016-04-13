/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package hanto.studentthhughes.common.movevalidator;

import java.util.Queue;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentthhughes.common.coordinate.HantoCoordinateImpl;
import hanto.studentthhughes.common.hantoboard.HantoBoard;
import hanto.studentthhughes.common.movecounter.MoveCounter;

/**
 * This class is used to make sure that the piece is moved to a contiguous location. 
 * 
 * @author Troy
 *
 */
public class LocationValidator implements MoveValidator {

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#isValidMove(hanto.studentThhughes.common.board.Board, hanto.studentThhughes.common.frontier.Frontier, hanto.common.HantoPiece, hanto.common.HantoCoordinate)
	 */
	@Override
	public boolean isValidMove(HantoBoard theBoard, HantoPiece piece, MoveCounter counter, HantoCoordinate to, HantoCoordinate from) {
		boolean result = false;
		if(counter.getNumberMoves(HantoPlayerColor.BLUE) == 0 &&
				counter.getNumberMoves(HantoPlayerColor.RED) == 0){
			if((new HantoCoordinateImpl(0,0)).equals(new HantoCoordinateImpl(to))){
				result = true;
			}
			 
		}else{
			// Get all of the neighbors of where you're going
			Queue<HantoCoordinate> neighbors = (new HantoCoordinateImpl(to)).getNeighbors();
			
			// Iterate over all the neighbors
			for(HantoCoordinate hc : neighbors){
				
				// If the location that you've found is occupied
				if(theBoard.isLocationOccupied(new HantoCoordinateImpl(hc))){
					// If it's being placed, not moved.
					if(from == null){		
						result = true;
						break;
					// If the location you've found is occupied and NOT the location that you're coming from
					}else if (!((new HantoCoordinateImpl(hc)).equals(new HantoCoordinateImpl(from)))){
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see hanto.studentThhughes.common.moveValidator.MoveValidator#invalidError()
	 */
	@Override
	public void invalidError() throws HantoException {
		throw new HantoException("Invalid Location Exception");

	}

}
